<?php

include 'app/vo/UnidadeVO.php';

class ServicosUnidade {
	
	
	private $conn;
	
	function ServicosUnidade() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addUnidade(UnidadeVO $unidade){
		
		$sql = "insert into unidade (descricao) values ('{$unidade->descricao}')";
		
		$resultado = $this->conn->Execute($sql);
		$unidade->codigo = $this->conn->insert_Id();
		return $unidade;
		
	}
	
	public function removerUnidade(UnidadeVO $unidade){
		
		$sql = "delete from unidade  where codigo = {$unidade->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarUnidade($texto,$coluna){
		$sql = "select * from unidade where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_unidade = new UnidadeVO();
			$dados_unidade->codigo = $registro->CODIGO;
			$dados_unidade->descricao = $registro->DESCRICAO;
			$retorna_dados_unidade [] = $dados_unidade;
		}
		return $retorna_dados_unidade;
	}
	
	public function getUnidades(){
		$sql = "select * from unidade";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_unidade = new UnidadeVO();
			$dados_unidade->codigo = $registro->CODIGO;
			$dados_unidade->descricao = $registro->DESCRICAO;
			$retorna_dados_unidade [] = $dados_unidade;
		}
		return $retorna_dados_unidade;	
	}
}


//$eu = new ServicosUnidade();
//$unidade = new UnidadeVO();
//$unidade->codigo = 2;
//echo $eu->removerUnidade($unidade);

?>