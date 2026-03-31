package senac.tsi.minha_saude.dominios.medicos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Embeddable
public class MedicoEspecialidadeId {
    @NotNull(message = "O Id de medico é obrigatorio")
    @Column(name = "medico_id",nullable = false)
    private Long medicoId;

    @NotNull(message = "O Id de especialidade é obrigatorio")
    @Column(name = "especialidade_id",nullable = false)
    private Long especialidadeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicoEspecialidadeId)) return false;
        MedicoEspecialidadeId that = (MedicoEspecialidadeId) o;
        return Objects.equals(medicoId, that.medicoId) &&
                Objects.equals(especialidadeId, that.especialidadeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicoId, especialidadeId);
    }


}
