package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="ItemPreVendaVO")]	
	public class ItemPreVendaVO	{
		
		public var codigo:int;
		public var descricao:String;
		public var quantidade:Number;
		public var valor:Number;

	}
}