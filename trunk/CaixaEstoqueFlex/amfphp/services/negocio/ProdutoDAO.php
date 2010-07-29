<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class ProdutoDAO extends DAO {
	
	function ProdutoDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($codBarra, $codGrupo, $descricao, $referencia, $codLocal, $codUnidade, $qtdPorUnidade, $qtdEmEstoque, $codFornecedor, $precoCompra, $precoVenda) {
		$sql = "INSERT INTO  produto (codBarra ,
			codGrupo ,
			descricao ,
			referencia ,
			codLocal ,
			codUnidade ,
			qtdPorUnidade ,
			qtdEmEstoque ,
			codFornecedor ,
			precoCompra ,
			precoVenda
			) VALUES ('" . $codBarra . "','$codGrupo','" . $descricao . "','" . $referencia . "'," . $codLocal . "," . $codUnidade . "," . $qtdPorUnidade . "," . $qtdEmEstoque . "," . $codFornecedor . "," . $precoCompra . "," . $precoVenda . ")";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir novo produto" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM produto where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir produto" );
		}
		
		$this->banco->desconecta ();
	}
	
	public function procurarPorDescricao($descricao) {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from produto where descricao='" . $descricao . "'";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_produtos ['dados'] = "";
		$dados_produtos ['dados'] [0] ['codigo'] = "";
		$dados_produtos ['dados'] [0] ['codBarra'] = "";
		$dados_produtos ['dados'] [0] ['codGrupo'] = "";
		$dados_produtos ['dados'] [0] ['descricao'] = "";
		$dados_produtos ['dados'] [0] ['referencia'] = "";
		$dados_produtos ['dados'] [0] ['codLocal'] = "";
		$dados_produtos ['dados'] [0] ['codUnidade'] = "";
		$dados_produtos ['dados'] [0] ['qtdPorUnidade'] = "";
		$dados_produtos ['dados'] [0] ['qtdEmEstoque'] = "";
		$dados_produtos ['dados'] [0] ['codFornecedor'] = "";
		$dados_produtos ['dados'] [0] ['precoCompra'] = "";
		$dados_produtos ['dados'] [0] ['precoVenda'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
				$dados_produtos ['dados'] [$posicao] ['codigo'] = $linha ['codigo'];
				$dados_produtos ['dados'] [$posicao] ['codBarra'] = $linha ['codBarra'];
				$dados_produtos ['dados'] [$posicao] ['codGrupo'] = $linha ['codGrupo'];
				$dados_produtos ['dados'] [$posicao] ['descricao'] = $linha ['descricao'];
				$dados_produtos ['dados'] [$posicao] ['referencia'] = $linha ['referencia'];
				$dados_produtos ['dados'] [$posicao] ['codLocal'] = $linha ['codLocal'];
				$dados_produtos ['dados'] [$posicao] ['codUnidade'] = $linha ['codUnidade'];
				$dados_produtos ['dados'] [$posicao] ['qtdPorUnidade'] = $linha ['qtdPorUnidade'];
				$dados_produtos ['dados'] [$posicao] ['qtdEmEstoque'] = $linha ['qtdEmEstoque'];
				$dados_produtos ['dados'] [$posicao] ['codFornecedor'] = $linha ['codFornecedor'];
				$dados_produtos ['dados'] [$posicao] ['precoCompra'] = $linha ['precoCompra'];
				$dados_produtos ['dados'] [$posicao] ['precoVenda'] = $linha ['precoVenda'];
				$posicao ++;
			}
			$this->banco->desconecta ();
			return $dados_produtos;
		} else {
			throw new Exception ( utf8_decode ( "Não existem produtos com este nome" ) );
		}
	
	}
	
	public function procurar($codigo) {
		
		$sql = "select * from produto where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_produtos ['dados'] = "";
		$dados_produtos ['dados'] ['codigo'] = "";
		$dados_produtos ['dados'] ['codBarra'] = "";
		$dados_produtos ['dados'] ['codGrupo'] = "";
		$dados_produtos ['dados'] ['descricao'] = "";
		$dados_produtos ['dados'] ['referencia'] = "";
		$dados_produtos ['dados'] ['codLocal'] = "";
		$dados_produtos ['dados'] ['codUnidade'] = "";
		$dados_produtos ['dados'] ['qtdPorUnidade'] = "";
		$dados_produtos ['dados'] ['qtdEmEstoque'] = "";
		$dados_produtos ['dados'] ['codFornecedor'] = "";
		$dados_produtos ['dados'] ['precoCompra'] = "";
		$dados_produtos ['dados'] ['precoVenda'] = "";
		
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				$entrou = 0;
				$dados_produtos ['dados'] ['codigo'] = $linha ['codigo'];
				$dados_produtos ['dados'] ['codBarra'] = $linha ['codBarra'];
				$dados_produtos ['dados'] ['codGrupo'] = $linha ['codGrupo'];
				$dados_produtos ['dados'] ['descricao'] = $linha ['descricao'];
				$dados_produtos ['dados'] ['referencia'] = $linha ['referencia'];
				$dados_produtos ['dados'] ['codLocal'] = $linha ['codLocal'];
				$dados_produtos ['dados'] ['codUnidade'] = $linha ['codUnidade'];
				$dados_produtos ['dados'] ['qtdPorUnidade'] = $linha ['qtdPorUnidade'];
				$dados_produtos ['dados'] ['qtdEmEstoque'] = $linha ['qtdEmEstoque'];
				$dados_produtos ['dados'] ['codFornecedor'] = $linha ['codFornecedor'];
				$dados_produtos ['dados'] ['precoCompra'] = $linha ['precoCompra'];
				$dados_produtos ['dados'] ['precoVenda'] = $linha ['precoVenda'];
			
			}
			$this->banco->desconecta ();
			
			if (!isset($entrou)) {
				throw new Exception ( utf8_decode ( "Não existem produtos com este codigo" ) );
			}
			
			return $dados_produtos;
		
		} else {
			throw new Exception ( utf8_decode ( "Não existem produtos com este codigo" ) );
		}
	
	}
	
	public function getprodutos() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from produto";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_produtos ['dados'] = "";
		$dados_produtos ['dados'] [0] ['codigo'] = "";
		$dados_produtos ['dados'] [0] ['codBarra'] = "";
		$dados_produtos ['dados'] [0] ['codGrupo'] = "";
		$dados_produtos ['dados'] [0] ['descricao'] = "";
		$dados_produtos ['dados'] [0] ['referencia'] = "";
		$dados_produtos ['dados'] [0] ['codLocal'] = "";
		$dados_produtos ['dados'] [0] ['codUnidade'] = "";
		$dados_produtos ['dados'] [0] ['qtdPorUnidade'] = "";
		$dados_produtos ['dados'] [0] ['qtdEmEstoque'] = "";
		$dados_produtos ['dados'] [0] ['codFornecedor'] = "";
		$dados_produtos ['dados'] [0] ['precoCompra'] = "";
		$dados_produtos ['dados'] [0] ['precoVenda'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
				$dados_produtos ['dados'] [$posicao] ['codigo'] = $linha ['codigo'];
				$dados_produtos ['dados'] [$posicao] ['codBarra'] = $linha ['codBarra'];
				$dados_produtos ['dados'] [$posicao] ['codGrupo'] = $linha ['codGrupo'];
				$dados_produtos ['dados'] [$posicao] ['descricao'] = $linha ['descricao'];
				$dados_produtos ['dados'] [$posicao] ['referencia'] = $linha ['referencia'];
				$dados_produtos ['dados'] [$posicao] ['codLocal'] = $linha ['codLocal'];
				$dados_produtos ['dados'] [$posicao] ['codUnidade'] = $linha ['codUnidade'];
				$dados_produtos ['dados'] [$posicao] ['qtdPorUnidade'] = $linha ['qtdPorUnidade'];
				$dados_produtos ['dados'] [$posicao] ['qtdEmEstoque'] = $linha ['qtdEmEstoque'];
				$dados_produtos ['dados'] [$posicao] ['codFornecedor'] = $linha ['codFornecedor'];
				$dados_produtos ['dados'] [$posicao] ['precoCompra'] = $linha ['precoCompra'];
				$dados_produtos ['dados'] [$posicao] ['precoVenda'] = $linha ['precoVenda'];
				$posicao ++;
			}
			$this->banco->desconecta ();
			return $dados_produtos;
		} else {
			throw new Exception ( "Não existem produtos com este nome" );
		}
	
	}
	
	public function atualizar($codigo, $nome, $tipoPessoa, $sexo, $dataNascimento, $endereco, $bairro, $cidade, $UF, $cep, $cpf_cnpj, $insc_estadual, $fone, $contato, $email, $url, $obs) {
		if ($dataNascimento == "") {
			$dataNascimento = "0000-00-00";
		}
		$sql = "UPDATE produto SET nome = '$nome', tipoPessoa = $tipoPessoa, sexo = $sexo, dataNascimento = '$dataNascimento',endereco='$endereco',bairro='$bairro',cidade='$cidade',UF='$UF',cep='$cep',
		cpf_cnpj='$cpf_cnpj',insc_estadual='$insc_estadual',fone='$fone',contato='$contato',email='$email',url='$url',obs='$obs' where codigo=$codigo";
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao atualizar  produto" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$produto = new ProdutoDAO();

//$produto->inserir("", 2, "","", 1, 1, 1, 1, 1,1, 1);
//$produto->procurar(1);
?>