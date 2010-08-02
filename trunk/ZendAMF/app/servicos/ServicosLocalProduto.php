<?php

include 'app/vo/LocalProdutoVO.php';

class ServicosLocalProduto {
	
	
	private $conn;
	
	function ServicosLocalProduto() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addLocalProduto(LocalProdutoVO $localporduto){
		
		$sql = "insert into localporduto (descricao) values ('{$localporduto->descricao}')";
		
		$resultado = $this->conn->Execute($sql);
		$localporduto->codigo = $this->conn->insert_Id();
		return $localporduto;
		
	}
	
	public function removerLocalProduto(LocalProdutoVO $localporduto){
		
		$sql = "delete from localporduto  where codigo = {$localporduto->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarLocalProduto($texto,$coluna){
		$sql = "select * from localporduto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_localporduto = new LocalProdutoVO();
			$dados_localporduto->codigo = $registro->CODIGO;
			$dados_localporduto->descricao = $registro->DESCRICAO;
			$retorna_dados_localporduto [] = $dados_localporduto;
		}
		return $retorna_dados_localporduto;
	}
	
	public function getLocalProdutos(){
		$sql = "select * from localporduto";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_localporduto = new LocalProdutoVO();
			$dados_localporduto->codigo = $registro->CODIGO;
			$dados_localporduto->descricao = $registro->DESCRICAO;
			$retorna_dados_localporduto [] = $dados_localporduto;
		}
		return $retorna_dados_localporduto;	
	}
}


//$eu = new ServicosLocalProduto();
//$localporduto = new LocalProdutoVO();
//$localporduto->codigo = 2;
//echo $eu->removerLocalProduto($localporduto);

?>