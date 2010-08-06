package servicos.remote{

	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	import servicos.vo.ProjetoVO;
	
	public class Remote {
		
		
		// Servicos Usuario
 		
 		public function listarProjeto(result:Function):void{
 			_remote.source = "ServicosProjeto";
			var async:AsyncToken = _remote.getItens();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addProjeto(result:Function,item:ProjetoVO):void{
			_remote.source = "ServicosProjeto";
			var async:AsyncToken = _remote.addItem(item);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function atualizarProjeto(result:Function,item:ProjetoVO):void{
			_remote.source = "ServicosProjeto";
			var async:AsyncToken = _remote.atualizarItem(item);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerProjeto(result:Function,item:ProjetoVO):void{
			_remote.source = "ServicosProjeto";
			var async:AsyncToken = _remote.removerItem(item);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarProjeto(result:Function,texto:String,coluna:String):void{
			_remote.source = "ServicosProjeto";
			var async:AsyncToken = _remote.pesquisarItens(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
				
		
		//--------------------setup----------------------------//
		private var _remote:RemoteObject;
		
		public function Remote(){
			
			_remote = new RemoteObject();
			_remote.showBusyCursor = true;
			_remote.destination = 'zend';			
			_remote.endpoint = "/admProjetos/ZendAMFAdminProjeto/bridge.php";
			
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