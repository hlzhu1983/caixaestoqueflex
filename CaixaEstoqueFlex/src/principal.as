
	import flash.utils.Dictionary;
	
	import modulos.caixa.Caixa;
	import modulos.orcamento.Orcamento;
	import modulos.unidade.UnidadeCRUD;
	
	import mx.controls.Alert;
	import mx.core.Window;
	import mx.events.MenuEvent;
	
	import utilidades.Util;
	
	public var map:Dictionary = new Dictionary();
	
	
	public var logou:Boolean;	
	
	public function init():void{
		this.logou = false;	
		Util.abrePopUp(this,ComponenteAutenticacao,true);
		menuBar.addEventListener(MenuEvent.ITEM_CLICK,itemClickInfo);
	}	
	
	private function itemClickInfo(event:MenuEvent):void {
		switch(event.label){
			case "Frente de Caixa":
				this.frenteLoja();
				break;
			case "Orçamento":
				this.orcamentoWindow();
				break;
			case "Gerenciamento de Unidades":
				this.gerUnidade();
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
	
	public function gerUnidade():void{		
		Util.abrePopUp(this,UnidadeCRUD,true);
	}
	
	
	
	


