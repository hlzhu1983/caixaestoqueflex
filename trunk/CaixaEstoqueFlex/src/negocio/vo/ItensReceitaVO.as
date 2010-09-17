package negocio.vo

{
	
	[Bindable]
	[RemoteClass(alias="vo.ItensReceitaVO")]
	public class ItensReceitaVO	{
		
		public var codigo:int;	 
		public var codReceita:int;
		public var descricao:String;
		public var codProduto:int;
		public var quantidade:Number;
			

	}
}
