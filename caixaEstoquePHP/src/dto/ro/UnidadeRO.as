package dto.ro
{
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	import dto.DadosUnidadeDTO;
	
	public class UnidadeRO{
		
		public var ro_unidade:RemoteObject;
		
		public static var instance:UnidadeRO;
		
		public function insert(dadosUnidade:DadosUnidadeDTO, result:Function):void{
			var asyn:AsyncToken = this.ro_unidade.insertUnidade(dadosUnidade);
			asyn.addResponder(new Responder(result,falhaExecucao));
		}	
		
		public function remover(dadosUnidade:DadosUnidadeDTO, result:Function):void{
			var asyn:AsyncToken = this.ro_unidade.removerUnidade(dadosUnidade);
			asyn.addResponder(new Responder(result,falhaExecucao));
		}	
		
		public function falhaExecucao(event:FaultEvent):void{
			Alert.show(event.fault.faultDetail,event.fault.faultString);
		}
		
		
		public static function getInstance():UnidadeRO{
			if(instance == null){
				instance = new UnidadeRO();				
			}
			return instance;
		}
		
		public function UnidadeRO(){
			this.ro_unidade = new RemoteObject();
			this.ro_unidade.showBusyCursor = true;
			this.ro_unidade.destination = 'amfphp';
			this.ro_unidade.source = 'manutencao_dto.ServicosUnidadeDTO';
		}

	}
}