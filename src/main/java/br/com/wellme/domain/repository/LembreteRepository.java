package br.com.wellme.domain.repository;

import br.com.wellme.domain.model.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByPacienteId(Long pacienteId);
    
    @Query("SELECT l FROM Lembrete l WHERE l.paciente.id = :pacienteId AND l.dataLembrete >= :dataInicio AND l.status = 'PENDENTE' ORDER BY l.dataLembrete ASC")
    List<Lembrete> findLembretesPendentes(@Param("pacienteId") Long pacienteId, @Param("dataInicio") LocalDateTime dataInicio);
}

