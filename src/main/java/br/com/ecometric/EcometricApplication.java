package br.com.ecometric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class EcometricApplication {

    public static void main(String[] args) {
        // Inicializa a aplicação Spring Boot sem carregar o arquivo .env
        SpringApplication.run(EcometricApplication.class, args);
    }

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "API Full-Stack do Projeto EcoMetric";
    }
}

