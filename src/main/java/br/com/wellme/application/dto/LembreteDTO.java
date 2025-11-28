package br.com.wellme.application.dto;

import br.com.wellme.domain.model.Lembrete;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LembreteDTO {

    private Long id;

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    private String pacienteNome;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data do lembrete é obrigatória")
    @Future(message = "Data do lembrete deve ser futura")
    private LocalDateTime dataLembrete;

    @NotNull(message = "Tipo do lembrete é obrigatório")
    private Lembrete.TipoLembrete tipo;

    private Lembrete.StatusLembrete status;

    private LocalDateTime dataCadastro;
}

