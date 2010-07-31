package remote{

	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	public class Remote {
		
 		
 		
 		public function listar(result:Function, source:String):void{
 			_remote.source = source;
			var async:AsyncToken = _remote.getUnidades();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
				
		
		
		
		//--------------------setup----------------------------//
		private var _remote:RemoteObject;
		
		public function Remote(){
			
			_remote = new RemoteObject();
			_remote.showBusyCursor = true;
			_remote.destination = 'zend';
			_remote.endpoint = "http://localhost/CEFlexPHP/php/bridge.php";
			
		}
		
		private function defaultFaultHandler(e:FaultEvent):void{
			Alert.show(e.toString()+ "","Erro");
		}
		
		private static var _instance:Remote;
		public static function getInstance():Remote{
			if(_instance == null){
				_instance = new Remote();
			}
			return _instance;
		}

	}
}