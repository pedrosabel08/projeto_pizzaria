-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pizzariabd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pizzariabd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pizzariabd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pizzariabd` ;

-- -----------------------------------------------------
-- Table `pizzariabd`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nomeCliente` VARCHAR(45) NOT NULL,
  `telefoneCliente` VARCHAR(45) NOT NULL,
  `rua` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `referencia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `nomeFornecedor` VARCHAR(45) NOT NULL,
  `cnpjFornecedor` VARCHAR(45) NOT NULL,
  `telefoneFornecedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(45) NOT NULL,
  `telefoneFuncionario` VARCHAR(45) NOT NULL,
  `cargoFuncionario` VARCHAR(45) NOT NULL,
  `salarioFuncionario` DOUBLE NOT NULL,
  `cpfFuncionario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `tamanhoPizza` VARCHAR(45) NULL DEFAULT NULL,
  `saborPizza` VARCHAR(45) NULL DEFAULT NULL,
  `bebida` VARCHAR(45) NULL DEFAULT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idPedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `idFornecedor` INT NULL DEFAULT NULL,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `precoProduto` DOUBLE NOT NULL,
  `qtdProduto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`idFornecedor`)
    REFERENCES `pizzariabd`.`fornecedor` (`idFornecedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `valorVenda` VARCHAR(45) NOT NULL,
  `dataVenda` DATE NOT NULL,
  `idCliente` INT NOT NULL,
  `idPedido` INT NOT NULL,
  PRIMARY KEY (`idVenda`),
  CONSTRAINT `fk_Venda_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `pizzariabd`.`cliente` (`idCliente`),
  CONSTRAINT `fk_venda_pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `pizzariabd`.`pedido` (`idPedido`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into admin (nome, senha) values ('Pedro', '12345');
insert into admin (nome, senha) values ('Arthur', '12345');
insert into admin (nome, senha) values ('Vitor', '12345');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (1, 'Alfredo Vieira', '47.474.698/0001-40', '(49)93853-3231');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (2, 'Arthur Vale', '95.322.810/0001-70', '(83)92254-2818');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (3, 'Estevão Rosário', '73.224.259/0001-90', '(73)92056-7033');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (4, 'Alfredo Lourenço','69.427.339/0001-75', '(98)93881-7264');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (5, 'Rodrigo Vale', '17.627.024/0001-78', '(97)93227-6595');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (6, 'Marcos Salles', '80.107.368/0001-92', '(98)93625-7628');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (7, 'Sandro Rios',  '25.854.123/0001-10', '(65)92152-4375');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (8, 'Nicolas Melo',  '14.459.938/0001-89', '(96)92374-9417');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (9, 'Igor Pinto',  '85.825.878/0001-19', '(86)92417-5347');

insert into fornecedor (idFornecedor, nomeFornecedor, cnpjFornecedor, telefoneFornecedor) values (10, 'Igor Vargas', '33.958.886/0001-30', '(91)93521-4808');

