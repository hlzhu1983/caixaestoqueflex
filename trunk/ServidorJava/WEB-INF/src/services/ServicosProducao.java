package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.ItemProducaoVO;
import vo.ProducaoVO;

public class ServicosProducao {

	public ProducaoVO addProducao(ProducaoVO item) {
		String sql = "insert into receita (dataProducao,obs) values ("
				+ item.dataProducao + "," + item.obs + ")";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir Producao!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			item.codigo = rs.getInt(1);
			for (ItemProducaoVO itemProducao : item.itensProducao) {
				itemProducao.codProducao = item.codigo;
				this.adicionarItemProducao(itemProducao, st);
			}
		} else
			throw new RuntimeException("Erro ao salvar Producao!");

		this.banco.getConexao().commit();

		return item;
	}

	public ItemProducaoVO adicionarItemProducao(ItemProducaoVO item,
			Statement st) throws SQLException {

		String sql = "insert into itensproducao (codProducao, codReceita)"
				+ " values ('" + item.codProducao + "','" + item.codReceita + "')";
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao Adicionar ItemProducao");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);

		return item;

	}

	public void excluirItemProducao(ItemProducaoVO item, Statement st)
			throws SQLException {

		String sql = "DELETE FROM itensProducao WHERE codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Deletar ItemProducao");
		}

	}

	public ArrayList<ItemProducaoVO> getAllItensProducao(String codigo) {
		String sql = "select i.codigo, p.descricao, i.codProducao, i.codReceita"
				+ " FROM itensproducao i, receita r, produto p " +
				"WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and i.codProducao  = "+ codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toItemProducao(resultado);
	}

	public ArrayList<ProducaoVO> pesquisarProducao(String texto, String coluna) {
		String sql = "select r.codigo as codigo, "
				+ "p.descricao as descricao, p.codigo as codproduto, p.qtdEmEstoque "
				+ "as qtdEstoque, r.quantidade as quantidade"
				+ " from receita r, produto p where produto.codigo = receita.codProduto and "
				+ coluna + " like '%" + texto + "%'";
		ResultSet resultado = banco.executar(sql);

		ArrayList<ProducaoVO> retorno = this.toProducao(resultado);
		for (ProducaoVO receitaVO : retorno) {
			receitaVO.itensProducao = this.getItensProducao(receitaVO);
		}
		return retorno;

	}

	public ArrayList<ItemProducaoVO> pesquisarItens(String texto, String coluna) {
		String sql = "select i.codigo, p.descricao, r.quantidade, i.codProducao, i.codReceita"
				+ " FROM itensProducao i, receita r, produto p " +
				"WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and "
				+ coluna + " like '%" + texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toItemProducao(resultado);

	}

	public ProducaoVO atualizarProducao(ProducaoVO item) {
		String sql = "UPDATE producao set dataProducao =" + item.dataProducao
				+ " , obs =" + item.obs + "where  codigo ="+ item.codigo;

		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();

		sql = "DELETE FROM itensreceita where  codProducao =" + item.codigo;

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao remover itens receita!");
		}
		for (ItemProducaoVO itemProducao : item.itensProducao) {
			itemProducao.codProducao = item.codigo;
			this.adicionarItemProducao(itemProducao, st);
		}

		this.banco.getConexao().commit();
		return item;
	}

	public void removerProducao(ProducaoVO item) {

		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();

		for (ItemProducaoVO itemProducao : item.itensProducao) {
			itemProducao.codProducao = item.codigo;
			this.excluirItemProducao(itemProducao, st);
		}

		String sql = "DELETE FROM receita where  codigo =" + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao remover Producao!");
		}

		this.banco.getConexao().commit();

	}

	public ArrayList<ItemProducaoVO> getItensProducao(String codigo) {
		String sql = "select i.codigo, p.descricao, r.quantidade, i.codProducao, i.codReceita"
				+ " FROM itensProducao i, receita r, produto p " +
				"WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and i.codigo  = "
				+ codigo;
		ResultSet resultado = banco.executar(sql);
		return this.toItemProducao(resultado);
	}

	public ArrayList<ProducaoVO> getItens() {
		String sql = "select * from producao";

		ResultSet rs = banco.executar(sql);
		ArrayList<ProducaoVO> retorno = this.toProducao(rs);
		for (ProducaoVO compraVO : retorno) {
			compraVO.itensProducao = this.getItensProducao(compraVO);
		}
		return retorno;
	}

	private ArrayList<ProducaoVO> toProducao(ResultSet rs) throws SQLException {
		ArrayList<ProducaoVO> gp = new ArrayList<ProducaoVO>();
		while (rs.next()) {
			ProducaoVO dados_item = new ProducaoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.dataProducao = rs.getString("dataProducao");
			dados_item.obs = rs.getString("obs");
			dados_item.descricao = rs.getString("descricao");

			ArrayList<ItemProducaoVO> itens;
			itens = this.getItensProducao(dados_item);
			if (itens.size() != 0)
				dados_item.itensProducao = itens;

			gp.add(dados_item);

		}
		return gp;
	}

	private ArrayList<ItemProducaoVO> getItensProducao(ProducaoVO item) {
		String sql = "select i.codigo, p.descricao, i.codProducao, i.codProduto, i.quantidade"
				+ " FROM itensProducao i, receita r, produto p WHERE i.codProduto = p.codigo and i.codProducao = "
				+ item.codigo;
		ResultSet rs = banco.executar(sql);
		return this.toItemProducao(rs);
	}

	private ArrayList<ItemProducaoVO> toItemProducao(ResultSet rs)
			throws SQLException {
		ArrayList<ItemProducaoVO> gp = new ArrayList<ItemProducaoVO>();
		while (rs.next()) {
			ItemProducaoVO dados_item = new ItemProducaoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codProducao = rs.getInt("codProducao");
			dados_item.codReceita = rs.getInt("codProduto");
			dados_item.descricao = rs.getString("descricao");
			gp.add(dados_item);
		}
		return gp;
	}

}
