package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity(name = "T_ECOMETRIC_MONITORAMENTO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Monitoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonitoramento;

    @Temporal(TemporalType.DATE)
    private Date dtValidade;

    private String stMonitoramento;
    private String dsMonitoramento;

    @Temporal(TemporalType.DATE)
    private Date dtEmissao;

    private Double vlPorcentagemDiferenca;
    private Double vlPorcentagemExpectativaMelhoria;

    @ManyToOne
    @JoinColumn(name = "id_login")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "id_consumo_energia")
    private ConsumoEnergia consumoEnergia;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projetos projeto;
}
