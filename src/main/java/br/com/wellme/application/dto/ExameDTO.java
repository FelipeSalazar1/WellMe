package br.com.wellme.application.dto;

import br.com.wellme.domain.model.Exame;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameDTO {

    private Long id;

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    private String pacienteNome;

    @NotBlank(message = "Tipo de exame é obrigatório")
    private String tipoExame;

    private String descricao;

    @NotNull(message = "Data do exame é obrigatória")
    @FutureOrPresent(message = "Data do exame deve ser presente ou futura")
    private LocalDate dataExame;

    private String laboratorio;

    private String resultado;

    private Exame.StatusExame status;

    private LocalDateTime dataCadastro;
}

