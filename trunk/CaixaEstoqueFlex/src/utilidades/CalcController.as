package utilidades
{
	import mx.collections.ArrayCollection;
	
	public class CalcController
	{  
	public static var ADD:int = 0;
	public static var SUB:int = 0;
	public static var MULT:int = 0;
	public static var DIV:int = 0;
	
    
		
	[Bindable]
	   public var texto:String = "";
		public var ponto:Boolean;
		public var pilha:Pilha;
		public function CalcController()
		{
	this.texto = "";
	this.ponto = false;
	this.array = new Pilha();
		}
      public function clearAll():void{
      this.texto = "";
     this.ponto = false;
     this.array = new Pilha();
      }
      
 	public function clearEntry():void{
  this.texto = "";
  this.ponto = false;
  this.pilha.pop;
 	}
 	public function setOperation0(op:int):void{
   switch(op){
   case ADD:
   break;
    case MULT:
   break;
    case SUB:
   break;
    case DIV:
   break;
   
   
   
   
   }
 	}
 	public function addNumber(num:String):void{
   if(num=='.'&&!ponto){
   this.ponto = true;
  this.texto += num; 
   }else if(num!='.'){
  this.texto += num; 
   }
  
 	}
 	public function doOperation():void{
  this.texto = "";
 	}
 	
	}
}