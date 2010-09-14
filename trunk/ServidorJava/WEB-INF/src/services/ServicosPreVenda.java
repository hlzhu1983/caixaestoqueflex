package services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import vo.ItemPreVendaVO;
import vo.PreVendaVO;
import vo.ProdutoVO;

public class ServicosPreVenda {

	public PreVendaVO abrirPreVenda(PreVendaVO item) {
		String sql = "insert into prevenda (codUsuario,status,dataAbertura,obs,valorTotal) values ("
				+ item.codUsuario + ",0 ,now(),'',0)";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir pré-venda!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar pré-venda!");
		this.banco.getConexao().commit();
		return item;
	}

	public ItemPreVendaVO addItemPreVenda(ItemPreVendaVO item) {
		String sql = "select * from produto where codigo = " + item.codProduto;

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		ArrayList<ProdutoVO> itens = new ServicosProduto().getProdutos(sql);
		;
		if (itens.size() == 0) {
			throw new RuntimeException("Produto não existe");
		}
		ProdutoVO registro = itens.get(0);
		if (registro.qtdEmEstoque < item.quantidade) {
			throw new RuntimeException(
					"Quantidade de produtos maior que disponível!");
		}
		sql = "insert into itensprevenda (codPrevenda, codProduto, descricao, quantidade,valor)"
				+ " values ('"
				+ item.codigoPrevenda
				+ "','"
				+ item.codProduto
				+ "','"
				+ item.descricao
				+ "','"
				+ item.quantidade
				+ "','"
				+ item.valor + "')";
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao Adicionar ItemPrevenda");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);

		sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque - "
				+ item.quantidade + ") where codigo = " + item.codProduto;

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar itemprevenda");
		}
		this.banco.getConexao().commit();

		return item;

	}

	public PreVendaVO fecharPreVenda(PreVendaVO item) {
		String sql = "UPDATE prevenda SET codUsuario = '" + item.codUsuario
				+ "' , status = '1', obs = '" + item.obs + "', valorTotal = '"
				+ item.valorTotal + "' where codigo = " + item.codigo + "";
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao fechar Prevenda");
		}
		return item;
	}

	public void removerItemPreVenda(ItemPreVendaVO item) {

		String sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "
				+ item.quantidade + ")  WHERE codigo = " + item.codProduto;

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar ItemPrevenda");
		}
		sql = "DELETE FROM itensprevenda WHERE codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Deletar ItemPrevenda");
		}

		this.banco.getConexao().commit();

	}

	public PreVendaVO cancelarPreVenda(PreVendaVO item) {
		String sql = "select * FROM itensprevenda WHERE codPrevenda = "
				+ item.codigo;
		int i = 0;
		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		ResultSet resultado = st.executeQuery(sql);
		ArrayList<ItemPreVendaVO> itens = this.toItemPreVenda(resultado);

		for (i = 0; i < itens.size(); i++) {
			ItemPreVendaVO registro = itens.get(i);
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "
					+ registro.quantidade + ")  WHERE codigo = "
					+ registro.codProduto;
			if (st.executeUpdate(sql) == 0) {
				throw new RuntimeException("Erro ao atualizar produto");
			}

		}
		if (itens.size() > 0) {
			sql = "DELETE FROM itensprevenda WHERE codPrevenda = "
					+ item.codigo;
			if (st.executeUpdate(sql) == 0) {
				throw new RuntimeException("Erro ao Deletar Prevenda");
			}
		}

		sql = "UPDATE prevenda SET codUsuario = '" + item.codUsuario
				+ "' , status = '2', obs = '" + item.obs + "' where codigo = "
				+ item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Atualizar Prevenda");
		}
		this.banco.getConexao().commit();
		return item;
	}

	public boolean removerItem(PreVendaVO item) {
		String sql = "delete from prevenda  where codigo = item.codigo";
		if (banco.executarNoQuery(sql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<PreVendaVO> filtrarData(String data, String coluna) {
		String sql = "select * from prevenda where DATE(" + coluna + ") = '"
				+ data + "' and status = 1";
		ResultSet result = banco.executar(sql);
		return this.toPreVenda(result);
	}

	public ArrayList<PreVendaVO> getAllItensPrevenda(String codigo) {
		String sql = "select * from itensprevenda where codPrevenda  = "
				+ codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toPreVenda(resultado);
	}

	public ArrayList<PreVendaVO> pesquisarItens(String texto, String coluna) {
		String sql = "select * from prevenda where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toPreVenda(resultado);
	}

	public ArrayList<PreVendaVO> getItens() {
		String sql = "select * from prevenda";

		ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);
	}

	public ArrayList<PreVendaVO> getItensValidos() {
		String sql = "select * from prevenda where status = 1";
		ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);
	}

	public ArrayList<PreVendaVO> pesquisarItensValidos(String texto,
			String coluna) {
		String sql = "select * from prevenda where " + coluna + " like '%"
				+ texto + "%' and status = 1";
		ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);
	}

	private ArrayList<PreVendaVO> toPreVenda(ResultSet rs) throws SQLException {
		ArrayList<PreVendaVO> gp = new ArrayList<PreVendaVO>();
		while (rs.next()) {
			PreVendaVO dados_item = new PreVendaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codUsuario = rs.getInt("codUsuario");
			dados_item.dataAbertura = rs.getString("dataAbertura");
			dados_item.obs = rs.getString("obs");
			dados_item.status = rs.getInt("status");
			dados_item.valorTotal = rs.getInt("valorTotal");

			ArrayList<ItemPreVendaVO> itens;
			itens = this.getItensPreVenda(dados_item);
			if (itens.size() != 0)
				dados_item.itemPreVenda = itens;

			gp.add(dados_item);

		}
		return gp;
	}

	private ArrayList<ItemPreVendaVO> getItensPreVenda(PreVendaVO item) {
		String sql = "select * FROM itensprevenda WHERE codPrevenda = "
				+ item.codigo;
		ResultSet rs = banco.executar(sql);
		return this.toItemPreVenda(rs);
	}

	private ArrayList<ItemPreVendaVO> toItemPreVenda(ResultSet rs)
			throws SQLException {
		ArrayList<ItemPreVendaVO> gp = new ArrayList<ItemPreVendaVO>();
		while (rs.next()) {
			ItemPreVendaVO dados_item = new ItemPreVendaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codigoPrevenda = rs.getInt("codPrevenda");
			dados_item.codProduto = rs.getInt("codProduto");
			dados_item.descricao = rs.getString("descricao");
			dados_item.quantidade = rs.getInt("quantidade");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
}
