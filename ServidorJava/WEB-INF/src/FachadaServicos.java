import java.util.ArrayList;
import services.ServicosCliente;
import services.ServicosFormadePagto;
import services.ServicosGrupoProduto;
import services.ServicosLocalProduto;
import services.ServicosUnidade;
import services.ServicosUsuario;
import vo.FormadePagtoVO;
import vo.UnidadeVO;
import vo.UsuarioVO;

public class FachadaServicos {

	private ServicosCliente servCliente;
	private ServicosUsuario servUsuario;
	private ServicosFormadePagto servFormadePagto;
	private ServicosUnidade servUnidade;
	private ServicosGrupoProduto servGrupoProduto;
	private ServicosLocalProduto servLocalProduto;;

	public FachadaServicos() {
		this.servCliente = new ServicosCliente();
		this.servUsuario = new ServicosUsuario();
		this.servFormadePagto = new ServicosFormadePagto();
		this.servGrupoProduto = new ServicosGrupoProduto();
		this.servLocalProduto = new ServicosLocalProduto();
		this.servUnidade = new ServicosUnidade();
	}

	//Servicos Usuario *************************************************************//

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
	
	//**********************************************************************************//

	
	//Servicos FormaPagamento *************************************************************//
	
	public ArrayList<FormadePagtoVO> getFormadePagtos() throws Exception {
		return this.servFormadePagto.getFormadePagtos();
	}

	public FormadePagtoVO salvarFormadePagto(FormadePagtoVO formaPagamento) throws Exception {
		if (formaPagamento.codigo == 0) {
			return this.servFormadePagto.addFormadePagto(formaPagamento);
		} else {
			return this.servFormadePagto.atualizarFormadePagto(formaPagamento);
		}
	}

	public boolean removerFormadePagto(FormadePagtoVO formaPagamento) {
		if (this.servFormadePagto.isExiste(formaPagamento)) {
			this.servFormadePagto.removerFormadePagto(formaPagamento);
			return true;
		} else {
			throw new RuntimeException("Usuário não existe!");
		}
	}

	public ArrayList<FormadePagtoVO> pesquisarFormadePagto(String texto, String coluna) {
		if (texto == null || coluna == null) {
			throw new RuntimeException("A pesquisa não pôde ser completada");
		} else {
			return this.servFormadePagto.pesquisarFormadePagto(texto, coluna);

		}
	}
	
	
	
	//**********************************************************************************//
	
	//Servicos Unidade *************************************************************//
	
	public ArrayList<UnidadeVO> getUnidades() throws Exception {
		return this.servUnidade.getUnidades();
	}

	public UnidadeVO salvarUnidade(UnidadeVO unidade) throws Exception {
		if (unidade.codigo == 0) {
			return this.servUnidade.addUnidade(unidade);
		} else {
			return this.servUnidade.atualizarUnidade(unidade);
		}
	}

	public boolean removerUnidade(UnidadeVO unidade) {
		if (this.servUnidade.isExiste(unidade)) {
			this.servUnidade.removerUnidade(unidade);
			return true;
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
	
	
	//**********************************************************************************//
	
	//Servicos LocalProduto *************************************************************//
	
//	public ArrayList<FormadePagtoVO> getFormadePagtos() throws Exception {
//		return this.servFormadePagto.getFormadePagtos();
//	}
//
//	public FormadePagtoVO salvarFormadePagto(FormadePagtoVO formaPagamento) throws Exception {
//		if (formaPagamento.codigo == 0) {
//			return this.servFormadePagto.addFormadePagto(formaPagamento);
//		} else {
//			return this.servFormadePagto.atualizarFormadePagto(formaPagamento);
//		}
//	}
//
//	public boolean removerFormadePagto(FormadePagtoVO formaPagamento) {
//		if (this.servFormadePagto.isExiste(formaPagamento)) {
//			this.servFormadePagto.removerFormadePagto(formaPagamento);
//			return true;
//		} else {
//			throw new RuntimeException("Usuário não existe!");
//		}
//	}
//
//	public ArrayList<FormadePagtoVO> pesquisarFormadePagto(String texto, String coluna) {
//		if (texto == null || coluna == null) {
//			throw new RuntimeException("A pesquisa não pôde ser completada");
//		} else {
//			return this.servFormadePagto.pesquisarFormadePagto(texto, coluna);
//
//		}
//	}
	//**********************************************************************************//
	
	//Servicos GrupoProduto *************************************************************//
	
//	public ArrayList<FormadePagtoVO> getFormadePagtos() throws Exception {
//		return this.servFormadePagto.getFormadePagtos();
//	}
//
//	public FormadePagtoVO salvarFormadePagto(FormadePagtoVO formaPagamento) throws Exception {
//		if (formaPagamento.codigo == 0) {
//			return this.servFormadePagto.addFormadePagto(formaPagamento);
//		} else {
//			return this.servFormadePagto.atualizarFormadePagto(formaPagamento);
//		}
//	}
//
//	public boolean removerFormadePagto(FormadePagtoVO formaPagamento) {
//		if (this.servFormadePagto.isExiste(formaPagamento)) {
//			this.servFormadePagto.removerFormadePagto(formaPagamento);
//			return true;
//		} else {
//			throw new RuntimeException("Usuário não existe!");
//		}
//	}
//
//	public ArrayList<FormadePagtoVO> pesquisarFormadePagto(String texto, String coluna) {
//		if (texto == null || coluna == null) {
//			throw new RuntimeException("A pesquisa não pôde ser completada");
//		} else {
//			return this.servFormadePagto.pesquisarFormadePagto(texto, coluna);
//
//		}
//	}
	
	
	//**********************************************************************************//
	
	//Servicos Usuario *************************************************************//
	//**********************************************************************************//
	
	//Servicos Usuario *************************************************************//
	//**********************************************************************************//
	
	
	
	public String mensagemCliente() {
		return "PEgou";
	}

	public static void main(String[] args) throws Exception {
		UnidadeVO u = new UnidadeVO();
		//u.codigo = 4;
		u.descricao = "M";
		FachadaServicos f = new FachadaServicos();
		f.salvarUnidade(u);

	}

}
