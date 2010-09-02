package modulos.prevenda
{
	import mx.printing.FlexPrintJob;
	import mx.printing.FlexPrintJobScaleType;
	
	import utilidades.Util;
	
	public class PrintPreVenda{
		
		
		public function PrintPreVenda(pVenda:Impressao){
			var p:FlexPrintJob = new FlexPrintJob();
			if(p.start()){
				pVenda.preencher();
				p.addObject(pVenda,FlexPrintJobScaleType.SHOW_ALL);
				p.send();
			}
		}

	}
}