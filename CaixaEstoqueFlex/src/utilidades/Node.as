package utilidades
{
	public class Node
	{
		private var elemento:Object;
		private var proximo:Node;
		public function Node()
		{
			this.elemento = null;
			this.proximo = null;
		}
    
    public function Node(elm:Object)
		{
			this.elemento = elm;
			this.proximo = null;
		}
     public function getNext():Node{
      return this.proximo;
     }
    public function setNext(node:Node):void{
    
     this.proximo = node;
    }
    
    public function setElemento(elm:Object):void{
     this.elemento = elm;
    }
     public function getElemento():Node{
      return this.elemento;
     }
	}
}