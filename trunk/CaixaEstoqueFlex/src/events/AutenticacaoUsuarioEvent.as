package events
{
	import flash.events.Event;
	
	import negocio.vo.UsuarioVO;

	public class AutenticacaoUsuarioEvent extends Event
	{
		
		public static const SUCESSO:String = "sucessoEvent";
		public static const FALHOU:String = "falhouEvent";
		
		public var usuario:UsuarioVO;
		
		public function AutenticacaoUsuarioEvent(usuario:UsuarioVO, type:String){
			super(type, true, false);
			this.usuario = usuario;
		}
		
		public override function clone():Event{
			return new AutenticacaoUsuarioEvent(this.usuario, this.type);
		}
		
	}
}