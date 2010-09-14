package vo;
import java.util.ArrayList;
import java.util.Date;


public class PreVendaVO {
	public int codigo;		
	public int codUsuario;
	public int status;
	public String obs;
	public ArrayList<ItemPreVendaVO> itemPreVenda;
	public String dataAbertura;
	public double valorTotal;
}
