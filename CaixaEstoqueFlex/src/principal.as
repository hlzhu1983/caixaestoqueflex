
	import modulos.orcamento.Orcamento;
	
	import mx.controls.Alert;
	import mx.core.Window;
	import mx.events.MenuEvent;
	
	import utilidades.Util;
	
	
	public var logou:Boolean;	
	
	public function init():void{
		this.logou = false;	
		Util.abrePopUp(this,ComponenteAutenticacao,true);
		menuBar.addEventListener(MenuEvent.ITEM_CLICK,itemClickInfo);
	}	
	
	private function itemClickInfo(event:MenuEvent):void {
		switch (event.index){
         case 0:
         	this.frenteLoja();
         	break;
          case 2:
         	this.orcamentoWindow();
         	break;
  		}
    }
	
	public function frenteLoja():void{
		this.logou = false;	
		var newWindow:Window = new FrenteLoja();
		
		newWindow.maximizable=false;
		newWindow.minimizable = false;
		newWindow.resizable = false;
		newWindow.open(false);
		newWindow.activate();
		
		//Util.abrePopUp(this,FrenteLoja,true);	
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
		
		//Util.abrePopUp(this,FrenteLoja,true);	
	}
	
	
	
	


