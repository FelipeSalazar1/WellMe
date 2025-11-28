package br.com.wellme.application.dto;

import br.com.wellme.domain.model.Consulta;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {

    private Long id;

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    private String pacienteNome;

    @NotNull(message = "Data e hora da consulta são obrigatórias")
    @FutureOrPresent(message = "Data da consulta deve ser presente ou futura")
    private LocalDateTime dataHora;

    private String tipoConsulta;

    private String medico;

    private String observacoes;

    private Consulta.StatusConsulta status;

    private LocalDateTime dataCadastro;
}

