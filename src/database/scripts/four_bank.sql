CREATE DATABASE four_bank;
USE four_bank;

CREATE TABLE `cadastro` (
  `cadastro_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `CPF` varchar(11) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `data_nascimento` varchar(10) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`cadastro_id`),
  UNIQUE KEY `CPF` (`CPF`)
);

CREATE TABLE `cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `data_nascimento` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  KEY `CPF` (`CPF`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`CPF`) REFERENCES `cadastro` (`CPF`)
);

CREATE TABLE `logins` (
  `logins_id` int(11) NOT NULL AUTO_INCREMENT,
  `CPF` varchar(11) DEFAULT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`logins_id`),
  KEY `CPF` (`CPF`),
  CONSTRAINT `logins_ibfk_1` FOREIGN KEY (`CPF`) REFERENCES `cadastro` (`CPF`)
);

DELIMITER $$

CREATE TRIGGER `after_insert_cadastro_cliente` 
AFTER INSERT ON `cadastro` 
FOR EACH ROW 
BEGIN
    INSERT INTO cliente (nome, CPF, endereco, telefone, email, data_nascimento) 
    VALUES (NEW.nome, NEW.CPF, NEW.endereco, NEW.telefone, NEW.email, NEW.data_nascimento);
END $$

CREATE TRIGGER `after_insert_cadastro_logins` 
AFTER INSERT ON `cadastro` 
FOR EACH ROW 
BEGIN
    INSERT INTO logins (CPF, senha) 
    VALUES (NEW.CPF, NEW.senha);
END $$

DELIMITER ;

COMMIT;
