<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class FornecedorDAO extends DAO {
	
	function FornecedorDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($nome, $endereco, $bairro, $cidade, $UF, $cep, $cpf_cnpj, $insc_estadual, $fone, $contato, $email, $URL, $obs,$avaliacao) {
		
		
		$sql = "INSERT INTO fornecedor (nome,endereco,bairro,cidade,UF,cep,
		cpf_cnpj,insc_estadual,fone,contato,email,url,obs,avaliacao) VALUES ('$nome','" . $endereco . "','" . $bairro . "','" . $cidade . "','" . $UF . "','" . $cep . "','" . $cpf_cnpj . "','" . $insc_estadual . "','" . $fone . "','" . $contato . "','" . $email . "','" . $URL . "','" . $obs . "','$avaliacao')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir novo fornecedor" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM fornecedor where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir fornecedor" );
		}
		
		$this->banco->desconecta ();
	}
	
	public function procurarPorNome($nome) {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from fornecedor where nome='" . $nome . "'";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_fornecedors ['dados'] = "";
		$dados_fornecedors ['dados'] [0] ['codigo'] = "";
		$dados_fornecedors ['dados'] [0] ['nome'] = "";
		$dados_fornecedors ['dados'] [0] ['endereco'] = "";
		$dados_fornecedors ['dados'] [0] ['bairro'] = "";
		$dados_fornecedors ['dados'] [0] ['cidade'] = "";
		$dados_fornecedors ['dados'] [0] ['UF'] = "";
		$dados_fornecedors ['dados'] [0] ['cep'] = "";
		$dados_fornecedors ['dados'] [0] ['cpf_cnpj'] = "";
		$dados_fornecedors ['dados'] [0] ['insc_estadual'] = "";
		$dados_fornecedors ['dados'] [0] ['fone'] = "";
		$dados_fornecedors ['dados'] [0] ['contato'] = "";
		$dados_fornecedors ['dados'] [0] ['email'] = "";
		$dados_fornecedors ['dados'] [0] ['url'] = "";
		$dados_fornecedors ['dados'] [0] ['obs'] = "";
		$dados_fornecedors ['dados'] [0] ['avaliacao'] = "";
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_fornecedors ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_fornecedors ['dados'] [$posicao] ['nome'] = $linha['nome'];
		$dados_fornecedors ['dados'] [$posicao] ['endereco'] = $linha['endereco'];
		$dados_fornecedors ['dados'] [$posicao] ['bairro'] = $linha['bairro'];
		$dados_fornecedors ['dados'] [$posicao] ['cidade'] = $linha['cidade'];
		$dados_fornecedors ['dados'] [$posicao] ['UF'] = $linha['UF'];
		$dados_fornecedors ['dados'] [$posicao] ['cep'] = $linha['cep'];
		$dados_fornecedors ['dados'] [$posicao] ['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_fornecedors ['dados'] [$posicao] ['insc_estadual'] = $linha['insc_estadual'];
		$dados_fornecedors ['dados'] [$posicao] ['fone'] = $linha['fone'];
		$dados_fornecedors ['dados'] [$posicao] ['contato'] = $linha['contato'];
		$dados_fornecedors ['dados'] [$posicao] ['email'] = $linha['email'];
		$dados_fornecedors ['dados'] [$posicao] ['url'] = $linha['url'];
		$dados_fornecedors ['dados'] [$posicao] ['obs'] = $linha['obs'];
        $dados_fornecedors ['dados'] [0] ['avaliacao'] = $linha['avaliacao'];
		$posicao ++;
			}
			$this->banco->desconecta();
		return $dados_fornecedors;
		} else {
			throw new Exception ( utf8_decode("Não existem fornecedors com este nome") );
		}
	
	}
	
	public function procurar($codigo) {
		
		$sql = "select * from fornecedor where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_fornecedors ['dados'] = "";
		$dados_fornecedors ['dados']['codigo'] = "";
		$dados_fornecedors ['dados']['nome'] = "";
		$dados_fornecedors ['dados']['endereco'] = "";
		$dados_fornecedors ['dados']['bairro'] = "";
		$dados_fornecedors ['dados']['cidade'] = "";
		$dados_fornecedors ['dados']['UF'] = "";
		$dados_fornecedors ['dados']['cep'] = "";
		$dados_fornecedors ['dados']['cpf_cnpj'] = "";
		$dados_fornecedors ['dados']['insc_estadual'] = "";
		$dados_fornecedors ['dados']['fone'] = "";
		$dados_fornecedors ['dados']['contato'] = "";
		$dados_fornecedors ['dados']['email'] = "";
		$dados_fornecedors ['dados']['url'] = "";
		$dados_fornecedors ['dados']['obs'] = "";
		$dados_fornecedors ['dados']['avaliacao'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			
		$dados_fornecedors ['dados']['codigo'] = $linha['codigo'];
		$dados_fornecedors ['dados']['nome'] = $lina['nome'];
		$dados_fornecedors ['dados']['tipoPessoa'] = $linha['tipoPessoa'];
		$dados_fornecedors ['dados']['sexo'] = $linha['sexo'];
		$dados_fornecedors ['dados']['dataNascimento'] = $linha['dataNascimento'];
		$dados_fornecedors ['dados']['endereco'] = $linha['endereco'];
		$dados_fornecedors ['dados']['bairro'] = $linha['bairro'];
		$dados_fornecedors ['dados']['cidade'] = $linha['cidade'];
		$dados_fornecedors ['dados']['UF'] = $linha['UF'];
		$dados_fornecedors ['dados']['cep'] = $linha['cep'];
		$dados_fornecedors ['dados']['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_fornecedors ['dados']['insc_estadual'] = $linha['insc_estadual'];
		$dados_fornecedors ['dados']['fone'] = $linha['fone'];
		$dados_fornecedors ['dados']['contato'] = $linha['contato'];
		$dados_fornecedors ['dados']['email'] = $linha['email'];
		$dados_fornecedors ['dados']['url'] = $linha['url'];
		$dados_fornecedors ['dados']['obs'] = $linha['obs'];
		$dados_fornecedors ['dados']['avaliacao'] = $linha['avaliacao'];						
			}
		$this->banco->desconecta();	
		
		if($linha==false){
			throw new Exception ( utf8_decode("Não existem fornecedors com este codigo") );
		}
		
		return $dados_fornecedors;
		
		} else {
			throw new Exception ( utf8_decode("Não existem fornecedors com este codigo") );
		}
	
	}
	
	public function getfornecedors() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from fornecedor";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_fornecedors ['dados'] = "";
		$dados_fornecedors ['dados'] [0] ['codigo'] = "";
		$dados_fornecedors ['dados'] [0] ['nome'] = "";
		$dados_fornecedors ['dados'] [0] ['endereco'] = "";
		$dados_fornecedors ['dados'] [0] ['bairro'] = "";
		$dados_fornecedors ['dados'] [0] ['cidade'] = "";
		$dados_fornecedors ['dados'] [0] ['UF'] = "";
		$dados_fornecedors ['dados'] [0] ['cep'] = "";
		$dados_fornecedors ['dados'] [0] ['cpf_cnpj'] = "";
		$dados_fornecedors ['dados'] [0] ['insc_estadual'] = "";
		$dados_fornecedors ['dados'] [0] ['fone'] = "";
		$dados_fornecedors ['dados'] [0] ['contato'] = "";
		$dados_fornecedors ['dados'] [0] ['email'] = "";
		$dados_fornecedors ['dados'] [0] ['url'] = "";
		$dados_fornecedors ['dados'] [0] ['obs'] = "";
		$dados_fornecedors ['dados'] [0] ['avaliacao'] = "";
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_fornecedors ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_fornecedors ['dados'] [$posicao] ['nome'] = $linha['nome'];
		$dados_fornecedors ['dados'] [$posicao] ['endereco'] = $linha['endereco'];
		$dados_fornecedors ['dados'] [$posicao] ['bairro'] = $linha['bairro'];
		$dados_fornecedors ['dados'] [$posicao] ['cidade'] = $linha['cidade'];
		$dados_fornecedors ['dados'] [$posicao] ['UF'] = $linha['UF'];
		$dados_fornecedors ['dados'] [$posicao] ['cep'] = $linha['cep'];
		$dados_fornecedors ['dados'] [$posicao] ['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_fornecedors ['dados'] [$posicao] ['insc_estadual'] = $linha['insc_estadual'];
		$dados_fornecedors ['dados'] [$posicao] ['fone'] = $linha['fone'];
		$dados_fornecedors ['dados'] [$posicao] ['contato'] = $linha['contato'];
		$dados_fornecedors ['dados'] [$posicao] ['email'] = $linha['email'];
		$dados_fornecedors ['dados'] [$posicao] ['url'] = $linha['url'];
		$dados_fornecedors ['dados'] [$posicao] ['obs'] = $linha['obs'];
		$dados_fornecedors ['dados'] [0] ['avaliacao'] = $linha['avaliacao'];
					$posicao ++;
			}
		$this->banco->desconecta();
			return $dados_fornecedors;
		} else {
			throw new Exception ( "Não existem fornecedors com este nome" );
		}
	
	}
	
	public function atualizar($codigo,$nome, $endereco, $bairro, $cidade, $UF, $cep, $cpf_cnpj, $insc_estadual, $fone, $contato, $email, $url, $obs,$avaliacao) {
	if($dataNascimento==""){$dataNascimento="0000-00-00";}
		$sql = "UPDATE fornecedor SET nome = '$nome',endereco='$endereco',bairro='$bairro',cidade='$cidade',UF='$UF',cep='$cep',
		cpf_cnpj='$cpf_cnpj',insc_estadual='$insc_estadual',fone='$fone',contato='$contato',email='$email',url='$url',obs='$obs',avaliacao='$avaliacao' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar  fornecedor" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$fornecedor = new FornecedorDAO();
//$fornecedor->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$fornecedor->inserir("","", "", "", "", "","","","","", "","","","");
//$fornecedor->procurar(1);
?>