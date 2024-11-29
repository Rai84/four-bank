CREATE TABLE Cliente (
  cliente_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único para o cliente
  nome VARCHAR(100) NOT NULL, -- Nome do cliente
  cpf VARCHAR(11) UNIQUE NOT NULL, -- CPF como identificador único
  endereco VARCHAR(200), -- Endereço do cliente
  telefone VARCHAR(15), -- Telefone como VARCHAR para incluir símbolos como '+', '-'
  email VARCHAR(100) UNIQUE, -- Email único
  data_nascimento VARCHAR(10), -- Data de nascimento no formato adequado
  senha VARCHAR(255) NOT NULL -- Senha criptografada
);

CREATE TABLE Conta (
  conta_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único da conta
  numero_conta VARCHAR(20) UNIQUE NOT NULL, -- Número da conta como string para maior flexibilidade
  saldo DECIMAL(15, 2) NOT NULL DEFAULT 0.00, -- Saldo com precisão para valores monetários
  cliente_id INT NOT NULL, -- Alterado para INT
  FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) -- Relacionamento com a tabela Cliente
);

DELIMITER $$

CREATE TRIGGER criar_conta_automaticamente
AFTER INSERT ON cliente
FOR EACH ROW
BEGIN
    INSERT INTO conta (numero_conta, saldo, cliente_id)
    VALUES (
        FLOOR(RAND() * 1000000), -- Número de conta aleatório
        0.00, 
        NEW.cliente_id -- Usando o cliente_id do cliente recém-inserido
    );
END$$

DELIMITER ;

CREATE TABLE Emprestimo (
  emprestimo_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único do empréstimo
  cliente_id INT NOT NULL, -- ID do cliente associado
  valor_emprestimo DECIMAL(10, 2) NOT NULL, -- Valor do empréstimo com precisão monetária
  data_emprestimo DATE NOT NULL, -- Data do empréstimo
  data_vencimento DATE NOT NULL, -- Data de vencimento
  parcelas INT NOT NULL, -- Número de parcelas
  FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) -- Relacionamento com a tabela Cliente
);

CREATE TABLE Caixinha 
( 
    caixinha_id INT PRIMARY KEY AUTO_INCREMENT,  -- Auto incremento para o id
    cliente_id INT,  
    saldoCaixinha DECIMAL(15, 2) NOT NULL DEFAULT 0.00,  -- Tipo DECIMAL para o saldo
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)  -- Relacionamento com a tabela Cliente
);

