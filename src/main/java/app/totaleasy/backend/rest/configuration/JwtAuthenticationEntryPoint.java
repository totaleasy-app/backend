package app.totaleasy.backend.rest.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import app.totaleasy.backend.rest.dto.response.ApiErrorResponse;
import app.totaleasy.backend.rest.exception.ApiError;
import app.totaleasy.backend.rest.util.DataTypeConverter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(DataTypeConverter.toJSON(
            new ApiErrorResponse(
                new ApiError("Você não está autenticado ou não está autorizado a acessar o recurso solicitado."),
                "Acesso negado.",
                HttpStatus.FORBIDDEN
            )
        ));
    }
}
