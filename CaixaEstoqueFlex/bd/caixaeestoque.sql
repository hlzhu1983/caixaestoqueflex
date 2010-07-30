-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Jul 24, 2010 as 12:22 
-- Versão do Servidor: 5.0.86
-- Versão do PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `caixaeestoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL auto_increment,
  `tipoPessoa` int(1) NOT NULL,
  `sexo` int(1) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  `endereco` varchar(250) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `UF` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cpf_cnpj` varchar(18) NOT NULL,
  `insc_estadual` varchar(15) default NULL,
  `fone` varchar(60) NOT NULL,
  `contato` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `url` varchar(150) default NULL,
  `obs` text,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `cliente`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `formadepgto`
--

CREATE TABLE IF NOT EXISTS `formadepgto` (
  `codigo` int(11) NOT NULL auto_increment,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `formadepgto`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `codigo` int(11) NOT NULL auto_increment,
  `endereco` varchar(250) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `UF` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cpf_cnpj` varchar(18) NOT NULL,
  `insc_estadual` varchar(15) default NULL,
  `fone` varchar(60) NOT NULL,
  `contato` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `url` varchar(150) default NULL,
  `obs` text,
  `avaliacao` text,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `fornecedor`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoproduto`
--

CREATE TABLE IF NOT EXISTS `grupoproduto` (
  `codigo` int(11) NOT NULL auto_increment,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `grupoproduto`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `localporduto`
--

CREATE TABLE IF NOT EXISTS `localporduto` (
  `codigo` int(11) NOT NULL auto_increment,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `localporduto`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `codigo` int(30) NOT NULL auto_increment,
  `codBarra` varchar(20) default NULL,
  `codGrupo` int(11) NOT NULL,
  `descricao` text,
  `referencia` varchar(250) NOT NULL,
  `codLocal` int(11) NOT NULL,
  `codUnidade` int(11) NOT NULL,
  `qtdPorUnidade` int(11) NOT NULL,
  `qtdEmEstoque` int(11) NOT NULL,
  `codFornecedor` int(11) NOT NULL,
  `precoCompra` decimal(10,2) default NULL,
  `precoVenda` decimal(10,2) NOT NULL,
  PRIMARY KEY  (`codigo`),
  KEY `codGrupo` (`codGrupo`),
  KEY `codLocal` (`codLocal`),
  KEY `codUnidade` (`codUnidade`),
  KEY `codFornecedor` (`codFornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `produto`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `unidade`
--

CREATE TABLE IF NOT EXISTS `unidade` (
  `codigo` int(11) NOT NULL auto_increment,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `unidade`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` int(11) NOT NULL auto_increment,
  `num_caixa` int(11) default NULL,
  `comissao` decimal(10,3) default NULL,
  `senha` varchar(30) NOT NULL,
  `permissao` int(11) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `usuario`
--


--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`codGrupo`) REFERENCES `grupoproduto` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`codLocal`) REFERENCES `localporduto` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_3` FOREIGN KEY (`codUnidade`) REFERENCES `unidade` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_4` FOREIGN KEY (`codFornecedor`) REFERENCES `fornecedor` (`codigo`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;