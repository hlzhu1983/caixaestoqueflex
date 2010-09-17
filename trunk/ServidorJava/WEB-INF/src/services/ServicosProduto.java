package services;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.rowset.spi.SyncResolver;

import vo.ProdutoVO;


public class ServicosProduto {
	
	
	public synchronized ProdutoVO addProduto(ProdutoVO item){
		String sql = "insert into produto (codBarra,codGrupo,descricao ,referencia ,codLocal ,codUnidade ,qtdPorUnidade ,qtdEmEstoque ,codFornecedor ,precoCompra ,precoVenda,foto) values ('"+item.codBarra+"' ,"+item.codGrupo+" ,'"+item.descricao+"'"+
	 ",'"+item.referencia+"' ,"+item.codLocal+""+
		" ,"+item.codUnidade+" ,"+item.qtdPorUnidade+""+
		 ","+item.qtdEmEstoque+" ,"+item.codFornecedor+""+
		 ","+item.precoCompra+" ,"+item.precoVenda+""+
		 ",'"+item.foto+"')";
		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar Produto!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar Produto!");
		this.banco.getConexao().commit();
		return item;
		
	}
	
	public synchronized boolean  removerProduto(ProdutoVO item){
		String sql = "delete from produto  where codigo = "+item.codigo+"";
		if(this.banco.executarNoQuery(sql)==0){
			return false;
		}
		return true;
	}
	
	public synchronized ProdutoVO atualizarProduto(ProdutoVO produto) {
		String sql = "UPDATE produto SET codBarra = '"+produto.codBarra+"', codGrupo = "+produto.codGrupo+", descricao = '"+produto.descricao+"', referencia = '"+produto.referencia+"',codLocal = "+produto.codLocal+",codUnidade="+produto.codUnidade+",qtdPorUnidade="+produto.qtdPorUnidade+",qtdEmEstoque="+produto.qtdEmEstoque+",codFornecedor="+produto.codFornecedor+",precoCompra="+produto.precoCompra+","+
		"precoVenda="+produto.precoVenda+",foto='"+produto.foto+"' where codigo="+produto.codigo+"";
		if(this.banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao atualizar Produto");
		}
		return produto;	
		
	}
	
	public ArrayList<ProdutoVO> pesquisarProduto(String texto,String coluna){
		String sql = "select * from produto where "+coluna+" like '%"+texto+"%'";
		ResultSet rs =  banco.executar(sql);
		return this.toProdutos(rs);
	}
	
	public ArrayList<ProdutoVO> getProduto(String texto,String coluna){
		String sql = "select * from produto where "+coluna+" = '"+texto+"'";
		ResultSet rs =  banco.executar(sql);
		return  this.toProdutos(rs);
	}
	
	public ArrayList<ProdutoVO> getProdutos(){
		String sql = "select * from produto";
		ResultSet rs =  banco.executar(sql);
		return this.toProdutos(rs);
	}

	public ArrayList<ProdutoVO> getProdutos(String sql){
		ResultSet rs =  banco.executar(sql);
		return this.toProdutos(rs);
	}
	
	public boolean isExiste(ProdutoVO produto) {
		String sql = "select * from produto where codigo = '"
				+ produto.codigo + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}
	
	private ArrayList<ProdutoVO> toProdutos(ResultSet rs) throws SQLException{
		
		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		while(rs.next()){
			ProdutoVO dados_item = new ProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codBarra = rs.getString("codBarra");
			dados_item.codGrupo = rs.getInt("codGrupo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.referencia = rs.getString("referencia");
			dados_item.codLocal = rs.getInt("codLocal");
			dados_item.codUnidade = rs.getInt("codUnidade");
			dados_item.qtdPorUnidade = rs.getInt("qtdPorUnidade");
			dados_item.qtdEmEstoque = rs.getInt("qtdEmEstoque");
			dados_item.codFornecedor = rs.getInt("codFornecedor");
			dados_item.precoCompra = rs.getDouble("precoCompra");
			dados_item.precoVenda = rs.getDouble("precoVenda");
			dados_item.foto = rs.getString("foto");
			produtos.add(dados_item);
		}
		return produtos;
	}	


	
}

