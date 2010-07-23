
import mx.containers.TitleWindow;
import mx.managers.PopUpManager;


public var logou:Boolean;



public function init():void{

	this.logou = false;
	
	var tWindow:TitleWindow = TitleWindow(PopUpManager.createPopUp(this,ComponentLogin,true));
	
	tWindow.showCloseButton = true;
	
	
}


