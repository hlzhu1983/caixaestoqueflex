import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.GrupoProdutoVO;
import vo.OrcamentoVO;


public class ServicosOrcamento {
	
	
	
	
	
	
	public OrcamentoVO abrirOrcamento(OrcamentoVO item){
	 String sql = "insert into orcamento (codUsuario,status,dataAbertura) values ('"+item.codUsuario+"','0' ,now())";
		
	 
	 if(banco.executarNoQuery(sql)==0){
			throw new RuntimeException("Erro ao Abrir Orcamento");
		}
		
		return item;
	}
	
	
	
	
	
	
	/*public OrcamentoVO fecharPreVenda(OrcamentoVO item){
		foreach (item->itemPreVenda as temp) {
			sql = "insert into itensprevenda (codPrevenda, codProduto, quantidade,valor) 
			      values ('item->codigo','temp->codProduto','temp->quantidade','temp->valor')";
			this->conn->Execute(sql);
		}
		if(item->codCliente==0){
			sql = "UPDATE prevenda SET codUsuario = 'item->codUsuario' , status = '1', obs = 'item->obs', desconto = 'item->desconto', valorTotal = 'item->valorTotal' where codigo = item->codigo";
		}else{
			sql = "UPDATE prevenda SET codUsuario = 'item->codUsuario' , codCliente = 'item->codCliente', status = '1', obs = 'item->obs', desconto = 'item->desconto', valorTotal = 'item->valorTotal' where codigo = item->codigo";	
		}
		this->conn->Execute(sql);
		return item;
	}*/
	
		
	public OrcamentoVO cancelarOrcamento(OrcamentoVO item){	
		String sql = "select * FROM itensprevenda WHERE codPrevenda = "+item.codigo+"";
		
		ResultSet resultado = banco.execute(sql);
		ArrayList<OrcamentoVO> orc= new ArrayList<OrcamentoVO>();
			orc =this.toOrcamento(resultado);
		
		for (int i = 0; i < orc.size(); i++) {
			OrcamentoVO orcamento = orc.get(i);
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "+ orcamento. +")  WHERE codigo = registro->CODPRODUTO";	
		}
		
		while(registro = resultado->FetchNextObject()){
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + registro->QUANTIDADE)  WHERE codigo = registro->CODPRODUTO";
			this->conn->Execute(sql);			
		}
		sql = "DELETE FROM itensprevenda WHERE codPrevenda = item->codigo";
		this->conn->Execute(sql);
		sql = "UPDATE prevenda SET codUsuario = 'item->codUsuario' , status = '2', obs = 'item->obs' where codigo = item->codigo";
		this->conn->Execute(sql);
		falhou = false;
		falhou = false;	
		if(this->conn->HasFailedTrans()){
			falhou = true;
		}
		this->conn->CompleteTrans();
		if(falhou){
			throw new Exception("Erro ao cancelar pré-venda. Pré-venda não cancelada!",100);
		}
		return item;
	}
	
	
	public OrcamentoVO removerItem(PreVendaVO item){
		
		sql = "delete from prevenda  where codigo = item->codigo";
		resultado = this->conn->Execute(sql);
		if(this->conn->Affected_Rows()==0){
			return false;
		}
		return true;
	}
	
	public OrcamentoVO pesquisarItens(texto,coluna){
		sql = "select * from prevenda where coluna like '%texto%'";
		resultado = this->conn->Execute(sql);
		while(registro = resultado->FetchNextObject()){			
			dados_item = new PreVendaVO();
			dados_item->codigo = registro->CODIGO;
			dados_item->codCliente = registro->CODCLIENTE;
			dados_item->codUsuario = registro->CODUSUARIO;
			dados_item->obs = registro->OBS;
			dados_item->status = registro->STATUS;
			dados_item->dataAbertura = registro->DATAABERTURA;
			dados_item->desconto = registro->DESCONTO;
			dados_item->valorTotal = registro->VALORTOTAL;
			retorna_dados_item [] = dados_item;
		}
		return retorna_dados_item;
	}
	
	public OrcamentoVO getItens(){
		sql = "select * from projeto";
		resultado = this->conn->Execute(sql);
		while(registro = resultado->FetchNextObject()){			
			dados_item = new PreVendaVO();
			dados_item->codigo = registro->CODIGO;
			dados_item->codCliente = registro->CODCLIENTE;
			dados_item->codUsuario = registro->CODUSUARIO;
			dados_item->obs = registro->OBS;
			dados_item->status = registro->STATUS;
			dados_item->dataAbertura = registro->DATAABERTURA;
			dados_item->desconto = registro->DESCONTO;
			dados_item->valorTotal = registro->VALORTOTAL;
			retorna_dados_item [] = dados_item;
		}
		return retorna_dados_item;	
	}


	private ArrayList<OrcamentoVO> toOrcamento(ResultSet rs)
    	throws SQLException {
      ArrayList<OrcamentoVO> gp = new ArrayList<OrcamentoVO>();
    while (rs.next()) {
	 OrcamentoVO dados_item = new OrcamentoVO();
	dados_item.codigo = rs.getInt("codigo");
	dados_item.codCliente = rs.getInt("codCliente");
	dados_item.codUsuario = rs.getInt("codUsuario");
	dados_item.dataAbertura = rs.getDate("dataAbertura");
	dados_item.itemOrcamento = rs.getInt("itemOrcamento");
	dados_item.obs = rs.getString("obs");
	dados_item.status = rs.getInt("status");
	dados_item.valorTotal = rs.getInt("valorTotal");
	

	gp.add(dados_item);
}
return gp;
}


}

