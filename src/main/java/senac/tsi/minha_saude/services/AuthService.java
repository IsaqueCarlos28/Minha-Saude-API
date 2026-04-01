package senac.tsi.minha_saude.services;

import org.springframework.stereotype.Service;
import senac.tsi.minha_saude.dominios.usuarios.DTO.LoginRequest;
import senac.tsi.minha_saude.dominios.usuarios.DTO.RegisterRequest;
import senac.tsi.minha_saude.dominios.usuarios.Usuario;
import senac.tsi.minha_saude.repositories.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository repository;

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public String login(LoginRequest request) {
        Usuario usuario = repository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenhaHash().equals(request.senha())) {
            throw new RuntimeException("Senha inválida");
        }

        return "Login OK";
    }

    public Usuario register(RegisterRequest request) {

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenhaHash(request.senha()); // sem criptografia por enquanto

        return repository.save(usuario);
    }
}