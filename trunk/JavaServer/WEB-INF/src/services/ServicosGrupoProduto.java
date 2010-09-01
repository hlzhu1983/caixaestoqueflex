package services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import exception.GrupoProdutoJaExistenteException;

import vo.GrupoProdutoVO;
import arquitetura.BancoDeDados;





class ServicosGrupoProduto {
	
	
	private BancoDeDados banco;
	
	public ServicosGrupoProduto() {
		
	}
	
	public GrupoProdutoVO addGrupoProduto(GrupoProdutoVO grupoproduto){
		
		String sql = "insert into grupoproduto (descricao) values ('"+grupoproduto.descricao+"')";
		
		if(banco.executarNoQuery(sql)==0){
			try {
				throw new GrupoProdutoJaExistenteException();
			} catch (GrupoProdutoJaExistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return grupoproduto;
		
	}
	
	public boolean removerGrupoProduto(GrupoProdutoVO grupoproduto){
		
		String sql = "delete from grupoproduto  where codigo = "+grupoproduto.codigo+"";
		
		if(banco.executarNoQuery(sql)==0){
			return false;
		}
		return true;
	}
	
	public Map<Integer, GrupoProdutoVO> pesquisarGrupoProduto(String texto,String coluna){
		String sql = "select * from grupoproduto where "+coluna+" like '%"+texto+"%'";
		Map<Integer,GrupoProdutoVO> gp = null;
		try{
			ResultSet rs =  banco.executar(sql);
			
			int i=0;
			while(rs.next()){
				
				GrupoProdutoVO dados_item = new GrupoProdutoVO();
				dados_item.codigo = rs.getInt("codigo");
				dados_item.descricao = rs.getString("descricao");
				
				gp.put(i, dados_item);
			i++;
			}
			
				
			}catch (SQLException e) {
			 System.out.println("erro");
			}
			return gp;
			}

	
	
	public  Map<Integer, GrupoProdutoVO> getGrupoProdutos(){
		String sql = "select * from grupoproduto";
		Map<Integer,GrupoProdutoVO> gp = null;
		try{
			ResultSet rs =  banco.executar(sql);
			
			int i=0;
			while(rs.next()){
				
				GrupoProdutoVO dados_item = new GrupoProdutoVO();
				dados_item.codigo = rs.getInt("codigo");
				dados_item.descricao = rs.getString("descricao");
				
				gp.put(i, dados_item);
			i++;
			}
			
				
			}catch (SQLException e) {
			 System.out.println("erro");
			}
			return gp;
			}
}
