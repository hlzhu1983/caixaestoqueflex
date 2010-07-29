<?php

class DAO{
	public $banco;
	
	protected function setBancoDeDados(BancoDeDados $banco){
		$this->banco = $banco;	
	}
	
	protected function getBancoDeDados(){
		return $this->banco;
	}
}


?>