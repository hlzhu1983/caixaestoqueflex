<?php
//include '/home2/pcmaisma/public_html/admProjetos/ZendAMFAdminProjeto/app/db/BaseDados.php';
//include '/home2/pcmaisma/public_html/admProjetos/ZendAMFAdminProjeto/app/vo/ProjetoVO.php';

//include 'app/db/BaseDados.php';
include 'app/vo/PreVendaVO.php';
include 'app/vo/ItemPrevendaVO.php';

class ServicosPreVenda {
	
	
	private $conn;
	
	
	function ServicosPreVenda() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	
	public function abrirPreVenda(PreVendaVO $item){
		$sql = "insert into prevenda (codUsuario,status,dataAbertura) values ('$item->codUsuario'		 
		 ,'0' ,now())";
		$this->conn->StartTrans();
		$resultado = $this->conn->Execute($sql);
		$item->codigo = $this->conn->insert_Id();
		
		if($this->conn->HasFailedTrans()){
			throw new Exception("Erro ao abri pré-Venda",16);
		}	
		
		$this->conn->CompleteTrans();
		$item->status = 0;
		
		return $item;
	}
	
	public function addItemPreVenda(ItemPreVendaVO $item){
			
			
			
			$sql ="select * from produto where codigo = $item->codProduto";
			
			$this->conn->StartTrans();
			
			$result = $this->conn->Execute($sql);	
			
			
			if(!$result){
				throw new Exception("Produto não existe",4);
			}
			$registro = $result->FetchNextObject();
			
			
			if($registro->QTDEMESTOQUE < $item->quantidade){
				throw new Exception("Quantidade de produtos maior que disponível!",15);
			}
			
			
			$sql = "insert into itensprevenda (codPrevenda, codProduto, quantidade,valor) 
			      values ('$item->codigoPrevenda','$item->codProduto','$item->quantidade','$item->valor')";
			$this->conn->Execute($sql);
			
					
			$sql = "UPDATE produto SET qtdEmEstoqu = (qtdEmEstoqu - $item->quantidade) where codigo = $item->codProduto";
						
			$result = $this->conn->Execute($sq);
			
			$falhou = false;	
			if($this->conn->HasFailedTrans()){
				$falhou = true;
			}
			
			$this->conn->CompleteTrans();
			
			if($falhou){
				throw new Exception("Erro ao inserir item!",16);
			}
			
			
			return $item;
			
	}
	
	
//	foreach ($item->itemPreVenda as $temp) {
//			$sql = "insert into itensprevenda (codPrevenda, codProduto, quantidade,valor) 
//			      values ('$item->codigo','$temp->codProduto','$temp->quantidade','$temp->valor')";
//			$this->conn->Execute($sql);
//		}
	
	public function fecharPreVenda(PreVendaVO $item){
		if($item->codCliente==0){
			$sql = "UPDATE prevenda SET codUsuario = '$item->codUsuario' , status = '1', obs = '$item->obs' where codigo = $item->codigo";
		}else{
			$sql = "UPDATE prevenda SET codUsuario = '$item->codUsuario' , codCliente = '$item->codCliente', status = '1', obs = '$item->obs' where codigo = $item->codigo";	
		}
		$this->conn->Execute($sql);
		return $item;
	}
	
	public function cancelarPreVenda(PreVendaVO $item){
		$sql = "UPDATE prevenda SET codUsuario = '$item->codUsuario' , status = '2', obs = '$item->obs' where codigo = $item->codigo";
		$this->conn->Execute($sql);
		return $item;
	}
	
	
	public function removerItem(PreVendaVO $item){
		
		$sql = "delete from prevenda  where codigo = $item->codigo";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarItens($texto,$coluna){
		$sql = "select * from prevenda where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new PreVendaVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->codCliente = $registro->CODCLIENTE;
			$dados_item->codUsuario = $registro->CODUSUARIO;
			$dados_item->obs = $registro->OBS;
			$dados_item->status = $registro->STATUS;
			$dados_item->dataAbertura = $registro->DATAABERTURA;
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
			$dados_item->codCliente = $registro->CODCLIENTE;
			$dados_item->codUsuario = $registro->CODUSUARIO;
			$dados_item->obs = $registro->OBS;
			$dados_item->status = $registro->STATUS;
			$dados_item->dataAbertura = $registro->DATAABERTURA;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;	
	}
}
//$eu = new ServicosPreVenda();
////$c = new PreVendaVo();
////$c->codUsuario = 0;
//$eu->addItemPreVenda(new ItemPreVendaVO());

?>