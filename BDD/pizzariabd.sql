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
CREATE SCHEMA IF NOT EXISTS `pizzariabd` DEFAULT CHARACTER SET utf8mb4;
USE `pizzariabd` ;

-- -----------------------------------------------------
-- Table `pizzariabd`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(45) NOT NULL,
  `cargoFuncionario` VARCHAR(45) NOT NULL,
  `salarioFuncionario` VARCHAR(45) NOT NULL,
  `cpfFuncionario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Endereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `ruaEndereco` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `referencia` VARCHAR(45) NULL,
  PRIMARY KEY (`idEndereco`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nomeCliente` VARCHAR(45) NOT NULL,
  `telefoneCliente` VARCHAR(45) NOT NULL,
  `Endereco_idEndereco` INT NOT NULL,
  PRIMARY KEY (`idCliente`, `Endereco_idEndereco`),
  CONSTRAINT `fk_Cliente_Endereco1`
    FOREIGN KEY (`Endereco_idEndereco`)
    REFERENCES `pizzariabd`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `nomeFornecedor` VARCHAR(45) NOT NULL,
  `cnpjFornecedor` VARCHAR(45) NOT NULL,
  `telefoneFornecedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `precoProduto` VARCHAR(45) NOT NULL,
  `qtdProduto` VARCHAR(45) NOT NULL,
  `Fornecedor_idFornecedor` INT NOT NULL,
  PRIMARY KEY (`idProduto`, `Fornecedor_idFornecedor`),
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`Fornecedor_idFornecedor`)
    REFERENCES `pizzariabd`.`Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pizzariabd`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`Venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `valorVenda` VARCHAR(45) NOT NULL,
  `dataVenda` DATE NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  `Funcionario_idFuncionario` INT NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  `Cliente_Endereco_idEndereco` INT NOT NULL,
  PRIMARY KEY (`idVenda`, `Produto_idProduto`, `Funcionario_idFuncionario`, `Cliente_idCliente`, `Cliente_Endereco_idEndereco`),
  CONSTRAINT `fk_Venda_Produto`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `pizzariabd`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_Funcionario1`
    FOREIGN KEY (`Funcionario_idFuncionario`)
    REFERENCES `pizzariabd`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_Cliente1`
    FOREIGN KEY (`Cliente_idCliente` , `Cliente_Endereco_idEndereco`)
    REFERENCES `pizzariabd`.`Cliente` (`idCliente` , `Endereco_idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into Admin(nome, senha) values ("Pedro", "12345");