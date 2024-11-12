package br.com.ecometric.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "T_ECOMETRIC_LOGIN")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogin;

    @NotBlank(message = "Login é obrigatório")
    private String nmLogin;

    @NotBlank(message = "Senha é obrigatória")
    private String nrSenha;

    private String stLogin;

    @OneToOne(mappedBy = "login")
    private Cadastro cadastro;
}
