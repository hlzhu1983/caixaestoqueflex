package events
{
	import flash.events.Event;
	
	import negocio.vo.ProdutoVO;

	public class ProdutoEvent extends Event	{
		
		public static const PRODUTOSELECIONADO:String = "produtoSelecionadoEvent";
		
		public var produto:ProdutoVO;
		
		public function ProdutoEvent(produto:ProdutoVO,type:String){
			super(type, false);
			this.produto = produto;
		}
		
		public override function clone():Event{
			return new ProdutoEvent(this.produto,this.type);
		}
		
	}
}