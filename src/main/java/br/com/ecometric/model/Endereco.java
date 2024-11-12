package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "T_ECOMETRIC_ENDERECO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    private String nrCep;
    private String nmLogradouro;
    private Integer nrLogradouro;
    private String dsComplemento;
    private String nmBairro;
    private String nmCidade;
    private String nmEstado;

    @ManyToOne
    @JoinColumn(name = "id_cadastro")
    private Cadastro cadastro;
}

