<?php

include 'app/db/BaseDados.php';
include_once 'library/Zend/Amf/Server.php';
include 'app/servicos/ServicosCliente.php';
include 'app/servicos/ServicosProduto.php';
include 'app/servicos/ServicosFormadePagto.php';
include 'app/servicos/ServicosFornecedor.php';
include 'app/servicos/ServicosUnidade.php';
include 'app/servicos/ServicosGrupoProduto.php';
include 'app/servicos/ServicosLocalProduto.php';
include 'app/servicos/ServicosUsuario.php';
include_once 'app/vo/ClienteVO.php';




$server = new Zend_Amf_Server();

$server->setClass('ServicosFornecedor');
$server->setClass('ServicosCliente');
$server->setClass('ServicosProduto');
$server->setClass('ServicosUnidade');
$server->setClass('ServicosFormadePagto');
$server->setClass('ServicosGrupoProduto');
$server->setClass('ServicosLocalProduto');
$server->setClass('ServicosUsuario');
//$server->setClassMap('UnidadeVO', 'UnidadeVO');
$server->setClassMap('ClienteVO', 'ClienteVO');

echo $server->handle();

//$s = new Servicos();
//$s->getUnidades();

?>