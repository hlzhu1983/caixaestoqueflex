package services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import vo.ItensCompraVO;
import vo.CompraVO;
import vo.ProdutoVO;

public class ServicosCompra {

	public CompraVO fecharCompra(CompraVO item) {
		String sql = "insert into compra (codUsuario,codFornecedor,dataCompra,NF) values ("
				+ item.codUsuario + ","+item.codFornecedor+" ,now(),"+item.NF+")";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir Compra!");
		}
     
		
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()){
			item.codigo = rs.getInt(1);
		 for (ItensCompraVO itemCompra : item.itemCompra) {
				itemCompra.codigoCompra = item.codigo;
				this.addItemCompra(itemCompra);
				}
		}else
			throw new RuntimeException("Erro ao salvar Compra!");
		
		
		
		this.banco.getConexao().commit();
		
		return item;
	}

	public ItensCompraVO addItemCompra(ItensCompraVO item) {
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
		
		sql = "insert into itensCompra (codCompra, codProduto, quantidade,valorCompra)"
				+ " values ('"
				+ item.codigoCompra
				+ "','"
				+ item.codProduto
				+ "','"
				+ item.quantidade
				+ "','"
				+ item.valorDeCompra + "')";
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao Adicionar ItemCompra");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);

		//adicionando ao produto a quantidade
		sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "
				+ item.quantidade + ") where codigo = " + item.codProduto;

		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao atualizar itemprevenda");
		}
		this.banco.getConexao().commit();

		return item;

	}

	

	public void removerItemCompra(ItensCompraVO item) {

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
		String sql = "select * from itensCompra where codCompra  = "
				+ codigo;
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
			

			ArrayList<ItensCompraVO> itens;
			itens = this.getItensCompra(dados_item);
			if (itens.size() != 0)
				dados_item.itemCompra = itens;

			gp.add(dados_item);

		}
		return gp;
	}

	private ArrayList<ItensCompraVO> getItensCompra(CompraVO item) {
		String sql = "select * FROM itensprevenda WHERE codPrevenda = "
				+ item.codigo;
		ResultSet rs = banco.executar(sql);
		return this.toItemCompra(rs);
	}

	private ArrayList<ItensCompraVO> toItemCompra(ResultSet rs)
			throws SQLException {
		ArrayList<ItensCompraVO> gp = new ArrayList<ItensCompraVO>();
		while (rs.next()) {
			ItensCompraVO dados_item = new ItensCompraVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codProduto = rs.getInt("codProduto");		
			dados_item.quantidade = rs.getInt("quantidade");
			dados_item.valorDeCompra = rs.getDouble("valor");
			dados_item.codigoCompra = rs.getInt("codigoCompra");
			gp.add(dados_item);
		}
		return gp;
	}
}
