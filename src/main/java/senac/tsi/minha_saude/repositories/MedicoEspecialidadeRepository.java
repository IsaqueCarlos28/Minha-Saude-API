package senac.tsi.minha_saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.tsi.minha_saude.dominios.medicos.MedicoEspecialidade;
import senac.tsi.minha_saude.dominios.medicos.MedicoEspecialidadeId;

@Repository
public interface MedicoEspecialidadeRepository extends JpaRepository<MedicoEspecialidade, MedicoEspecialidadeId>{}
