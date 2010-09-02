package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.UsuarioVO;

public class ServicosUsuario {

	public UsuarioVO addUsuario(UsuarioVO usuario) {
		String sql = "insert into usuario (nome,comissao,senha,permissao,login) values ('"
				+ usuario.nome
				+ "',"
				+ usuario.comissao
				+ ",'"
				+ usuario.senha
				+ "'," + usuario.permissao + ",'" + usuario.login + "')";
		Statement st;
		if (this.isExiste(usuario)) {
			throw new RuntimeException("Usuário ja existe!");
		}
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar Usuario");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			usuario.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar Usuario");
		this.banco.getConexao().commit();
		return usuario;

	}

	public UsuarioVO atualizarUsuario(UsuarioVO usuario) {
		String sql = "UPDATE usuario SET nome = '" + usuario.nome
				+ "' , comissao = " + usuario.comissao + " , senha = '"
				+ usuario.senha + "', permissao = " + usuario.permissao
				+ " , login  = '" + usuario.login
				+ "' where codigo = "+usuario.codigo+"";
		if (!this.isExiste(usuario)) {
			throw new RuntimeException("Usuário não existe!");
		}
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("erro no addUsuario");
		}
		return usuario;

	}

	public UsuarioVO autenticacaoUsuario(UsuarioVO usuario) throws Exception {
		String sql = "select * from usuario where login = '" + usuario.login
				+ "'";
		ResultSet rs = banco.executar(sql);
		if (rs == null) {
			throw new Exception("Consulta Inválida");
		}
		ArrayList<UsuarioVO> users = this.toUsurio(rs, true);
		if (users.size() == 0) {
			throw new RuntimeException("Usuário não existe");
		}
		UsuarioVO usuarioBD = users.get(0);
		if (usuarioBD.senha.equals(usuario.senha)) {
			return usuarioBD;
		} else {
			throw new Exception("Login ou senha incorretos!");
		}

	}

	public void removerUsuario(UsuarioVO usuario) {
		String sql = "delete from usuario  where codigo = " + usuario.codigo
				+ "";
		this.banco.executarNoQuery(sql);
	}

	public ArrayList<UsuarioVO> pesquisarUsuario(String texto, String coluna) {
		String sql = "select * from usuario where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		return this.toUsurio(rs, false);
	}

	public ArrayList<UsuarioVO> getUsuarios() {
		String sql = "select * from usuario";
		ResultSet rs = banco.executar(sql);
		return this.toUsurio(rs, false);
	}

	private ArrayList<UsuarioVO> toUsurio(ResultSet rs, boolean isLogar) {
		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			while (rs.next()) {
				if (!isLogar && rs.getInt("codigo") == 0)
					continue;
				UsuarioVO dados_item = new UsuarioVO();
				dados_item.codigo = rs.getInt("codigo");
				dados_item.nome = rs.getString("nome");
				dados_item.comissao = rs.getDouble("comissao");
				dados_item.login = rs.getString("login");
				dados_item.permissao = rs.getInt("permissao");
				dados_item.senha = rs.getString("senha");
				usuarios.add(dados_item);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao criar Cliente");
		}
		return usuarios;
	}

	public boolean isExiste(UsuarioVO usuario) {
		String sql = "select * from usuario where nome = '" + usuario.nome
				+ "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

}
