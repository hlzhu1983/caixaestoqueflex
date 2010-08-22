package events
{
	import flash.events.Event;
	
	import negocio.vo.VendaVO;

	public class VendaEvent extends Event {
		
		public static const FINALIZADASUCEESSO:String = "finalizadaSucessoEvent";
		public static const FINALIZADAFALHOU:String = "finalizadaFalhouEvent";
		
		public var venda:VendaVO;
		
		public function VendaEvent(venda:VendaVO, type:String){
			super(type, false, false);
			this.venda = venda;
		}
		
		public override function clone():Event{
			return new VendaEvent(this.venda, this.type);
		}
		
	}
}