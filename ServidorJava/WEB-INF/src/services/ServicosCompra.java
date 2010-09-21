package services;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


import vo.ItemCompraVO;
import vo.CompraVO;
import vo.ProdutoVO;

public class ServicosCompra {

	public CompraVO fecharCompra(CompraVO item) {
		
		String sql = "insert into compra (codUsuario,codFornecedor,dataCompra,NF) values ("
				+ item.codUsuario
				+ ","
				+ item.codFornecedor
				+ " ,now(),'"
				+ item.NF + "')";

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
				this.adicionarItemCompra(itemCompra, st);
			}
		} else
			throw new RuntimeException("Erro ao salvar Compra!");

		this.banco.getConexao().commit();

		return item;
	}

	public ItemCompraVO adicionarItemCompra(ItemCompraVO item, Statement st)
			throws SQLException {
		
		String sql = "select * from produto where codigo = " + item.codProduto;

		ArrayList<ProdutoVO> itens = new ServicosProduto().getProdutos(sql);
		
		if (itens.size() == 0) {
			throw new RuntimeException("Produto não existe");
		}
		ProdutoVO produto = itens.get(0);

		sql = "insert into itensCompra (codCompra, codProduto, quantidade,valorCompra)"
				+ " values ('"
				+ item.codigoCompra
				+ "','"
				+ item.codProduto
				+ "','" + item.quantidade + "','" + item.valorCompra + "')";
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

	public void retirarItemCompra(ItemCompraVO item,Statement st) throws SQLException {
		
		String sql = "select * from produto where codigo = " + item.codProduto;

		ArrayList<ProdutoVO> itens = new ServicosProduto().getProdutos(sql);
		
		if (itens.size() == 0) {
			throw new RuntimeException("Produto não existe");
		}
		
		ProdutoVO registro = itens.get(0);
		
     if(registro.qtdEmEstoque>=item.quantidade){
		 sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque - "
				+ item.quantidade + ")  WHERE codigo = " + item.codProduto;

		 if (st.executeUpdate(sql) == 0) {
				throw new RuntimeException("Erro ao atualizar produto");
			}
    
		sql = "DELETE FROM itensCompra WHERE codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao Deletar ItemCompra");
		}
		
     }else{
    	 throw new RuntimeException("Quantidade em produto é insuficiênte");
     }
     
     
	}

	public ArrayList<ItemCompraVO> getAllItensCompra(String codigo) {
		String sql = "select * from itensCompra where codCompra  = " + codigo;
		ResultSet resultado = banco.executar(sql);

		return this.toItemCompra(resultado);
	}

	public ArrayList<CompraVO> pesquisarCompra(String texto, String coluna) {
		String sql  ="";
		
		if(coluna.equals("dataCompra")){
			 sql = "select *,date_format(dataCompra, '%d/%m/%Y') as dataEntrada from compra where date_format(" + coluna + ",'%d/%m/%Y')='"
			+ texto + "'";
		}else if(coluna.equals("dataIntervalo")){
			String[] valor = texto.split("#");
			sql = "select *,date_format(dataCompra, '%d/%m/%Y') as dataEntrada from compra where date_format(datacompra,'%d/%m/%Y') BETWEEN '"+valor[0]+"' AND '"+valor[1]+"'";
			//throw new RuntimeException(sql);
		}else{
		 sql = "select *,date_format(dataCompra, '%d/%m/%Y') as dataEntrada from compra where " + coluna + " like '%"
		+ texto + "%'";			
				}
		ResultSet resultado = banco.executar(sql);

		ArrayList<CompraVO> retorno = this.toCompra(resultado);
		for (CompraVO compraVO : retorno) {
			compraVO.itemCompra = this.getItensCompra(compraVO);
		}
		return retorno;

	}
	public ArrayList<ItemCompraVO> pesquisarItens(String texto, String coluna) {
		String sql = "select * from itensCompra where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toItemCompra(resultado);

	}

	public CompraVO atualizarCompra(CompraVO item) {
		
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();
	   for (int i = 0; i < item.itemCompra.size(); i++) {
		   
		   ItemCompraVO itemCompra = item.itemCompra.get(i);
		   
		   itemCompra.codigoCompra = item.codigo;
			if(itemCompra.status==0){
			this.updateItemCompra(itemCompra,st);
			
			}else{
				
				this.retirarItemCompra(itemCompra,st);
				item.itemCompra.remove(i);
			}
	   }
		
		
		

		
		
		
		String sql = "UPDATE compra set codUsuario =" + item.codUsuario
				+ " , codFornecedor =" + item.codFornecedor
				+ " , dataCompra = now(), NF ='" + item.NF
				+ "' " + "where  codigo =" + item.codigo;

		
		
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao abrir Compra!");
		}

			
		this.banco.getConexao().commit();
		return item;
	}

	public void removerCompra(CompraVO item) {
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		Statement st = this.banco.getConexao().createStatement();
		
		for (int i = 0; i < item.itemCompra.size(); i++) {
			ItemCompraVO itemCompra = item.itemCompra.get(i);
		
			itemCompra.codigoCompra = item.codigo;
			
				
				this.retirarItemCompra(itemCompra,st);
				
			
				
		}
		
		String sql = "DELETE FROM compra where  codigo =" + item.codigo;

		
		
		if (st.executeUpdate(sql)==0) {
			throw new RuntimeException("Erro ao remover Compra!");
		}
		
		
				
		this.banco.getConexao().commit();
		
	}

	private void updateItemCompra(ItemCompraVO itemCompra, Statement st) throws SQLException {
		
		String sql = "";
		double quantidade = 0;
		
		
		if (itemCompra.codigo == 0) {
        
			this.adicionarItemCompra(itemCompra, st);

		} else {
			
			sql = "select * from produto where codigo = " + itemCompra.codProduto;

			ArrayList<ProdutoVO> itensProduto = new ServicosProduto().getProdutos(sql);
				
				
			if (itensProduto.size() == 0) {
					throw new RuntimeException("Produto não existe");
			}
				
				
			ProdutoVO registro = itensProduto.get(0);

				
			ArrayList<ItemCompraVO> itens = this
						.pegarItensCompra(itemCompra.codigo + "",st);

				if (itens.size() == 0) {
					throw new RuntimeException("Item não existe");
				}
				
				ItemCompraVO icompra = itens.get(0);
				
				if (icompra.quantidade != itemCompra.quantidade) {
               
					
					if(registro.qtdEmEstoque+(itemCompra.quantidade-icompra.quantidade)<0){
                	throw new RuntimeException("Não pode atualizar Item");
					}else{
                	quantidade = itemCompra.quantidade-icompra.quantidade;
                	sql = "UPDATE itensCompra set codCompra = "
						+ itemCompra.codigoCompra + ",codProduto ="
						+ itemCompra.codProduto + " ,quantidade ="
						+ itemCompra.quantidade + ",valorCompra = "
						+ itemCompra.valorCompra + " where codigo="
						+ itemCompra.codigo;
                	if(st.executeUpdate(sql)==0){
   					 throw new RuntimeException("Não foi possível atualizar o item");
   				 
   					}
					}
                
					
					} else {
					quantidade = 0;
					 sql = "UPDATE itensCompra set codCompra = "
							+ itemCompra.codigoCompra + ",codProduto ="
							+ itemCompra.codProduto + " ,quantidade ="
							+ itemCompra.quantidade + ",valorCompra = "
							+ itemCompra.valorCompra + " where codigo="
							+ itemCompra.codigo;
					 
					 if(st.executeUpdate(sql)==0){
						 throw new RuntimeException("Não foi possível atualizar o item");
					 }
				}
				registro.qtdEmEstoque = quantidade;
				//atualizando o produto resultante
				sql = "UPDATE produto SET qtdEmEstoque = "
					+ registro.qtdEmEstoque + ") where codigo = " + registro.codigo;

			if (st.executeUpdate(sql) == 0) {
				throw new RuntimeException("Erro ao atualizar itemprevenda");
			}

				
		}
	}

	private ArrayList<ItemCompraVO> pegarItensCompra(String codigo,Statement st) throws SQLException {
		String sql = "select * from itensCompra where codigo  = " + codigo;
		ResultSet resultado = st.executeQuery(sql);

		return this.toItemCompra(resultado);
	}

	public ArrayList<CompraVO> getItens() {
		String sql = "select *,date_format(dataCompra, '%d/%m/%Y') as dataEntrada from compra";

		ResultSet rs = banco.executar(sql);
		ArrayList<CompraVO> retorno = this.toCompra(rs);
		for (CompraVO compraVO : retorno) {
			compraVO.itemCompra = this.getItensCompra(compraVO);
		}
		return retorno;
	}

	private ArrayList<CompraVO> toCompra(ResultSet rs) throws SQLException {
		ArrayList<CompraVO> gp = new ArrayList<CompraVO>();
		while (rs.next()) {
			CompraVO dados_item = new CompraVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codUsuario = rs.getInt("codUsuario");
			
			dados_item.dataCompra =  rs.getString("dataEntrada");
			
			dados_item.NF = rs.getString("NF");
			dados_item.codFornecedor = rs.getInt("codFornecedor");

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
			dados_item.valorCompra = rs.getDouble("valorCompra");
			dados_item.codigoCompra = rs.getInt("codCompra");
			gp.add(dados_item);
		}
		return gp;
	}

	
}