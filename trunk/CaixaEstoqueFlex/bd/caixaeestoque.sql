-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Ago 02, 2010 as 01:13 
-- Versão do Servidor: 5.1.41
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
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `tipoPessoa` int(1) NOT NULL,
  `sexo` int(1) NOT NULL,
  `dataNascimento` date NOT NULL,
  `endereco` varchar(250) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `UF` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cpf_cnpj` varchar(18) NOT NULL,
  `insc_estadual` varchar(15) DEFAULT NULL,
  `fone` varchar(60) NOT NULL,
  `contato` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `url` varchar(150) DEFAULT NULL,
  `obs` text,
  `dataCadastro` date NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `tipoPessoa`, `sexo`, `dataNascimento`, `endereco`, `bairro`, `cidade`, `UF`, `cep`, `cpf_cnpj`, `insc_estadual`, `fone`, `contato`, `email`, `url`, `obs`, `dataCadastro`) VALUES
(40, 'Hitalo Oliveira da silva', 0, 0, '1989-05-05', 'Av tempo feliz 47', 'Sancho', 'Recife', 'PE', '50920700', '11111', '11111111', '87403495', 'Hitalo Oliveira', 'hitalo', '', 'O cara mais lindo do mundo', '2010-08-01'),
(41, 'anderson xrilo', 0, 0, '1988-04-02', '', '', '', 'PE', '', '', '', '', '', '', '', '', '2010-08-01'),
(42, 'vvvv', 0, 0, '2010-08-04', '', '', '', 'PE', '', '', '', '', '', '', '', '', '2010-08-01'),
(43, 'vvvvvvvvvv', 0, 0, '2010-08-19', '', '', '', 'PE', '', '', '', '', '', '', '', '', '2010-08-11');

-- --------------------------------------------------------

--
-- Estrutura da tabela `formadepgto`
--

CREATE TABLE IF NOT EXISTS `formadepgto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `formadepgto`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `endereco` varchar(250) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `UF` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cpf_cnpj` varchar(18) NOT NULL,
  `insc_estadual` varchar(15) DEFAULT NULL,
  `fone` varchar(60) NOT NULL,
  `contato` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `url` varchar(150) DEFAULT NULL,
  `obs` text,
  `avaliacao` text,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codigo`, `nome`, `endereco`, `bairro`, `cidade`, `UF`, `cep`, `cpf_cnpj`, `insc_estadual`, `fone`, `contato`, `email`, `url`, `obs`, `avaliacao`) VALUES
(1, '', '', '', '', '', '', '', '', '', '', '', '', '', ''),
(5, '', '', '', '', '', '', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoproduto`
--

CREATE TABLE IF NOT EXISTS `grupoproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `grupoproduto`
--

INSERT INTO `grupoproduto` (`codigo`, `descricao`) VALUES
(2, 'bebidas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `localporduto`
--

CREATE TABLE IF NOT EXISTS `localporduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `localporduto`
--

INSERT INTO `localporduto` (`codigo`, `descricao`) VALUES
(1, 'hitalo');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `codigo` int(30) NOT NULL AUTO_INCREMENT,
  `codBarra` varchar(20) DEFAULT NULL,
  `codGrupo` int(11) NOT NULL,
  `descricao` text,
  `referencia` varchar(250) NOT NULL,
  `codLocal` int(11) NOT NULL,
  `codUnidade` int(11) NOT NULL,
  `qtdPorUnidade` int(11) NOT NULL,
  `qtdEmEstoque` int(11) NOT NULL,
  `codFornecedor` int(11) NOT NULL,
  `precoCompra` decimal(10,2) DEFAULT NULL,
  `precoVenda` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codGrupo` (`codGrupo`),
  KEY `codLocal` (`codLocal`),
  KEY `codUnidade` (`codUnidade`),
  KEY `codFornecedor` (`codFornecedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `codBarra`, `codGrupo`, `descricao`, `referencia`, `codLocal`, `codUnidade`, `qtdPorUnidade`, `qtdEmEstoque`, `codFornecedor`, `precoCompra`, `precoVenda`) VALUES
(12, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(13, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(14, 'aAA', 2, 'asdÃ§kj', 'sdfg', 1, 1, 10, 1, 1, '1.00', '1.00'),
(15, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(16, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(17, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(18, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(19, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00'),
(20, '', 2, '', '', 1, 1, 1, 1, 1, '1.00', '1.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `unidade`
--

CREATE TABLE IF NOT EXISTS `unidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `unidade`
--

INSERT INTO `unidade` (`codigo`, `descricao`) VALUES
(1, 'M'),
(9, 'Kg');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `num_caixa` int(11) DEFAULT NULL,
  `comissao` decimal(10,3) DEFAULT NULL,
  `senha` varchar(30) NOT NULL,
  `permissao` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
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
