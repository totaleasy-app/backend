package app.totaleasy.backend.rest.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import app.totaleasy.backend.rest.dto.response.ApiErrorResponse;
import app.totaleasy.backend.rest.exception.ApiError;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.service.UsuarioService;
import app.totaleasy.backend.rest.util.DataTypeConverter;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws
        ServletException,
        IOException
    {
        try {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authorizationHeader.substring(7);
            String username = this.jwtService.extractUsername(jwt);

            if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) return;

            Usuario usuario = this.usuarioService.findByUsername(username);

            if (!this.jwtService.isJwtValid(jwt, usuario)) return;

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                usuario.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException expiredJwtException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(DataTypeConverter.toJSON(
                new ApiErrorResponse(
                    new ApiError("O seu token de acesso é inválido. Autentique-se novamente."),
                    "Falha de autenticação.",
                    HttpStatus.UNAUTHORIZED
                )
            ));
        }
    }
}
