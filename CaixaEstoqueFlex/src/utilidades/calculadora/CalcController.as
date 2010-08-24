package utilidades.calculadora
{
	import flash.sampler.NewObjectSample;
	
	public class CalcController
	{  
	public static var MEM:int= 0;	
	public static var ADD:int = 1;
	public static var SUB:int = 2;
	public static var MULT:int = 3;
	public static var DIV:int = 4;
	public static var RES:int = 5;
    
		
	[Bindable]
	   public var texto:String = "0";
		public var ponto:Boolean;
		public var zerar:Boolean;
		public var operar:ObjCalc;
		public var ultimo:ObjCalc;
		public function CalcController()
		{
	this.texto = "0";
	this.ponto = false;
	this.operar = new ObjCalc();
	this.zerar = true;
	this.ultimo = new ObjCalc();
		}
      public function clearAll():void{
      this.texto = "0";
      this.zerar = true;
     this.ponto = false;
      this.operar = new ObjCalc();
      this.ultimo = new ObjCalc();
      }
      
 	public function clearEntry():void{
  this.texto = "0";
  this.zerar = true;
  this.ponto = false;
  this.ultimo =new ObjCalc();
 
 	}
 	public function setOperation0(op:int):void{
   var valor:ObjCalc = new ObjCalc();
   switch(op){
   case ADD:
   this.operar.num1 = Number(this.texto);
   this.operar.op = ADD;
   this.zerar = true;
   this.ponto = false;
   break;
   
    case MULT:
     this.operar.num1 = Number(this.texto);
   this.operar.op = MULT;
   this.zerar = true;
   this.ponto = false;
   break;
    case SUB:
     this.operar.num1 = Number(this.texto);
   this.operar.op = SUB;
   this.zerar = true;
    this.ponto = false;
   break;
    case DIV:
     this.operar.num1 = Number(this.texto);
   this.operar.op = DIV;
   this.zerar = true;
   this.ponto = false;
   break;
   
   
   
   
   }
 	}
 	public function addNumber(num:String):void{
   if(num=='.'&&!ponto){
   this.ponto = true;
  if(zerar){
  this.texto = "0"+num;
  this.zerar = false;
  }else{
  this.texto += num;}
   }else if(num!='.'){
  if(zerar){
  this.texto = num;
  this.zerar = false;
  }else{
  this.texto += num;} 
   }
  
 	}
 	public function doOperation():void{
    switch(this.operar.op){
     case ADD:
     
   this.operar.num2 = Number(this.texto);
   this.ultimo.num1 = this.operar.num2;
   this.ultimo.op = ADD;
   this.texto =(this.operar.num1+this.operar.num2).toString();
   this.zerar = true;
   this.ponto = false;
   this.operar = new ObjCalc();
   break;
    case MULT:
    this.operar.num2 = Number(this.texto);
    this.ultimo.num1 =this.operar.num2;
    this.ultimo.op = MULT;
   this.texto =(this.operar.num2*this.operar.num1).toString();
   this.zerar = true;
  this.ponto = false;
   this.operar = new ObjCalc();
   break;
    case SUB:
    this.operar.num2 = Number(this.texto);
    this.ultimo.num1 = this.operar.num2;
     this.ultimo.op = SUB;
   this.texto =(this.operar.num1-this.operar.num2).toString();
   this.zerar = true;
   this.ponto = false;
   this.operar = new ObjCalc();
   break;
    case DIV:
    this.operar.num2 = Number(this.texto);
    this.ultimo.num1 = this.operar.num2;
      this.ultimo.op = DIV;
    this.texto =(this.operar.num1/this.operar.num2).toString();

   this.zerar = true;
    this.ponto = false;
    this.operar = new ObjCalc();
     
   break;
   case MEM:
   if(this.ultimo.num1!=0){
  
    this.operar.num1 = Number(this.texto);
   this.operar.op = this.ultimo.op;
    this.texto = this.ultimo.num1.toString();
    this.doOperation();   
   }
   break;
 	}
 	
	}}
}