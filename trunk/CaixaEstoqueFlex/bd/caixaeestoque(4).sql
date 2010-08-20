-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Ago 20, 2010 as 10:50 
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `cliente`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `formapagamentovenda`
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codigo`, `nome`, `endereco`, `bairro`, `cidade`, `UF`, `cep`, `cpf_cnpj`, `insc_estadual`, `fone`, `contato`, `email`, `url`, `obs`, `avaliacao`) VALUES
(1, 'EMPRESA A', 'AVENIDA A', 'A', 'A', 'PE', '50920700', '00000000000000', '0000000000000', '000', '000', '000', '00', '00000', 'GASG');

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
(1, 'PREGO'),
(2, 'ceramicas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itensprevenda`
--

CREATE TABLE IF NOT EXISTS `itensprevenda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codPrevenda` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `itensprevenda_ibfk_1` (`codPrevenda`),
  KEY `itensprevenda_ibfk_2` (`codProduto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=85 ;

--
-- Extraindo dados da tabela `itensprevenda`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `localproduto`
--

CREATE TABLE IF NOT EXISTS `localproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `localproduto`
--

INSERT INTO `localproduto` (`codigo`, `descricao`) VALUES
(1, 'PRATILEIRA 1');

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
  `desconto` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`codigo`),  
  KEY `codUsuario_2` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=50 ;

--
-- Extraindo dados da tabela `prevenda`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `codBarra`, `codGrupo`, `descricao`, `referencia`, `codLocal`, `codUnidade`, `qtdPorUnidade`, `qtdEmEstoque`, `codFornecedor`, `precoCompra`, `precoVenda`, `foto`) VALUES
(2, '1111', 2, 'CERAMICA BRANCA', 'ASÃ‡LDFIJ', 1, 3, 1, '0.50', 1, '1.00', '1.00', ''),
(3, '', 1, 'NOVO PRODUTO', '1 X 1', 1, 2, 0, '14.00', 1, '0.10', '1.25', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `unidade`
--

CREATE TABLE IF NOT EXISTS `unidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `unidade`
--

INSERT INTO `unidade` (`codigo`, `descricao`) VALUES
(1, 'M'),
(2, 'M2'),
(3, 'UN');

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
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`codigo`, `comissao`, `senha`, `permissao`, `nome`, `login`) VALUES
(1, '30.500', 'admin', 4, 'ADMIN', 'ADMIN'),
(2, '0.000', 'A', 1, 'JULIANA', 'JULIANA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE IF NOT EXISTS `venda` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codCliente` int(11) DEFAULT NULL,
  `codPreVenda` int(11) DEFAULT NULL,
  `codOrcamento` int(11) DEFAULT NULL,
  `tipoImport` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `venda`
--


--
-- Restrições para as tabelas dumpadas
--

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
  ADD CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`codPreVenda`) REFERENCES `prevenda` (`codigo`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
