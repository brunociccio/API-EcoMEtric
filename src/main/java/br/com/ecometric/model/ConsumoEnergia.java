package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity(name = "T_ECOMETRIC_CONSUMO_ENERGIA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumoEnergia;

    private String nrUnidadeConsumidora;
    private String nrFatura;

    @Temporal(TemporalType.DATE)
    private Date dtReferenciaInicial;

    @Temporal(TemporalType.DATE)
    private Date dtReferenciaFinal;

    @Temporal(TemporalType.DATE)
    private Date dtEmissao;

    @Temporal(TemporalType.DATE)
    private Date dtVencimento;

    private String stPagamento;
    private Double qtdConsumoKwh;
    private String tpLeitura;
    private Double qtdLeituraAnterior;
    private Double vlTotal;
    private Double vlTarifaConsumo;
    private Double vlTarifasImpostos;
    private String stBandeiraTarifa;
}
