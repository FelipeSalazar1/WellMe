package br.com.wellme.application.service;

import br.com.wellme.application.dto.PacienteDTO;
import br.com.wellme.application.exception.BusinessException;
import br.com.wellme.application.exception.ResourceNotFoundException;
import br.com.wellme.domain.model.Paciente;
import br.com.wellme.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));
        return toDTO(paciente);
    }

    @Transactional
    public PacienteDTO criar(PacienteDTO dto) {
        if (pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("Já existe um paciente cadastrado com este CPF");
        }
        
        Paciente paciente = toEntity(dto);
        paciente = pacienteRepository.save(paciente);
        return toDTO(paciente);
    }

    @Transactional
    public PacienteDTO atualizar(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));
        
        if (!paciente.getCpf().equals(dto.getCpf()) && pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("Já existe um paciente cadastrado com este CPF");
        }
        
        atualizarDados(paciente, dto);
        paciente = pacienteRepository.save(paciente);
        return toDTO(paciente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getDataNascimento(),
                paciente.getSexo(),
                paciente.getEndereco(),
                paciente.getObservacoes(),
                paciente.getDataCadastro()
        );
    }

    private Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setSexo(dto.getSexo());
        paciente.setEndereco(dto.getEndereco());
        paciente.setObservacoes(dto.getObservacoes());
        return paciente;
    }

    private void atualizarDados(Paciente paciente, PacienteDTO dto) {
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setSexo(dto.getSexo());
        paciente.setEndereco(dto.getEndereco());
        paciente.setObservacoes(dto.getObservacoes());
    }
}

