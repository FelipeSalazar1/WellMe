package br.com.wellme.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lembretes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Paciente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotBlank(message = "Título é obrigatório")
    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "Data do lembrete é obrigatória")
    @Future(message = "Data do lembrete deve ser futura")
    @Column(nullable = false)
    private LocalDateTime dataLembrete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLembrete tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLembrete status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        if (status == null) {
            status = StatusLembrete.PENDENTE;
        }
    }

    public enum TipoLembrete {
        CONSULTA, EXAME, MEDICAMENTO, VACINA, OUTRO
    }

    public enum StatusLembrete {
        PENDENTE, CONCLUIDO, CANCELADO
    }
}

