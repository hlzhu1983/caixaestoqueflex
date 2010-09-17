package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.ItensReceitaVO;
import vo.ReceitaVO;

public class ServicosReceita {

	public ReceitaVO addReceita(ReceitaVO item) {
		String sql = "insert into receita (codProduto,quantidade) values ("
				+ item.codProduto + "," + item.quantidade + ")";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir Receita!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			item.codigo = rs.getInt(1);
			for (ItensReceitaVO itemReceita : item.itensReceita) {
				itemReceita.codReceita = item.codigo;
				this.adicionarItemReceita(itemReceita, st);
			}
		} else
			throw new RuntimeException("Erro ao salvar Receita!");

		this.banco.getConexao().commit();

		return item;
	}

	public ItensReceitaVO adicionarItemReceita(ItensReceitaVO item, Statement st)
			throws SQLException {

		String sql = "insert into itensreceita (codReceita, codProduto, quantidade)"
				+ " values ('" + item.codReceita + "','" + item.codProduto
				+ "','" + item.quantidade + "')";
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao Adicionar ItemReceita");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);

		return item;

	}

	public void excluirItemReceita(ItensReceitaVO item, Statement st) throws SQLException {

		String sql = "DELETE FROM itensReceita WHERE codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Deletar ItemReceita");
		}

	}

	public ArrayList<ItensReceitaVO> getAllItensReceita(String codigo) {
		String sql = "select i.codigo, p.descricao, i.codReceita, i.codProduto, i.quantidade" +
				" FROM itensReceita i, produto p WHERE i.codProduto = p.codigo and i.codReceita  = " + codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toItemReceita(resultado);
	}

	public ArrayList<ReceitaVO> pesquisarReceita(String texto, String coluna) {
		String sql = "select r.codigo as codigo, " +
				"p.descricao as descricao, p.codigo as codproduto, p.qtdEmEstoque " +
				"as qtdEstoque, r.quantidade as quantidade" +
				" from receita r, produto p where p.codigo = r.codProduto and " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		ArrayList<ReceitaVO> retorno = this.toReceita(resultado);
		for (ReceitaVO receitaVO : retorno) {
			receitaVO.itensReceita = this.pegarItensReceita(receitaVO);
		}
		return retorno;

	}
	
	
	public ArrayList<ReceitaVO> pesquisarReceita(String codigo) {
		String sql = "select r.codigo as codigo, " +
				"p.descricao as descricao, p.codigo as codproduto, p.qtdEmEstoque " +
				"as qtdEstoque, r.quantidade as quantidade" +
				" from receita r, produto p where p.codigo = r.codProduto and p.codigo = " + codigo;
		ResultSet resultado = banco.executar(sql);

		ArrayList<ReceitaVO> retorno = this.toReceita(resultado);
		for (ReceitaVO receitaVO : retorno) {
			receitaVO.itensReceita = this.pegarItensReceita(receitaVO);
		}
		return retorno;

	}


	public ArrayList<ItensReceitaVO> pesquisarItens(String texto, String coluna) {
		String sql = "select i.codigo, p.descricao, i.codReceita, i.codProduto, i.quantidade" +
				" FROM itensReceita i, produto p WHERE i.codProduto = p.codigo and " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toItemReceita(resultado);

	}

	public ReceitaVO atualizarReceita(ReceitaVO item) {
		String sql = "UPDATE receita set codProduto =" + item.codProduto
				+ " , quantidade =" + item.quantidade + "where  codigo ="
				+ item.codigo;

		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();

		sql = "DELETE FROM itensreceita where  codReceita =" + item.codigo;

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao remover itens receita!");
		}
		for (ItensReceitaVO itemReceita : item.itensReceita) {
			itemReceita.codReceita = item.codigo;
			this.adicionarItemReceita(itemReceita, st);
		}

		this.banco.getConexao().commit();
		return item;
	}

	public void removerReceita(ReceitaVO item) {

		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();

		for (ItensReceitaVO itemReceita : item.itensReceita) {
			itemReceita.codReceita = item.codigo;
			this.excluirItemReceita(itemReceita, st);
		}

		String sql = "DELETE FROM receita where  codigo =" + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao remover Receita!");
		}

		this.banco.getConexao().commit();

	}

	public ArrayList<ItensReceitaVO> getItensReceita(String codigo) {
		String sql = "select i.codigo, p.descricao, i.codReceita, i.codProduto, i.quantidade" +
				" FROM itensReceita i, produto p WHERE i.codProduto = p.codigo and codigo  = " + codigo;
		ResultSet resultado = banco.executar(sql);
		return this.toItemReceita(resultado);
	}

	public ArrayList<ReceitaVO> getItens() {
		String sql = "select r.codigo as codigo, " +
				"p.descricao as descricao, p.codigo as codproduto, p.qtdEmEstoque " +
				"as qtdEstoque, r.quantidade as quantidade" +
				" from receita r, produto p where p.codigo = r.codProduto ";

		ResultSet rs = banco.executar(sql);
		ArrayList<ReceitaVO> retorno = this.toReceita(rs);
		for (ReceitaVO compraVO : retorno) {
			compraVO.itensReceita = this.pegarItensReceita(compraVO);
		}
		return retorno;
	}

	private ArrayList<ReceitaVO> toReceita(ResultSet rs) throws SQLException {
		ArrayList<ReceitaVO> gp = new ArrayList<ReceitaVO>();
		while (rs.next()) {
			ReceitaVO dados_item = new ReceitaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codProduto = rs.getInt("codProduto");
			dados_item.quantidade = rs.getDouble("quantidade");
			dados_item.qtdEstoque = rs.getDouble("qtdEstoque");
			dados_item.descricao = rs.getString("descricao");

			ArrayList<ItensReceitaVO> itens;
			itens = this.pegarItensReceita(dados_item);
			if (itens.size() != 0)
				dados_item.itensReceita = itens;

			gp.add(dados_item);

		}
		return gp;
	}

	private ArrayList<ItensReceitaVO> pegarItensReceita(ReceitaVO item) throws SQLException {
		String sql = "select i.codigo, p.descricao, i.codReceita, i.codProduto, i.quantidade" +
				" FROM itensReceita i, produto p WHERE i.codProduto = p.codigo and i.codReceita = "
				+ item.codigo;
		ResultSet rs = banco.executar(sql);
		return this.toItemReceita(rs);
	}

	private ArrayList<ItensReceitaVO> toItemReceita(ResultSet rs)
			throws SQLException {
		ArrayList<ItensReceitaVO> gp = new ArrayList<ItensReceitaVO>();
		while (rs.next()) {
			ItensReceitaVO dados_item = new ItensReceitaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codReceita = rs.getInt("codReceita");
			dados_item.codProduto = rs.getInt("codProduto");
			dados_item.descricao = rs.getString("descricao");
			dados_item.quantidade = rs.getInt("quantidade");
			gp.add(dados_item);
		}
		return gp;
	}

}
