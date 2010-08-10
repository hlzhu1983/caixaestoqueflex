<?php
//include 'app/db/BaseDados.php';
include 'app/vo/ProdutoVO.php';

class ServicosProduto {
	
	
	private $conn;
	
	function ServicosProduto() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addProduto(ProdutoVO $item){
		
		$sql = "insert into produto (codBarra,codGrupo
		 ,descricao ,referencia ,codLocal ,codUnidade
		 ,qtdPorProduto ,qtdEmEstoque ,codFornecedor ,precoCompra ,precoVenda,foto) values ('{$item->nome}'		 
		 ,'{$item->codBarra}' ,{$item->codGrupo} ,'{$item->descricao}'
		 ,'{$item->referencia}' ,{$item->codLocal}
		 ,{$item->codUnidade} ,{$item->qtdPorUnidade}
		 ,{$item->qtdEmEstoque} ,{$item->codFornecedor}
		 ,{$item->precoCompra} ,{$item->precoVenda}
		 ,'{$item->foto}')";
		
		$resultado = $this->conn->Execute($sql);
		$item->codigo = $this->conn->insert_Id();
		return $item;
		
	}
	
	public function removerProduto(ProdutoVO $item){
		
		$sql = "delete from produto  where codigo = {$item->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarProduto($texto,$coluna){
		$sql = "select * from produto where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new ProdutoVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->codBarra = $registro->CODBARRA;
			$dados_item->codGrupo = $registro->CODGRUPO;
			$dados_item->descricao = $registro->DESCRICAO;
			$dados_item->referencia = $registro->REFERENCIA;
			$dados_item->codLocal = $registro->CODLOCAL;
			$dados_item->codUnidade = $registro->CODUNIDADE;
			$dados_item->qtdPorUnidade = $registro->QTDPORUNIDADE;
			$dados_item->qtdEmEstoque = $registro->QTDEMESTOQUE;
			$dados_item->codFornecedor = $registro->CODFORNECEDOR;
			$dados_item->precoCompra = $registro->PRECOCOMPRA;
			$dados_item->precoVenda = $registro->PRECOVENDA;
			$dados_item->foto = $registro->FOTO;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;
	}
	
	public function getProdutos(){
		$sql = "select * from produto";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new ProdutoVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->codBarra = $registro->CODBARRA;
			$dados_item->codGrupo = $registro->CODGRUPO;
			$dados_item->descricao = $registro->DESCRICAO;
			$dados_item->referencia = $registro->REFERENCIA;
			$dados_item->codLocal = $registro->CODLOCAL;
			$dados_item->codUnidade = $registro->CODUNIDADE;
			$dados_item->qtdPorUnidade = $registro->QTDPORUNIDADE;
			$dados_item->qtdEmEstoque = $registro->QTDEMESTOQUE;
			$dados_item->codFornecedor = $registro->CODFORNECEDOR;
			$dados_item->precoCompra = $registro->PRECOCOMPRA;
			$dados_item->precoVenda = $registro->PRECOVENDA;
			$dados_item->foto = $registro->FOTO;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;	
	}


	public function atualizarProduto(ProdutoVO $produto) {
		
		$sql = "UPDATE produto SET codBarra = '$produto->codBarra', codGrupo = $produto->codGrupo, descricao = '$produto->descricao', referencia = '$produto->referencia',codLocal = $produto->codLocal,codUnidade=$produto->codUnidade,qtdPorUnidade=$produto->bairro,qtdEmEstoque=$produto->qtdEmEstoque,codFornecedor=$produto->codFornecedor,precoCompra=$produto->precoCompra,
		precoVenda=$produto->precoVenda,foto='$produto->foto' where codigo=$produto->codigo";
			
		$resultado = $this->conn->Execute($sql);
		return $produto;	
		
	}
}
//$eu = new ServicosProduto();
//$c = new ProdutoVO();
//$c->codigo = 47;
//$c->dataCadastro = "2010-09-31";
//$eu->getProdutos();

?>
