package services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import vo.FormaPgtoVendaVO;
import vo.ItemGraficoVO;
import vo.RankingClienteVO;
import vo.RankingFormaPagamentoVO;
import vo.RankingProdutoVO;
import vo.VendaVO;

public class ServicosVenda {

	public VendaVO abrirVenda(VendaVO item) {
		String sql = "insert into venda (codUsuario,codPreVenda,status,dataVenda,valorTotal,obs) values ('"
				+ item.codUsuario
				+ "', '"
				+ item.codPreVenda
				+ "','0' ,now(),0,'')";
		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao abrir Venda!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar venda!");
		this.banco.getConexao().commit();
		return item;
	}

	public VendaVO fecharVenda(VendaVO item) {
		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		String sql;
		if (item.formasPagamento != null) {
			for (FormaPgtoVendaVO f : item.formasPagamento) {
				if (f.codFormaPagamento == 0) {
					sql = "UPDATE cliente SET limCredito = (limCredito - "
							+ f.valor + ") WHERE codigo = " + item.codCliente;
					st.executeUpdate(sql);
				}
				sql = "insert into formapagamentovenda (codVenda,codFormaPagamento,numParcelas, valor) values ("
						+ f.codVenda
						+ ","
						+ f.codFormaPagamento
						+ ","
						+ f.parcelas + "," + f.valor + ")";
				if (st.executeUpdate(sql) == 0) {
					throw new RuntimeException(
							"Erro ao adicionar forma pagamento!");
				}
			}
		}
		sql = "UPDATE prevenda SET status = 3 WHERE codigo = "
				+ item.codPreVenda;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException(
					"Erro ao atualizar prevenda ao fechar venda!");
		}
		if (item.codCliente == 0)
			sql = "UPDATE venda SET codUsuario = '" + item.codUsuario
					+ "' ,  codPreVenda = " + item.codPreVenda
					+ ", status = '1', desconto = " + item.desconto
					+ " , obs = '" + item.obs + "', valorTotal = '"
					+ item.valorTotal + "' where codigo = " + item.codigo;
		else
			sql = "UPDATE venda SET codUsuario = '" + item.codUsuario
					+ "' , codCliente = " + item.codCliente
					+ ", codPreVenda = " + item.codPreVenda
					+ ", status = '1', desconto = " + item.desconto
					+ " , obs = '" + item.obs + "', valorTotal = '"
					+ item.valorTotal + "' where codigo = " + item.codigo;
		if (st.executeUpdate(sql) == 0) {
			throw new RuntimeException("Erro ao fechar venda!");
		}
		this.banco.getConexao().commit();
		return item;
	}

	public VendaVO cancelarVenda(VendaVO item) {

		String sql = "UPDATE venda SET codUsuario = '" + item.codUsuario
				+ "', codPreVenda = null , status = '2', obs = '" + item.obs
				+ "' where codigo = " + item.codigo;
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao adicionar forma pagamento!");
		}

		return item;
	}

	public ArrayList<VendaVO> filtrarData(Date data, String coluna) {
		String sql = "select * from venda where DATE("+coluna+") = '"+data+"' order by DATE("+coluna+") desc" ;
		ResultSet rs = banco.executar(sql);
		return this.toVenda(rs);
	}

	public ArrayList<VendaVO> pesquisarItens(String texto, String coluna) {
		String sql = "select * from venda where "+coluna+" like '%"+texto+"%'";
		ResultSet rs = banco.executar(sql);
		return this.toVenda(rs);
	}

	public ArrayList<VendaVO> getItens() {
		String sql = "select * from venda";
		ResultSet rs = banco.executar(sql);
		return this.toVenda(rs);

	}
	
	public ArrayList<VendaVO> getVendasHoje() {
		String sql = "select * from venda where Date(dataVenda) = Date(now()) and status = 1";
		ResultSet rs = banco.executar(sql);
		return this.toVenda(rs);

	}
	
	public ArrayList<VendaVO> getVendas(String sql) {
		ResultSet rs = banco.executar(sql);
		return this.toVenda(rs);
	}
	
	
	public ArrayList<ItemGraficoVO> getGraficoVendaTotal(){
		String sql = "select Month(datavenda) as mes, Year(datavenda) as ano, sum(valortotal) as valor from venda where status = 1 group by Year(datavenda), Month(datavenda)";
		ResultSet rs = banco.executar(sql);
		ArrayList<ItemGraficoVO> gp = new ArrayList<ItemGraficoVO>();
		ItemGraficoVO dados_item;
		while (rs.next()) {
			dados_item = new ItemGraficoVO();
			dados_item.yField = rs.getString("mes")+"/"+ rs.getString("ano");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<ItemGraficoVO> getGraficoVendaIntervalo(String str){
		String sql = "select Month(datavenda) as mes, Year(datavenda) as ano, sum(valortotal) as valor from venda where status = 1 and "+str+"group by Year(datavenda), Month(datavenda)";
		ResultSet rs = banco.executar(sql);
		ArrayList<ItemGraficoVO> gp = new ArrayList<ItemGraficoVO>();
		ItemGraficoVO dados_item;
		while (rs.next()) {
			dados_item = new ItemGraficoVO();
			dados_item.yField = rs.getString("mes")+"/"+ rs.getString("ano");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<ItemGraficoVO> getGraficoVendaMes(String str){
		String sql = "select Month(datavenda) as mes, Year(datavenda) as ano, sum(valortotal) as valor from venda where status = 1 and Month(datavenda) = "+str+" group by Year(datavenda), Month(datavenda)";
		ResultSet rs = banco.executar(sql);
		ArrayList<ItemGraficoVO> gp = new ArrayList<ItemGraficoVO>();
		ItemGraficoVO dados_item;
		while (rs.next()) {
			dados_item = new ItemGraficoVO();
			dados_item.yField = rs.getString("mes")+"/"+ rs.getString("ano");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<ItemGraficoVO> getGraficoVendaAno(String str){
		String sql = "select Month(datavenda) as mes, Year(datavenda) as ano, sum(valortotal) as valor from venda where status = 1 and Year(datavenda) = "+str+" group by Year(datavenda), Month(datavenda)";
		ResultSet rs = banco.executar(sql);
		ArrayList<ItemGraficoVO> gp = new ArrayList<ItemGraficoVO>();
		ItemGraficoVO dados_item;
		while (rs.next()) {
			dados_item = new ItemGraficoVO();
			dados_item.yField = rs.getString("mes")+"/"+ rs.getString("ano");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<ItemGraficoVO> getGraficoVendaMesAno(String mes, String ano){
		String sql = "select Day(datavenda) as dia,Month(datavenda) as mes, Year(datavenda) as ano, sum(valortotal) as valor from venda where status = 1 and Month(datavenda) = "+mes+" and Year(datavenda) = "+ano+" group by Year(datavenda), Month(datavenda),Day(datavenda)";
		ResultSet rs = banco.executar(sql);
		ArrayList<ItemGraficoVO> gp = new ArrayList<ItemGraficoVO>();
		ItemGraficoVO dados_item;
		while (rs.next()) {
			dados_item = new ItemGraficoVO();
			dados_item.yField = rs.getString("mes")+"/"+ rs.getString("ano");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<RankingClienteVO> getRankingCliente(){
		String sql = "select c.codigo as codigo,c.nome as nome, c.fone as fone, sum(valortotal) as valor from venda v , cliente c where v.status = 1 and v.codCliente = c.codigo group by c.codigo,c.nome,c.fone";
		ResultSet rs = banco.executar(sql);
		ArrayList<RankingClienteVO> gp = new ArrayList<RankingClienteVO>();
		RankingClienteVO dados_item;
		while (rs.next()) {
			dados_item = new RankingClienteVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.nome = rs.getString("nome");
			dados_item.fone = rs.getInt("fone");
			dados_item.valorTotal = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	
	public ArrayList<RankingProdutoVO> getRankingProduto(){
		String sql = "select p.codigo as codigo,p.descricao as descricao, sum(i.quantidade) as quantidade from venda v , itensprevenda i, produto p where v.status = 1 and v.codprevenda = i.codprevenda and i.codproduto = p.codigo group by p.codigo ,p.descricao";
		ResultSet rs = banco.executar(sql);
		ArrayList<RankingProdutoVO> gp = new ArrayList<RankingProdutoVO>();
		RankingProdutoVO dados_item;
		while (rs.next()) {
			dados_item = new RankingProdutoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.quantidade = rs.getInt("quantidade");
			gp.add(dados_item);
		}
		return gp;
	}
	
	public ArrayList<RankingFormaPagamentoVO> getRankingFormaPagamento(){
		String sql = "select fv.codigo as codigo,f.descricao as descricao, sum(fv.valor) as valor from venda v , formadepgto f, formapagamentovenda fv where v.status = 1 and v.codigo = fv.codVenda and f.codigo = fv.codFormaPagamento group by fv.codFormaPagamento , f.descricao";
		ResultSet rs = banco.executar(sql);
		ArrayList<RankingFormaPagamentoVO> gp = new ArrayList<RankingFormaPagamentoVO>();
		RankingFormaPagamentoVO dados_item;
		while (rs.next()) {
			dados_item = new RankingFormaPagamentoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.descricao = rs.getString("descricao");
			dados_item.valorTotal = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
	}
	
	
	
	
	private ArrayList<FormaPgtoVendaVO> getFormaPgtoVendaVO(VendaVO v){
		String sql = "select * from formapagamentovenda where codVenda = "+v.codigo;
		ResultSet rs = banco.executar(sql);
		ArrayList<FormaPgtoVendaVO> gp = new ArrayList<FormaPgtoVendaVO>();
		FormaPgtoVendaVO dados_item;
		while (rs.next()) {
			dados_item = new FormaPgtoVendaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codVenda = rs.getInt("codVenda");
			dados_item.codFormaPagamento = rs.getInt("codFormaPagamento");
			dados_item.parcelas = rs.getInt("numParcelas");
			dados_item.valor = rs.getDouble("valor");
			gp.add(dados_item);
		}
		return gp;
		
	}

	private ArrayList<VendaVO> toVenda(ResultSet rs) throws SQLException {
		ArrayList<VendaVO> gp = new ArrayList<VendaVO>();
		VendaVO dados_item;
		while (rs.next()) {
			dados_item = new VendaVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codCliente = rs.getInt("codCliente");
			dados_item.codUsuario = rs.getInt("codUsuario");
			dados_item.codPreVenda = rs.getInt("codPreVenda");
			dados_item.obs = rs.getString("obs");
			dados_item.status = rs.getInt("status");
			dados_item.dataVenda = rs.getString("dataVenda");
			dados_item.valorTotal = rs.getDouble("valorTotal");
			dados_item.desconto = rs.getDouble("desconto");
			dados_item.formasPagamento = this.getFormaPgtoVendaVO(dados_item);
			gp.add(dados_item);
		}
		return gp;
	}

}
