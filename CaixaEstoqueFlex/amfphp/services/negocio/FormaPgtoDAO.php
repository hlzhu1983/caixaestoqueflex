<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class FormaPgtoDAO extends DAO {
	
	function FormaPgtoDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($descricao) {
		
		$sql = "INSERT INTO formadepgto (descricao) VALUES ('$descricao')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir nova formadepgto" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM formadepgto where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir formadepgto" );
		}
		
		$this->banco->desconecta ();
	}
	
	
	public function procurar($codigo) {
		
		$sql = "select * from formadepgto where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_formadepgtos ['dados'] = "";
		$dados_formadepgtos ['dados']['codigo'] = "";
		$dados_formadepgtos ['dados']['descricao'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {

			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			$entrou=1;
		$dados_formadepgtos ['dados']['codigo'] = $linha['codigo'];
		$dados_formadepgtos ['dados']['descricao'] = $linha['descricao'];
								
			}
		$this->banco->desconecta();	
		
			if(!isset($entrou)){
				throw new Exception ( "Não existem formadepgtos com este nome" );
			}
		
		return $dados_formadepgtos;
		
		} else {
			throw new Exception ( utf8_decode("Não existem formadepgtos com este codigo") );
		}
	
	}
	
	public function getformadepgtos() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from formadepgto";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_formadepgtos ['dados'] = "";
		$dados_formadepgtos ['dados'] [0] ['codigo'] = "";
		$dados_formadepgtos ['dados'] [0] ['descricao'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				$entrou = 0;
		$dados_formadepgtos ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_formadepgtos ['dados'] [$posicao] ['descricao'] = $linha['descricao'];
					$posicao ++;
			}
		
		$this->banco->desconecta();
			return $dados_formadepgtos;
		} else {
			throw new Exception ( "Não existem formadepgtos com este nome" );
		}
		
		
	
	}
	
	public function atualizar($codigo,$descricao){
		
		$sql = "UPDATE formadepgto SET descricao='$descricao' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar formadepgto" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$formadepgto = new FormaPgtoDAO();
//$formadepgto->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$formadepgto->inserir(0,"murilo",1,"","","","","","","","","","","","","");
//$formadepgto->procurar(1);
?>