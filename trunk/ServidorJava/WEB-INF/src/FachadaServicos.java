import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import services.ServicosCliente;
import services.ServicosFormadePagto;
import services.ServicosFornecedor;
import services.ServicosGrupoProduto;
import services.ServicosLocalProduto;
import services.ServicosProduto;
import services.ServicosUnidade;
import services.ServicosUsuario;
import vo.FormadePagtoVO;
import vo.FornecedorVO;
import vo.GrupoProdutoVO;
import vo.LocalProdutoVO;
import vo.ProdutoVO;
import vo.UnidadeVO;
import vo.UsuarioVO;

public class FachadaServicos {

	private ServicosCliente servCliente;
	private ServicosUsuario servUsuario;
	private ServicosFormadePagto servFormadePagto;
	private ServicosUnidade servUnidade;
	private ServicosGrupoProduto servGrupoProduto;
	private ServicosLocalProduto servLocalProduto;
	private ServicosFornecedor servForncedor;
	private ServicosProduto servProduto;

	public FachadaServicos() {
		this.servCliente = new ServicosCliente();
		this.servUsuario = new ServicosUsuario();
		this.servFormadePagto = new ServicosFormadePagto();
		this.servGrupoProduto = new ServicosGrupoProduto();
		this.servLocalProduto = new ServicosLocalProduto();
		this.servUnidade = new ServicosUnidade();
		this.servForncedor = new ServicosFornecedor();
		this.servProduto = new ServicosProduto();
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

	public FormadePagtoVO adcionarFormadePagto(FormadePagtoVO formaPagamento)
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
			return this.servProduto.pesquisarProduto(texto, coluna);
		}

	}

	public ArrayList<ProdutoVO> getProdutos() {
		return this.servProduto.getProdutos();
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
		UnidadeVO u = new UnidadeVO();
		// u.codigo = 4;
		u.descricao = "M";
		FachadaServicos f = new FachadaServicos();
		String[] str = { "", "", "", "", "", "", "codLocal = 2" };
		//f.filtrarProdutos(str);

	}
}
