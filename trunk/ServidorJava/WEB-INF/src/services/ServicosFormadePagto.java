package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.FormadePagtoVO;

public class ServicosFormadePagto {

	public FormadePagtoVO addFormadePagto(FormadePagtoVO formadepgto) {

		String sql = "insert into formadepgto (descricao) values ('"
				+ formadepgto.descricao + "')";
		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar Forma de pagamento!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			formadepgto.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar Forma de pagamento!");
		this.banco.getConexao().commit();
		return formadepgto;

	}

	public boolean removerFormadePagto(FormadePagtoVO formadepgto) {
		String sql = "delete from formadepgto  where codigo = "
				+ formadepgto.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public FormadePagtoVO atualizarFormadePagto(FormadePagtoVO formaPagamento) {
		String sql = "UPDATE formadepgto SET descricao = '"
				+ formaPagamento.descricao + "' where codigo = "
				+ formaPagamento.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar forma de pagamento!");
		}
		return formaPagamento;

	}

	public ArrayList<FormadePagtoVO> pesquisarFormadePagto(String texto,
			String coluna) {
		String sql = "select * from formadepgto where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet rs = banco.executar(sql);
		return toFormaPagto(rs);
	}

	public ArrayList<FormadePagtoVO> getFormadePagtos() {
		String sql = "select * from formadepgto";
		ResultSet rs = banco.executar(sql);
		return toFormaPagto(rs);
	}

	private ArrayList<FormadePagtoVO> toFormaPagto(ResultSet rs)
			throws SQLException {
		ArrayList<FormadePagtoVO> fp = new ArrayList<FormadePagtoVO>();
		while (rs.next()) {
			FormadePagtoVO dados_item = new FormadePagtoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");
			fp.add(dados_item);
		}
		return fp;

	}

	public boolean isExiste(FormadePagtoVO formadepgto) {
		String sql = "select * from formadepgto where codigo = '"
				+ formadepgto.codigo + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

}
