package br.com.wellme.application.service;

import br.com.wellme.application.dto.LembreteDTO;
import br.com.wellme.application.exception.ResourceNotFoundException;
import br.com.wellme.domain.model.Lembrete;
import br.com.wellme.domain.model.Paciente;
import br.com.wellme.domain.repository.LembreteRepository;
import br.com.wellme.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LembreteService {

    private final LembreteRepository lembreteRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<LembreteDTO> listarTodos() {
        return lembreteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LembreteDTO buscarPorId(Long id) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lembrete não encontrado com ID: " + id));
        return toDTO(lembrete);
    }

    @Transactional(readOnly = true)
    public List<LembreteDTO> buscarPorPaciente(Long pacienteId) {
        return lembreteRepository.findByPacienteId(pacienteId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LembreteDTO criar(LembreteDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        Lembrete lembrete = toEntity(dto, paciente);
        lembrete = lembreteRepository.save(lembrete);
        return toDTO(lembrete);
    }

    @Transactional
    public LembreteDTO atualizar(Long id, LembreteDTO dto) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lembrete não encontrado com ID: " + id));
        
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        atualizarDados(lembrete, dto, paciente);
        lembrete = lembreteRepository.save(lembrete);
        return toDTO(lembrete);
    }

    @Transactional
    public void deletar(Long id) {
        if (!lembreteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lembrete não encontrado com ID: " + id);
        }
        lembreteRepository.deleteById(id);
    }

    private LembreteDTO toDTO(Lembrete lembrete) {
        LembreteDTO dto = new LembreteDTO();
        dto.setId(lembrete.getId());
        dto.setPacienteId(lembrete.getPaciente().getId());
        dto.setPacienteNome(lembrete.getPaciente().getNome());
        dto.setTitulo(lembrete.getTitulo());
        dto.setDescricao(lembrete.getDescricao());
        dto.setDataLembrete(lembrete.getDataLembrete());
        dto.setTipo(lembrete.getTipo());
        dto.setStatus(lembrete.getStatus());
        dto.setDataCadastro(lembrete.getDataCadastro());
        return dto;
    }

    private Lembrete toEntity(LembreteDTO dto, Paciente paciente) {
        Lembrete lembrete = new Lembrete();
        lembrete.setPaciente(paciente);
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setDataLembrete(dto.getDataLembrete());
        lembrete.setTipo(dto.getTipo());
        if (dto.getStatus() != null) {
            lembrete.setStatus(dto.getStatus());
        }
        return lembrete;
    }

    private void atualizarDados(Lembrete lembrete, LembreteDTO dto, Paciente paciente) {
        lembrete.setPaciente(paciente);
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setDataLembrete(dto.getDataLembrete());
        lembrete.setTipo(dto.getTipo());
        if (dto.getStatus() != null) {
            lembrete.setStatus(dto.getStatus());
        }
    }
}

