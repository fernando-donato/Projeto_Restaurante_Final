# restaurante-web-app

Projeto exemplo: aplicação Spring Boot + JPA para controle básico de restaurante.

## Pré-requisitos
- Java 17 JDK
- Maven 3.6+
- MySQL (crie o DB `restaurante_db`)

## Configurar banco
1. Inicie o MySQL.
2. Crie o banco `restaurante_db` (exemplo):

```sql
CREATE DATABASE restaurante_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. Verifique `src/main/resources/application.properties` e ajuste `spring.datasource.username`/`password` se necessário.

## Executar em desenvolvimento
- Importar como projeto Maven no NetBeans/IntelliJ.
- Rodar o arquivo `RestauranteWebAppApplication.java` (Run).
- Ou pelo terminal: `mvn spring-boot:run`
- Acesse: http://localhost:8080

## Endpoints principais (API)
- `GET /api/pratos` — listar pratos
- `GET /api/funcionarios` — listar funcionários
- `GET /api/funcionarios/garcons` — listar garçons (cargo = Garçom)
- `POST /api/funcionarios` — criar funcionário
- `GET /api/clientes` — listar clientes
- `POST /api/clientes` — criar cliente
- `POST /api/login` — endpoint de login (JSON com `{ "username": "...", "password": "..." }`)
- `POST /api/pedidos` — registrar pedido (veja frontend `static/js/script.js` para o formato do JSON)

## Front-end estático
Os arquivos estáticos estão em `src/main/resources/static/` (index.html, css, js).

---
Arquivo gerado automaticamente.
