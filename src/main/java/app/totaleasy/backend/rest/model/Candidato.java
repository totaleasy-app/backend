package app.totaleasy.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Candidato")
@Table(name = "candidato", schema = "public")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Candidato implements Serializable {

    @Id
    @Column(name = "id_candidato", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_tse_candidato", nullable = false)
    private Integer numeroTSE;

    @Column(name = "cod_tse_candidato", unique = true, length = 15, nullable = false)
    private String codigoTSE;

    @Column(name = "nome_completo", length = 63)
    private String nomeCompleto;

    @Column(name = "nome_urna", length = 63)
    private String nomeUrna;

    @Column(name = "nome_social", length = 63)
    private String nomeSocial;

    @Column(name = "num_cpf", length = 11, columnDefinition = "bpchar")
    private String numeroCPF;

    @Column(name = "num_titulo_eleitoral", length = 12, columnDefinition = "bpchar")
    private String numeroTituloEleitoral;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_cor_raca")
    private CorRaca corRaca;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_grau_instrucao")
    private GrauInstrucao grauInstrucao;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_estado_civil")
    private EstadoCivil estadoCivil;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_ocupacao")
    private Ocupacao ocupacao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Candidatura> candidaturas;

    public Candidato() {
        this.candidaturas = new LinkedHashSet<>();
    }

    public Candidato(Integer numeroTSE, String codigoTSE, String nomeCompleto) {
        this();
        this.numeroTSE = numeroTSE;
        this.codigoTSE = codigoTSE;
        this.nomeCompleto = nomeCompleto;
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

        Candidato candidato = (Candidato) object;

        return Objects.equals(this.codigoTSE, candidato.codigoTSE);
    }
}
