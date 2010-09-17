package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	
	[Bindable]
	[RemoteClass(alias="vo.ReceitaVO")]
	public class ReceitaVO {
		
		public var codigo:int;
		public var codProduto:int;
		public var descricao:String;
		public var qtdEstoque:Number;
		public var quantidade:Number;
		public var itensReceita:ArrayCollection;
		

	}
}