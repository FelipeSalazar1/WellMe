package br.com.wellme.domain.repository;

import br.com.wellme.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteId(Long pacienteId);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId AND c.dataHora >= :dataInicio ORDER BY c.dataHora ASC")
    List<Consulta> findProximasConsultas(@Param("pacienteId") Long pacienteId, @Param("dataInicio") LocalDateTime dataInicio);
}

