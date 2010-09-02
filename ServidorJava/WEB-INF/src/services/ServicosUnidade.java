package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.UnidadeVO;

public class ServicosUnidade {

	public UnidadeVO addUnidade(UnidadeVO unidade) {
		String sql = "insert into unidade (descricao) values ('"
				+ unidade.descricao + "')";

		Statement st;
		if (this.isExiste(unidade)) {
			throw new RuntimeException("Unidade ja existe!");
		}
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar unidade!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			unidade.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar unidade!");
		this.banco.getConexao().commit();

		return unidade;

	}

	public boolean removerUnidade(UnidadeVO unidade) {
		String sql = "delete from unidade  where codigo = " + unidade.codigo
				+ "";
		if (banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}
	
	public UnidadeVO atualizarUnidade(UnidadeVO unidade) {
		String sql = "UPDATE unidade SET descricao = '"
				+ unidade.descricao + "' wher codigo = "
				+ unidade.codigo + "";
		if (!this.isExiste(unidade)) {
			throw new RuntimeException("Unidade não existe!");
		}
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar unidade!");
		}
		return unidade;

	}

	public ArrayList<UnidadeVO> pesquisarUnidade(String texto, String coluna) {
		String sql = "select * from unidade where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		return this.toUnidade(rs);
	}

	public ArrayList<UnidadeVO> getUnidades() {
		String sql = "select * from unidade";
		ResultSet rs = banco.executar(sql);
		return this.toUnidade(rs);
	}

	public boolean isExiste(UnidadeVO unidade) {
		String sql = "select * from unidade where descricao = '" + unidade.descricao + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

	private ArrayList<UnidadeVO> toUnidade(ResultSet rs) throws SQLException {
		ArrayList<UnidadeVO> fp = new ArrayList<UnidadeVO>();
		while (rs.next()) {

			UnidadeVO dados_item = new UnidadeVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");

			fp.add(dados_item);
		}
		return fp;

	}

}
