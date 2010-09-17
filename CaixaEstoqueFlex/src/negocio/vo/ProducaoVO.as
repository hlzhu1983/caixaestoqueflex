package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="vo.ProducaoVO")]	
	public class ProducaoVO	{
		
		public var codigo:int;
		public var dataProducao:String;
		public var obs:String;
		public var descricao:String;
		public var itensProducao:ArrayCollection;

	}
}