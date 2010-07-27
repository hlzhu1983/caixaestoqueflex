package negocio.business
{
	import mx.rpc.IResponder;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	import negocio.vo.*;
    
	public class ClienteDAODelegate
	{
		private var responder : IResponder;
		private var service : Object;
		
		public function ClienteDAODelegate(pResponder : IResponder )
		{
			service = new RemoteObject();
			service.endpoint="http://localhost/flex-php/CaixaEstoqueFlex/amfphp/gateway.php";
			service.destination="amfphp";
			service.makeObjectsBindable=true;
			service.showBusyCursor=true;
			service.source="ClienteDAO";

			responder = pResponder;
		}   

		public function inserir(tipoPessoa:int, nome:String, sexo:int, dataNascimento:String, endereco:String, bairro:String, cidade:String, UF:OString, cep:String, cpf_cnpj:String, insc_estadual:String, fone:String, contato:String, email:String, URL:String, obs:String):void
		{
			var call:Object = service.inserir(tipoPessoa, nome, sexo, dataNascimento, endereco, bairro, cidade, UF, cep, cpf_cnpj, insc_estadual, fone, contato, email, URL, obs);
			call.addResponder(responder);
		}

		public function remover(codigo:int):void
		{
			var call:Object = service.remover(codigo);
			call.addResponder(responder);
		}

		public function procurarPorNome(nome:String):void
		{
			var call:Object = service.procurarPorNome(nome);
			call.addResponder(responder);
		}

		public function procurar(codigo:int):void
		{
			var call:Object = service.procurar(codigo);
			call.addResponder(responder);
		}

		public function getClientes():void
		{
			var call:Object = service.getClientes();
			call.addResponder(responder);
		}

		public function alterar(codigo:Object, tipoPessoa:Object, sexo:Object, dataNascimento:Object, endereco:Object, bairro:Object, cidade:Object, UF:Object, cep:Object, cpf_cnpj:Object, insc_estadual:Object, fone:Object, contato:Object, email:Object, URL:Object, obs:Object):void
		{
			var call:Object = service.alterar(codigo, tipoPessoa, sexo, dataNascimento, endereco, bairro, cidade, UF, cep, cpf_cnpj, insc_estadual, fone, contato, email, URL, obs);
			call.addResponder(responder);
		}

			

	}

}	