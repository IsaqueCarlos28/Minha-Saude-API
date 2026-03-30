package senac.tsi.minha_saude.entities.Usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario",unique = true,nullable = false)
    private Usuario usuario;

    @NotBlank(message = "O crm não deve estar em branco")
    @Column(name = "crm",nullable = false,length = 6)
    private String crm;

    @NotNull(message = "Unidade Federativa é obrigatorio")
    @Column(name = "uf",nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeFederativa uf;

    public Medico() {}

    public Medico (Usuario usuario, String crm,UnidadeFederativa uf){
        setUsuario(usuario);
        setCrm(crm);
        setUf(uf);
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = onlyDigitsFormat(crm);
    }

    public UnidadeFederativa getUf() {
        return uf;
    }
    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }


    //METODOS

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
               Uf: %s
           }
           """.formatted(id,(usuario != null ? usuario.getId() : null), crm, uf);
    }
}
