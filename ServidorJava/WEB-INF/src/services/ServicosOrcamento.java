package services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.UtilData;
import vo.ItemPreVendaVO;
import vo.OrcamentoVO;

public class ServicosOrcamento {

	public OrcamentoVO abrirOrcamento(OrcamentoVO item) {
		String sql = "insert into orcamento (codUsuario,status,dataAbertura) values ('"
				+ item.codUsuario + "','0' ,now())";

		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao Abrir Orcamento");
		}

		return item;
	}

	public OrcamentoVO fecharPreVenda(OrcamentoVO item) {
		String sql = "";
		for (int i = 0; i < item.itemPreVenda.size(); i++) {
			ItemPreVendaVO temp = item.itemPreVenda.get(i);
			sql = "insert into itensprevenda (codPrevenda, codProduto, quantidade,valor) "
					+ " values ('"
					+ item.codigo
					+ "','"
					+ temp.codProduto
					+ "','" + temp.quantidade + "','" + temp.valor + "')";
			if (banco.executarNoQuery(sql) == 0) {
				throw new RuntimeException("Erro ao Adicionar ItemPrevenda");
			}
		}
		if (item.codCliente == 0) {
			sql = "UPDATE prevenda SET codUsuario = '" + item.codUsuario
					+ "' , status = '1', obs = '" + item.obs
					+ "',dataAbertura ='"
					+ UtilData.formatar(item.dataAbertura)
					+ "',  valorTotal = '" + item.valorTotal
					+ "' where codigo = item->codigo";
		} else {
			sql = "UPDATE prevenda SET codUsuario = '" + item.codUsuario
					+ "' , codCliente = '" + item.codCliente
					+ "', status = '1', obs = '" + item.obs
					+ "',  valorTotal = '" + item.valorTotal
					+ "' where codigo = " + item.codigo;
		}
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao fechar PreVenda");
		}
		return item;
	}

	public OrcamentoVO cancelarOrcamento(OrcamentoVO item) {
		String sql = "select * FROM itensprevenda WHERE codPrevenda = "
				+ item.codigo + "";

		ResultSet resultado = banco.executar(sql);
		ArrayList<ItemPreVendaVO> orc = new ArrayList<ItemPreVendaVO>();
		orc = this.toItemPreVenda(resultado);

		for (int i = 0; i < orc.size(); i++) {
			ItemPreVendaVO orcamento = orc.get(i);
			sql = "UPDATE produto SET qtdEmEstoque = (qtdEmEstoque + "
					+ orcamento.quantidade + ")  WHERE codigo = "
					+ orcamento.codProduto;
			if (banco.executarNoQuery(sql) == 0) {
				throw new RuntimeException("Erro ao atualizar ItemPreVenda");
			}
		}

		sql = "DELETE FROM itensprevenda WHERE codPrevenda = " + item.codigo;
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao remover ItemPreVenda");
		}
		sql = "UPDATE prevenda SET codUsuario = '" + item.codUsuario
				+ "' , status = '2', obs = '" + item.obs + "' where codigo = "
				+ item.codigo;
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Erro ao Atualizar PreVenda");
		}
		return item;
	}

	private ArrayList<ItemPreVendaVO> toItemPreVenda(ResultSet rs) throws SQLException {

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

	public boolean removerItem(OrcamentoVO item) {

		String sql = "delete from orcamento  where codigo = " + item.codigo;

		if (banco.executarNoQuery(sql) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<OrcamentoVO> pesquisarItens(String texto, String coluna) {
		String sql = "select * from orcamento where " + coluna + " like '%"
				+ texto + "%'";
		ResultSet resultado = banco.executar(sql);

		return this.toOrcamento(resultado);
	}

	public ArrayList<OrcamentoVO> getItens() {
		String sql = "select * from orcamento";
		ResultSet resultado = banco.executar(sql);
		return this.toOrcamento(resultado);
	}

	private ArrayList<OrcamentoVO> toOrcamento(ResultSet rs) throws SQLException {
		ArrayList<OrcamentoVO> gp = new ArrayList<OrcamentoVO>();
		while (rs.next()) {
			OrcamentoVO dados_item = new OrcamentoVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.codCliente = rs.getInt("codCliente");
			dados_item.codUsuario = rs.getInt("codUsuario");
			dados_item.dataAbertura = rs.getDate("dataAbertura");
			dados_item.obs = rs.getString("obs");
			dados_item.status = rs.getInt("status");
			dados_item.valorTotal = rs.getInt("valorTotal");

			gp.add(dados_item);
		}
		return gp;
	}

}
