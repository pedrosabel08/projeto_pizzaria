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
AUTO_INCREMENT = 5
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
  `salarioFuncionario` VARCHAR(45) NOT NULL,
  `cpfFuncionario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `precoProduto` DOUBLE NOT NULL,
  `qtdProduto` VARCHAR(45) NOT NULL,
  `Fornecedor_idFornecedor` INT NOT NULL,
  PRIMARY KEY (`idProduto`, `Fornecedor_idFornecedor`),
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`Fornecedor_idFornecedor`)
    REFERENCES `pizzariabd`.`fornecedor` (`idFornecedor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzariabd`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `tamanhoPizza` VARCHAR(45) NULL,
  `saborPizza` VARCHAR(45) NULL,
  `bebida` VARCHAR(45) NULL,
  `preco` VARCHAR(45) NULL,
  PRIMARY KEY (`idPedido`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `pizzariabd`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `valorVenda` VARCHAR(45) NOT NULL,
  `dataVenda` DATE NOT NULL,
  `idFuncionario` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `idPedido` INT NOT NULL,
  PRIMARY KEY (`idVenda`),
  CONSTRAINT `fk_Venda_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `pizzariabd`.`funcionario` (`idFuncionario`),
  CONSTRAINT `fk_venda_pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `pizzariabd`.`pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into admin (nome, senha) values ('Pedro', '12345');
insert into admin (nome, senha) values ('Arthur', '12345');
insert into admin (nome, senha) values ('Vitor', '12345');