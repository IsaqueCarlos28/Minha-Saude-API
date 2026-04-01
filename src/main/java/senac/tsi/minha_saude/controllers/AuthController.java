package senac.tsi.minha_saude.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.tsi.minha_saude.dominios.usuarios.DTO.LoginRequest;
import senac.tsi.minha_saude.dominios.usuarios.DTO.RegisterRequest;
import senac.tsi.minha_saude.dominios.usuarios.Usuario;
import senac.tsi.minha_saude.repositories.UsuarioRepository;
import senac.tsi.minha_saude.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repository;
    private final AuthService service;

    public AuthController(AuthService service,UsuarioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequest request) {
        if (repository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        return ResponseEntity.ok(service.register(request));
    }

}
