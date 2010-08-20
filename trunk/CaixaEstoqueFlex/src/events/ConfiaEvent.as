package events
{
	import flash.events.Event;
	
	import negocio.vo.ClienteVO;

	public class ConfiaEvent extends Event	{
		public static const ADICIONADOSUCEESSO:String = "adicaoSucessoEvent";
		public static const ADICIONADOFALHOU:String = "adicaoFalhouEvent";
		
		public var cliente:ClienteVO;
		public var formaPgto:FormadePagtoVO;
		
		public function ItemPreVendaEvent(cliente:ClienteVO,formaPgto:FormadePagtoVO, type:String){
			super(type, false, false);
			this.formaPgto = formaPgto;
			this.cliente = cliente;
		}
		
		public override function clone():Event{
			return new ItemPreVendaEvent(this.cliente,this.formaPgto, this.type);
		}
		
		
	}
}