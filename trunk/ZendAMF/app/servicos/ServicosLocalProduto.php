<?php

include 'app/vo/LocalProdutoVO.php';

class ServicosLocalProduto {
	
	
	private $conn;
	
	function ServicosLocalProduto() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addLocalProduto(LocalProdutoVO $localproduto){
		
		$sql = "insert into localproduto (descricao) values ('{$localproduto->descricao}')";
		$resultado = $this->conn->Execute($sql);
		$localproduto->codigo = $this->conn->insert_Id();
		return $localproduto;
		
	}
	
	public function atualizarLocalProduto(LocalProdutoVO $localproduto){
		$f = fopen('log1.txt','w+');
		fwrite($f,'entroue');
		$sql = "update localproduto SET descricao = '$localproduto->descricao' WHERE codigo = $localproduto->codigo";
		fwrite($f,$sql);
		$resultado = $this->conn->Execute($sql);
		return $localproduto;
		
	}
	
	public function removerLocalProduto(LocalProdutoVO $localproduto){
		$sql = "delete from localproduto  where codigo = {$localproduto->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarLocalProduto($texto,$coluna){
		$sql = "select * from localproduto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_localproduto = new LocalProdutoVO();
			$dados_localproduto->codigo = $registro->CODIGO;
			$dados_localproduto->descricao = $registro->DESCRICAO;
			$retorna_dados_localproduto [] = $dados_localproduto;
		}
		return $retorna_dados_localproduto;
	}
	
	public function getLocalProdutos(){
		$sql = "select * from localproduto";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_localproduto = new LocalProdutoVO();
			$dados_localproduto->codigo = $registro->CODIGO;
			$dados_localproduto->descricao = $registro->DESCRICAO;
			$retorna_dados_localproduto [] = $dados_localproduto;
		}
		return $retorna_dados_localproduto;	
	}
}


//$eu = new ServicosLocalProduto();
//$localproduto = new LocalProdutoVO();
//$localproduto->codigo = 2;
//echo $eu->removerLocalProduto($localproduto);

?>