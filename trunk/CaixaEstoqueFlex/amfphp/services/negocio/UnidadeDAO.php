<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class UnidadeDAO extends DAO {
	
	function UnidadeDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($descricao) {
		
		$sql = "INSERT INTO unidade (descricao) VALUES ('$descricao')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir nova Unidade" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM unidade where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir unidade" );
		}
		
		$this->banco->desconecta ();
	}
	
	
	public function procurar($codigo) {
		
		$sql = "select * from unidade where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_unidades ['dados'] = "";
		$dados_unidades ['dados']['codigo'] = "";
		$dados_unidades ['dados']['descricao'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			$entrou=0;
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			$entrou=1;
		$dados_unidades ['dados']['codigo'] = $linha['codigo'];
		$dados_unidades ['dados']['descricao'] = $linha['descricao'];
								
			}
		$this->banco->desconecta();	
		
		if($entrou==0){
			throw new Exception ( utf8_decode("Não existem unidadess com este codigo") );
		}
		
		return $dados_unidades;
		
		} else {
			throw new Exception ( utf8_decode("Não existem unidades com este codigo") );
		}
	
	}
	
	public function getunidades() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from unidade";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_unidades ['dados'] = "";
		$dados_unidades ['dados'] [0] ['codigo'] = "";
		$dados_unidades ['dados'] [0] ['descricao'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_unidades ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_unidades ['dados'] [$posicao] ['descricao'] = $linha['descricao'];
					$posicao ++;
			}
		$this->banco->desconecta();
			return $dados_unidades;
		} else {
			throw new Exception ( "Não existem unidades com este nome" );
		}
	
	}
	
	public function atualizar($codigo,$descricao){
		$sql = "UPDATE unidade SET descricao='$descricao' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar unidade" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$unidade = new UnidadeDAO();
//$unidade->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$unidade->inserir(0,"murilo",1,"","","","","","","","","","","","","");
//$unidade->procurar(1);
?>