<?php

include 'app/vo/UsuarioVO.php';

class ServicosUsuario {
	
	
	private $conn;
	
	function ServicosUsuario() {
		$db = new BaseDados();
		$this->conn = $db->conn;
	}
	
	public function addUsuario(UsuarioVO $usuario){
		
		$sql = "insert into usuario (nome,num_caixa,comissao,senha,permissao) values ('{$usuario->nome}',{$usuario->num_caixa},{$usuario->comissao},'{$usuario->senha}',{$usuario->permissao})";
		
		$resultado = $this->conn->Execute($sql);
		$usuario->codigo = $this->conn->insert_Id();
		return $usuario;
		
	}
	
	public function removerUsuario(UsuarioVO $usuario){
		
		$sql = "delete from usuario  where codigo = {$usuario->codigo}";
		$resultado = $this->conn->Execute($sql);
		if($this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public function pesquisarUsuario($texto,$coluna){
		$sql = "select * from usuario where $coluna like '%$texto%'";
		$resultado = $this->conn->Execute($sql);
		while($registro = $resultado->FetchNextObject()){			
			$dados_usuario = new UsuarioVO();
			$dados_usuario->nome = $registro->NOME;
			$dados_usuario->codigo = $registro->CODIGO;
			$dados_usuario->num_caixa = $registro->NUM_CAIXA;
			$dados_usuario->comissao = $registro->COMISSAO;
			$dados_usuario->permissao = $registro->PERMISSAO;
			$dados_usuario->senha = "";
			$retorna_dados_usuario [] = $dados_usuario;
		}
		return $retorna_dados_usuario;
	}
	
	public function getUsuarios(){
		$sql = "select * from usuario";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$dados_usuario = new UsuarioVO();
			$dados_usuario->nome = $registro->NOME;
			$dados_usuario->codigo = $registro->CODIGO;
			$dados_usuario->num_caixa = $registro->NUM_CAIXA;
			$dados_usuario->comissao = $registro->COMISSAO;
			$dados_usuario->permissao = $registro->PERMISSAO;
			$dados_usuario->senha = "";
			$retorna_dados_usuario [] = $dados_usuario;
		}
		return $retorna_dados_usuario;	
	}
}


//$eu = new ServicosUsuario();
//$usuario = new UsuarioVO();
//$usuario->codigo = 2;
//echo $eu->removerUsuario($usuario);

?>