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
CREATE SCHEMA IF NOT EXISTS `pizzariabd` DEFAULT CHARACTER SET utf8mb4 ;
USE `pizzariabd` ;

-- -----------------------------------------------------
-- Table `pizzariabd`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`admin` (
  `idAdmin` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `pizzariabd`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeCliente` VARCHAR(45) NOT NULL,
  `telefoneCliente` VARCHAR(45) NOT NULL,
  `rua` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `referencia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
-- -----------------------------------------------------
-- Table `pizzariabd`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`fornecedor` (
  `idFornecedor` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeFornecedor` VARCHAR(45) NOT NULL,
  `cnpjFornecedor` VARCHAR(45) NOT NULL,
  `telefoneFornecedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pizzariabd`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`funcionario` (
  `idFuncionario` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(45) NOT NULL,
  `cargoFuncionario` VARCHAR(45) NOT NULL,
  `salarioFuncionario` VARCHAR(45) NOT NULL,
  `cpfFuncionario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pizzariabd`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `precoProduto` DOUBLE NOT NULL,
  `qtdProduto` VARCHAR(45) NOT NULL,
  `Fornecedor_idFornecedor` INT(11) NOT NULL,
  PRIMARY KEY (`idProduto`, `Fornecedor_idFornecedor`),
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`Fornecedor_idFornecedor`)
    REFERENCES `pizzariabd`.`fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `pizzariabd`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzariabd`.`venda` (
  `idVenda` INT(11) NOT NULL AUTO_INCREMENT,
  `valorVenda` VARCHAR(45) NOT NULL,
  `dataVenda` DATE NOT NULL,
  `Produto_idProduto` INT(11) NOT NULL,
  `Funcionario_idFuncionario` INT(11) NOT NULL,
  `Cliente_idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`idVenda`, `Produto_idProduto`, `Funcionario_idFuncionario`, `Cliente_idCliente`),
  CONSTRAINT `fk_Venda_Funcionario1`
    FOREIGN KEY (`Funcionario_idFuncionario`)
    REFERENCES `pizzariabd`.`funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_Produto`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `pizzariabd`.`produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
