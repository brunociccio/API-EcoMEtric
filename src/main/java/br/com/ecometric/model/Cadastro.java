package br.com.ecometric.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity(name = "T_ECOMETRIC_CADASTRO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCadastro;

    @NotBlank(message = "Nome da empresa é obrigatório")
    private String nmEmpresa;

    @NotBlank(message = "CNPJ é obrigatório")
    private String nrCnpj;

    private String inscricaoEstadual;

    private String razaoSocial;

    private String porte;

    @Temporal(TemporalType.DATE)
    private Date dtAbertura;

    private String email;

    @OneToOne
    @JoinColumn(name = "T_ECOMETRIC_LOGIN_id_login")
    private Login login;
}
