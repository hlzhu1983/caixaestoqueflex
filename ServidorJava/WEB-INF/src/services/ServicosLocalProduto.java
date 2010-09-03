package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.LocalProdutoVO;

public class ServicosLocalProduto {

	public LocalProdutoVO addLocalProduto(LocalProdutoVO localproduto) {

		String sql = "insert into localproduto (descricao) values ('"
				+ localproduto.descricao + "')";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar local produto!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			localproduto.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar Local Produto!");
		this.banco.getConexao().commit();
		return localproduto;

	}

	public boolean removerLocalProduto(LocalProdutoVO localproduto) {
		String sql = "delete from localproduto  where codigo = "
				+ localproduto.codigo + "";

		if (banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public LocalProdutoVO atualizarLocalProduto(LocalProdutoVO localproduto) {
		String sql = "UPDATE localproduto SET descricao = '"
				+ localproduto.descricao + "' where codigo = "
				+ localproduto.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar local produto!");
		}
		return localproduto;

	}

	public ArrayList<LocalProdutoVO> pesquisarLocalProduto(String texto,
			String coluna) {
		String sql = "select * from localproduto where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		return this.toLocalProduto(rs);
	}

	public ArrayList<LocalProdutoVO> getLocalProdutos() {
		String sql = "select * from localproduto";
		ResultSet rs = banco.executar(sql);
		return this.toLocalProduto(rs);
	}

	public boolean isExiste(LocalProdutoVO localProduto) {
		String sql = "select * from localproduto where codigo = '"
				+ localProduto.codigo + "' or descricao = '"
				+ localProduto.descricao + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

	private ArrayList<LocalProdutoVO> toLocalProduto(ResultSet rs)
			throws SQLException {
		ArrayList<LocalProdutoVO> lp = new ArrayList<LocalProdutoVO>();
		while (rs.next()) {
			LocalProdutoVO dados_item = new LocalProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");

			lp.add(dados_item);
		}
		return lp;
	}
}
