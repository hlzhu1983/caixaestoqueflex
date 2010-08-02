<?php

include 'app/vo/GrupoProdutoVO.php';

class ServicosGrupoProduto {
	
	
	private $conn;
	
	function ServicosGrupoProduto() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addGrupoProduto(GrupoProdutoVO $grupoproduto){
		
		$sql = "insert into grupoproduto (descricao) values ('{$grupoproduto->descricao}')";
		
		$resultado = $this->conn->Execute($sql);
		$grupoproduto->codigo = $this->conn->insert_Id();
		return $grupoproduto;
		
	}
	
	public function removerGrupoProduto(GrupoProdutoVO $grupoproduto){
		
		$sql = "delete from grupoproduto  where codigo = {$grupoproduto->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarGrupoProduto($texto,$coluna){
		$sql = "select * from grupoproduto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_grupoproduto = new GrupoProdutoVO();
			$dados_grupoproduto->codigo = $registro->CODIGO;
			$dados_grupoproduto->descricao = $registro->DESCRICAO;
			$retorna_dados_grupoproduto [] = $dados_grupoproduto;
		}
		return $retorna_dados_grupoproduto;
	}
	
	public function getGrupoProdutos(){
		$sql = "select * from grupoproduto";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_grupoproduto = new GrupoProdutoVO();
			$dados_grupoproduto->codigo = $registro->CODIGO;
			$dados_grupoproduto->descricao = $registro->DESCRICAO;
			$retorna_dados_grupoproduto [] = $dados_grupoproduto;
		}
		return $retorna_dados_grupoproduto;	
	}
}


//$eu = new ServicosGrupoProduto();
//$grupoproduto = new GrupoProdutoVO();
//$grupoproduto->codigo = 2;
//echo $eu->removerGrupoProduto($grupoproduto);

?>