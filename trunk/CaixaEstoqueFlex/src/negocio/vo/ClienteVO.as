package negocio.vo
{
	[Bindable]
	[RemoteClass(alias="ClienteVO")]
	public class ClienteVO{
		
		public var codigo:int;
		public var nome:String;
		public var tipoPessoa:int;
		public var sexo:int;
		public var dataNascimento:String;
		public var dataCadastro:String;
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
		public var limCredito:Number;
		public var obs:String;
		
		
		
		public function getDataNascimento():Date{
			return new Date(this.dataNascimento);
		}
		
		public function getDataCadastro():Date{
			return new Date(this.dataCadastro);
		}

	}
}