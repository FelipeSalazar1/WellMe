package br.com.wellme.application.service;

import br.com.wellme.application.dto.ExameDTO;
import br.com.wellme.application.exception.ResourceNotFoundException;
import br.com.wellme.domain.model.Exame;
import br.com.wellme.domain.model.Paciente;
import br.com.wellme.domain.repository.ExameRepository;
import br.com.wellme.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository exameRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<ExameDTO> listarTodos() {
        return exameRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ExameDTO buscarPorId(Long id) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame não encontrado com ID: " + id));
        return toDTO(exame);
    }

    @Transactional(readOnly = true)
    public List<ExameDTO> buscarPorPaciente(Long pacienteId) {
        return exameRepository.findByPacienteId(pacienteId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExameDTO criar(ExameDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        Exame exame = toEntity(dto, paciente);
        exame = exameRepository.save(exame);
        return toDTO(exame);
    }

    @Transactional
    public ExameDTO atualizar(Long id, ExameDTO dto) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame não encontrado com ID: " + id));
        
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        atualizarDados(exame, dto, paciente);
        exame = exameRepository.save(exame);
        return toDTO(exame);
    }

    @Transactional
    public void deletar(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exame não encontrado com ID: " + id);
        }
        exameRepository.deleteById(id);
    }

    private ExameDTO toDTO(Exame exame) {
        ExameDTO dto = new ExameDTO();
        dto.setId(exame.getId());
        dto.setPacienteId(exame.getPaciente().getId());
        dto.setPacienteNome(exame.getPaciente().getNome());
        dto.setTipoExame(exame.getTipoExame());
        dto.setDescricao(exame.getDescricao());
        dto.setDataExame(exame.getDataExame());
        dto.setLaboratorio(exame.getLaboratorio());
        dto.setResultado(exame.getResultado());
        dto.setStatus(exame.getStatus());
        dto.setDataCadastro(exame.getDataCadastro());
        return dto;
    }

    private Exame toEntity(ExameDTO dto, Paciente paciente) {
        Exame exame = new Exame();
        exame.setPaciente(paciente);
        exame.setTipoExame(dto.getTipoExame());
        exame.setDescricao(dto.getDescricao());
        exame.setDataExame(dto.getDataExame());
        exame.setLaboratorio(dto.getLaboratorio());
        exame.setResultado(dto.getResultado());
        if (dto.getStatus() != null) {
            exame.setStatus(dto.getStatus());
        }
        return exame;
    }

    private void atualizarDados(Exame exame, ExameDTO dto, Paciente paciente) {
        exame.setPaciente(paciente);
        exame.setTipoExame(dto.getTipoExame());
        exame.setDescricao(dto.getDescricao());
        exame.setDataExame(dto.getDataExame());
        exame.setLaboratorio(dto.getLaboratorio());
        exame.setResultado(dto.getResultado());
        if (dto.getStatus() != null) {
            exame.setStatus(dto.getStatus());
        }
    }
}

