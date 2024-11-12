# EcoMetric - Java Spring Boot Application

Este projeto é uma aplicação Java Spring Boot para o sistema `EcoMetric`, voltada para o monitoramento e análise de economia energética em pequenas e médias empresas. O sistema utiliza banco de dados configurável e fornece endpoints seguros para interação com o chatbot de economia energética.

## CRED
- API Java Spring Boot com Maven e Thymeleaf desenvolvida por Bruno Ciccio - Desenvolvedor Full-Stack Java e DevOps
- GitHub: https://github.com/brunociccio
- Linkedin: https://www.linkedin.com/in/bruno-ciccio/
- email: dev.bruno.ciccio@gmail.com

## Requisitos

- Conta na Azure com permissões para criar recursos (caso seja implantado na Azure)
- Docker e Docker Compose instalados (para execução local via contêiner)
- Git instalado e configurado
- Java 17+ instalado
- Maven instalado
- Visual Studio Code ou IDE compatível com Java

## Passo a Passo para Implantação

### 1. Clone o Repositório

Clone o repositório do projeto para sua máquina local:

```bash
git clone https://github.com/brunociccio/API-EcoMetric
cd API-EcoMetric
```

### 2. Configuração do Banco de Dados

Este projeto permite o uso de diversos bancos de dados compatíveis com Spring Boot (como MySQL, PostgreSQL, Oracle, etc.). Configure as variáveis de ambiente no arquivo `.env` ou nos parâmetros Docker para fornecer as credenciais do banco.

#### Exemplo de Configuração no Arquivo `.env`:

Crie um arquivo `.env` com as seguintes variáveis de ambiente:

```plaintext
DB_URL=jdbc:seu_banco_de_dados_url
DB_USER=seu_usuario
DB_PASS=sua_senha
SECURITY_JWT_SECRET_KEY=sua_chave_secreta
SPRING_SECURITY_USER_NAME=seu_nome_usuario
SPRING_SECURITY_USER_PASSWORD=sua_senha
SPRING_AI_OPENAI_API_KEY=sua_chave_api_openai
JWT_TOKEN_VALIDITY=3600000
```

No arquivo `application.properties`, configure para ler as variáveis:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.ai.openai.api-key=${SPRING_AI_OPENAI_API_KEY}
```

### 3. Executar a Aplicação com Docker

O projeto está preparado para ser executado em um contêiner Docker. Use o seguinte comando para construir e iniciar o contêiner:

```bash
docker build -t ecometric-app .
docker run -p 8080:8080 \
  -e SECURITY_JWT_SECRET_KEY="SuaChaveSecretaAqui" \
  -e SPRING_SECURITY_USER_NAME="devbrunociccio" \
  -e SPRING_SECURITY_USER_PASSWORD="ecometric" \
  -e SPRING_AI_OPENAI_API_KEY="sua_chave_openai" \
  -e JWT_TOKEN_VALIDITY="3600000" \
  -e DB_URL="jdbc:seu_banco_de_dados_url" \
  -e DB_USER="seu_usuario" \
  -e DB_PASS="sua_senha" \
  ecometric-app
```

### 4. Login e Autenticação

O sistema oferece login com as seguintes credenciais:

- **Login com usuário padrão**:
  - Nome de usuário: `ecometric`
  - Senha: `ecometric`
- **Login de administrador**:
  - Nome de usuário: `adminEcometric`
  - Senha: `ecometric`

#### Após o Login:
- O usuário será redirecionado para a página de **Chat Economia Energética** no endpoint `/chatEconomiaEnergetica`, onde pode interagir com o chatbot especializado em economia de energia.
- O botão de logout estará disponível para encerrar a sessão.

### 5. Endpoints e Testes

#### Teste os Endpoints da API no Insomnia ou Swagger

- Acesse o Swagger da API no endpoint:
  - [Swagger Documentation](http://localhost:8080/docs)

- Acesse o backend:
  - [Página Inicial da API](http://localhost:8080/home)

- Acesse a tela de login:
  - [Página de Login](http://localhost:8080/custom-login)

Endpoints importantes para teste no Insomnia:
- GET /cadastro - Lista todos os cadastros
- POST /cadastro - Cria um novo cadastro
- GET /cadastro/{id} - Retorna um cadastro específico pelo ID
- PUT /cadastro/{id} - Atualiza um cadastro existente
- DELETE /cadastro/{id} - Exclui um cadastro pelo ID

### 6. Scripts de Banco de Dados

Para criar as tabelas no banco de dados, utilize o script `V1__criar_tabelas_iniciais.sql`, que se encontra na pasta `resources/db/migration`.

### 7. Estrutura de Diretórios

- **controller**: Contém os controladores para gerenciar as rotas do sistema, incluindo autenticação e o chat de economia energética.
- **service**: Contém a lógica de negócios, incluindo o `ChatEconomiaEnergeticaService`, responsável por interagir com a API da OpenAI.
- **security**: Configuração de segurança, como JWT e controle de acesso.
- **config**: Configurações gerais do Spring Boot, incluindo `SecurityConfig`.
- **resources**: Arquivos estáticos, templates e scripts SQL para criação de tabelas.

### Conclusão

O *EcoMetric* é uma aplicação focada em monitoramento e economia energética para pequenas e médias empresas, com autenticação JWT, integração com a API da OpenAI para o chatbot e configuração modular para diferentes bancos de dados. Este projeto foi projetado com foco em segurança, escalabilidade e uso simplificado via contêiner Docker.
