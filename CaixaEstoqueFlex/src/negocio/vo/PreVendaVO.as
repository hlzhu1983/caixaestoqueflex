package negocio.vo
{
	
	[Bindable]
	[RemoteClass(alias="PreVendaVO")]	
	public class PreVendaVO	{
		
		public var codigo:int;
		public var codCliente:int;
		public var codUsuario:int;
		public var status:int;
		public var obs:String;
		public var itensPreVenda:Array;

	}
}