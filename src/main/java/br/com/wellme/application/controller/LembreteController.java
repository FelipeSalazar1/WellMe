package br.com.wellme.application.controller;

import br.com.wellme.application.dto.LembreteDTO;
import br.com.wellme.application.service.LembreteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lembretes")
@RequiredArgsConstructor
public class LembreteController {

    private final LembreteService lembreteService;

    @GetMapping
    public ResponseEntity<List<LembreteDTO>> listarTodos() {
        return ResponseEntity.ok(lembreteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LembreteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lembreteService.buscarPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<LembreteDTO>> buscarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(lembreteService.buscarPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<LembreteDTO> criar(@Valid @RequestBody LembreteDTO dto) {
        LembreteDTO lembreteCriado = lembreteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lembreteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LembreteDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LembreteDTO dto) {
        return ResponseEntity.ok(lembreteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lembreteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

