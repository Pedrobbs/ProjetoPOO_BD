-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Caixa` (
  `id` INT NOT NULL,
  `lucro` DOUBLE NOT NULL,
  `Funcionario_cpf` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `cpf` VARCHAR(20) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `gasto` DOUBLE NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto` (
  `idProduto` INT NOT NULL,
  `custo` DOUBLE NOT NULL,
  `preco` DOUBLE NOT NULL,
  `Caixa_id` INT DEFAULT NULL,
  `Cliente_cpf` VARCHAR(20) DEFAULT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produto_Caixa1_idx` (`Caixa_id` ASC) VISIBLE,
  INDEX `fk_Produto_Cliente1_idx` (`Cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Caixa1`
    FOREIGN KEY (`Caixa_id`)
    REFERENCES `mydb`.`Caixa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Cliente1`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `mydb`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fornecedor` (
  `cnpj` VARCHAR(20) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `Caixa_id` INT NOT NULL,
  PRIMARY KEY (`cnpj`),
  INDEX `fk_Fornecedor_Caixa_idx` (`Caixa_id` ASC) VISIBLE,
  CONSTRAINT `fk_Fornecedor_Caixa`
    FOREIGN KEY (`Caixa_id`)
    REFERENCES `mydb`.`Caixa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Login` (
  `idLogin` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLogin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcionario` (
  `cpf` VARCHAR(20) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `salario` DOUBLE NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `Caixa_id` INT,
  `Login_idLogin` INT DEFAULT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `fk_Funcionario_Caixa1_idx` (`Caixa_id` ASC) VISIBLE,
  INDEX `fk_Funcionario_Login1_idx` (`Login_idLogin` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Caixa1`
    FOREIGN KEY (`Caixa_id`)
    REFERENCES `mydb`.`Caixa` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Login1`
    FOREIGN KEY (`Login_idLogin`)
    REFERENCES `mydb`.`Login` (`idLogin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente_has_Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente_has_Funcionario` (
  `Cliente_cpf` VARCHAR(20) NOT NULL,
  `Funcionario_cpf` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Cliente_cpf`, `Funcionario_cpf`),
  INDEX `fk_Cliente_has_Funcionario_Funcionario1_idx` (`Funcionario_cpf` ASC) VISIBLE,
  INDEX `fk_Cliente_has_Funcionario_Cliente1_idx` (`Cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_has_Funcionario_Cliente1`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `mydb`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_has_Funcionario_Funcionario1`
    FOREIGN KEY (`Funcionario_cpf`)
    REFERENCES `mydb`.`Funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Avulso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Avulso` (
  `idAvulso` INT NOT NULL,
  `Cliente_cpf` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idAvulso`),
  INDEX `fk_Avulso_Cliente1_idx` (`Cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Avulso_Cliente1`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `mydb`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fidelidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fidelidade` (
  `idFidelidade` INT NOT NULL,
  `Cliente_cpf` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idFidelidade`),
  INDEX `fk_Fidelidade_Cliente1_idx` (`Cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Fidelidade_Cliente1`
    FOREIGN KEY (`Cliente_cpf`)
    REFERENCES `mydb`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

select * from login;
select * from funcionario;
select * from produto;
select * from fornecedor;
select * from cliente;
select * from avulso;
select * from fidelidade;
select * from caixa;