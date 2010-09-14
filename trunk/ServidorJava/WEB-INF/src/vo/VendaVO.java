package vo;
import java.util.ArrayList;
import java.util.Date;


public class VendaVO {

	public int codigo;
	public int codCliente;
	public int codPreVenda;
	public int codUsuario;
	public int status;
	public String dataVenda;
	public double desconto;
	public  double valorTotal;
	public ArrayList<FormaPgtoVendaVO> formasPagamento;
	public String obs;
	
}
