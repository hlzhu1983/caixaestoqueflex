package negocio.vo
{
	import mx.collections.ArrayCollection;
	
	
	[Bindable]
	[RemoteClass(alias="vo.ReceitaVO")]
	public class ReceitaVO {
		
		
		public var codigo:int;
		
		public var codProduto:int;
		
		public var qtdProduzido:Number;
		
		public var itensReceita:ArrayCollection;
		

	}
}