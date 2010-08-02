<?php

include 'app/db/BaseDados.php';
include_once 'library/Zend/Amf/Server.php';
include_once 'app/servicos/ServicosCliente.php';
include 'app/servicos/ServicosUnidade.php';
include_once 'app/vo/ClienteVO.php';



$server = new Zend_Amf_Server();


$server->setClass('ServicosCliente');
$server->setClass('ServicosUnidade');
$server->setClass('ServicosFormadePgto');
$server->setClass('ServicosGrupoProduto');
$server->setClass('ServicosLocalProduto');
//$server->setClassMap('UnidadeVO', 'UnidadeVO');
$server->setClassMap('ClienteVO', 'ClienteVO');

echo $server->handle();

//$s = new Servicos();
//$s->getUnidades();

?>