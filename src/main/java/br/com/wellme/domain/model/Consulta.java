package br.com.wellme.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Paciente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotNull(message = "Data e hora da consulta são obrigatórias")
    @FutureOrPresent(message = "Data da consulta deve ser presente ou futura")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 200)
    private String tipoConsulta;

    @Column(length = 100)
    private String medico;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        if (status == null) {
            status = StatusConsulta.AGENDADA;
        }
    }

    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA, REMARCADA
    }
}

