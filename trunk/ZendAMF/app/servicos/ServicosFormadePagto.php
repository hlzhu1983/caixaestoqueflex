<?php

include 'app/vo/FormadePagtoVO.php';

class ServicosFormadePagto {
	
	
	private $conn;
	
	function ServicosFormadePagto() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addFormadePagto(FormadePagtoVO $formadepagto){
	 	
		$sql = "insert into formadepgto (descricao) values ('{$formadepagto->descricao}')";
		
		$resultado = $this->conn->Execute($sql);
		$formadepagto->codigo = $this->conn->insert_Id();
		return $formadepagto;
		
	}
	
	public function removerFormadePagto(FormadePagtoVO $formadepagto){
		
		$sql = "delete from formadepgto  where codigo = {$formadepagto->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function getFormadePagto($texto,$coluna){
		$sql = "select * from formadepgto where $coluna = '$texto'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){
			if($registro->CODIGO !=0){			
				$retorna_dados_formadepagto [] = $this->toFormaPgto($registro);
			}
		}
		return $retorna_dados_formadepagto;
	}
	
	public function getConfia($texto,$coluna){
		$sql = "select * from formadepgto where $coluna = '$texto'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){
			$dados_formadepagto = new FormadePagtoVO();
			$dados_formadepagto->codigo = $registro->CODIGO;
			$dados_formadepagto->descricao = $registro->DESCRICAO;			
		}
		return $dados_formadepagto;
	}
	
	
	public function pesquisarFormadePagto($texto,$coluna){
		$sql = "select * from formadepgto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){
			if($registro->CODIGO !=0){			
				$retorna_dados_formadepagto [] = $this->toFormaPgto($registro);
			}
		}
		return $retorna_dados_formadepagto;
	}
	
	public function getFormadePagtos(){
		$sql = "select * from formadepgto";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){
			if($registro->CODIGO !=0){				
				$retorna_dados_formadepagto [] = $this->toFormaPgto($registro);
			}
		}
		return $retorna_dados_formadepagto;	
	}
	
	
	private function toFormaPgto($registro){
		$dados_formadepagto = new FormadePagtoVO();
		$dados_formadepagto->codigo = $registro->CODIGO;
		$dados_formadepagto->descricao = $registro->DESCRICAO;
		return $dados_formadepagto;
	}
}


//$eu = new ServicosFormadePagto();
//$formadepagto = new FormadePagtoVO();
//$formadepagto->codigo = 2;
//echo $eu->removerFormadePagto($formadepagto);

?>