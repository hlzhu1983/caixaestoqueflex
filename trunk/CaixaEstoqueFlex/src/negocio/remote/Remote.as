package negocio.remote{

	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	import negocio.vo.*;
	
	public class Remote {
		
		//Servicos Cliente
		
 		public function listarCliente(result:Function):void{
 			_remote.source = "ServicosCliente";
			var async:AsyncToken = _remote.getClientes();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addCliente(result:Function,cliente:ClienteVO):void{
			_remote.source = "ServicosCliente";
			var async:AsyncToken = _remote.addCliente(cliente);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerCliente(result:Function,cliente:ClienteVO):void{
			_remote.source = "ServicosCliente";
			var async:AsyncToken = _remote.removerCliente(cliente);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarCliente(result:Function,texto:String,coluna:String):void{
			_remote.source = "ServicosCliente";
			var async:AsyncToken = _remote.pesquisarCliente(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		public function atualizarCliente(result:Function,cliente:ClienteVO):void{
			_remote.source = "ServicosCliente";
			var async:AsyncToken = _remote.atualizarCliente(cliente);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		//**********************************************************************************//
		
		// Servicos Unidade
 		
 		public function listarUnidade(result:Function):void{
 			_remote.source = "ServicosUnidade";
			var async:AsyncToken = _remote.getUnidades();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addUnidade(result:Function,unidade:UnidadeVO):void{
			var async:AsyncToken = _remote.addUnidade(unidade);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerUnidade(result:Function,unidade:UnidadeVO):void{
			var async:AsyncToken = _remote.removerUnidade(unidade);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarUnidade(result:Function,texto:String,coluna:String):void{
			var async:AsyncToken = _remote.pesquisarUnidade(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
				
		
		//**********************************************************************************//
		
		// Servicos FormadePagto
 		
 		public function listarFormadePagto(result:Function):void{
 			_remote.source = "ServicosFormadePagto";
			var async:AsyncToken = _remote.getFormadePagtos();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addFormadePagto(result:Function,forma:FormadePagtoVO):void{
			var async:AsyncToken = _remote.addFormadePagto(forma);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerFormadePagto(result:Function,forma:FormadePagtoVO):void{
			var async:AsyncToken = _remote.removerFormadePagto(forma);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarFormadePagto(result:Function,texto:String,coluna:String):void{
			var async:AsyncToken = _remote.pesquisarFormadePagto(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		
		//**********************************************************************************//
		
		// Servicos GrupoProduto
 		
 		public function listarGrupoProduto(result:Function):void{
 			_remote.source = "ServicosGrupoProduto";
			var async:AsyncToken = _remote.getGrupoProdutos();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addGrupoProduto(result:Function,grupo:GrupoProdutoVO):void{
			var async:AsyncToken = _remote.addGrupoProduto(grupo);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerGrupoProduto(result:Function,grupo:GrupoProdutoVO):void{
			var async:AsyncToken = _remote.removerGrupoProduto(grupo);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarGrupoProduto(result:Function,texto:String,coluna:String):void{
			var async:AsyncToken = _remote.pesquisarGrupoProduto(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
			
		//**********************************************************************************//
		
		// Servicos LocalProduto
 		
 		public function listarLocalProduto(result:Function):void{
 			_remote.source = "ServicosLocalProduto";
			var async:AsyncToken = _remote.getLocalProdutos();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addLocalProduto(result:Function,local:LocalProdutoVO):void{
			var async:AsyncToken = _remote.addLocalProduto(local);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerLocalProduto(result:Function,local:LocalProdutoVO):void{
			var async:AsyncToken = _remote.removerLocalProduto(local);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarLocalProduto(result:Function,texto:String,coluna:String):void{
			var async:AsyncToken = _remote.pesquisarLocalProduto(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
			
	//-------------------------------Servico Fornecedor		
			public function listarFornecedor(result:Function):void{
 			_remote.source = "ServicosFornecedor";
			var async:AsyncToken = _remote.getFornecedors();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addFornecedor(result:Function,fornecedor:FornecedorVO):void{
			_remote.source = "ServicosFornecedor";
			var async:AsyncToken = _remote.addFornecedor(fornecedor);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerFornecedor(result:Function,fornecedor:FornecedorVO):void{
			_remote.source = "ServicosFornecedor";
			var async:AsyncToken = _remote.removerFornecedor(fornecedor);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		public function pesquisarFornecedor(result:Function,texto:String,coluna:String):void{
			_remote.source = "ServicosFornecedor";
			var async:AsyncToken = _remote.pesquisarFornecedor(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		public function atualizarFornecedor(result:Function,fornecedor:FornecedorVO):void{
			_remote.source = "ServicosFornecedor";
			var async:AsyncToken = _remote.atualizarFornecedor(fornecedor);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
	
	//**********************************************************************************//
		
		// Servicos Usuario
 		
 		public function listarUsuario(result:Function):void{
 			_remote.source = "ServicosUsuario";
			var async:AsyncToken = _remote.getUsuarios();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addUsuario(result:Function,usuario:UsuarioVO):void{
			_remote.source = "ServicosUsuario";
			var async:AsyncToken = _remote.addUsuario(usuario);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function logar(result:Function,usuario:UsuarioVO):void{
			_remote.source = "ServicosUsuario";
			var async:AsyncToken = _remote.logar(usuario);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		public function removerUsuario(result:Function,usuario:UsuarioVO):void{
			_remote.source = "ServicosUsuario";
			var async:AsyncToken = _remote.removerUsuario(usuario);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarUsuario(result:Function,texto:String,coluna:String):void{
			_remote.source = "ServicosUsuario";
			var async:AsyncToken = _remote.pesquisarUsuario(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
				
		//Servicos Produto
		
 		public function listarProduto(result:Function):void{
 			_remote.source = "ServicosProduto";
			var async:AsyncToken = _remote.getProdutos();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function addProduto(result:Function,produto:ProdutoVO):void{
			_remote.source = "ServicosProduto";
			var async:AsyncToken = _remote.addProduto(produto);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function removerProduto(result:Function,produto:ProdutoVO):void{
			_remote.source = "ServicosProduto";
			var async:AsyncToken = _remote.removerProduto(produto);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function pesquisarProduto(result:Function,texto:String,coluna:String):void{
			_remote.source = "ServicosProduto";
			var async:AsyncToken = _remote.pesquisarProduto(texto,coluna);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		public function atualizarProduto(result:Function,produto:ProdutoVO):void{
			_remote.source = "ServicosProduto";
			var async:AsyncToken = _remote.atualizarProduto(produto);
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		//--------------------setup----------------------------//
		private var _remote:RemoteObject;
		
		public function Remote(){
			
			_remote = new RemoteObject();
			_remote.showBusyCursor = true;
			_remote.destination = 'zend';			
			_remote.endpoint = "http://localhost/Zendamf/bridge.php";
			
		}
		
		private function defaultFaultHandler(e:FaultEvent):void{
			Alert.show(e.toString()	+ "","Erro");
		}
		
		private static var _instance:Remote;
		public static function getInstance():Remote{
			if(_instance == null){
				_instance = new Remote();
			}
			return _instance;
		}

	}
}