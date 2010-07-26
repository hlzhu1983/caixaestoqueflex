
	import mx.controls.Alert;
	import mx.events.MenuEvent;
	
	import utilidades.Util;
	
	
	public var logou:Boolean;	
	
	public function init():void{
		this.logou = false;	
		Util.abrePopUp(this,ComponentLogin,true);
		menuBar.addEventListener(MenuEvent.ITEM_CLICK,itemClickInfo);
	}	
	
	private function itemClickInfo(event:MenuEvent):void {
         if(event.item.@id == "fLoja")
         	this.frenteLoja();
    }
	
	public function frenteLoja():void{
		this.logou = false;	
		Util.abrePopUp(this,FrenteLoja,true);	
	}
	
	


