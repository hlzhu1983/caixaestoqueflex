
import mx.containers.TitleWindow;
import mx.managers.PopUpManager;


public var logou:Boolean;



public function init():void{

this.logou = false;
TitleWindow(
PopUpManager.createPopUp(this,ComponentLogin,true));
}


