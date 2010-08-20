package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="OrcamentoVO")]	
	public class OrcamentoVO{
		
		public var codigo:int;
		public var codCliente:int;
		public var codUsuario:int;
		public var status:int;
		public var obs:String;
		public var itemOrcamento:Array;
		public var dataAbertura:Date;
		public var valorTotal:Number;

	}
}