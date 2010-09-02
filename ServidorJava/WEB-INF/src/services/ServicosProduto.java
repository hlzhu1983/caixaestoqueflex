package services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import vo.ProdutoVO;
import arquitetura.BancoDeDados;


class ServicosProduto {
	
	
	private BancoDeDados banco;
	
	
	
	public ProdutoVO addProduto(ProdutoVO item){
		
		String sql = "insert into produto (codBarra,codGrupo,descricao ,referencia ,codLocal ,codUnidade ,qtdPorUnidade ,qtdEmEstoque ,codFornecedor ,precoCompra ,precoVenda,foto) values ('"+item.codBarra+"' ,"+item.codGrupo+" ,'"+item.descricao+"'"+
	 ",'"+item.referencia+"' ,"+item.codLocal+""+
		" ,"+item.codUnidade+" ,"+item.qtdPorUnidade+""+
		 ","+item.qtdEmEstoque+" ,"+item.codFornecedor+""+
		 ","+item.precoCompra+" ,"+item.precoVenda+""+
		 ",'"+item.foto+"')";
		
		if(this.banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Adicionar Produto");
		}
		return item;
		
	}
	
	public boolean removerProduto(ProdutoVO item){
		if(item.codigo==0){
			throw new RuntimeException("Produto não pode ser resolvido!");
		}
		String sql = "delete from produto  where codigo = "+item.codigo+"";
		
		if(this.banco.executarNoQuery(sql)==0){
			return false;
		}
		return true;
	}
	
	public Map<Integer, ProdutoVO> pesquisarProduto(String texto,String coluna){
		String sql = "select * from produto where "+coluna+" like '%"+texto+"%'";
		ResultSet rs =  banco.executar(sql);
		Map<Integer,ProdutoVO> produtos = null;
		int i=0;
		try{
		while(rs.next()){
			
			ProdutoVO dados_item = new ProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codBarra = rs.getString("codBarra");
			dados_item.codGrupo = rs.getInt("codGrupo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.referencia = rs.getString("referencia");
			dados_item.codLocal = rs.getInt("codLocal");
			dados_item.codUnidade = rs.getInt("codUnidade");
			dados_item.qtdPorUnidade = rs.getInt("qtdPorEstoque");
			dados_item.qtdEmEstoque = rs.getInt("qtdEmEstoque");
			dados_item.codFornecedor = rs.getInt("codFornecedor");
			dados_item.precoCompra = rs.getDouble("precoCompra");
			dados_item.precoVenda = rs.getDouble("precoVenda");
			dados_item.foto = rs.getString("foto");
			produtos.put(i, dados_item);
		i++;
		}
		}catch (SQLException e) {
			System.out.println("erro");
		}
		
		
		return produtos;
	}
	
	public Map<Integer, ProdutoVO> getProduto(String texto,String coluna){
		String sql = "select * from produto where "+coluna+" like '%"+texto+"%'";
		ResultSet rs =  banco.executar(sql);
		Map<Integer,ProdutoVO> produtos = null;
		int i=0;
		try{
		while(rs.next()){
			
			ProdutoVO dados_item = new ProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codBarra = rs.getString("codBarra");
			dados_item.codGrupo = rs.getInt("codGrupo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.referencia = rs.getString("referencia");
			dados_item.codLocal = rs.getInt("codLocal");
			dados_item.codUnidade = rs.getInt("codUnidade");
			dados_item.qtdPorUnidade = rs.getInt("qtdPorEstoque");
			dados_item.qtdEmEstoque = rs.getInt("qtdEmEstoque");
			dados_item.codFornecedor = rs.getInt("codFornecedor");
			dados_item.precoCompra = rs.getDouble("precoCompra");
			dados_item.precoVenda = rs.getDouble("precoVenda");
			dados_item.foto = rs.getString("foto");
			produtos.put(i, dados_item);
		i++;
		}
		}catch (SQLException e) {
			System.out.println("erro");
		}
		
		
		return produtos;
	}
	
	public Map<Integer, ProdutoVO> getProdutos(){
		String sql = "select * from produto";
		ResultSet rs =  banco.executar(sql);
		Map<Integer,ProdutoVO> produtos = null;
		int i=0;
		try{
		while(rs.next()){
			
			ProdutoVO dados_item = new ProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codBarra = rs.getString("codBarra");
			dados_item.codGrupo = rs.getInt("codGrupo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.referencia = rs.getString("referencia");
			dados_item.codLocal = rs.getInt("codLocal");
			dados_item.codUnidade = rs.getInt("codUnidade");
			dados_item.qtdPorUnidade = rs.getInt("qtdPorEstoque");
			dados_item.qtdEmEstoque = rs.getInt("qtdEmEstoque");
			dados_item.codFornecedor = rs.getInt("codFornecedor");
			dados_item.precoCompra = rs.getDouble("precoCompra");
			dados_item.precoVenda = rs.getDouble("precoVenda");
			dados_item.foto = rs.getString("foto");
			produtos.put(i, dados_item);
		i++;
		}
		}catch (SQLException e) {
			System.out.println("erro");
		}
		
		
		return produtos;
	}

	
	public Map<Integer, ProdutoVO> filtroProduto(String[] sqlArray){
		String sql = "select * from produto where ";
		boolean entrou = false;
		for (String string : sqlArray) {
			if(string != ""){
				if(entrou){
					sql = ""+sql+" and "+string+" ";
				}else{
					sql = ""+sql+" "+string+" ";
					entrou = true;
				}
			}
		}
		
		return this.toProdutos(this.banco.executar(sql));	
	}
	
	private Map<Integer, ProdutoVO> toProdutos(ResultSet rs){
		
		Map<Integer,ProdutoVO> produtos = null;
		int i=0;
		try{
		while(rs.next()){
			
			ProdutoVO dados_item = new ProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codBarra = rs.getString("codBarra");
			dados_item.codGrupo = rs.getInt("codGrupo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.referencia = rs.getString("referencia");
			dados_item.codLocal = rs.getInt("codLocal");
			dados_item.codUnidade = rs.getInt("codUnidade");
			dados_item.qtdPorUnidade = rs.getInt("qtdPorEstoque");
			dados_item.qtdEmEstoque = rs.getInt("qtdEmEstoque");
			dados_item.codFornecedor = rs.getInt("codFornecedor");
			dados_item.precoCompra = rs.getDouble("precoCompra");
			dados_item.precoVenda = rs.getDouble("precoVenda");
			dados_item.foto = rs.getString("foto");
			produtos.put(i, dados_item);
		i++;
		}
		}catch (SQLException e) {
			System.out.println("erro");
		}
		
		
		return produtos;
	}	


	public ProdutoVO atualizarProduto(ProdutoVO produto) {
		if(produto.codigo == 0){
			throw new RuntimeException("Produto não pôde ser atualizado!");
		}
		String sql = "UPDATE produto SET codBarra = '"+produto.codBarra+"', codGrupo = "+produto.codGrupo+", descricao = '"+produto.descricao+"', referencia = '"+produto.referencia+"',codLocal = "+produto.codLocal+",codUnidade="+produto.codUnidade+",qtdPorUnidade="+produto.qtdPorUnidade+",qtdEmEstoque="+produto.qtdEmEstoque+",codFornecedor="+produto.codFornecedor+",precoCompra="+produto.precoCompra+","+
		"precoVenda="+produto.precoVenda+",foto='"+produto.foto+"' where codigo="+produto.codigo+"";
		if(this.banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao atualizar Produto");
		}
		return produto;	
		
	}
}

