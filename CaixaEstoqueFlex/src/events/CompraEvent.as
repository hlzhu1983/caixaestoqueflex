package events
{
	import flash.events.Event;
	
	import negocio.vo.CompraVO;
	
	public class CompraEvent extends Event
	{
			public static const COMPRASELECIONADA:String = "compraSelecionadaEvent";
		
		public var compra:CompraVO;
		
		public function CompraEvent(compra:CompraVO,type:String){
			super(type, false);
			this.compra = compra;
		}
		
		public override function clone():Event{
			return new CompraEvent(this.compra,this.type);
		}
	

	}
}