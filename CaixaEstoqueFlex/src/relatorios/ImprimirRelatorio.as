// ActionScript file
import mx.controls.Label;
import mx.printing.FlexPrintJob;


 public function doPrint():void{
 	var print:FlexPrintJob = new FlexPrintJob();
 	if(print.start() != true){
 		nomeUsuario.text = "pegou";
 		return
 	}
 	var l:Label = new Label();
 	l.text = "Impressao";
 	print.addObject(l);
 	print.send(); 
 }
