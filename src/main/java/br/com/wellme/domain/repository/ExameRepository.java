package br.com.wellme.domain.repository;

import br.com.wellme.domain.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByPacienteId(Long pacienteId);
    
    @Query("SELECT e FROM Exame e WHERE e.paciente.id = :pacienteId AND e.dataExame >= :dataInicio ORDER BY e.dataExame ASC")
    List<Exame> findProximosExames(@Param("pacienteId") Long pacienteId, @Param("dataInicio") LocalDate dataInicio);
}

