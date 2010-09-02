package negocio.vo
{
	
	[Bindable]
	[RemoteClass(alias="vo.PreVendaVO")]	
	public class PreVendaVO	{
		
		public var codigo:int;		
		public var codUsuario:int;
		public var status:int;
		public var obs:String;
		public var itemPreVenda:Array;
		public var dataAbertura:String;
		public var valorTotal:Number;

	}
}