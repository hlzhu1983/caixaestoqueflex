package services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import vo.ItemCompraVO;
import vo.CompraVO;
import vo.ProdutoVO;

public class ServicosCompra {

	public CompraVO fecharCompra(CompraVO item) {
		String sql = "insert into compra (codUsuario,codFornecedor,dataCompra,NF) values ("
				+ item.codUsuario
				+ ","
				+ item.codFornecedor
				+ " ,now(),"
				+ item.NF + ")";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir Compra!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			item.codigo = rs.getInt(1);
			for (ItemCompraVO itemCompra : item.itemCompra) {
				itemCompra.codigoCompra = item.codigo;
				this.adicionarItemCompra(itemCompra,st);
			}
		} else
			throw new RuntimeException("Erro ao salvar Compra!");

		this.banco.getConexao().commit();

		return item;
	}

	public ItemCompraVO adicionarItemCompra(ItemCompraVO item,Statement st) throws SQLException {
		String sql = "select * from produto where codigo = " + item.codProduto;

		ArrayList<ProdutoVO> itens = new ServicosProduto().getProdutos(sql);
		;
		if (itens.size() == 0) {
			throw new RuntimeException("Produto não existe");
		}
		ProdutoVO registro = itens.get(0);

		sql = "insert into itensCompra (codCompra, codProduto, quantidade,valorCompra)"
				+ " values ('"
				+ item.codigoCompra
				+ "','"
				+ item.codProduto
				+ "','" + item.quantidade + "','" + item.valorDeCompra + "')";
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao Adicionar ItemCompra");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);

		// adicionando ao produto a quantidade
		sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "
				+ item.quantidade + ") where codigo = " + item.codProduto;

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar itemprevenda");
		}

		return item;

	}

	public void removerItemCompra(ItemCompraVO item) {

		String sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque - "
				+ item.quantidade + ")  WHERE codigo = " + item.codProduto;

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar ItemCompra");
		}
		sql = "DELETE FROM itensCompra WHERE codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Deletar ItemCompra");
		}

		this.banco.getConexao().commit();

	}

	public ArrayList<CompraVO> getAllItensCompra(String codigo) {
		String sql = "select * from itensCompra where codCompra  = " + codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toCompra(resultado);
	}

	public ArrayList<CompraVO> pesquisarItens(String texto, String coluna) {
		String sql = "select * from compra where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toCompra(resultado);
	}

	public ArrayList<CompraVO> getItens() {
		String sql = "select * from compra";

		ResultSet rs = banco.executar(sql);
		return this.toCompra(rs);
	}

	private ArrayList<CompraVO> toCompra(ResultSet rs) throws SQLException {
		ArrayList<CompraVO> gp = new ArrayList<CompraVO>();
		while (rs.next()) {
			CompraVO dados_item = new CompraVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codUsuario = rs.getInt("codUsuario");
			dados_item.dataCompra = rs.getString("dataAbertura");
			dados_item.NF = rs.getString("obs");
			dados_item.codFornecedor = rs.getInt("status");

			ArrayList<ItemCompraVO> itens;
			itens = this.getItensCompra(dados_item);
			if (itens.size() != 0)
				dados_item.itemCompra = itens;

			gp.add(dados_item);

		}
		return gp;
	}

	private ArrayList<ItemCompraVO> getItensCompra(CompraVO item) {
		String sql = "select * FROM itensCompra WHERE codCompra = "
				+ item.codigo;
		ResultSet rs = banco.executar(sql);
		return this.toItemCompra(rs);
	}

	private ArrayList<ItemCompraVO> toItemCompra(ResultSet rs)
			throws SQLException {
		ArrayList<ItemCompraVO> gp = new ArrayList<ItemCompraVO>();
		while (rs.next()) {
			ItemCompraVO dados_item = new ItemCompraVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codProduto = rs.getInt("codProduto");
			dados_item.quantidade = rs.getInt("quantidade");
			dados_item.valorDeCompra = rs.getDouble("valor");
			dados_item.codigoCompra = rs.getInt("codCompra");
			gp.add(dados_item);
		}
		return gp;
	}
}