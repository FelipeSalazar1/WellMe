package br.com.wellme.application.controller;

import br.com.wellme.application.dto.ConsultaDTO;
import br.com.wellme.application.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.buscarPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> criar(@Valid @RequestBody ConsultaDTO dto) {
        ConsultaDTO consultaCriada = consultaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

