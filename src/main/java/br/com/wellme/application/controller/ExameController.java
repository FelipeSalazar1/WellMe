package br.com.wellme.application.controller;

import br.com.wellme.application.dto.ExameDTO;
import br.com.wellme.application.service.ExameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exames")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService exameService;

    @GetMapping
    public ResponseEntity<List<ExameDTO>> listarTodos() {
        return ResponseEntity.ok(exameService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(exameService.buscarPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ExameDTO>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(exameService.buscarPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<ExameDTO> criar(@Valid @RequestBody ExameDTO dto) {
        ExameDTO exameCriado = exameService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exameCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ExameDTO dto) {
        return ResponseEntity.ok(exameService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        exameService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

