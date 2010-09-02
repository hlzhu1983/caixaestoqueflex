package arquitetura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;

public class BancoDeDados {

	private String driver = "com.mysql.jdbc.Driver";
	private String URL = "jdbc:mysql://localhost/caixaEestoque";
	private String USE = "root";
	private String SENHA = "root";
	private Connection conexao;

	public void conectar() throws SQLException {
		Connection con = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Erro ao carregar driver mysql!");
		}
		con = DriverManager.getConnection(URL, USE, SENHA);
		this.conexao = con;
	}

	public void close() throws SQLException {
		if (this.conexao != null)
			this.conexao.close();
	}

	public ResultSet executar(String query) throws SQLException {
		Statement st;
		ResultSet rs;
		this.conectar();
		st = this.conexao.createStatement();
		rs = st.executeQuery(query);
		return rs;
	}

	public int executarNoQuery(String query) throws SQLException {
		Statement st;
		int rs = 0;
		this.conectar();
		st = this.conexao.createStatement();
		rs = st.executeUpdate(query);
		return rs;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String url) {
		URL = url;
	}

	public String getUSE() {
		return USE;
	}

	public void setUSE(String use) {
		USE = use;
	}

	public String getSENHA() {
		return SENHA;
	}

	public void setSENHA(String senha) {
		SENHA = senha;
	}

	public Connection getConexao() {
		return conexao;
	}

}