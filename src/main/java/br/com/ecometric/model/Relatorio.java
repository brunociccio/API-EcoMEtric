package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity(name = "T_ECOMETRIC_RELATORIO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatorio;

    private String dsMelhorias;
    private String dsPontosFaltantesMelhorias;
    private String stRelatorio;

    @Temporal(TemporalType.DATE)
    private Date dtEmissaoRelatorio;
    
    private Double vlDiferencaConsumo;
    private String dsJustificativaAumento;

    @ManyToOne
    @JoinColumn(name = "id_login")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "id_consumo_energia")
    private ConsumoEnergia consumoEnergia;
}
