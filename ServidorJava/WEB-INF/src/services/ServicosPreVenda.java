import java.sql.ResultSet;
import java.util.ArrayList;

import vo.ItemPreVendaVO;
import vo.PreVendaVO;
import arquitetura.BancoDeDados;


public class ServicosPreVenda {
	
	

	
	

	
	
	public PreVendaVO abrirPreVenda(PreVendaVO item){
	//	f = fopen('log2.txt',"w+");
	String sql = "insert into prevenda (codUsuario,status,dataAbertura) values ("+item.codUsuario+",0 ,now())";
		
		
		if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Adicionar Cliente");
		}	
		
		
		return item;
	}
	
	public ItemPreVendaVO addItemPreVenda(ItemPreVendaVO item){
		String	sql ="select * from produto where codigo = "+item.codProduto;
			
		ResultSet result = banco.executar(sql);	
		ArrayList<ItemPreVendaVO> itens  = this.toItemPreVenda(result);
		if(itens.size()==0){
				throw new RuntimeException("Produto não existe");
			}
			ItemPreVendaVO registro = itens.get(0);
			if(registro.quantidade < item.quantidade){
				throw new RuntimeException("Quantidade de produtos maior que disponível!");
			}
			sql = "insert into itensprevenda (codPrevenda, codProduto, descricao, quantidade,valor)"+ 
			      "values ('"+item.codigoPrevenda+"','"+item.codProduto+"','"+item.descricao+"','"+item.quantidade+"','"+item.valor+"')";
			if(banco.executarNoQuery(sql)==0){
				throw new RuntimeException("Erro ao Adicionar ItemPrevenda");
			}
					
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque - "+item.quantidade+") where codigo = "+item.codProduto;
			
			if(banco.executarNoQuery(sql)==0){
				throw new RuntimeException("Erro ao atualizar itemprevenda");
			}
			
			
			return item;
			
	}
	
	
//	foreach (item.itemPreVenda as temp) {
//			sql = "insert into itensprevenda (codPrevenda, codProduto, quantidade,valor) 
//			      values ('item.codigo','temp.codProduto','temp.quantidade','temp.valor')";
//			this.conn.Execute(sql);
//		}
	
	public PreVendaVO fecharPreVenda(PreVendaVO item){
String sql = "UPDATE prevenda SET codUsuario = '"+item.codUsuario+"' , status = '1', obs = '"+item.obs+"', valorTotal = '"+item.valorTotal+"' where codigo = "+item.codigo+"";		
if(banco.executarNoQuery(sql)==0){
	throw new RuntimeException("Erro ao fechar Prevenda");
}
		return item;
	}
	
	public void removerItemPreVenda(ItemPreVendaVO item){
		
		String sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "+item.quantidade+")  WHERE codigo = "+item.codProduto;
		if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao atualizar ItemPrevenda");
		}
		sql = "DELETE FROM itensprevenda WHERE codigo = "+item.codigo;
		if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Deletar ItemPrevenda");
		}
	}
	
	public PreVendaVO cancelarPreVenda(PreVendaVO item){	
		String sql = "select * FROM itensprevenda WHERE codPrevenda = item.codigo";
		this.conn.StartTrans();
		ResultSet resultado = banco.executar(sql);
		ArrayList<ItemPreVendaVO> itens= this.toItemPreVenda(resultado);
		for (int i = 0; i < itens.size(); i++) {
			ItemPreVendaVO registro;
		
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "+registro.quantidade+")  WHERE codigo = "+registro.codProduto;
			if(banco.executarNoQuery(sql)==0){
				throw new RuntimeException("Erro ao atualizar produto");
			}		
		}
		sql = "DELETE FROM itensprevenda WHERE codPrevenda = "+item.codigo;
		if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Deletar Prevenda");
		}
		sql = "UPDATE prevenda SET codUsuario = '"+item.codUsuario+"' , status = '2', obs = '"+item.obs+"' where codigo = "+item.codigo;
		if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Atualizar Prevenda");
		}
		return item;
	}
	
	
	public boolean removerItem(PreVendaVO item){
		
		String sql = "delete from prevenda  where codigo = item.codigo";
		
		if(banco.executarNoQuery(sql)==0){
			return false;
		}else{
			return true;
		}
	}
	
	public ArrayList<PreVendaVO> filtraData(String data,String coluna){
		String sql = "select * from prevenda where DATE("+coluna+") = '"+data+"' and status = 1";
		ResultSet result = banco.executar(sql);
		return this.toPreVenda(result);
	}
	
	private ArrayList<PreVendaVO> getItensPrevenda(String codigo){
		String sql = "select * from itensprevenda where codPrevenda  = "+codigo;
		ResultSet resultado = banco.executa(sql);
		
		return this.toPreVenda(resultado);
	}
	
	
	
	public ArrayList<PreVendaVO> pesquisarItens(String texto,String coluna){
      String sql = "select * from prevenda where "+coluna+" like '%"+texto+"%'";
		ResultSet resultado = banco.executar(sql);
		
		return this.toPreVenda(resultado);
	}
	
	public ArrayList<PreVendaVO> getItens(){
		String sql = "select * from prevenda";
	
	ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);	
	}
	
	public ArrayList<PreVendaVO> getItensValidos(){
		String sql = "select * from prevenda where status = 1";
		ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);	
	}
	
	public ArrayList<PreVendaVO> pesquisarItensValidos(String texto,String coluna){
		String sql = "select * from prevenda where "+coluna+" like '%"+texto+"%' and status = 1";
		ResultSet rs = banco.executar(sql);
		return this.toPreVenda(rs);
	}
	
	
	private ArrayList<PreVendaVO> toPreVenda(ResultSet rs){
		  ArrayList<PreVendaVO> gp = new ArrayList<PreVendaVO>();
		while (rs.next()) {
			PreVendaVO dados_item = new PreVendaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codUsuario = rs.getInt("codUsuario");
			dados_item.dataAbertura = rs.getDate("dataAbertura");
			dados_item.obs = rs.getString("obs");
			dados_item.status = rs.getInt("status");
			dados_item.valorTotal = rs.getInt("valorTotal");
			
			
			
			

			gp.add(dados_item);
		}
		return gp;
		}
	
	
	private ArrayList<ItemPreVendaVO> toItemPreVenda(ResultSet rs) {
		
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
