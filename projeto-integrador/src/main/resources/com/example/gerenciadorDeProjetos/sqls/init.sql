SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE TABLE IF NOT EXISTS `NivelDeAcesso` (
  `idNivelDeAcesso` INT NOT NULL AUTO_INCREMENT,
  `tipoDeAcesso` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `permissoes` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idNivelDeAcesso`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `IdNivelDeAcesso` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  INDEX `fk_Funcionario_NivelDeAcesso1_idx` (`IdNivelDeAcesso`),
  CONSTRAINT `fk_Funcionario_NivelDeAcesso1`
    FOREIGN KEY (`IdNivelDeAcesso`)
    REFERENCES `NivelDeAcesso` (`idNivelDeAcesso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Projeto` (
  `idProjeto` INT NOT NULL AUTO_INCREMENT,
  `nomeProjeto` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataTermino` DATE NOT NULL,
  PRIMARY KEY (`idProjeto`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Documentos` (
  `idDocumentação` INT NOT NULL AUTO_INCREMENT,
  `idFuncionario` INT NOT NULL,
  `idProjeto` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `documento` BLOB NULL,
  PRIMARY KEY (`idDocumentação`),
  INDEX `fk_Documentação_Projeto1_idx` (`idProjeto`),
  CONSTRAINT `fk_Documentação_Projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Documentação_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Tarefa` (
  `idTarefa` INT NOT NULL AUTO_INCREMENT,
  `nomeDaTarefa` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `status` VARCHAR(25) NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataTermino` DATE NOT NULL,
  PRIMARY KEY (`idTarefa`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `TarefaFuncionario` (
  `idFuncionario` INT NOT NULL,
  `idTarefa` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`, `idTarefa`),
  INDEX `fk_Funcionario_has_Tarefa_Tarefa1_idx` (`idTarefa`),
  INDEX `fk_Funcionario_has_Tarefa_Funcionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_Funcionario_has_Tarefa_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_has_Tarefa_Tarefa1`
    FOREIGN KEY (`idTarefa`)
    REFERENCES `Tarefa` (`idTarefa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ProjetoTarefa` (
  `idTarefa` INT NOT NULL,
  `idProjeto` INT NOT NULL,
  PRIMARY KEY (`idTarefa`, `idProjeto`),
  INDEX `fk_Tarefa_has_Projeto_Projeto1_idx` (`idProjeto`),
  INDEX `fk_Tarefa_has_Projeto_Tarefa1_idx` (`idTarefa`),
  CONSTRAINT `fk_Tarefa_has_Projeto_Tarefa1`
    FOREIGN KEY (`idTarefa`)
    REFERENCES `Tarefa` (`idTarefa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tarefa_has_Projeto_Projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ProjetoFuncionário` (
  `idFuncionario` INT NOT NULL,
  `idProjeto` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`, `idProjeto`),
  INDEX `fk_Funcionario_has_Projeto_Projeto1_idx` (`idProjeto`),
  INDEX `fk_Funcionario_has_Projeto_Funcionario1_idx` (`idFuncionario`),
  CONSTRAINT `fk_Funcionario_has_Projeto_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_has_Projeto_Projeto1`
    FOREIGN KEY (`idProjeto`)
    REFERENCES `Projeto` (`idProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
