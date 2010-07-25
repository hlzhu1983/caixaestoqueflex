<?php

class DAO{
	public $banco;
	
	public function setBancoDeDados(BancoDeDados $banco){
		$this->banco = $banco;	
	}
	
	public function getBancoDeDados(){
		return $this->banco;
	}

}


?>