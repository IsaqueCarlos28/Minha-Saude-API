package senac.tsi.minha_saude.dominios.especialidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import senac.tsi.minha_saude.dominios.medicos.MedicoEspecialidadeId;

import java.util.Objects;

@Entity
@Table(name = "especialidade")
public class Especialidade {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome da Especialidade não pode ser em branco")
    @Column(name = "nome",nullable = false,length = 150)
    private String nome;

    public Especialidade (){}

    public Especialidade (String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Especialidade)) return false;
        Especialidade that = (Especialidade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString(){
        return """
        Especialidade{
            Id: %s
            Nome: %s
        }
        """.formatted(id,nome);
    }
}
