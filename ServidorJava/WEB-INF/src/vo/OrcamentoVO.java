package vo;
import java.sql.Date;
import java.util.ArrayList;


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
