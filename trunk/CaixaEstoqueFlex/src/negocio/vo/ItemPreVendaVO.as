package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="ItemPreVendaVO")]	
	public class ItemPreVendaVO	{
		
		public var codigoPrevenda:int;
		public var codProduto:int;
		public var quantidade:Number;
		public var valor:Number;

	}
}