package br.com.ecometric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@Controller
public class EcometricApplication {

    public static void main(String[] args) {
        // Inicializa Dotenv para carregar variáveis de ambiente do arquivo .env
        Dotenv dotenv = Dotenv.configure()
                .directory(".") // Diretório do arquivo .env
                .filename(".env") // Nome do arquivo .env
                .load();

        SpringApplication.run(EcometricApplication.class, args);
    }

    @Bean
    public Dotenv dotenv() {
        // Disponibiliza Dotenv como Bean no contexto Spring
        return Dotenv.load();
    }

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "API Full-Stack do Projeto EcoMetric";
    }
}
