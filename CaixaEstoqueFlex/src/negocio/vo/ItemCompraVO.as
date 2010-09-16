package negocio.vo
{[Bindable]
	[RemoteClass(alias="vo.ItemCompraVO")]
	public class ItemCompraVO
	{
	public var codigo:int;
	public var codigoCompra:int;
	public var descricao:String;
	public var status:int;
	public var codProduto:int;
	public var quantidade:int;
	public var valorCompra:Number;
	}
}