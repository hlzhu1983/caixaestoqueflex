<?php
//include 'app/db/BaseDados.php';
include 'app/vo/UsuarioVO.php';

class ServicosUsuario {
	
	
	private $conn;
	
	function ServicosUsuario() {
			$db = new BaseDados();
			$this->conn = $db->conn;
	}
	
	public function addUsuario(UsuarioVO $usuario){
		
		$sql = "insert into usuario (nome,comissao,senha,permissao,login) values ('{$usuario->nome}',{$usuario->comissao},'{$usuario->senha}',{$usuario->permissao},'{$usuario->login}')";
		$resultado = $this->conn->Execute($sql);
		$usuario->codigo = $this->conn->insert_Id();
		return $usuario;
		
	}
	
	public function logar(UsuarioVO $usuario){
		$sql = "select * from usuario where login = '{$usuario->login}'";
		$resultado = $this->conn->Execute($sql);
		
		 if(!$resultado){
			 throw new Exception(Erros::$SQL_ERRO_MENSAGEM,Erros::$SQL_ERRO_CODIGO);
		 }
		 $dados_usuario = NULL;
		while($registro = $resultado->FetchNextObject()){			
			 $dados_usuario = $this->toUsuario($registro);
		}
		if($dados_usuario->senha==$usuario->senha){
			return $dados_usuario;
		}else{
			 throw new Exception(Erros::$AUTENTICACAO_FALHOU_MENSAGEM
			 ,Erros::$AUTENTICACAO_FALHOU_CODIGO); 
		}
		
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
			$retorna_dados_usuario [] = $this->toUsuario($registro);
		}
		return $retorna_dados_usuario;
	}
	
	public function getUsuarios(){
		$sql = "select * from usuario";
		
		$resultado = $this->conn->Execute($sql);
		
		while($registro = $resultado->FetchNextObject()){			
			$retorna_dados_usuario [] = $this->toUsuario($registro);
		}
		return $retorna_dados_usuario;	
	}
	
	private function toUsuario($registro){
		if($registro->CODIGO != 0){
			$dados_usuario = new UsuarioVO();
			$dados_usuario->nome = $registro->NOME;
			$dados_usuario->codigo = $registro->CODIGO;
			$dados_usuario->comissao = (float)$registro->COMISSAO;
			$dados_usuario->permissao = $registro->PERMISSAO;
			$dados_usuario->senha = $registro->SENHA;
			$dados_usuario->login = $registro->LOGIN;
			return $dados_usuario;
		}
	}
}


//$eu = new ServicosUsuario();
//$usuario = new UsuarioVO();
//$usuario->login = "ADMIN";
//$usuario->senha = "admi";
//$servico = new ServicosUsuario();
//$eu->logar($usuario);
//$usuario->nome = "autoTesteTestado";
//$usuario->comissao = 10.98;
//$usuario->permissao = 1;
//$usuario->senha = "senha";
//$eu->addUsuario($usuario);
//$eu->getUsuarios();

?>