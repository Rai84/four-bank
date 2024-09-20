# FOUR-BANK API

## Descrição

A **FOUR-BANK API** é uma aplicação backend desenvolvida em Java, projetada para oferecer serviços bancários essenciais, abrangendo tanto operações financeiras com moeda fiduciária quanto com criptomoedas. A API permite gerenciar contas bancárias, realizar transferências, consultar saldos e operar com criptomoedas. A aplicação se conecta a um banco de dados MySQL para o armazenamento e gerenciamento dos dados.

## Funcionalidades  

### Moeda Fiduciária

- **Gerenciamento de Contas:**
  - Criar, consultar, atualizar e excluir contas bancárias.
  
- **Operações Financeiras:**
  - Transferências via Pix, pagamento de boletos e recarga de celular.

- **Consultas:**
  - Saldo e extrato de transações.  

### Criptomoedas

- **Consultas:**
  - Verificação de saldo e extratos de criptomoedas.

- **Operações Financeiras:**
  - Compra e venda de criptomoedas.
 
## Protótipos

Acesse o [protótipo High Fidelity](https://www.figma.com/design/fQjVmMJRd2LiwFXE2JEQS7/Untitled?node-id=0-1&t=VZBmfRrC2gM4xnQJ-1) para visualizar o design completo da interface da aplicação.

## Requisitos

- **Java 17** ou superior
- **MySQL** ou outro banco de dados compatível

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/Rai84/pi.git
   cd banco-api
   ```

2. Configure o banco de dados MySQL:

   - Crie um banco de dados chamado `banco_pi`.
   - Execute o script SQL localizado em `src/database/four_bank.sql` para criar as tabelas necessárias:

     ```bash
     mysql -u seu_usuario -p banco_pi < src/database/four_bank.sql
     ```

   - Atualize as credenciais do banco de dados no arquivo `src/database/DatabaseManager.java` com seu usuário, senha e URL do banco de dados MySQL.

3. Compile e execute a aplicação:

   ```bash
   javac -cp "path/to/mysql-connector.jar" src/*.java
   java -cp "path/to/mysql-connector.jar:." Main
   ```

4. A API estará disponível em `http://localhost:5500`.

---

## Contato

#### Desenvolvedores:
  - [Rai Gonçalves](https://github.com/Rai84)
  - [Pedro Alves](https://github.com/PedroTheProgramer)
  - [Talita Keniata](https://github.com/Keniata15)
  - [Kelly Cristina](https://github.com/kellycmds239)

---
