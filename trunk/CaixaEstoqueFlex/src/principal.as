
	import events.AutenticacaoUsuarioEvent;
	
	import modulos.caixa.Caixa;
	import modulos.cliente.CadastroCliente;
	import modulos.formadepagto.CadastroFormadePagto;
	import modulos.fornecedor.CadastroFornecedor;
	import modulos.grupoproduto.CadastroGrupoProduto;
	import modulos.localproduto.CadastroLocalProduto;
	import modulos.orcamento.Orcamento;
	import modulos.prevenda.PreVendaWindow;
	import modulos.produto.ProdutoWindow;
	import modulos.unidade.CadastroUnidade;
	import modulos.usuario.CadastroUsuario;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.core.Window;
	import mx.events.MenuEvent;
	
	import negocio.vo.UsuarioVO;
	
	import utilidades.Util;
	
	[Bindable]
	public var usuario:UsuarioVO;
	
	public var logou:Boolean;
	
	
	public function init():void{
		this.logou = false;	
		Application.application.systemManager.addEventListener(AutenticacaoUsuarioEvent.SUCESSO,usuarioAutenticado);
		Util.abrePopUp(this,ComponenteAutenticacao,true);
		menuBar.addEventListener(MenuEvent.ITEM_CLICK,itemClickInfo);
	}	
	
	public function usuarioAutenticado(event:AutenticacaoUsuarioEvent):void{
		this.usuario = event.usuario;
		this.logou = true;
	}
	
	
	private function itemClickInfo(event:MenuEvent):void {
		switch(event.label){
			case "Frente de Caixa":
				this.frenteLoja();
				break;
			case "Orçamento":
				this.orcamentoWindow();
				break;
			case "Pré-Venda":
				this.preVendaWindow();
				break;
			case "Cadastro Unidades":
				this.gerUnidade();
				break;
			case "Cadastro Cliente":
				this.gerCliente();
				break;
			case "Cadastro Produto":
				this.gerProduto();
				break;
			case "Cadastro Forma de Pagamento": 
			    this.gerFormadePagto();
			    break;
		    case "Cadastro Grupo Produto": 
			    this.gerGrupoProduto();
			    break;
			 case "Cadastro Local Produto": 
			    this.gerLocalProduto();
			    break;
			  case "Cadastro Fornecedor":
				this.gerFornecedor();
				break;
			  case "Cadastro Usuário":
				this.gerUsuario();
				break;
			    	
			default:
				Alert.show("Não implementado!","Alerta "+event.item.@id,4,this);
				break;
		}
    }
    
	
	public function frenteLoja():void{
		this.logou = false;	
		var newWindow:Window = new Caixa();
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;		
		newWindow.open(false);
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
	}
	
	public function orcamentoWindow():void{
		this.logou = false;	
		var newWindow:Window = new Orcamento();
		
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;
		newWindow.open(false);
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
		
	}
	
	public function preVendaWindow():void{
		this.logou = false;	
		var newWindow:Window = new PreVendaWindow();
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
		
	}
	
	public function gerUnidade():void{		
		Util.abrePopUp(this,CadastroUnidade,true);
	}
	public function gerFormadePagto():void{		
		Util.abrePopUp(this,CadastroFormadePagto,true);
	}
	
	public function gerGrupoProduto():void{		
		Util.abrePopUp(this,CadastroGrupoProduto,true);
	}

	public function gerLocalProduto():void{		
		Util.abrePopUp(this,CadastroLocalProduto,true);
	}

	
	public function gerFornecedor():void{		
		Util.abrePopUp(this,CadastroFornecedor,true);
	}
	public function gerCliente():void{		
		Util.abrePopUp(this,CadastroCliente,true);
	}
	
	public function gerProduto():void{
		
		var newWindow:Window = new ProdutoWindow();
		
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;		
		newWindow.open(false);
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
		}
		
	public function gerUsuario():void{		
		Util.abrePopUp(this,CadastroUsuario,true);
	}
	
	
	


