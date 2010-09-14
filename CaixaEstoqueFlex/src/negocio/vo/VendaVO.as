package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="vo.VendaVO")]	
	public class VendaVO {
		
		public var codigo:int;
		public var codCliente:int;
		public var codPreVenda:int;
		public var codUsuario:int;
		public var status:int;
		public var dataVenda:String;
		public var desconto:Number;
		public var valorTotal:Number;
		public var formasPagamento:ArrayCollection;
		public var obs:String;

	}
}