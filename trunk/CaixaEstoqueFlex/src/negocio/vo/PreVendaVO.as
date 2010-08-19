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
		public var itemPreVenda:Array;
		public var dataAbertura:Date;
		public var desconto:Number;
		public var valorTotal:Number;

	}
}