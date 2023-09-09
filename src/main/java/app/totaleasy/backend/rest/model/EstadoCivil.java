package app.totaleasy.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "EstadoCivil")
@Table(name = "estado_civil", schema = "public")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class EstadoCivil implements Serializable {

    @Id
    @Column(name = "id_estado_civil", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_tse_estado_civil", unique = true, nullable = false)
    private Integer codigoTSE;

    @Column(name = "nome", unique = true, length = 31)
    private String nome;

    @OneToMany(mappedBy = "estadoCivil", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Candidato> candidatos;

    public EstadoCivil() {
        this.candidatos = new LinkedHashSet<>();
    }

    public EstadoCivil(Integer codigoTSE, String nome) {
        this.codigoTSE = codigoTSE;
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        EstadoCivil estadoCivil = (EstadoCivil) object;

        return Objects.equals(this.codigoTSE, estadoCivil.codigoTSE);
    }
}
