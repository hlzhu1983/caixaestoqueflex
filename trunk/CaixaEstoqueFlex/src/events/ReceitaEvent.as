package events
{
	import flash.events.Event;
	
	import negocio.vo.ReceitaVO;

	public class ReceitaEvent extends Event	{
		
		public static const RECEITASELECIONADA:String = "receitaSelecionadaEvent";
		
		public var receita:ReceitaVO;
		
		public function ReceitaEvent(receita:ReceitaVO,type:String){
			super(type, false);
			this.receita = receita;
		}
		
		public override function clone():Event{
			return new ReceitaEvent(this.receita,this.type);
		}
		
		
	}
}