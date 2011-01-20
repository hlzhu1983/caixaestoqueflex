package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="vo.CompraVO")]
	public class CompraVO
	{
		public var codigo:int;
		public var NF:String;
		public var codUsuario:int;
		public var codFornecedor:int;
	    public var dataCompra:String;
	    public var itemCompra:ArrayCollection;
	    public var valorTotalCompra:Number;
	}
}