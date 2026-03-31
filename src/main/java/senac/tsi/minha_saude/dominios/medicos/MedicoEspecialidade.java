package senac.tsi.minha_saude.dominios.medicos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import senac.tsi.minha_saude.dominios.especialidade.Especialidade;

import java.util.Objects;

@Entity
@Table(name = "medico_especialidade")
public class MedicoEspecialidade {

        @EmbeddedId
        private MedicoEspecialidadeId composedId;

        @ManyToOne
        @MapsId("medicoId")
        private Medico medico;

        @ManyToOne
        @MapsId("especialidadeId")
        private Especialidade especialidade;

        @NotBlank(message = "O RQE é obrigatorio")
        @Column(name = "rqe",nullable = false,length = 5)
        private String rqe;

        public MedicoEspecialidade () {}

    public MedicoEspecialidade (Medico medico,Especialidade especialidade,String rqe) {
        setMedico(medico);
        setEspecialidade(especialidade);
        setRqe(rqe);
    }

    public MedicoEspecialidadeId getComposedId() {
        return composedId;
    }
    public void setComposedId(MedicoEspecialidadeId composedId) {
        this.composedId = composedId;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getRqe() {
        return rqe;
    }
    public void setRqe(String rqe) {
        this.rqe = rqe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicoEspecialidade)) return false;
        MedicoEspecialidade that = (MedicoEspecialidade) o;
        return Objects.equals(composedId, that.composedId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String toString(){
        return """
            Especialidade{
                medicoId: %s
                especialidade: %s
                RQE: %s
            }
        """.formatted(
                medico != null ? medico.getId() : null,
                especialidade != null ? especialidade.getNome() : null,
                rqe
        );
    }
}
