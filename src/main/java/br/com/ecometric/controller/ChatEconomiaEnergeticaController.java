package br.com.ecometric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecometric.service.ChatEconomiaEnergeticaService;

@RestController
@RequestMapping("/chatEconomiaEnergetica")
public class ChatEconomiaEnergeticaController {

    @Autowired
    private ChatEconomiaEnergeticaService chatService;

    @PostMapping("/ask")
    public String askQuestion(@RequestParam("question") String question) {
        return chatService.getAnswerFromAi(question);
    }
}
