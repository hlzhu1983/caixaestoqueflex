package utilidades
{
	import flash.display.DisplayObject;
	
	import mx.containers.TitleWindow;
	import mx.managers.PopUpManager;
	
	public class Util
	{
		
		
		public static function abrePopUp(proprietario:DisplayObject,classe:Class,model:Boolean):void{
			var tWindow:TitleWindow = TitleWindow(PopUpManager.createPopUp(proprietario,classe,true));
			PopUpManager.centerPopUp(tWindow);
		}

	}
}