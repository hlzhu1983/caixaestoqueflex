package utilidades.calculadora
{
	public class Pilha
	{
		private var node:Node;
		
		public function Pilha()
		{
			
			this.node = new Node(null);
			
		}

       public function insert(elm:Object):void{
        var noh: Node= new Node(null);
        noh.setElemento(this.node.getElemento());
        noh.setNext(this.node.getNext());
        
        this.node.setElemento(elm);
        this.node.setNext(noh);
       }
     public function pop():Object{
     if(!this.isEmpty()){
     var elm:Object = this.node.getElemento();
     this.node = this.node.getNext();
     return elm;
     }else{
     return null;}
     
     }
     public function top():Object{
     return this.node.getElemento();
     }
    public function isEmpty():Boolean{
    return (this.node.getElemento()==null)? true:false;
    }   

	}
}