package br.com.ecometric.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "T_ECOMETRIC_PROJETOS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjeto;

    private String nmProjeto;
    private String dsIdeiaProjeto;
    private String dsPontosMelhorias;
    private String dsTopicosPrincipais;
    private Double vlPorcentagemMelhoria;
    private String stProjeto;
}
