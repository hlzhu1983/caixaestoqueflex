package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="vo.ItemPreVendaVO")]	
	public class ItemPreVendaVO	{
		
		public var codigo:int;
		public var codigoPrevenda:int;
		public var codProduto:int;
		public var descricao:String;
		public var quantidade:Number;
		public var valor:Number;

	}
}