<?php

class Erros {
	
		
		public static $AUTENTICACAO_FALHOU_CODIGO = 1;
		public static $AUTENTICACAO_FALHOU_MENSAGEM = 'Falha na autenticação. Login ou Senha incoretos!';
		
		public static $ERRO_AO_CONECTAR_CODIGO = 2;
		public static $ERRO_AO_CONECTAR_MENSAGEM = 'Erro ao conectar com Base de Dados';
		
		public static $ERRO_AO_ADD_USUARIO_CODIGO = 3;
		public static $ERRO_AO_ADD_USUARIO_MENSAGEM = 'Erro ao conectar com Base de Dados';
	
		public static $SQL_ERRO_CODIGO = 4;
		public static $SQL_ERRO_MENSAGEM = 'Erro na transação com  a base de dados';
}

?>