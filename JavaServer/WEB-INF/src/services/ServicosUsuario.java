import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import arquitetura.BancoDeDados;
import vo.UsuarioVO;

class ServicosUsuario {

	private BancoDeDados banco;

	public UsuarioVO addUsuario(UsuarioVO usuario) {

		String sql = "insert into usuario (nome,comissao,senha,permissao,login) values ('"
				+ usuario.nome
				+ "',"
				+ usuario.comissao
				+ ",'"
				+ usuario.senha
				+ "'," + usuario.permissao + ",'" + usuario.login + "')";

		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("erro no addUsuario");
		}

		return usuario;

	}

	public UsuarioVO atualizarUsuario(UsuarioVO usuario) {
		String sql = "UPDATE usuario SET nome = '" + usuario.nome
				+ "' , comissao = " + usuario.comissao + " , senha = '"
				+ usuario.senha + "', permissao = " + usuario.permissao
				+ " , login  = '" + usuario.login
				+ "' where codigo = usuario.codigo";
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("erro no addUsuario");
		}
		return usuario;

	}

	public UsuarioVO logar(UsuarioVO usuario){
		String sql = "select * from usuario where login = 'usuario.login'";
		
		
		 if(!resultado){
			 throw new Exception(Erros::SQL_ERRO_MENSAGEM,Erros::SQL_ERRO_CODIGO);
	}
		 dados_usuario = NULL;
		 f = fopen('logou.txt','w+');
		 fwrite(f,dados_usuario.login);
		while(registro = resultado.FetchNextObject(){
			dados_usuario = new ();
			dados_usuario.nome = registro.NOME;
			dados_usuario.codigo = registro.CODIGO;
			dados_usuario.comissao = (float)registro.COMISSAO;
			dados_usuario.permissao = registro.PERMISSAO;
			dados_usuario.senha = registro.SENHA;
			dados_usuario.login = registro.LOGIN;
		}
		fwrite(f,dados_usuario.login);
		fwrite(f,dados_usuario.senha);
		if(dados_usuario.senha==usuario.senha){
			fwrite(f,'Entrou');
			return dados_usuario;
		}else{
			 throw new Exception(Erros::AUTENTICACAO_FALHOU_MENSAGEM
			 ,Erros::AUTENTICACAO_FALHOU_CODIGO); 
		}
		
		}

	public boolean removerUsuario(UsuarioVO usuario) {

		String sql = "delete from usuario  where codigo = " + usuario.codigo
				+ "";

		if (this.banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public Map<Integer, UsuarioVO> pesquisarUsuario(String texto, String coluna) {
		String sql = "select * from usuario where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		Map<Integer, UsuarioVO> usuarios = null;
		int i = 0;
		try {
			while (rs.next()) {

				UsuarioVO dados_item = new UsuarioVO();
				dados_item.codigo = rs.getInt("codigo");
				dados_item.nome = rs.getString("nome");
				dados_item.comissao = rs.getDouble("comissao");
				dados_item.login = rs.getString("login");
				dados_item.permissao = rs.getInt("permissao");
				usuarios.put(i, dados_item);
				i++;
			}
		} catch (SQLException e) {
			System.out.println("erro");
		}
		return usuarios;
	}

	public Map<Integer, UsuarioVO> getUsuarios() {
		String sql = "select * from usuario";

		ResultSet rs = banco.executar(sql);
		Map<Integer, UsuarioVO> usuarios = null;
		int i = 0;
		try {
			while (rs.next()) {

				UsuarioVO dados_item = new UsuarioVO();
				dados_item.codigo = rs.getInt("codigo");
				dados_item.nome = rs.getString("nome");
				dados_item.comissao = rs.getDouble("comissao");
				dados_item.login = rs.getString("login");
				dados_item.permissao = rs.getInt("permissao");
				usuarios.put(i, dados_item);
				i++;
			}
		} catch (SQLException e) {
			System.out.println("erro");
		}
		return usuarios;
	}

}
