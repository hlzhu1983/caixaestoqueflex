package events
{
	import flash.events.Event;
	
	import negocio.vo.FornecedorVO;

	public class FornecedorEvent extends Event{
		
		public static const FORNECEDORSELECIONADO:String = "fornecedorSelecionadoEvent";
		
		public var fornecedor:FornecedorVO;
		
		public function FornecedorEvent(fornecedor:FornecedorVO, type:String){
			super(type, false,false);
			this.fornecedor = fornecedor;
		}
		
		public override function clone():Event{
			return new FornecedorEvent(this.fornecedor,this.type);
		}
		
	}
}