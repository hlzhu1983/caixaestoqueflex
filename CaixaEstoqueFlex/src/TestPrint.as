package
{
	 import flash.display.Sprite;
	 import flash.geom.Rectangle;
	 import flash.printing.PrintJob;
	 import flash.printing.PrintJobOrientation;
	 import flash.text.TextField;
       
    public class TestPrint extends Sprite {
        private var sheet1:Sprite;
        private var sheet2:Sprite;
           
        public function TestPrint() {
            init();
            //printOnePerPage();
//            printTwoPerPage();
           printTopHalf();
            draw();
        }
        
        private function init():void {
            sheet1 = new Sprite();
            createSheet(sheet1, "Once upon a time...", {x:10, y:50, width:80, height:130});

            sheet2 = new Sprite();
            createSheet(sheet2, "There was a great story to tell, and it ended quickly.\n\nThe end.", null);
        }
        
        private function createSheet(sheet:Sprite, str:String, imgValue:Object):void {
            sheet.graphics.beginFill(0xEEEEEE);
            sheet.graphics.lineStyle(1, 0x000000);
            sheet.graphics.drawRect(0, 0, 100, 200);
            sheet.graphics.endFill();
            
            var txt:TextField = new TextField();
            txt.height = 200;
            txt.width = 100;
            txt.wordWrap = true;
            txt.text = str;
            
            if(imgValue != null) {
                var img:Sprite = new Sprite();
                img.graphics.beginFill(0xFFFFFF);
                img.graphics.drawRect(imgValue.x, imgValue.y, imgValue.width, imgValue.height);
                img.graphics.endFill();
                sheet.addChild(img);
            }
            sheet.addChild(txt);
        }
        
        private function printOnePerPage():void {
            var pj:PrintJob = new PrintJob();
            var pagesToPrint:uint = 0;
            if(pj.start()) {                
                if(pj.orientation == PrintJobOrientation.LANDSCAPE) {    
                    throw new Error("Without embedding fonts you must print one sheet per page with an orientation of portrait.");
                }
                
                sheet1.height = pj.pageHeight;
                sheet1.width = pj.pageWidth;
                sheet2.height = pj.pageHeight;
                sheet2.width = pj.pageWidth;

                try {
                    pj.addPage(sheet1);
                    pagesToPrint++;
                }
                catch(e:Error) {
                    // do nothing
                }

                try {
                    pj.addPage(sheet2);
                    pagesToPrint++;
                }
                catch(e:Error) {
                    // do nothing
                }

                if(pagesToPrint > 0) {
                    pj.send();
                }
            }
        }
        
        private function printTwoPerPage():void {
            var pj:PrintJob = new PrintJob();
            var pagesToPrint:uint = 0;
            if(pj.start()) {                
                if(pj.orientation == PrintJobOrientation.PORTRAIT) {
                    throw new Error("Without embedding fonts you must print two sheets per page with an orientation of landscape.");
                }
                
                sheet1.height = pj.pageHeight;
                sheet1.width = pj.pageWidth/2;
                sheet2.height = pj.pageHeight;
                sheet2.width = pj.pageWidth/2;

                var sheets:Sprite = new Sprite();
                sheets.addChild(sheet1);
                sheets.addChild(sheet2);
                sheets.getChildAt(1).x = sheets.getChildAt(0).width;
                try {
                    pj.addPage(sheets);
                    pagesToPrint++;
                }
                catch(e:Error) {
                    // do nothing
                }

                if(pagesToPrint > 0) {
                    pj.send();
                }
            }
        }

        private function printTopHalf():void {
            var pj:PrintJob = new PrintJob();
            var pagesToPrint:uint = 0;
            if(pj.start()) {                
                if(pj.orientation == PrintJobOrientation.PORTRAIT) {
                    throw new Error("Without embedding fonts you must print the top half with an orientation of landscape.");
                }
                
                sheet1.height = pj.pageHeight;
                sheet1.width = pj.pageWidth/2;
                sheet2.height = pj.pageHeight;
                sheet2.width = pj.pageWidth/2;

                var sheets:Sprite = new Sprite();
                sheets.addChild(sheet1);
                sheets.addChild(sheet2);
                sheets.getChildAt(1).x = sheets.getChildAt(0).width;
                try {
                    pj.addPage(sheets, new Rectangle(0, 0, sheets.width, sheets.height/2));
                    pagesToPrint++;
                }
                catch(e:Error) {
                    // do nothing
                }

                if(pagesToPrint > 0) {
                    pj.send();
                }
            }
        }


        private function draw():void {
            var sheetWidth:Number = this.stage.stageWidth/2;
            var sheetHeight:Number = this.stage.stageHeight;
            
            addChild(sheet1);
            sheet1.width = sheetWidth;
            sheet1.height = sheetHeight;
            
            addChild(sheet2);
            sheet2.width = sheetWidth;
            sheet2.height = sheetHeight;
            sheet2.x = sheet1.width;
        }        
    }

}