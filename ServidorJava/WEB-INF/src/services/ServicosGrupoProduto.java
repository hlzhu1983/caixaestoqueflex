package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.GrupoProdutoVO;

public class ServicosGrupoProduto {

	public GrupoProdutoVO addGrupoProduto(GrupoProdutoVO grupoproduto) {

		String sql = "insert into grupoproduto (descricao) values ('"
				+ grupoproduto.descricao + "')";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar grupo de produto!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			grupoproduto.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar grupo de produto!");
		this.banco.getConexao().commit();
		return grupoproduto;

	}

	public boolean removerGrupoProduto(GrupoProdutoVO grupoproduto) {
		String sql = "delete from grupoproduto  where codigo = "
				+ grupoproduto.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public GrupoProdutoVO atualizarGrupoProduto(GrupoProdutoVO grupoproduto) {
		String sql = "UPDATE grupoproduto SET descricao = '"
				+ grupoproduto.descricao + "' where codigo = "
				+ grupoproduto.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar grupo produto!");
		}
		return grupoproduto;

	}

	public ArrayList<GrupoProdutoVO> pesquisarGrupoProduto(String texto,
			String coluna) {
		String sql = "select * from grupoproduto where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		return this.toGrupoProduto(rs);
	}

	public ArrayList<GrupoProdutoVO> getGrupoProdutos() {
		String sql = "select * from grupoproduto";
		ResultSet rs = banco.executar(sql);
		return this.toGrupoProduto(rs);
	}

	public boolean isExiste(GrupoProdutoVO grupoproduto) {
		String sql = "select * from grupoproduto where codigo = '"+grupoproduto.codigo+"' OR descricao = '"
				+ grupoproduto.descricao + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

	private ArrayList<GrupoProdutoVO> toGrupoProduto(ResultSet rs)
			throws SQLException {
		ArrayList<GrupoProdutoVO> gp = new ArrayList<GrupoProdutoVO>();
		while (rs.next()) {
			GrupoProdutoVO dados_item = new GrupoProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");

			gp.add(dados_item);
		}
		return gp;
	}

}
