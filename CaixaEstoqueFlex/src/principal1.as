
	import events.AutenticacaoUsuarioEvent;
	
	import modulos.cliente.CadastroCliente;
	import modulos.compra.Compra;
	import modulos.formadepagto.CadastroFormadePagto;
	import modulos.fornecedor.CadastroFornecedor;
	import modulos.grupoproduto.CadastroGrupoProduto;
	import modulos.localproduto.CadastroLocalProduto;
	import modulos.orcamento.Orcamento;
	import modulos.prevenda.PreVendaWindow;
	import modulos.produto.ProdutoWindow;
	import modulos.receitas.ReceitaTitleWindow;
	import modulos.unidade.CadastroUnidade;
	import modulos.usuario.CadastroUsuario;
	import modulos.venda.VendaWindow;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.core.Window;
	import mx.effects.Glow;
	import mx.effects.Move;
	import mx.events.ItemClickEvent;
	import mx.events.ListEvent;
	
	import negocio.vo.UsuarioVO;
	
	import relatorios.ResumoVendas;
	
	import utilidades.Util;
	import utilidades.calculadora.Calculadora;
	
	[Bindable]
	public var usuario:UsuarioVO;
	
	[Bindable]
	public var itensLista:ArrayCollection;
	
	public var currentMenu:int;
	
	public var logou:Boolean;
	
	[Bindable]
	public var menuArray:ArrayCollection;
	
	public var menuItens:Array = [
				{label:"Arquivo"},{label:"Cadastros"},{label:"Módulos"},
	           {label:"Relatórios"},{label:"Utilitários"},{label:"Ajuda"}];
	           
	public var menuItensVendedor:Array = [{label:"Módulos"}];
	
	private var listArray:Array=[
         [{label: "Sair"}],
         [{label: "Cad. Cliente", funcao: gerCliente},
         {label: "Cad. Usuário", funcao: gerUsuario },{label: "Cad. Gpo Produto", funcao: gerGrupoProduto },
         {label: "Cad. Loc. Produto", funcao: gerLocalProduto},{label: "Cad. Produto", funcao: gerProduto },
         {label: "Cad. Fornecedor", funcao: gerFornecedor}, {label: "Cad. Unidades", funcao: gerUnidade},
         {label: "Cad. Forma Pagamento", funcao: gerFormadePagto }],
         [{label: "Pré-Venda", funcao: preVendaWindow},{label: "Compra", funcao: compraWindow},{label: "Janela de Venda", funcao: abreCaixaWindow},
         {label: "Pedido de Compra"},{label: "Receitas", funcao: gerReceita},{label: "Orçamento", funcao: orcamentoWindow},
         {label: "Transf Filiais"},{label: "Devolução"}],
         [{label: "Resumo Vendas", funcao: resumoVendaWindow}],
         [{label: "Calculadora", funcao:gerCalculadora},{label: "Cálculo de Dias"}],
         [{label: "Ajuda"},{label: "Sobre o Sistema"},{label: "Tópicos de Ajuda"},
         {label: "Versão"}]];
	
	
	public function init():void{
		this.logou = false;	
		Application.application.systemManager.addEventListener(AutenticacaoUsuarioEvent.SUCESSO,usuarioAutenticado);
		Util.abrePopUp(this,ComponenteAutenticacao,true);
		/* var glow:GlowFilter = new GlowFilter();
		glow.color = 0x009922;
		glow.alpha = 1;
		glow.blurX = 25;
		glow.blurY = 25;
		glow.quality = BitmapFilterQuality.MEDIUM;
		
		var blur:BlurFilter = new BlurFilter();
		blur.blurX = 10;
		blur.blurY = 10;
		blur.quality = BitmapFilterQuality.MEDIUM
		
		var bevel:BevelFilter = new BevelFilter();

		bevel.distance = 100;
		bevel.angle = 0;
		bevel.highlightColor = 0xFFFF00;
		bevel.highlightAlpha = 0.8;
		bevel.shadowColor = 0x666666;
		bevel.shadowAlpha = 0.8;
		bevel.blurX = 10;
		bevel.blurY = 10;
		bevel.strength = 5;
		bevel.quality = BitmapFilterQuality.HIGH;
		bevel.type = BitmapFilterType.INNER;
		bevel.knockout = false;
		
		var gradientBevel:GradientBevelFilter = new GradientBevelFilter();

		gradientBevel.distance = 8;
		gradientBevel.angle = 225; // opposite of 45 degrees
		gradientBevel.colors = [0xFFFFCC, 0xFEFE78, 0x8F8E01];
		gradientBevel.alphas = [1, 0, 1];
		gradientBevel.ratios = [0, 128, 255];
		gradientBevel.blurX = 8;
		gradientBevel.blurY = 8;
		gradientBevel.quality = BitmapFilterQuality.HIGH;
		
		this.bTarefas.filters = [gradientBevel]; */
	}	
	
	
	private function logoff():void{
		this.tl_itens.visible = false;
		Util.abrePopUp(this,ComponenteAutenticacao,true);
	}
		
		
	
	public function usuarioAutenticado(event:AutenticacaoUsuarioEvent):void{
		Config.usuario = event.usuario;
		this.usuario = event.usuario;
		if(this.usuario.permissao == 1){
			this.menuArray  = new ArrayCollection(this.menuItensVendedor);
		}else{
			this.menuArray  = new ArrayCollection(this.menuItens);
		}
			
		this.logou = true;
	}
	
	private function itemClickTile(event:ListEvent):void {
		this.tl_itens.visible = false;
		try{
			if(this.usuario.codigo == 1){
				this.listArray[2][(event.rowIndex * 4) + event.columnIndex].funcao();
			}else{
				this.listArray[this.currentMenu][(event.rowIndex * 4) + event.columnIndex].funcao();
			}
		}catch(e:Error){
			Alert.show("Não Implementado","Ops!");
		}
	}
	
	private function itemClickInfo(event:ItemClickEvent):void {
		this.tl_itens.visible = true;
		this.menuEfeito();
		if(this.usuario.permissao == 1){
			this.currentMenu = 2;
			this.tl_itens.dataProvider = new ArrayCollection(this.listArray[2]);
		}else{
			this.currentMenu = event.index;
			this.tl_itens.dataProvider = new ArrayCollection(this.listArray[event.index]);
		}
    }
    
    public function menuEfeito():void{
    	var m:Move = new Move(this.tl_itens);
    	m.xFrom = 0;
    	m.yFrom = 0;
    	m.duration = 100;
    	m.xBy = Application.application.width/4;
    	m.play();
    	
    	this.glowEfeito(this.tl_itens);
    	
    }
    
    
    private function glowEfeito(o:Object=null):void{
    	var g:Glow = new Glow(o);
    	g.duration = 1000; 
	    g.alphaFrom=1.0; 
	    g.alphaTo=0.9; 
	    g.blurXFrom=0.0; 
	    g.blurXTo=1000.0; 
	    g.blurYFrom=0.0; 
	    g.blurYTo=1000.0; 
	    g.color=0x000000;
	    g.play();
    }
	
	public function abreCaixaWindow():void{
		this.logou = false;	
		var newWindow:Window = new VendaWindow();
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
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;
		newWindow.open(false);
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
	}
	
	public function compraWindow():void{
				Util.abrePopUp(this,Compra,true);
	}
	public function gerCalculadora():void{		
		Util.abrePopUp(this,Calculadora,true);
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
		Util.abrePopUp(this,ProdutoWindow,true);
	}
		
	public function gerUsuario():void{		
		Util.abrePopUp(this,CadastroUsuario,true);
	}
	
	public function gerReceita():void{		
		Util.abrePopUp(this,ReceitaTitleWindow,true);
	}
	
	public function testImage():void{		
		Util.abrePopUp(this,TesteImage,true);
	}
	
	public function resumoVendaWindow():void{		
		var newWindow:Window = new ResumoVendas();
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;
		newWindow.open(false);
		newWindow.nativeWindow.x = 0;
		newWindow.nativeWindow.y = 0;
		newWindow.activate();
	}

	
	
	


