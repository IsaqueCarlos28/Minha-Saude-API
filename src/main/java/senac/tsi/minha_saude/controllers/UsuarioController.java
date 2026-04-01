package senac.tsi.minha_saude.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.tsi.minha_saude.dominios.usuarios.Usuario;
import senac.tsi.minha_saude.exceptions.UsuarioNotFoundException;
import senac.tsi.minha_saude.repositories.UsuarioRepository;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Usuario",description = "Usuario route")
@RestController
public class UsuarioController {

    private final UsuarioRepository repository;

    UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @Tag(name = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content
            )
    })
    @GetMapping("/Login/{id}")
    Usuario usuario(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException(id));
    }

    @PostMapping("/Usuario")
    Usuario newUsuario(@RequestBody Usuario newUsuario){
        return repository.save(newUsuario);
    }

    @Tag(name = "Alterar senha")
    @PutMapping("Usuario/AlterarSenha/{id}&{senha}")
    public ResponseEntity<Usuario> novoUsuario(@RequestBody Usuario updatedUsuario,
                           @PathVariable Long id,
                           @PathVariable String senha){
        return repository.findById(id)
                .map(usuario ->{
                    updatedUsuario.setSenhaHash(senha);
                    return ResponseEntity.ok(repository.save(usuario));
                }).orElseGet(() -> {
                    return ResponseEntity.created(URI.create("/books/"+
                                    updatedUsuario.getId()))
                            .body(repository.save(updatedUsuario));
                });
    }
}
