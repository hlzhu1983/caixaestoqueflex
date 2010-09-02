package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="vo.UsuarioVO")]
	public class UsuarioVO
	{
		public var codigo:int;
		public var nome:String;
		public var comissao:Number;
		public var senha:String;
		public var permissao:int;
		public var login:String;
	}
}