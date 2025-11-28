package br.com.wellme.application.service;

import br.com.wellme.application.dto.ConsultaDTO;
import br.com.wellme.application.exception.ResourceNotFoundException;
import br.com.wellme.domain.model.Consulta;
import br.com.wellme.domain.model.Paciente;
import br.com.wellme.domain.repository.ConsultaRepository;
import br.com.wellme.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<ConsultaDTO> listarTodos() {
        return consultaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
        return toDTO(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> buscarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultaDTO criar(ConsultaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        Consulta consulta = toEntity(dto, paciente);
        consulta = consultaRepository.save(consulta);
        return toDTO(consulta);
    }

    @Transactional
    public ConsultaDTO atualizar(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
        
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));
        
        atualizarDados(consulta, dto, paciente);
        consulta = consultaRepository.save(consulta);
        return toDTO(consulta);
    }

    @Transactional
    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrada com ID: " + id);
        }
        consultaRepository.deleteById(id);
    }

    private ConsultaDTO toDTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.getId());
        dto.setPacienteId(consulta.getPaciente().getId());
        dto.setPacienteNome(consulta.getPaciente().getNome());
        dto.setDataHora(consulta.getDataHora());
        dto.setTipoConsulta(consulta.getTipoConsulta());
        dto.setMedico(consulta.getMedico());
        dto.setObservacoes(consulta.getObservacoes());
        dto.setStatus(consulta.getStatus());
        dto.setDataCadastro(consulta.getDataCadastro());
        return dto;
    }

    private Consulta toEntity(ConsultaDTO dto, Paciente paciente) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.getDataHora());
        consulta.setTipoConsulta(dto.getTipoConsulta());
        consulta.setMedico(dto.getMedico());
        consulta.setObservacoes(dto.getObservacoes());
        if (dto.getStatus() != null) {
            consulta.setStatus(dto.getStatus());
        }
        return consulta;
    }

    private void atualizarDados(Consulta consulta, ConsultaDTO dto, Paciente paciente) {
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.getDataHora());
        consulta.setTipoConsulta(dto.getTipoConsulta());
        consulta.setMedico(dto.getMedico());
        consulta.setObservacoes(dto.getObservacoes());
        if (dto.getStatus() != null) {
            consulta.setStatus(dto.getStatus());
        }
    }
}

