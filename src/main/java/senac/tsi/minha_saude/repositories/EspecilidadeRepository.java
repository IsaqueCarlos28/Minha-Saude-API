package senac.tsi.minha_saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.tsi.minha_saude.dominios.especialidade.Especialidade;

@Repository
public interface EspecilidadeRepository extends JpaRepository<Especialidade,Long> {
}
