<?php
//include '/home2/pcmaisma/public_html/admProjetos/ZendAMFAdminProjeto/app/db/BaseDados.php';
//include '/home2/pcmaisma/public_html/admProjetos/ZendAMFAdminProjeto/app/vo/ProjetoVO.php';

//include 'app/db/BaseDados.php';
include 'app/vo/PreVendaVO.php';

class ServicosPreVenda {
	
	
	private $conn;
	
	
	function ServicosPreVenda() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	
	public function abrirPreVenda(PreVendaVO $item){
		$sql = "insert into prevenda (codVendedor,status,dataAbertura) values ('{$item->codVendedor}'		 
		 ,'0' ,'now()')";
		$resultado = $this->conn->Execute($sql);
		$item->codigo = $this->conn->insert_Id();
		return $item;
	}
	
	public function addItem(PreVendaVO $item){
		$sql = "insert into projeto (nome,descricao,obs,dataCriacao,dataFinal) values ('{$item->nome}'		 
		 ,'{$item->descricao}' ,'{$item->obs}' ,'{$item->dataCriacao}'
		 ,'{$item->dataFinal}')";
		$resultado = $this->conn->Execute($sql);
		$item->codigo = $this->conn->insert_Id();
		return $item;
		
	}
	
	public function removerItem(PreVendaVO $item){
		
		$sql = "delete from projeto  where codigo = {$item->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarItens($texto,$coluna){
		$sql = "select * from projeto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new PreVendaVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->nome = $registro->NOME;
			$dados_item->descricao = $registro->DESCRICAO;
			$dados_item->obs = $registro->OBS;
			$dados_item->dataCriacao = $registro->DATACRIACAO;
			$dados_item->dataFinal = $registro->DATAFINAL;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;
	}
	
	public function getItens(){
		$sql = "select * from projeto";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new PreVendaVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->nome = $registro->NOME;
			$dados_item->descricao = $registro->DESCRICAO;
			$dados_item->obs = $registro->OBS;
			$dados_item->dataCriacao = $registro->DATACRIACAO;
			$dados_item->dataFinal = $registro->DATAFINAL;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;	
	}


	public function atualizarItem(PreVendaVO $item) {
		
		$sql = "UPDATE projeto SET nome = '$item->nome', descricao = $item->descricao, obs='$item->obs',datafriacao='$item->dataCriacao' ,datafinal='$item->dataFinal' where codigo=$item->codigo";
		$resultado = $this->conn->Execute($sql);
		return $item;	
		
	}
}
//$eu = new ServicosPreVenda();
////$c = new PreVendaVo();
////$c->nome = "Projeto Caixa Estoque Flex";
////$c->dataCadastro = "2010-09-31";
//$eu->getItens();

?>