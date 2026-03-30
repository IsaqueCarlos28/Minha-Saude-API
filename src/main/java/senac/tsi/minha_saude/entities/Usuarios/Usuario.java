package senac.tsi.minha_saude.entities.Usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Objects;
//import lombok.Getter;

@Entity
@Table(name = "usuario") //nome no database
public class Usuario {

    //CAMPOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    @Column(name = "nome",nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "CPF não pode estar em branco")
    @CPF(message = "CPF invalido")
    @Column(name = "cpf",nullable = false, length = 11, unique = true)
    private String cpf;

    @NotBlank(message = "Email não pode estar em branco")
    @Email(message = "Email inválido")
    @Column(name = "email",nullable = false,length = 150,unique = true)
    private String email;

    @NotBlank(message = "Telefone não pode estar em branco")
    @Column(name = "telefone",nullable = false, length = 11,unique = true)
    @Pattern(regexp = "\\d{10,11}", message = "Formato de telefone errado")
    private String telefone;

    @NotNull(message = "Gênero é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero",nullable = false)
    private Genero genero;

    @Past
    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento",nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "Senha não pode estar em branco")
    @Column(name = "senha_hash",nullable = false)
    @JsonIgnore
    private String senhaHash;

    @NotNull(message = "Role é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    protected Role role;

    //CONSTRUCTORS
    public Usuario(){}

    public Usuario(String nome,String cpf,String email, Genero genero,
            LocalDate dataNascimento,String telefone,String senhaHash,Role role){

        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setGenero(genero);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
        setSenhaHash(senhaHash);
        setRole(role);
    }

    //GETTERS & SETTERS

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = onlyDigitsFormat(cpf);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = onlyDigitsFormat(telefone);
    }

    public String getSenhaHash() {
        return senhaHash;
    }
    public void setSenhaHash(String hash) {
        this.senhaHash = hash;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    //METODOS

    protected String onlyDigitsFormat(String value){
        return (value != null) ? value.replaceAll("\\D", ""):null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario that = (Usuario) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return """
           Usuario{
               ID: %s
               Nome: %s
               Email: %s
               Gênero: %s
               Data de Nascimento: %s
               Role: %s
           }
           """.formatted(id, nome, email, genero,dataNascimento, role);
    }
}