package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	public class CompraVO
	{
		public var codigo:int;
		public var NF:String;
		public var codUsuario:int;
		public var codFornecedor:int;
	    public var dataCompra:String;
	    public var itemCompra:ArrayCollection;
	}
}