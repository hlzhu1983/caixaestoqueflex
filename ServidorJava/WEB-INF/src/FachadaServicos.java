import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import exception.ClienteJaexisteException;
import services.ServicosCliente;
import services.ServicosCompra;
import services.ServicosFormadePagto;
import services.ServicosFornecedor;
import services.ServicosGrupoProduto;
import services.ServicosLocalProduto;
import services.ServicosPreVenda;
import services.ServicosProduto;
import services.ServicosUnidade;
import services.ServicosUsuario;
import services.ServicosVenda;
import util.UtilData;
import vo.ClienteVO;
import vo.CompraVO;
import vo.FormaPgtoVendaVO;
import vo.FormadePagtoVO;
import vo.FornecedorVO;
import vo.GrupoProdutoVO;
import vo.ItemGraficoVO;
import vo.ItemPreVendaVO;
import vo.LocalProdutoVO;
import vo.PreVendaVO;
import vo.ProdutoVO;
import vo.RankingClienteVO;
import vo.RankingFormaPagamentoVO;
import vo.RankingProdutoVO;
import vo.UnidadeVO;
import vo.UsuarioVO;
import vo.VendaVO;

public class FachadaServicos {

	private ServicosCliente servCliente;
	private ServicosUsuario servUsuario;
	private ServicosFormadePagto servFormadePagto;
	private ServicosUnidade servUnidade;
	private ServicosGrupoProduto servGrupoProduto;
	private ServicosLocalProduto servLocalProduto;
	private ServicosFornecedor servForncedor;
	private ServicosProduto servProduto;
	private ServicosPreVenda servPreVenda;
	private ServicosVenda servVenda;
	private ServicosCompra servCompra;

	public FachadaServicos() {
		this.servCliente = new ServicosCliente();
		this.servUsuario = new ServicosUsuario();
		this.servFormadePagto = new ServicosFormadePagto();
		this.servGrupoProduto = new ServicosGrupoProduto();
		this.servLocalProduto = new ServicosLocalProduto();
		this.servUnidade = new ServicosUnidade();
		this.servForncedor = new ServicosFornecedor();
		this.servProduto = new ServicosProduto();
		this.servPreVenda = new ServicosPreVenda();
		this.servVenda = new ServicosVenda();
		this.servCompra = new ServicosCompra();
	}

	public CompraVO fecharCompra(CompraVO item) {
		if (item == null)
			throw new RuntimeException("Parametro invalido");
		return this.servCompra.fecharCompra(item);
	}
	


	public ClienteVO addCliente(ClienteVO item) throws SQLException {
		if (item == null)
			throw new RuntimeException("Parametro invalido");
		return this.servCliente.adicionarCliente(item);
	}

	public boolean removerCliente(ClienteVO item) {
		if (item == null)
			throw new RuntimeException("Parametro invalido");

		return this.servCliente.removerCliente(item);

	}

	public ArrayList<ClienteVO> pesquisarCliente(String texto, String coluna) {
		if (texto == null || coluna == null)
			throw new RuntimeException("Parametro invalido");
		return this.servCliente.pesquisarCliente(texto, coluna);

	}

	public ArrayList<ClienteVO> getClientes() {
		return this.servCliente.getClientes();

	}

	public ClienteVO atualizarCliente(ClienteVO item) {
		if (item == null)
			throw new RuntimeException("Parametro invalido");
		return this.servCliente.atualizarCliente(item);
	}

	// Servicos Usuario
	// *************************************************************//

	public UsuarioVO autenticacaoUsuario(UsuarioVO usuario) throws Exception {
		return this.servUsuario.autenticacaoUsuario(usuario);
	}

	public ArrayList<UsuarioVO> getUsuarios() throws Exception {
		return this.servUsuario.getUsuarios();
	}

	public UsuarioVO salvarUsuario(UsuarioVO usuario) throws Exception {
		if (usuario.codigo == 0) {
			return this.servUsuario.addUsuario(usuario);
		} else {
			return this.servUsuario.atualizarUsuario(usuario);
		}
	}

	public boolean removerUsuario(UsuarioVO usuario) {
		if (this.servUsuario.isExiste(usuario)) {
			this.servUsuario.removerUsuario(usuario);
			return true;
		} else {
			throw new RuntimeException("Usuário não existe!");
		}
	}

