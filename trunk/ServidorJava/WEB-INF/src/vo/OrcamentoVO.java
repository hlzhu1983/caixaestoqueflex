package vo;
import java.util.ArrayList;
import java.util.Date;


public class OrcamentoVO {

	
	public int codigo;
	public int codCliente;
	public int codUsuario;
	public int status;
	public String obs;
	public ArrayList<ItemPreVendaVO> itemPreVenda;
	public Date dataAbertura;
	public double valorTotal;
}
