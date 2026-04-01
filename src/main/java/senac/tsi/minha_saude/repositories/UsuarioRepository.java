package senac.tsi.minha_saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.tsi.minha_saude.dominios.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
