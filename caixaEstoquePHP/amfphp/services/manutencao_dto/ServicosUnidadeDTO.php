<?php

	include_once('../../adodb/adodb.inc.php');
	include_once('../vo/dto/DadosUnidadeDTO.php');

	class ServicosUnidadeDTO{	

		
		function ServicosUnidadeDTO() {
			$tipo_banco    = "mysql";
		    $servidor      = "localhost";
		    $usuario       = "root";
		    $senha         = "root";
			$dbName            = "caixaeestoque";

			global $db;
			$db  = NewADOConnection($tipo_banco);			
			$db->Connect($servidor,$usuario,$senha,$dbName);
		}
		
		public function insertUnidade(DadosUnidadeDTO $dados_unidade){
			global $db;
			$sql = "insert into unidade (descricao) values('{$dados_unidade->descricao}')";
			$resultado = $db->Execute($sql);
			$codigo = $db->insert_Id();
			$dados_unidade->codigo = $codigo;
			return $dados_unidade;			
		}
		
		public function removerUnidade(DadosUnidadeDTO $dados_unidade){
			global $db;
			$sql = "delete from unidade where codigo = {$dados_unidade->codigo}";
			$resultado = $db->Execute($sql);
			return $dados_unidade;			
		}
	}
	
//$servico = new ServicosUnidadeDTO();
//$servico->insertUnidade("hitalo");

?>