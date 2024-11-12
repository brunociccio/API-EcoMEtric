package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "T_ECOMETRIC_CONTATO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContato;

    private Integer nrDdi;
    private Integer nrDdd;
    private Integer nrTelefone;
    private String tpContato;
    private String stTelefone;

    @ManyToOne
    @JoinColumn(name = "id_cadastro")
    private Cadastro cadastro;
}
