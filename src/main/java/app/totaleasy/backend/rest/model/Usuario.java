package app.totaleasy.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "usuario", schema = "public")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Usuario implements UserDetails {

    @Id
    @Column(name = "id_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 31)
    private String username;

    @Column(name = "senha", nullable = false, length = 72, columnDefinition = "bpchar")
    private String senha;

    @Column(name = "nome", length = 127)
    private String nome;

    @Column(name = "sobrenome", length = 127)
    private String sobrenome;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JsonIgnore
    @ToString.Exclude
    private Set<PapelUsuario> papeis;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<BoletimUrnaUsuario> boletinsUrna;

    @Transient
    private Boolean bloqueado;

    @Transient
    private Boolean habilitado;

    public Usuario() {
        this.papeis = new LinkedHashSet<>();
        this.boletinsUrna = new LinkedHashSet<>();
    }

    public Usuario(String username, String senha, String nome, String sobrenome) {
        this();
        this.username = username;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Set<String> getNomesPapeis() {
        Set<String> nomesPapeis = new LinkedHashSet<>();

        for (PapelUsuario papelUsuario : this.papeis) {
            nomesPapeis.add(papelUsuario.getPapel().getNome());
        }

        return nomesPapeis;
    }

    public void update(Usuario usuario) {
        if (usuario == null) return;

        this.senha = usuario.senha;
        this.nome = usuario.nome;
        this.sobrenome = usuario.sobrenome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Usuario usuario = (Usuario) object;

        return Objects.equals(this.username, usuario.username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new LinkedHashSet<>();

        for (PapelUsuario papelUsuario : this.papeis) {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(papelUsuario.getPapel().getNome())));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
