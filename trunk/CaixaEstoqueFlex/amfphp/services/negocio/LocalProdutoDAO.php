<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class LocalProdutoDAO extends DAO {
	
	function LocalProdutoDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($descricao) {
		
		$sql = "INSERT INTO localporduto (descricao) VALUES ('$descricao')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir nova localporduto" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM localporduto where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir localporduto" );
		}
		
		$this->banco->desconecta ();
	}
	
	
	public function procurar($codigo) {
		
		$sql = "select * from localporduto where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_localpordutos ['dados'] = "";
		$dados_localpordutos ['dados']['codigo'] = "";
		$dados_localpordutos ['dados']['descricao'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			$entrou=0;
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			$entrou=1;
		$dados_localpordutos ['dados']['codigo'] = $linha['codigo'];
		$dados_localpordutos ['dados']['descricao'] = $linha['descricao'];
								
			}
		$this->banco->desconecta();	
		
		if($entrou==0){
			throw new Exception ( utf8_decode("Não existem localpordutoss com este codigo") );
		}
		
		return $dados_localpordutos;
		
		} else {
			throw new Exception ( utf8_decode("Não existem localpordutos com este codigo") );
		}
	
	}
	
	public function getlocalpordutos() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from localporduto";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_localpordutos ['dados'] = "";
		$dados_localpordutos ['dados'] [0] ['codigo'] = "";
		$dados_localpordutos ['dados'] [0] ['descricao'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_localpordutos ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_localpordutos ['dados'] [$posicao] ['descricao'] = $linha['descricao'];
					$posicao ++;
			}
		$this->banco->desconecta();
			return $dados_localpordutos;
		} else {
			throw new Exception ( "Não existem localpordutos com este nome" );
		}
	
	}
	
	public function atualizar($codigo,$descricao){
		$sql = "UPDATE localporduto SET descricao='$descricao' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar localporduto" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$localporduto = new LocalProdutoDAO();
//$localporduto->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$localporduto->inserir(0,"murilo",1,"","","","","","","","","","","","","");
//$localporduto->procurar(1);
?>