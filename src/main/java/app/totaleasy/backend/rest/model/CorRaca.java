package app.totaleasy.backend.rest.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "CorRaca")
@Table(name = "cor_raca", schema = "public")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class CorRaca implements Serializable {

    @Id
    @Column(name = "id_cor_raca", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_tse_cor_raca", unique = true, nullable = false)
    private Integer codigoTSE;

    @Column(name = "nome", unique = true, length = 31)
    private String nome;

    @OneToMany(mappedBy = "corRaca", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Candidato> candidatos;

    public CorRaca() {
        this.candidatos = new LinkedHashSet<>();
    }

    public CorRaca(Integer codigoTSE, String nome) {
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

        CorRaca corRaca = (CorRaca) object;

        return Objects.equals(this.codigoTSE, corRaca.codigoTSE);
    }
}
