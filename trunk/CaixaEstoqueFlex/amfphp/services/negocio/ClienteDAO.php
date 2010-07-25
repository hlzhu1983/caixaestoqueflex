<?php
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/DAO.class.php");
include (dirname ( dirname ( __FILE__ ) ) . "/arquitetura/BancoDeDados.php");
class ClienteDAO extends DAO {
	
	function ClienteDAO() {
		$this->banco = new BancoDeDados ( dirname ( dirname ( __FILE__ ) ) . "/arquitetura/base.xml" );
	
	}
	
	public function inserir($tipoPessoa,$nome, $sexo, $dataNascimento, $endereco, $bairro, $cidade, $UF, $cep, $cpf_cnpj, $insc_estadual, $fone, $contato, $email, $URL, $obs) {
		if ($dataNascimento == "") {
			$dataNascimento = "0000-00-00";
		}
		
		$sql = "INSERT INTO cliente (tipoPessoa,nome,sexo,dataNascimento,endereco,bairro,cidade,UF,cep,
		cpf_cnpj,insc_estadual,fone,contato,email,url,obs) VALUES (" . $tipoPessoa .",'$nome'," . $sexo . ",'" . $dataNascimento . "','" . $endereco . "','" . $bairro . "','" . $cidade . "','" . $UF . "','" . $cep . "','" . $cpf_cnpj . "','" . $insc_estadual . "','" . $fone . "','" . $contato . "','" . $email . "','" . $URL . "','" . $obs . "')";
		$this->banco->conecta ();
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao inserir novo cliente" );
		}
		$this->banco->desconecta ();
	}
	
	public function remover($codigo) {
		$sql = "DELETE FROM cliente where codigo=$codigo";
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ( $sql )) {
			throw new Exception ( "Erro ao excluir cliente" );
		}
		
		$this->banco->desconecta ();
	}
	
	public function procurarPorNome($nome) {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from cliente where nome='" . $nome . "'";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_clientes ['dados'] = "";
		$dados_clientes ['dados'] [0] ['codigo'] = "";
		$dados_clientes ['dados'] [0] ['nome'] = "";
		$dados_clientes ['dados'] [0] ['tipoPessoa'] = "";
		$dados_clientes ['dados'] [0] ['sexo'] = "";
		$dados_clientes ['dados'] [0] ['dataNascimento'] = "";
		$dados_clientes ['dados'] [0] ['endereco'] = "";
		$dados_clientes ['dados'] [0] ['bairro'] = "";
		$dados_clientes ['dados'] [0] ['cidade'] = "";
		$dados_clientes ['dados'] [0] ['UF'] = "";
		$dados_clientes ['dados'] [0] ['cep'] = "";
		$dados_clientes ['dados'] [0] ['cpf_cnpj'] = "";
		$dados_clientes ['dados'] [0] ['insc_estadual'] = "";
		$dados_clientes ['dados'] [0] ['fone'] = "";
		$dados_clientes ['dados'] [0] ['contato'] = "";
		$dados_clientes ['dados'] [0] ['email'] = "";
		$dados_clientes ['dados'] [0] ['url'] = "";
		$dados_clientes ['dados'] [0] ['obs'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_clientes ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_clientes ['dados'] [$posicao] ['nome'] = $linha['nome'];
		$dados_clientes ['dados'] [$posicao] ['tipoPessoa'] = $linha['tipoPessoa'];
		$dados_clientes ['dados'] [$posicao] ['sexo'] = $linha['sexo'];
		$dados_clientes ['dados'] [$posicao] ['dataNascimento'] = $linha['dataNascimento'];
		$dados_clientes ['dados'] [$posicao] ['endereco'] = $linha['endereco'];
		$dados_clientes ['dados'] [$posicao] ['bairro'] = $linha['bairro'];
		$dados_clientes ['dados'] [$posicao] ['cidade'] = $linha['cidade'];
		$dados_clientes ['dados'] [$posicao] ['UF'] = $linha['UF'];
		$dados_clientes ['dados'] [$posicao] ['cep'] = $linha['cep'];
		$dados_clientes ['dados'] [$posicao] ['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_clientes ['dados'] [$posicao] ['insc_estadual'] = $linha['insc_estadual'];
		$dados_clientes ['dados'] [$posicao] ['fone'] = $linha['fone'];
		$dados_clientes ['dados'] [$posicao] ['contato'] = $linha['contato'];
		$dados_clientes ['dados'] [$posicao] ['email'] = $linha['email'];
		$dados_clientes ['dados'] [$posicao] ['url'] = $linha['url'];
		$dados_clientes ['dados'] [$posicao] ['obs'] = $linha['obs'];
					$posicao ++;
			}
			$this->banco->desconecta();
		return $dados_clientes;
		} else {
			throw new Exception ( utf8_decode("Não existem clientes com este nome") );
		}
	
	}
	
	public function procurar($codigo) {
		
		$sql = "select * from cliente where codigo=$codigo";
		
		$this->banco->conecta ();
		
		$dados_clientes ['dados'] = "";
		$dados_clientes ['dados']['codigo'] = "";
		$dados_clientes ['dados']['nome'] = "";
		$dados_clientes ['dados']['tipoPessoa'] = "";
		$dados_clientes ['dados']['sexo'] = "";
		$dados_clientes ['dados']['dataNascimento'] = "";
		$dados_clientes ['dados']['endereco'] = "";
		$dados_clientes ['dados']['bairro'] = "";
		$dados_clientes ['dados']['cidade'] = "";
		$dados_clientes ['dados']['UF'] = "";
		$dados_clientes ['dados']['cep'] = "";
		$dados_clientes ['dados']['cpf_cnpj'] = "";
		$dados_clientes ['dados']['insc_estadual'] = "";
		$dados_clientes ['dados']['fone'] = "";
		$dados_clientes ['dados']['contato'] = "";
		$dados_clientes ['dados']['email'] = "";
		$dados_clientes ['dados']['url'] = "";
		$dados_clientes ['dados']['obs'] = "";
		
		$resposta = $this->banco->executaSQL ( $sql );
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
			
		$dados_clientes ['dados']['codigo'] = $linha['codigo'];
		$dados_clientes ['dados']['nome'] = $lina['nome'];
		$dados_clientes ['dados']['tipoPessoa'] = $linha['tipoPessoa'];
		$dados_clientes ['dados']['sexo'] = $linha['sexo'];
		$dados_clientes ['dados']['dataNascimento'] = $linha['dataNascimento'];
		$dados_clientes ['dados']['endereco'] = $linha['endereco'];
		$dados_clientes ['dados']['bairro'] = $linha['bairro'];
		$dados_clientes ['dados']['cidade'] = $linha['cidade'];
		$dados_clientes ['dados']['UF'] = $linha['UF'];
		$dados_clientes ['dados']['cep'] = $linha['cep'];
		$dados_clientes ['dados']['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_clientes ['dados']['insc_estadual'] = $linha['insc_estadual'];
		$dados_clientes ['dados']['fone'] = $linha['fone'];
		$dados_clientes ['dados']['contato'] = $linha['contato'];
		$dados_clientes ['dados']['email'] = $linha['email'];
		$dados_clientes ['dados']['url'] = $linha['url'];
		$dados_clientes ['dados']['obs'] = $linha['obs'];
								
			}
		$this->banco->desconecta();	
		
		if($linha==false){
			throw new Exception ( utf8_decode("Não existem clientes com este codigo") );
		}
		
		return $dados_clientes;
		
		} else {
			throw new Exception ( utf8_decode("Não existem clientes com este codigo") );
		}
	
	}
	
	public function getClientes() {
		$banco = $this->getBancoDeDados ();
		$sql = "select * from cliente";
		//conxao banco
		$this->banco->conecta ();
		
		$resposta = $banco->executaSQL ( $sql );
		$dados_clientes ['dados'] = "";
		$dados_clientes ['dados'] [0] ['codigo'] = "";
		$dados_clientes ['dados'] [0] ['nome'] = "";
		$dados_clientes ['dados'] [0] ['tipoPessoa'] = "";
		$dados_clientes ['dados'] [0] ['sexo'] = "";
		$dados_clientes ['dados'] [0] ['dataNascimento'] = "";
		$dados_clientes ['dados'] [0] ['endereco'] = "";
		$dados_clientes ['dados'] [0] ['bairro'] = "";
		$dados_clientes ['dados'] [0] ['cidade'] = "";
		$dados_clientes ['dados'] [0] ['UF'] = "";
		$dados_clientes ['dados'] [0] ['cep'] = "";
		$dados_clientes ['dados'] [0] ['cpf_cnpj'] = "";
		$dados_clientes ['dados'] [0] ['insc_estadual'] = "";
		$dados_clientes ['dados'] [0] ['fone'] = "";
		$dados_clientes ['dados'] [0] ['contato'] = "";
		$dados_clientes ['dados'] [0] ['email'] = "";
		$dados_clientes ['dados'] [0] ['url'] = "";
		$dados_clientes ['dados'] [0] ['obs'] = "";
		
		$posicao = 0;
		if ($resposta != null) {
			while ( $linha = mysql_fetch_array ( $resposta ) ) {
				
		$dados_clientes ['dados'] [$posicao] ['codigo'] = $linha['codigo'];
		$dados_clientes ['dados'] [$posicao] ['nome'] = $linha['nome'];
		$dados_clientes ['dados'] [$posicao] ['tipoPessoa'] = $linha['tipoPessoa'];
		$dados_clientes ['dados'] [$posicao] ['sexo'] = $linha['sexo'];
		$dados_clientes ['dados'] [$posicao] ['dataNascimento'] = $linha['dataNascimento'];
		$dados_clientes ['dados'] [$posicao] ['endereco'] = $linha['endereco'];
		$dados_clientes ['dados'] [$posicao] ['bairro'] = $linha['bairro'];
		$dados_clientes ['dados'] [$posicao] ['cidade'] = $linha['cidade'];
		$dados_clientes ['dados'] [$posicao] ['UF'] = $linha['UF'];
		$dados_clientes ['dados'] [$posicao] ['cep'] = $linha['cep'];
		$dados_clientes ['dados'] [$posicao] ['cpf_cnpj'] = $linha['cpf_cnpj'];
		$dados_clientes ['dados'] [$posicao] ['insc_estadual'] = $linha['insc_estadual'];
		$dados_clientes ['dados'] [$posicao] ['fone'] = $linha['fone'];
		$dados_clientes ['dados'] [$posicao] ['contato'] = $linha['contato'];
		$dados_clientes ['dados'] [$posicao] ['email'] = $linha['email'];
		$dados_clientes ['dados'] [$posicao] ['url'] = $linha['url'];
		$dados_clientes ['dados'] [$posicao] ['obs'] = $linha['obs'];
					$posicao ++;
			}
		$this->banco->desconecta();
			return $dados_clientes;
		} else {
			throw new Exception ( "Não existem clientes com este nome" );
		}
	
	}
	
	public function atualizar($codigo,$nome, $tipoPessoa, $sexo, $dataNascimento, $endereco, $bairro, $cidade, $UF, $cep, $cpf_cnpj, $insc_estadual, $fone, $contato, $email, $url, $obs) {
	if($dataNascimento==""){$dataNascimento="0000-00-00";}
		$sql = "UPDATE cliente SET nome = '$nome', tipoPessoa = $tipoPessoa, sexo = $sexo, dataNascimento = '$dataNascimento',endereco='$endereco',bairro='$bairro',cidade='$cidade',UF='$UF',cep='$cep',
		cpf_cnpj='$cpf_cnpj',insc_estadual='$insc_estadual',fone='$fone',contato='$contato',email='$email',url='$url',obs='$obs' where codigo=$codigo";
		
		
		$this->banco->conecta ();
		
		if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao atualizar  cliente" );
		}
		$this->banco->desconecta ();
	}
}
//teste insercao
//$cliente = new ClienteDAO();
//$cliente->atualizar(31,"anderson murilo",1,1,"","","","","","","","","","","","","");
//$cliente->inserir(0,"murilo",1,"","","","","","","","","","","","","");
//$cliente->procurar(1);
?>