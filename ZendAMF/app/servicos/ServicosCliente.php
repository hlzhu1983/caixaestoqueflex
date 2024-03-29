<?php
//include 'app/db/BaseDados.php';
include 'app/vo/ClienteVO.php';

class ServicosCliente {
	
	
	private $conn;
	
	function ServicosCliente() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addCliente(ClienteVO $item){
		
		$sql = "insert into cliente (nome,tipoPessoa
		 ,sexo ,dataNascimento ,dataCadastro ,endereco
		 ,bairro ,cidade ,UF ,cep ,cpf_cnpj ,insc_estadual
		 ,fone ,contato ,email ,url ,obs,limCredito) values ('{$item->nome}'		 
		 ,'{$item->tipoPessoa}' ,'{$item->sexo}' ,'{$item->dataNascimento}'
		 ,'{$item->dataCadastro}' ,'{$item->endereco}'
		 ,'{$item->bairro}' ,'{$item->cidade}'
		 ,'{$item->UF}' ,'{$item->cep}'
		 ,'{$item->cpf_cnpj}' ,'{$item->insc_estadual}'
		 ,'{$item->fone}' ,'{$item->contato}'
		 ,'{$item->email}' ,'{$item->url}' ,'{$item->obs}','{$item->limCredito}')";
		
		$resultado = $this->conn->Execute($sql);
		$item->codigo = $this->conn->insert_Id();
		$dateC = explode("-",$item->dataCadastro);
		$dateN = explode("-",$item->dataNascimento);
		$item->dataCadastro = $dateC[1]."/" . $dateC[2] . "/" .$dateC[0];
		$item->dataNascimento = $dateN[1]."/" . $dateN[2] . "/" .$dateN[0];
		return $item;
		
	}
	
	public function removerCliente(ClienteVO $item){
		
		$sql = "delete from cliente  where codigo = {$item->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarCliente($texto,$coluna){
		$f = fopen('log0.txt','w+');
		
		$sql = "select *, date_format(datacadastro, '%m/%d/%Y') as dCadastro, date_format(datanascimento, '%m/%d/%Y') as dNascimento from cliente where $coluna like '%$texto%'";
		fwrite($f,$sql);
		$resultado = $this->conn->Execute($sql);
		$retorna_dados_item = null;
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new ClienteVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->nome = $registro->NOME;
			$dados_item->bairro = $registro->BAIRRO;
			$dados_item->cep = $registro->CEP;
			$dados_item->cidade = $registro->CIDADE;
			$dados_item->contato = $registro->CONTATO;
			$dados_item->cpf_cnpj = $registro->CPF_CNPJ;
			$dados_item->dataCadastro = $registro->DCADASTRO;
			$dados_item->dataNascimento = $registro->DNASCIMENTO;
			$dados_item->email = $registro->EMAIL;
			$dados_item->endereco = $registro->ENDERECO;
			$dados_item->fone = $registro->FONE;
			$dados_item->insc_estadual = $registro->INSC_ESTADUAL;
			$dados_item->obs = $registro->OBS;
			$dados_item->sexo = $registro->SEXO;
			$dados_item->tipoPessoa = $registro->TIPOPESSOA;
			$dados_item->UF = $registro->UF;
			$dados_item->url = $registro->URL;
			$dados_item->limCredito = $registro->LIMCREDITO;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;
	}
	
	public function getClientes(){
		$sql = "select *,date_format(datacadastro, '%m/%d/%Y') as dCadastro, date_format(datanascimento, '%m/%d/%Y') as dNascimento from cliente";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_item = new ClienteVO();
			$dados_item->codigo = $registro->CODIGO;
			$dados_item->nome = $registro->NOME;
			$dados_item->bairro = $registro->BAIRRO;
			$dados_item->cep = $registro->CEP;
			$dados_item->cidade = $registro->CIDADE;
			$dados_item->contato = $registro->CONTATO;
			$dados_item->cpf_cnpj = $registro->CPF_CNPJ;
			$dados_item->dataCadastro = $registro->DCADASTRO;
			$dados_item->dataNascimento = $registro->DNASCIMENTO;
			$dados_item->email = $registro->EMAIL;
			$dados_item->endereco = $registro->ENDERECO;
			$dados_item->fone = $registro->FONE;
			$dados_item->insc_estadual = $registro->INSC_ESTADUAL;
			$dados_item->obs = $registro->OBS;
			$dados_item->sexo = $registro->SEXO;
			$dados_item->tipoPessoa = $registro->TIPOPESSOA;
			$dados_item->UF = $registro->UF;
			$dados_item->url = $registro->URL;
			$dados_item->limCredito = $registro->LIMCREDITO;
			$retorna_dados_item [] = $dados_item;
		}
		return $retorna_dados_item;	
	}


	public function atualizarCliente(ClienteVO $cliente) {
		
		$sql = "UPDATE cliente SET nome = '$cliente->nome', tipoPessoa = $cliente->tipoPessoa, sexo = $cliente->sexo, dataNascimento = '$cliente->dataNascimento',dataCadastro = '$cliente->dataCadastro',endereco='$cliente->endereco',bairro='$cliente->bairro',cidade='$cliente->cidade',UF='$cliente->UF',cep='$cliente->cep',
		cpf_cnpj='$cliente->cpf_cnpj',insc_estadual='$cliente->insc_estadual',fone='$cliente->fone',contato='$cliente->contato',email='$cliente->email',url='$cliente->url',obs='$cliente->obs' where codigo=$cliente->codigo";
			
		$resultado = $this->conn->Execute($sql);
		$dateC = explode("-",$cliente->dataCadastro);
		$dateN = explode("-",$cliente->dataNascimento);
		$cliente->dataCadastro = $dateC[1]."/" . $dateC[2] . "/" .$dateC[0];
		$cliente->dataNascimento = $dateN[1]."/" . $dateN[2] . "/" .$dateN[0];
		return $cliente;	
		
	}
}
//$eu = new ServicosCliente();
//$c = new ClienteVO();
//$c->codigo = 47;
//$c->dataCadastro = "2010-09-31";
//$eu->getClientes();

?>
