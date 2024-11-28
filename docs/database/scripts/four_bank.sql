CREATE TABLE Cliente (
  cliente_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único para o cliente
  nome VARCHAR(100) NOT NULL, -- Nome do cliente
  cpf VARCHAR(11) UNIQUE NOT NULL, -- CPF como identificador único
  endereco VARCHAR(200), -- Endereço do cliente
  telefone VARCHAR(15), -- Telefone como VARCHAR para incluir símbolos como '+', '-'
  email VARCHAR(100) UNIQUE, -- Email único
  data_nascimento VARCHAR(8), -- Data de nascimento no formato adequado
  senha VARCHAR(255) NOT NULL -- Senha criptografada
);

CREATE TABLE Conta (
  conta_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único da conta
  numero_conta VARCHAR(20) UNIQUE NOT NULL, -- Número da conta como string para maior flexibilidade
  saldo DECIMAL(15, 2) NOT NULL DEFAULT 0.00, -- Saldo com precisão para valores monetários
  cliente_id VARCHAR(11) NOT NULL, -- CPF do cliente associado à conta
  FOREIGN KEY (cliente_id) REFERENCES Cliente(cpf) -- Relacionamento com a tabela Cliente
);

CREATE TABLE Emprestimo (
  emprestimo_id INT PRIMARY KEY AUTO_INCREMENT, -- ID único do empréstimo
  cliente_id INT NOT NULL, -- ID do cliente associado
  valor_emprestimo DECIMAL(10, 2) NOT NULL, -- Valor do empréstimo com precisão monetária
  data_emprestimo DATE NOT NULL, -- Data do empréstimo
  data_vencimento DATE NOT NULL, -- Data de vencimento
  parcelas INT NOT NULL, -- Número de parcelas
  FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) -- Relacionamento com a tabela Cliente
);

DELIMITER $$

CREATE TRIGGER adiciona_emprestimo_ao_saldo
AFTER INSERT ON Emprestimo
FOR EACH ROW
BEGIN
    -- Atualiza o saldo da conta associada ao cliente
    UPDATE Conta
    SET saldo = saldo + NEW.valor_emprestimo
    WHERE cliente_id = NEW.cliente_id;
END$$

DELIMITER ;
