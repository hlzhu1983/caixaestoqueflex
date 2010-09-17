package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.ItemProducaoVO;
import vo.ItemReceitaVO;
import vo.ProducaoVO;
import vo.ProdutoVO;
/*
 * 
 * 
 * USA UPDATE PRODUTO, MAS SOH FOI MANTIDO OS QUE SOMAM; 
 * 
 * 
 * */
public class ServicosProducao {

	private ServicosReceita servReceita = new ServicosReceita();

	public ProducaoVO addProducao(ProducaoVO item) {
		String sql = "insert into producao (dataProducao,obs,descricao) values ('"
				+ item.dataProducao + " ','" + item.obs + "','"+item.descricao+"')";

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
				
		ArrayList<ItemReceitaVO> itens = servReceita.recuperarItensReceita(item.codReceita+"");
		
		String sql;
		for (ItemReceitaVO itensReceitaVO : itens) {
			 sql = "select * from produto where codigo = " + itensReceitaVO.codProduto;

			ArrayList<ProdutoVO> itensProduto = new ServicosProduto().getProdutos(sql);
			
			if (itensProduto.size() == 0) {
				throw new RuntimeException("Produto não existe");
			}
			
			ProdutoVO registro = itensProduto.get(0);
			
			registro.qtdEmEstoque -= itensReceitaVO.quantidade;
			(new ServicosProduto()).atualizarProduto(registro);
		//	sql = "update produto set qtdEmEstoque = qtdEmEstoque - "
		//		+ itensReceitaVO.quantidade + " where codigo = "
		//			+ itensReceitaVO.codProduto;
		//	if (st.executeUpdate(sql) == 0) {
		//		throw new RuntimeException("Erro ao atualizar quantidade produto!");
		//	}
			
		}
		
		sql = "update produto set qtdEmEstoque = qtdEmEstoque + " +
					"(select quantidade from receita where codigo = "+item.codReceita +") " +
			  "where codigo = (select codProduto from receita where codigo = "+item.codReceita +")";
		
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar quantidade produto!");
		}
		
		sql = "insert into itensproducao (codProducao, codReceita)"
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
				+ " FROM itensproducao i, receita r, produto p "
				+ "WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and i.codProducao  = "
				+ codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toItemProducao(resultado);
	}

	public ArrayList<ProducaoVO> pesquisarProducao(String texto, String coluna) {
		String sql = "select * from producao where "
				+ coluna + " like '%" + texto + "%'";
		ResultSet resultado = banco.executar(sql);
		return this.toProducao(resultado);

	}

	public ArrayList<ItemProducaoVO> pesquisarItens(String texto, String coluna) {
		String sql = "select i.codigo, p.descricao, r.quantidade, i.codProducao, i.codReceita"
				+ " FROM itensProducao i, receita r, produto p "
				+ "WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and "
				+ coluna + " like '%" + texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toItemProducao(resultado);

	}

	public ProducaoVO atualizarProducao(ProducaoVO item) {
		String sql = "UPDATE producao set dataProducao =" + item.dataProducao
				+ " , obs =" + item.obs + "where  codigo =" + item.codigo;

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
				+ " FROM itensProducao i, receita r, produto p "
				+ "WHERE i.codReceita = r.codigo and r.codProduto = p.codigo and i.codigo  = "
				+ codigo;
		ResultSet resultado = banco.executar(sql);
		return this.toItemProducao(resultado);
	}

	public ArrayList<ProducaoVO> getItens() {
		String sql = "select * from producao";

		ResultSet rs = banco.executar(sql);
		
		return toProducao(rs);
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
		String sql = "select * FROM itensProducao WHERE codProducao = "+ item.codigo;
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
			dados_item.codReceita = rs.getInt("codReceita");
			//dados_item.descricao = rs.getString("descricao");
			gp.add(dados_item);
		}
		return gp;
	}

}
