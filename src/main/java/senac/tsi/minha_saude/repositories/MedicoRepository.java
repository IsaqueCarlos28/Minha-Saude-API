package senac.tsi.minha_saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.tsi.minha_saude.dominios.medicos.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
