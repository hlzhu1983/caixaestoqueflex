package events
{
	import flash.events.Event;
	
	import negocio.vo.PreVendaVO;
	
	public class PreVendaEvent extends Event	{
		public static const ADICIONADOSUCEESSO:String = "adicaoSucessoEvent";
		public static const ADICIONADOFALHOU:String = "adicaoFalhouEvent";
		
		public var preVenda:PreVendaVO;
		
		public function PreVendaEvent(preVenda:PreVendaVO, type:String){
			super(type, false, false);
			this.preVenda = preVenda;
		}
		
		public override function clone():Event{
			return new PreVendaEvent(this.preVenda, this.type);
		}

	}
}