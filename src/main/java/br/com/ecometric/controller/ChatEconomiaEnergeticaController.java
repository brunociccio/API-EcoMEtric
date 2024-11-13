package br.com.ecometric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ecometric.service.ChatEconomiaEnergeticaService;

@Controller
public class ChatEconomiaEnergeticaController {

    @Autowired
    private ChatEconomiaEnergeticaService chatService;

    @GetMapping("/chatEconomiaEnergetica")
    public String showChatPage() {
        return "chatEconomiaEnergetica"; 
    }

    @PostMapping("/chatEconomiaEnergetica/ask")
    @ResponseBody
    public String askQuestion(@RequestParam("question") String question) {
        return chatService.getAnswerFromAi(question);
    }
}
