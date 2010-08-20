package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="VendaVO")]	
	public class VendaVO {
		
		public var codigo:int;
		public var codCliente:int;
		public var codPreVenda:int;
		public var codUsuario:int;
		public var status:int;
		public var dataVenda:String;
		public var desconto:Number;
		public var valorTotal:Number;
		public var formasPagamento:Array;
		public var obs:String;

	}
}