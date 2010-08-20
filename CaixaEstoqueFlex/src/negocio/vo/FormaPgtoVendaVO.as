package negocio.vo
{
	
	[Bindable]
	[RemoteClass(alias="FormaPgtoVendaVO")]
	public class FormaPgtoVendaVO	{
		
		public var codigo:int;
		public var codVenda:int;
		public var codFormaPagamento:int;
		public var parcelas:int;
		public var valor:Number;

	}
}