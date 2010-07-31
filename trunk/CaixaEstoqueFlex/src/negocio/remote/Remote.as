package negocio.remote{

	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	import negocio.vo.UnidadeVO;
	
	public class Remote {
		
 		
 		
 		public function listar(result:Function):void{
			var async:AsyncToken = _remote.getUnidades();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addUnidade(result:Function,unidade:UnidadeVO):void{
			var async:AsyncToken = _remote.addUnidade(unidade);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerUnidade(result:Function,unidade:UnidadeVO):void{
			var async:AsyncToken = _remote.removerUnidade(unidade);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarUnidade(result:Function,texto:String,coluna:String):void{
			var async:AsyncToken = _remote.pesquisarUnidade(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
				
		
		
		
		//--------------------setup----------------------------//
		private var _remote:RemoteObject;
		
		public function Remote(){
			
			_remote = new RemoteObject();
			_remote.showBusyCursor = true;
			_remote.destination = 'zend';
			_remote.source = "UnidadeServicos";
			_remote.endpoint = "http://localhost/Zendamf/bridge.php";
			
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