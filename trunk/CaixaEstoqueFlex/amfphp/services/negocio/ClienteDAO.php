<?php 
include(dirname(dirname(__FILE__))."/arquitetura/DAO.class.php");
include(dirname(dirname(__FILE__))."/arquitetura/BancoDeDados.php");
class ClienteDAO extends DAO{
	
	
	function ClienteDAO() {
  		$this->banco = new BancoDeDados ( dirname(dirname(__FILE__))."/arquitetura/base.xml" );
	
	}
	
	public function inserir($tipoPessoa,$sexo,$dataNascimento,$endereco,$bairro,$cidade,$UF,
	$cep,$cpf_cnpj,$insc_estadual,$fone,$contato,$email,$URL,$obs){
		if($dataNascimento==""){$dataNascimento = "0000-00-00";}
		
		
		
		$sql = "INSERT INTO cliente (tipoPessoa,sexo,dataNascimento,endereco,bairro,cidade,UF,cep,
		cpf_cnpj,insc_estadual,fone,contato,email,url,obs) VALUES (".$tipoPessoa.",".$sexo.",'".$dataNascimento."','"
		.$endereco."','"
		.$bairro."','"
		.$cidade."','"
		.$UF."','"
		.$cep."','"
		.$cpf_cnpj."','"
		.$insc_estadual."','"
		.$fone."','"
		.$contato."','"
		.$email."','"
		.$URL."','"
		.$obs."')";
		$this->banco->conecta();
	if (! $this->banco->UpdateSQL ($sql)) {
			throw new Exception ( "Erro ao inserir novo cliente" . "<br>" );
		}
	$this->banco->desconecta();	
	}
	
	
	public function remover($codigo)
	{
		$sql = "DELETE FROM TABLE cliente where codigo=".$codigo;
	$this->banco->conecta();	
	$resposta = $banco->executaSQL ( $sql ) or die ( "Não foi possivel remover a mensagem" );
	}
	
	public function getCliente($codigo){
		
		
	}
	
	public function getClientes(){
		
	}
	public function alterar($codigo,$tipoPessoa,$sexo,$dataNascimento,$endereco,$bairro,$cidade,$UF,
	$cep,$cpf_cnpj,$insc_estadual,$fone,$contato,$email,$URL,$obs){
	$sql = "UPDATE TABLE cliente set tipoPessoa = $tipoPessoa,sexo = $sexo,dataNascimento = '$dataNascimento',endereco='$endereco',bairro='$bairro',cidade='$cidade',UF='$UF',cep='$cep',
		cpf_cnpj='$cpf_cnpj',insc_estadual='$insc_estadual',fone='$fone',contato='$contato',email='$email',url='$url',obs='$obs' where codigo=$codigo)";	
	 $this->banco->conecta();
	$resposta = $banco->executaSQL ( $sql ) or die ( "Não foi possivel atualizar a Obra" );
	$this->banco->desconecta();
	}
}
//teste insercao
//$cliente = new ClienteDAO();
//$cliente->inserir(0,0,"","","","","","","","","","","","","");
?>