package app.totaleasy.backend.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

import java.util.Objects;

@Entity(name = "PapelPermissao")
@Table(
    name = "papel_permissao",
    schema = "public",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_papel", "id_permissao"})}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class PapelPermissao implements Serializable {

    @Id
    @Column(name = "id_papel_permissao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_papel", nullable = false)
    private Papel papel;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_permissao", nullable = false)
    private Permissao permissao;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.papel.getNome(),
            this.permissao.getNome()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        PapelPermissao papelPermissao = (PapelPermissao) object;

        return
            Objects.equals(this.papel.getNome(), papelPermissao.papel.getNome()) &&
            Objects.equals(this.permissao.getNome(), papelPermissao.permissao.getNome());
    }
}
