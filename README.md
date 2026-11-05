# 🔗 Encurtador de URL

Um microserviço simples, leve e direto ao ponto para encurtamento de URLs, desenvolvido com **Java** e **Spring Boot**. O projeto foca em uma arquitetura limpa, persistência com JPA e geração de códigos curtos usando Base62.

## 🚀 Tecnologias

* **Java** (JDK 26)
* **Spring Boot** (v4.1.2)
* **Spring Data JPA / Hibernate**
* **H2 Database** (Banco em memória)

## ⚙️ Como Funciona

1. **Encurtar (`POST /api/shorten`)**: Envia o link original e a aplicação gera um código alfanumérico exclusivo de 6 caracteres.
2. **Redirecionar (`GET /{shortCode}`)**: Busca o código no banco H2 e redireciona o navegador instantaneamente para o link de destino original (`HTTP 302`).

## 🛠️ Como Executar Localmente

1. Clona o repositório:
   ```bash
   git clone [https://github.com/leo22-22/Encurtador-Url.git](https://github.com/leo22-22/Encurtador-Url.git)
