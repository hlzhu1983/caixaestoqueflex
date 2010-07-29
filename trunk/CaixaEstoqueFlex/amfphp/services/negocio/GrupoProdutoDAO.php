<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class GrupoProdutoDAO extends DAO {
	
	function GrupoProdutoDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($descricao) {
		
		$sql = "INSERT INTO grupoproduto (descricao) VALUES ('$descricao')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir nova grupoproduto" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM grupoproduto where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir grupoproduto" );
		}
		
		$this->banco->desconecta ();
	}
	
	
	public function procurar($codigo) {
		
		$sql = "select * from grupoproduto where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_grupoprodutos ['dados'] = "";
		$dados_grupoprodutos ['dados']['codigo'] = "";
		$dados_grupoprodutos ['dados']['descricao'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			$entrou=0;
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			$entrou=1;
		$dados_grupoprodutos ['dados']['codigo'] = $linha['codigo'];
		$dados_grupoprodutos ['dados']['descricao'] = $linha['descricao'];
								
			}
		$this->banco->desconecta();	
		
		if($entrou==0){
			throw new Exception ( utf8_decode("Não existem grupoprodutoss com este codigo") );
		}
		
		return $dados_grupoprodutos;
		
		} else {
			throw new Exception ( utf8_decode("Não existem grupoprodutos com este codigo") );
		}
	
	}
	
	public function getgrupoprodutos() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from grupoproduto";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_grupoprodutos ['dados'] = "";
		$dados_grupoprodutos ['dados'] [0] ['codigo'] = "";
		$dados_grupoprodutos ['dados'] [0] ['descricao'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_grupoprodutos ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_grupoprodutos ['dados'] [$posicao] ['descricao'] = $linha['descricao'];
					$posicao ++;
			}
		$this->banco->desconecta();
			return $dados_grupoprodutos;
		} else {
			throw new Exception ( "Não existem grupoprodutos com este nome" );
		}
	
	}
	
	public function atualizar($codigo,$descricao){
		$sql = "UPDATE grupoproduto SET descricao='$descricao' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar grupoproduto" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$grupoproduto = new GrupoProdutoDAO();
//$grupoproduto->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$grupoproduto->inserir(0,"murilo",1,"","","","","","","","","","","","","");
//$grupoproduto->remover(2);
?>