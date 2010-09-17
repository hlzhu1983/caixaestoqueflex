package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="vo.ItemProducaoVO")]	
	public class ItemProducaoVO	{
		
		public var codigo:int;
		public var codProducao:int;
		public var descricao:String;
		public var codReceita:int;
		

	}
}