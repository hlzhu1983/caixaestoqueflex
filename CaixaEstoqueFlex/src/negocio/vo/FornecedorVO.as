package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="vo.FornecedorVO")]
	

	public class FornecedorVO
	{
		
		public var codigo:int;
		public var nome:String;
		public var endereco:String;
		public var bairro:String;
		public var cidade:String;
		public var UF:String
		public var cep:String;
		public var cpf_cnpj:String;
		public var insc_estadual:String;
		public var fone:String;
		public var contato:String;
		public var email:String;
		public var url:String;
		public var obs:String;
		public var avaliacao:String;


	
}}