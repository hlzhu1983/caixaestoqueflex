package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="VendaVO")]	
	public class VendaVO {
		
		public var codigo:int;
		public var codCliente:int;
		public var codPrevenda:int;
		public var codUsuario:int;
		public var status:int;		
		public var itemVenda:Array;
		public var dataVenda:Date;
		public var desconto:Number;
		public var valorTotal:Number;
		public var obs:String;

	}
}