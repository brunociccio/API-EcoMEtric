package br.com.ecometric.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatEconomiaEnergeticaService {

    // Injeta a chave da API do OpenAI a partir do application.properties ou variáveis de ambiente
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    public String getAnswerFromAi(String question) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + openAiApiKey);
            headers.set("Content-Type", "application/json");

            // Ajuste para saudações e restrição ao tema Economia Energética
            String prompt;
            if (isGreeting(question)) {
                prompt = "Diga 'Olá! Tudo bem? Sou uma IA especializada em Economia Energética. Como posso ajudar?'";
            } else if (!isRelatedToEconomiaEnergetica(question)) { 
                // Retorna uma resposta padrão se o assunto for irrelevante
                return "Posso ajudar apenas com assuntos sobre economia energética. Por favor, pergunte algo sobre consumo de energia, eficiência energética, economia ou sustentabilidade.";
            } else {
                prompt = "Você é um especialista em Economia Energética. Responda apenas a perguntas sobre o tema. Pergunta: " + question;
            }

            String requestBody = "{"
                    + "\"model\": \"gpt-3.5-turbo\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],"
                    + "\"max_tokens\": 100"
                    + "}";

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.openai.com/v1/chat/completions",
                    HttpMethod.POST, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return extractResponse(response.getBody());
            } else {
                return "Erro: " + response.getStatusCode();
            }

        } catch (Exception e) {
            return "Erro ao chamar a IA: " + e.getMessage();
        }
    }

    // Método para verificar saudações
    private boolean isGreeting(String question) {
        String[] greetings = {"oi", "olá", "ola", "tudo bem", "bom dia", "boa tarde", "boa noite"};
        for (String greeting : greetings) {
            if (question.toLowerCase().contains(greeting)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar se a pergunta é relacionada à Economia Energética
    private boolean isRelatedToEconomiaEnergetica(String question) {
        String[] keywords = {"energia", "consumo", "eficiência energética", "economia", "sustentabilidade", "economia energética"};
        for (String keyword : keywords) {
            if (question.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    // Método para extrair a resposta JSON
    private String extractResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            
            // Navega até o campo 'choices' e pega o primeiro item, depois extrai 'message.content'
            String message = jsonNode
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
            
            return message;
        } catch (Exception e) {
            return "Erro ao processar a resposta da IA.";
        }
    }
}
