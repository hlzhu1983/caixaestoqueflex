package negocio.vo
{
	
	[Bindable]
	[RemoteClass(alias="vo.FormaPgtoVendaVO")]
	public class FormaPgtoVendaVO	{
		
		public var codigo:int;
		public var codVenda:int;
		public var codFormaPagamento:int;
		public var parcelas:int;
		public var valor:Number;

	}
}