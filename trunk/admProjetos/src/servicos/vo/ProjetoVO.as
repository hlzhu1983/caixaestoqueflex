package servicos.vo
{
	
	[Bindable]
	[RemoteClass(alias="ProjetoVO")]
	public class ProjetoVO	{
		
		public var codigo:int;
		public var nome:String;
		public var descricao:String;
		public var dataCriacao:String;
		public var dataFinal:String;
		public var obs:String;

	}
}