package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="ProdutoVO")]	
	public class ProdutoVO	{
		
		public var codigo:int;
		public var codBarra:String;
		public var codGrupo:int;
		public var descricao:String;
		public var referencia:String;
		public var codLocal:int;
		public var codUnidade:int;
		public var qtdPorUnidade:Number;
		public var qtdEmEstoque:Number;
		public var codFornecedor:int;
		public var precoCompra:Number;
		public var precoVenda:Number;
		public var foto:String;

	}
}