	public ArrayList<UsuarioVO> pesquisarUsuario(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servUsuario.pesquisarUsuario(texto, coluna);

		}
	}

	// **********************************************************************************//

	// Servicos FormaPagamento
	// *************************************************************//

	public ArrayList<FormadePagtoVO> getFormadePagtos() throws Exception {
		return this.isDefaultFormaPagamento(this.servFormadePagto
				.getFormadePagtos());
	}

	public FormadePagtoVO adicionarFormadePagto(FormadePagtoVO formaPagamento)
			throws Exception {
		return this.servFormadePagto.addFormadePagto(formaPagamento);
	}

	public FormadePagtoVO atualizarFormadePagto(FormadePagtoVO formaPagamento)
			throws Exception {
		if (formaPagamento.codigo == 0) {
			throw new RuntimeException(
					"Esta forma de pagamento não pode ser atualizada!");
		} else {
			if (this.servFormadePagto.isExiste(formaPagamento)) {
				return this.servFormadePagto
						.atualizarFormadePagto(formaPagamento);
			} else {
				throw new RuntimeException("Forma de pagamento não existe!");
			}
		}
	}

	public boolean removerFormadePagto(FormadePagtoVO formaPagamento) {
		if (this.servFormadePagto.isExiste(formaPagamento)) {
			if (formaPagamento.codigo != 0) {
				return this.servFormadePagto
						.removerFormadePagto(formaPagamento);
			} else {
				throw new RuntimeException(
						"Forma pagamento não pode ser removida!");
			}
		} else {
			throw new RuntimeException("Forma pagamento não existe!");
		}
	}

	public ArrayList<FormadePagtoVO> pesquisarFormadePagto(String texto,
			String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.isDefaultFormaPagamento(this.servFormadePagto
					.pesquisarFormadePagto(texto, coluna));

		}
	}

	// **********************************************************************************//

	// Servicos Unidade
	// *************************************************************//

	public ArrayList<UnidadeVO> getUnidades() throws Exception {
		return this.servUnidade.getUnidades();
	}

	public UnidadeVO salvarUnidade(UnidadeVO unidade) throws Exception {
		if (!this.servUnidade.isExiste(unidade)) {
			return this.servUnidade.addUnidade(unidade);
		} else {
			return this.servUnidade.atualizarUnidade(unidade);
		}
	}

	public boolean removerUnidade(UnidadeVO unidade) {
		if (this.servUnidade.isExiste(unidade)) {
			return this.servUnidade.removerUnidade(unidade);
		} else {
			throw new RuntimeException("Unidade não existe!");
		}
	}

	public ArrayList<UnidadeVO> pesquisarUnidade(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servUnidade.pesquisarUnidade(texto, coluna);

		}
	}

	// **********************************************************************************//

	// Servicos LocalProduto
	// *************************************************************//

	public ArrayList<LocalProdutoVO> getLocalProdutos() throws Exception {
		return this.servLocalProduto.getLocalProdutos();
	}

	public LocalProdutoVO salvarLocalProduto(LocalProdutoVO localProduto)
			throws Exception {
		if (!this.servLocalProduto.isExiste(localProduto)) {
			return this.servLocalProduto.addLocalProduto(localProduto);
		} else {
			return this.servLocalProduto.atualizarLocalProduto(localProduto);
		}
	}

	public boolean removerLocalProduto(LocalProdutoVO localProduto) {
		if (this.servLocalProduto.isExiste(localProduto)) {
			return this.servLocalProduto.removerLocalProduto(localProduto);
		} else {
			throw new RuntimeException("Usuário não existe!");
		}
	}

	public ArrayList<LocalProdutoVO> pesquisarLocalProduto(String texto,
			String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servLocalProduto.pesquisarLocalProduto(texto, coluna);

		}
	}

	// **********************************************************************************//

	// Servicos GrupoProduto
	// *************************************************************//

	public ArrayList<GrupoProdutoVO> getGrupoProdutos() throws Exception {
		return this.servGrupoProduto.getGrupoProdutos();
	}

	public GrupoProdutoVO salvarGrupoProduto(GrupoProdutoVO grupoproduto)
			throws Exception {
		if (!this.servGrupoProduto.isExiste(grupoproduto)) {
			return this.servGrupoProduto.addGrupoProduto(grupoproduto);
		} else {
			return this.servGrupoProduto.atualizarGrupoProduto(grupoproduto);
		}
	}

	public boolean removerGrupoProduto(GrupoProdutoVO grupoProduto) {
		if (this.servGrupoProduto.isExiste(grupoProduto)) {
			return this.servGrupoProduto.removerGrupoProduto(grupoProduto);
		} else {
			throw new RuntimeException("Grupo produto não existe!");
		}
	}

	public ArrayList<GrupoProdutoVO> pesquisarGrupoProduto(String texto,
			String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servGrupoProduto.pesquisarGrupoProduto(texto, coluna);

		}
	}

	// **********************************************************************************//

	// Servicos Fornecedor
	// *************************************************************//

	public FornecedorVO salvarFornecedor(FornecedorVO fornecedor) {
		if (fornecedor.codigo == 0) {
			return this.servForncedor.addFornecedor(fornecedor);
		} else {
			return this.servForncedor.atualizarFornecedor(fornecedor);
		}

	}

	public boolean removerFornecedor(FornecedorVO fornecedor) {
		if (this.servForncedor.isExiste(fornecedor)) {
			return this.servForncedor.removerFornecedor(fornecedor);
		} else {
			throw new RuntimeException("Fornecedor não existe!");
		}
	}

	public ArrayList<FornecedorVO> pesquisarFornecedor(String texto,
			String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servForncedor.pesquisarFornecedor(texto, coluna);
		}

	}

	public ArrayList<FornecedorVO> getFornecedores() {
		return this.servForncedor.getFornecedores();
	}

	public ArrayList<FornecedorVO> filtrarFornecedores(ArrayList<String> str) {
		if (str.size() == 0) {
			throw new RuntimeException(
					"Operação de filtragem não pode ser realizada!");
		}

		String sql = "select * from  fornecedor where " + str.get(0);
		for (int i = 1; i < str.size(); i++) {
			sql += " and " + str.get(0);
		}
		// throw new RuntimeException(sql);

		return this.servForncedor.getFornecedores(sql);
	}

	// **********************************************************************************//

	// Servicos Produto
	// *************************************************************//

	public ProdutoVO adicionarProduto(ProdutoVO produto) {
		return this.servProduto.addProduto(produto);
	}

	public ProdutoVO atualizarProduto(ProdutoVO produto) throws Exception {
		if (produto.codigo == 0) {
			throw new RuntimeException("Este produto não pode ser atualizada!");
		} else {
			if (this.servProduto.isExiste(produto)) {
				return this.servProduto.atualizarProduto(produto);
			} else {
				throw new RuntimeException("Produto não existe!");
			}
		}
	}

	public boolean removerProduto(ProdutoVO produto) {
		if (this.servProduto.isExiste(produto)) {
			return this.servProduto.removerProduto(produto);
		} else {
			throw new RuntimeException("Produto não existe!");
		}
	}

	public ArrayList<ProdutoVO> pesquisarProduto(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.isDefaultProduto(this.servProduto.pesquisarProduto(
					texto, coluna));
		}

	}

	public ProdutoVO getProduto(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			ArrayList<ProdutoVO> p = this.servProduto.getProduto(texto, coluna);
			this.isDefaultProduto(p);
			if (p.size() == 0)
				return null;
			else
				return p.get(0);
		}

	}

	public ArrayList<ProdutoVO> getProdutos() {
		return this.isDefaultProduto(this.servProduto.getProdutos());
	}

	public ArrayList<ProdutoVO> filtrarProdutos(ArrayList<ProdutoVO> str) {
		if (str.size() == 0) {
			throw new RuntimeException(
					"Operação de filtragem não pode ser realizada!");
		}

		String sql = "select * from  produto where " + str.get(0);
		for (int i = 1; i < str.size(); i++) {
			sql += " and " + str.get(0);
		}
		// throw new RuntimeException(sql);

		return this.isDefaultProduto(this.servProduto.getProdutos(sql));

	}

	// **********************************************************************************//

	public PreVendaVO abrirPreVenda(PreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.abrirPreVenda(item);
	}

	public ItemPreVendaVO addItemPreVenda(ItemPreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.addItemPreVenda(item);
	}

	public PreVendaVO fecharPreVenda(PreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.fecharPreVenda(item);

	}

	public boolean removerItemPreVenda(ItemPreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		this.servPreVenda.removerItemPreVenda(item);
		return true;
	}

	public PreVendaVO cancelarPreVenda(PreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.cancelarPreVenda(item);
	}

	public boolean removerItem(PreVendaVO item) {
		if (item == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.removerItem(item);
	}

	public ArrayList<PreVendaVO> filtrarData(String data, String coluna) {
		if (data == null || coluna == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.filtrarData(data, coluna);
	}

	public ArrayList<PreVendaVO> getAllItensPrevenda(String codigo) {
		if (codigo == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.getAllItensPrevenda(codigo);
	}

	public ArrayList<PreVendaVO> pesquisarItens(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.pesquisarItens(texto, coluna);
	}

	public ArrayList<PreVendaVO> getPreVendas() {
		return this.servPreVenda.getItens();
	}

	public ArrayList<PreVendaVO> getItensValidos() {
		return this.servPreVenda.getItensValidos();
	}

	public ArrayList<PreVendaVO> pesquisarItensValidos(String texto,
			String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("Parametro inválido exception!");
		}
		return this.servPreVenda.pesquisarItensValidos(texto, coluna);

	}

	// **********************************************************************************//

	public VendaVO abrirVenda(VendaVO item) {
		if (item == null)
			throw new RuntimeException("Venda não pode ser aberta!");
		return this.servVenda.abrirVenda(item);
	}

	public VendaVO fecharVenda(VendaVO item) {
		if (item == null)
			throw new RuntimeException("Venda não pode ser aberta!");
		return this.servVenda.fecharVenda(item);
	}

	public VendaVO cancelarVenda(VendaVO item) {
		if (item == null)
			throw new RuntimeException("Venda não pode ser aberta!");
		return this.servVenda.cancelarVenda(item);
	}

	public ArrayList<VendaVO> filtrarData(Date data, String coluna) {
		if (data == null || coluna == null)
			throw new RuntimeException("Venda não pode ser aberta!");
		return this.servVenda.filtrarData(data, coluna);
	}

	public ArrayList<VendaVO> pesquisarVenda(String texto, String coluna) {
		if (texto == null || coluna == null)
			throw new RuntimeException("Venda não pode ser aberta!");
		return this.servVenda.pesquisarItens(texto, coluna);
	}

	public ArrayList<VendaVO> getVendas() {
		return this.servVenda.getItens();

	}

	public ArrayList<VendaVO> getVendasHoje() {
		// return this.servVenda.getVendasHoje();
		return this.servVenda.getItens();
	}

	public ArrayList<ItemGraficoVO> geraGraficoVendaTotal() {
		return this.servVenda.getGraficoVendaTotal();

	}

	public ArrayList<ItemGraficoVO> geraGraficoVendaIntervalo(String str) {
		if (str == null)
			throw new RuntimeException("Grafico não pode ser gerado!");
		return this.servVenda.getGraficoVendaIntervalo(str);

	}

	public ArrayList<ItemGraficoVO> geraGraficoVendaMes(String str) {
		if (str == null)
			throw new RuntimeException("Grafico não pode ser gerado!");
		return this.servVenda.getGraficoVendaMes(str);

	}

	public ArrayList<ItemGraficoVO> geraGraficoVendaAno(String str) {
		if (str == null)
			throw new RuntimeException("Grafico não pode ser gerado!");
		return this.servVenda.getGraficoVendaAno(str);

	}

	public ArrayList<ItemGraficoVO> geraGraficoVendaMesAno(String mes,
			String ano) {
		if (mes == null || ano == null)
			throw new RuntimeException("Grafico não pode ser gerado!");
		return this.servVenda.getGraficoVendaMesAno(mes, ano);

	}

	public ArrayList<RankingClienteVO> getRankingCliente() {
		return this.servVenda.getRankingCliente();

	}

	public ArrayList<RankingProdutoVO> getRankingProduto() {
		return this.servVenda.getRankingProduto();

	}

	public ArrayList<RankingFormaPagamentoVO> getRankingFormaPagamento() {
		return this.servVenda.getRankingFormaPagamento();

	}

	public ArrayList<VendaVO> filtrarVendas(ArrayList<String> str) {
		if (str.size() == 0) {
			throw new RuntimeException(
					"Operação de filtragem não pode ser realizada!");
		}

		String sql = "select * from  venda where " + str.get(0);
		for (int i = 1; i < str.size(); i++) {
			sql += " and " + str.get(i);
		}
		// throw new RuntimeException(sql);
		if (1 == 1)
			throw new RuntimeException(sql + " and status = 1");
		return this.servVenda.getVendas(sql + " and status = 1");
	}

	// **********************************************************************************//

	public String mensagemCliente() {
		return "Pegou";
	}

	private ArrayList<FormadePagtoVO> isDefaultFormaPagamento(
			ArrayList<FormadePagtoVO> a) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).codigo == 0) {
				a.remove(i);
			}
		}
		return a;
	}

	private ArrayList<ProdutoVO> isDefaultProduto(ArrayList<ProdutoVO> a) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).codigo == 0) {
				a.remove(i);
			}
		}
		return a;
	}

	public static void main(String[] args) throws Exception {
		ClienteVO p = new ClienteVO();
		p.nome = "cliente";
		FachadaServicos f = new FachadaServicos();
		f.getVendas();
	}
}
