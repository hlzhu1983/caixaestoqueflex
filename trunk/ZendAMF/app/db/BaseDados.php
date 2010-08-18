<?php

include_once('adodb/adodb.inc.php');

class BaseDados {
		
	public $conn;
		
		
	function BaseDados() {
		$tipo_banco    = "mysql";
		$servidor      = "localhost";
		$usuario       = "root";
		$senha         = "root";
		$db            = "caixaeestoque";
		 
	    $this->conn = NewADOConnection($tipo_banco);
		$this->conn->Connect($servidor,$usuario,$senha,$db);
	}
}
?>