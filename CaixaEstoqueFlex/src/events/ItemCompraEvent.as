package events
{
	import flash.events.Event;
	
	import negocio.vo.ItemCompraVO;
	import negocio.vo.ProdutoVO;

	public class ItemCompraEvent extends Event
	{
		public static const ADICIONADOSUCEESSO:String = "adicaoSucessoEvent";
		public static const ADICIONADOFALHOU:String = "adicaoFalhouEvent";
		
		public var itemCompra:ItemCompraVO;
		
		public function ItemCompraEvent(itemCompra:ItemCompraVO, type:String){
			super(type, false, false);
			this.itemCompra = itemCompra;
		}
		
		public override function clone():Event{
			return new ItemCompraEvent(this.itemCompra, this.type);
		}
		
	}
}