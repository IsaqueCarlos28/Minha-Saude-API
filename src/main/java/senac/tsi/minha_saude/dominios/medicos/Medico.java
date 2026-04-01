package senac.tsi.minha_saude.dominios.medicos;

import jakarta.persistence.*;
import senac.tsi.minha_saude.dominios.especialidade.Especialidade;
import senac.tsi.minha_saude.dominios.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario",unique = true,nullable = false)
    private Usuario usuario;

    @Embedded
    private CRM crm;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoEspecialidade> especialidades = new ArrayList<>();



    public Medico() {}

    public Medico (Usuario usuario, CRM crm){
        setUsuario(usuario);
        setCrm(crm);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CRM getCrm() {
        return crm;
    }
    public void setCrm(CRM crm) {
        this.crm = crm;
    }

    public List<MedicoEspecialidade> getEspecialidades() {
        return especialidades;
    }

    //METODOS

    public void addEspecialidade(Especialidade especialidade, String rqe) {

        boolean jaExiste = especialidades.stream().anyMatch(
                me -> me.getEspecialidade().getId().equals(especialidade.getId())
        );

        if (jaExiste) {
            throw new IllegalArgumentException("Este médico já possui essa especialidade");
        }

        MedicoEspecialidade me = new MedicoEspecialidade(this, especialidade, rqe);
        especialidades.add(me);
    }

    private String onlyDigitsFormat(String value){
        return (value != null) ? value.replaceAll("\\D", ""):null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;
        Medico that = (Medico) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return """
           Medico{
               ID: %s
               Id Usuario: %s
               Crm: %s
           }
           """.formatted(id,(usuario != null ? usuario.getId() : null), crm);
    }
}
