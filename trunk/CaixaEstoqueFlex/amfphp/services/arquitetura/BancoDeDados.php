<?php

class BancoDeDados{

	private $host;
	private $user;
	private $password;
	private $connection;
	private $squema;
	
	public function BancoDeDados($resource){
		$xml = simplexml_load_file($resource);
		$this->host = $xml->sessionfactory->host;
		$this->user = $xml -> sessionfactory -> username;
		$this->password = $xml -> sessionfactory -> password;
		$this->squema = $xml -> sessionfactory -> database;
	} 
	
	public function conecta(){
		$this -> connection = mysql_connect($this->host,$this->user,$this->password);
		mysql_select_db($this->squema,$this->connection);
		return $this->connection;
	}
	
	public function desconecta(){
		mysql_close($this->connection);
	}
	
	public function updateSQL($sql){
		$recordset = mysql_query($sql,$this->connection);
		$resposta = mysql_affected_rows();
		if($resposta < 1)
			return false;
		else
			return true;
	}
	
	public function executaSQL($sql){
		$resposta = mysql_query($sql,$this->connection);
		 
		return $resposta;
	}
	
	public function iniciarTransacao(){
		$this->executaSQL('begin');
	}
	
	public function efetivarTransacao(){
		$this->executaSQL('commit');
	}
	
	public function desfazerTransacao(){
		$this->executaSQL('rollback');
	}
	
}
		



?>