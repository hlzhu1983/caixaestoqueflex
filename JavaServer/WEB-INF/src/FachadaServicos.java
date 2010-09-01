import services.ServicosCliente;




public class FachadaServicos {
	
	
	private ServicosCliente servCliente;
	
	
	public FachadaServicos() {
		
		this.servCliente = new ServicosCliente();
	}
	
	
	public String mensagemCliente(){
		return "PEgou";
	}
	
	

}
