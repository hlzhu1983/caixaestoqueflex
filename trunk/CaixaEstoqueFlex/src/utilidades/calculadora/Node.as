package utilidades.calculadora
{
	public class Node
	{
		private var elemento:Object;
		private var proximo:Node;
		
    
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
     public function getElemento():Object{
      return this.elemento;
     }
	}
}