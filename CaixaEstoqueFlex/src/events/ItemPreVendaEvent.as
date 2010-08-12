package events
{
	import flash.events.Event;
	
	import negocio.vo.ItemPreVendaVO;
	import negocio.vo.ProdutoVO;

	public class ItemPreVendaEvent extends Event
	{
		public static const ADICIONADOSUCEESSO:String = "adicaoSucessoEvent";
		public static const ADICIONADOFALHOU:String = "adicaoFalhouEvent";
		
		public var itemPreVenda:ItemPreVendaVO;
		
		public function ItemPreVendaEvent(itemPreVenda:ItemPreVendaVO, type:String){
			super(type, true, false);
			this.itemPreVenda = itemPreVenda;
		}
		
		public override function clone():Event{
			return new ItemPreVendaEvent(this.itemPreVenda, this.type);
		}
		
	}
}