package negocio.vo

{
	import mx.collections.ArrayCollection;
	
	
	[Bindable]
	[RemoteClass(alias="vo.ItemReceitaVO")]
	public class ItemReceitaVO	{
		
		public var codigo:int;	 
		public var codReceita:int;
		public var codProduto:int;
		public var descricao:String;
		public var quantidade:Number;

	}
}
