-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Set 14, 2010 as 10:43 
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
  `dataNascimento` datetime NOT NULL,
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
  `limCredito` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `obs` text,
  `dataCadastro` datetime NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `tipoPessoa`, `sexo`, `dataNascimento`, `endereco`, `bairro`, `cidade`, `UF`, `cep`, `cpf_cnpj`, `insc_estadual`, `fone`, `contato`, `email`, `url`, `limCredito`, `obs`, `dataCadastro`) VALUES
(1, 'ASF', 0, 0, '2010-09-13 09:29:31', '', '', '', 'PE', '', '', '', '', '', '', '', '1.00', '', '2010-09-13 09:29:31'),
(2, 'HITALO', 0, 0, '2010-09-13 00:00:00', '', '', '', 'PE', '', '', '', '123456467', '', '', '', '1.00', '', '2010-09-13 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE IF NOT EXISTS `compra` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `NF` int(11) NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `codFornecedor` int(11) NOT NULL,
  `dataCompra` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo`),
  KEY `fk_fornecedorCompra` (`codFornecedor`),
  KEY `fk_usuarioCompra` (`codUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `compra`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `formadepgto`
--

CREATE TABLE IF NOT EXISTS `formadepgto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `formadepgto`
--

INSERT INTO `formadepgto` (`codigo`, `descricao`) VALUES
(0, 'CONFIA'),
(1, 'A VISTA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `formapagamentovenda`
--

CREATE TABLE IF NOT EXISTS `formapagamentovenda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codVenda` int(11) NOT NULL,
  `codFormaPagamento` int(11) NOT NULL,
  `numParcelas` int(11) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codFormaPagamento` (`codFormaPagamento`),
  KEY `codVenda` (`codVenda`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `formapagamentovenda`
--

INSERT INTO `formapagamentovenda` (`codigo`, `codVenda`, `codFormaPagamento`, `numParcelas`, `valor`) VALUES
(3, 24, 1, 1, '2.00'),
(4, 26, 1, 1, '12.00'),
(6, 28, 1, 1, '1.00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codigo`, `nome`, `endereco`, `bairro`, `cidade`, `UF`, `cep`, `cpf_cnpj`, `insc_estadual`, `fone`, `contato`, `email`, `url`, `obs`, `avaliacao`) VALUES
(3, 'HITALO OLIVEIRA DA SILVA', '', '', '', 'PE', '', '', '', '', '', 'HITALOOLIVEIRADASILVA@GMAIL.COM.BR', '', '', ''),
(4, 'EMPRESA A', '', '', '', 'PE', '', '', '', '', '', '', '', '\r\r\r\r', '\r');

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoproduto`
--

CREATE TABLE IF NOT EXISTS `grupoproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descricao` (`descricao`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `grupoproduto`
--

INSERT INTO `grupoproduto` (`codigo`, `descricao`) VALUES
(2, 'PREGO'),
(3, 'VARA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itensprevenda`
--

CREATE TABLE IF NOT EXISTS `itensprevenda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codPrevenda` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `descricao` text NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `itensprevenda_ibfk_1` (`codPrevenda`),
  KEY `itensprevenda_ibfk_2` (`codProduto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Extraindo dados da tabela `itensprevenda`
--

INSERT INTO `itensprevenda` (`codigo`, `codPrevenda`, `codProduto`, `descricao`, `quantidade`, `valor`) VALUES
(3, 2, 1, 'PREGO GRANDE', '1.00', '1.00'),
(4, 2, 1, 'PREGO GRANDE', '1.00', '1.00'),
(6, 4, 1, 'PREGO GRANDE', '1.00', '1.00'),
(7, 4, 1, 'PREGO GRANDE', '1.00', '1.00'),
(8, 4, 1, 'PREGO GRANDE', '10.00', '10.00'),
(12, 7, 1, 'PREGO GRANDE', '1.00', '1.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `localproduto`
--

CREATE TABLE IF NOT EXISTS `localproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descricao` (`descricao`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `localproduto`
--

INSERT INTO `localproduto` (`codigo`, `descricao`) VALUES
(3, 'PRATELEIRA'),
(2, 'PRATELEIRA 1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `prevenda`
--

CREATE TABLE IF NOT EXISTS `prevenda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codUsuario` int(11) NOT NULL,
  `dataAbertura` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `obs` text NOT NULL,
  `valorTotal` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codUsuario_2` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `prevenda`
--

INSERT INTO `prevenda` (`codigo`, `codUsuario`, `dataAbertura`, `status`, `obs`, `valorTotal`) VALUES
(1, 0, '2010-09-09 08:29:54', 2, '', '0.00'),
(2, 0, '2010-09-12 20:36:36', 3, 'null', '2.00'),
(4, 0, '2010-09-12 21:22:51', 3, 'null', '12.00'),
(5, 0, '2010-09-12 21:24:23', 2, 'null', '0.00'),
(6, 0, '2010-09-12 22:09:03', 2, 'null', '0.00'),
(7, 0, '2010-09-12 22:14:50', 3, 'null', '1.00'),
(8, 0, '2010-09-13 00:30:57', 2, 'null', '0.00'),
(9, 0, '2010-09-14 09:32:53', 2, 'null', '0.00');

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
  `qtdEmEstoque` decimal(10,2) unsigned NOT NULL,
  `codFornecedor` int(11) NOT NULL,
  `precoCompra` decimal(10,2) DEFAULT NULL,
  `precoVenda` decimal(10,2) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codGrupo` (`codGrupo`),
  KEY `codLocal` (`codLocal`),
  KEY `codUnidade` (`codUnidade`),
  KEY `codFornecedor` (`codFornecedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `codBarra`, `codGrupo`, `descricao`, `referencia`, `codLocal`, `codUnidade`, `qtdPorUnidade`, `qtdEmEstoque`, `codFornecedor`, `precoCompra`, `precoVenda`, `foto`) VALUES
(0, NULL, 2, 'OUTROS', '', 2, 2, 0, '100.00', 3, NULL, '0.00', NULL),
(1, '', 2, 'PREGO GRANDE', '', 2, 4, 0, '84.00', 3, '1.00', '1.00', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `unidade`
--

CREATE TABLE IF NOT EXISTS `unidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descricao` (`descricao`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `unidade`
--

INSERT INTO `unidade` (`codigo`, `descricao`) VALUES
(4, 'G'),
(2, 'L'),
(3, 'M');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `comissao` decimal(10,3) DEFAULT NULL,
  `senha` varchar(30) NOT NULL,
  `permissao` int(11) NOT NULL,
  `nome` varchar(250) DEFAULT NULL,
  `login` varchar(60) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`codigo`, `comissao`, `senha`, `permissao`, `nome`, `login`) VALUES
(0, '0.000', '1', 0, 'ADMIN', 'ADMIN'),
(15, '0.000', '1', 1, 'VEND', 'VEND'),
(17, '0.000', '1', 1, 'HITALO ', 'HITALO ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE IF NOT EXISTS `venda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codCliente` int(11) DEFAULT NULL,
  `codPreVenda` int(11) DEFAULT NULL,
  `codUsuario` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `dataVenda` datetime NOT NULL,
  `desconto` decimal(10,2) NOT NULL DEFAULT '0.00',
  `valorTotal` decimal(10,2) NOT NULL,
  `obs` text NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codCliente` (`codCliente`),
  KEY `codPreVenda` (`codPreVenda`),
  KEY `codUsuario` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codigo`, `codCliente`, `codPreVenda`, `codUsuario`, `status`, `dataVenda`, `desconto`, `valorTotal`, `obs`) VALUES
(3, NULL, NULL, 15, 2, '2010-09-12 23:43:21', '0.00', '0.00', 'null'),
(4, NULL, NULL, 0, 2, '2010-09-13 00:31:22', '0.00', '0.00', 'null'),
(5, NULL, NULL, 0, 2, '2010-09-13 00:34:30', '0.00', '0.00', 'null'),
(6, NULL, NULL, 0, 2, '2010-09-13 00:38:25', '0.00', '0.00', 'null'),
(7, NULL, NULL, 0, 2, '2010-09-13 00:38:43', '0.00', '0.00', 'null'),
(8, NULL, 7, 0, 0, '2010-09-13 00:41:08', '0.00', '0.00', ''),
(9, NULL, 2, 0, 0, '2010-09-13 00:53:12', '0.00', '0.00', ''),
(10, NULL, NULL, 0, 2, '2010-09-13 00:53:26', '0.00', '0.00', 'null'),
(11, NULL, NULL, 0, 2, '2010-09-13 01:06:43', '0.00', '0.00', 'null'),
(12, NULL, NULL, 0, 2, '2010-09-13 01:12:52', '0.00', '0.00', 'null'),
(13, NULL, NULL, 0, 2, '2010-09-13 01:15:24', '0.00', '0.00', 'null'),
(14, NULL, NULL, 0, 2, '2010-09-13 01:25:16', '0.00', '0.00', 'null'),
(15, NULL, NULL, 0, 2, '2010-09-13 01:31:20', '0.00', '0.00', 'null'),
(16, NULL, NULL, 0, 2, '2010-09-13 01:33:09', '0.00', '0.00', 'null'),
(17, NULL, 2, 0, 0, '2010-09-13 01:35:03', '0.00', '0.00', ''),
(18, NULL, 2, 0, 0, '2010-09-13 01:35:29', '0.00', '0.00', ''),
(19, NULL, NULL, 0, 2, '2010-09-13 01:36:11', '0.00', '0.00', 'null'),
(20, NULL, NULL, 0, 2, '2010-09-13 01:37:29', '0.00', '0.00', 'null'),
(21, NULL, 2, 0, 0, '2010-09-13 01:37:55', '0.00', '0.00', ''),
(22, NULL, NULL, 0, 2, '2010-09-13 01:38:34', '0.00', '0.00', 'null'),
(23, NULL, 2, 0, 0, '2009-08-13 01:39:24', '0.00', '10.00', ''),
(24, 2, NULL, 0, 1, '2009-08-13 06:17:11', '0.00', '20.00', 'null'),
(25, 2, 4, 0, 1, '2010-07-13 06:22:18', '0.00', '35.00', ''),
(26, 1, NULL, 0, 1, '2010-09-13 06:39:03', '0.00', '12.00', 'null'),
(28, 1, 7, 0, 1, '2009-09-13 19:41:02', '0.00', '1.00', 'null');

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_usuarioCompra` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codigo`),
  ADD CONSTRAINT `fk_fornecedorCompra` FOREIGN KEY (`codFornecedor`) REFERENCES `fornecedor` (`codigo`);

--
-- Restrições para a tabela `formapagamentovenda`
--
ALTER TABLE `formapagamentovenda`
  ADD CONSTRAINT `formapagamentovenda_ibfk_1` FOREIGN KEY (`codFormaPagamento`) REFERENCES `formadepgto` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `formapagamentovenda_ibfk_2` FOREIGN KEY (`codVenda`) REFERENCES `venda` (`codigo`) ON UPDATE CASCADE;

--
-- Restrições para a tabela `itensprevenda`
--
ALTER TABLE `itensprevenda`
  ADD CONSTRAINT `itensprevenda_ibfk_1` FOREIGN KEY (`codPrevenda`) REFERENCES `prevenda` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `itensprevenda_ibfk_2` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Restrições para a tabela `prevenda`
--
ALTER TABLE `prevenda`
  ADD CONSTRAINT `prevenda_ibfk_5` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`codGrupo`) REFERENCES `grupoproduto` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`codLocal`) REFERENCES `localproduto` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_3` FOREIGN KEY (`codUnidade`) REFERENCES `unidade` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_ibfk_4` FOREIGN KEY (`codFornecedor`) REFERENCES `fornecedor` (`codigo`) ON UPDATE CASCADE;

--
-- Restrições para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`codPreVenda`) REFERENCES `prevenda` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codigo`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
