package br.com.wellme.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "exames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Paciente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotNull(message = "Tipo de exame é obrigatório")
    @Column(nullable = false, length = 100)
    private String tipoExame;

    @Column(length = 200)
    private String descricao;

    @FutureOrPresent(message = "Data do exame deve ser presente ou futura")
    @Column(nullable = false)
    private LocalDate dataExame;

    @Column(length = 100)
    private String laboratorio;

    @Column(columnDefinition = "TEXT")
    private String resultado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusExame status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        if (status == null) {
            status = StatusExame.AGENDADO;
        }
    }

    public enum StatusExame {
        AGENDADO, REALIZADO, CANCELADO, PENDENTE
    }
}

