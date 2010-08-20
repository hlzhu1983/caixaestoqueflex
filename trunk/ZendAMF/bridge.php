<?php

include 'app/db/BaseDados.php';
include 'app/Erros.php';
include_once 'library/Zend/Amf/Server.php';
include 'app/servicos/ServicosCliente.php';
include 'app/servicos/ServicosPreVenda.php';
include 'app/servicos/ServicosProduto.php';
include 'app/servicos/ServicosFormadePagto.php';
include 'app/servicos/ServicosFornecedor.php';
include 'app/servicos/ServicosUnidade.php';
include 'app/servicos/ServicosGrupoProduto.php';
include 'app/servicos/ServicosLocalProduto.php';
include 'app/servicos/ServicosUsuario.php';
include 'app/servicos/ServicosVenda.php';
include_once 'app/vo/ClienteVO.php';
require_once 'Zend/Amf/Server/Exception.php';



$server = new Zend_Amf_Server();
$server->setClass('ServicosPreVenda');
$server->setClass('ServicosFornecedor');
$server->setClass('ServicosCliente');
$server->setClass('ServicosProduto');
$server->setClass('ServicosUnidade');
$server->setClass('ServicosFormadePagto');
$server->setClass('ServicosGrupoProduto');
$server->setClass('ServicosLocalProduto');
$server->setClass('ServicosUsuario');
$server->setClass('ServicosVenda');
$server->setClassMap('UnidadeVO', 'UnidadeVO');
$server->setClassMap('ClienteVO', 'ClienteVO');

$server->setProduction(false);

echo $server->handle();

//$s = new Servicos();
//$s->getUnidades();

?>