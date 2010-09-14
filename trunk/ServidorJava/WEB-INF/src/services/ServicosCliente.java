package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import exception.ClienteJaexisteException;

import vo.*;

public class ServicosCliente {

	public ClienteVO adicionarCliente(ClienteVO item) throws SQLException {

		String sql = "insert into cliente (nome,tipoPessoa,sexo ,dataNascimento ,dataCadastro ,endereco,bairro ,cidade ,UF ,cep ,cpf_cnpj ,insc_estadual,fone ,contato ,email ,url ,obs,limCredito) values ("
				+ "'"
				+ item.nome
				+ "','"
				+ item.tipoPessoa
				+ "','"
				+ item.sexo
				+ "','"
				+ item.dataNascimento
				+ "','"
				+ item.dataCadastro
				+ "','"
				+ item.endereco
				+ "','"
				+ item.bairro
				+ "','"
				+ item.cidade
				+ "',"
				+ "'"
				+ item.UF
				+ "','"
				+ item.cep
				+ "','"
				+ item.cpf_cnpj
				+ "','"
				+ item.insc_estadual
				+ "','"
				+ item.fone
				+ "','"
				+ item.contato
				+ "','"
				+ item.email
				+ "','"
				+ item.url
				+ "','"
				+ item.obs
				+ "','" + item.limCredito + "')";

		Statement st;
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();

		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao adicionar de produto!");
		}

		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar cliente!");
		this.banco.getConexao().commit();

		return item;

	}

	public boolean removerCliente(ClienteVO item) {

		String sql = "delete from cliente  where codigo = " + item.codigo;

		if (banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public ArrayList<ClienteVO> pesquisarCliente(String texto, String coluna) {
		String sql = "select *, date_format(datacadastro, '%m/%d/%Y') as dCadastro, date_format(datanascimento, '%m/%d/%Y') as dNascimento from cliente where "
				+ coluna + " like '%" + texto + "%'";
		ResultSet rs = banco.executar(sql);
		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		int i = 0;
		while (rs.next()) {

			ClienteVO dados_item = new ClienteVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.nome = rs.getString("nome");
			dados_item.bairro = rs.getString("bairro");
			dados_item.cep = rs.getString("cep");
			dados_item.cidade = rs.getString("cidade");
			dados_item.contato = rs.getString("contato");
			dados_item.cpf_cnpj = rs.getString("cpf_cnpj");
			dados_item.dataCadastro = rs.getString("dCadastro");
			dados_item.dataNascimento = rs.getString("dNascimento");
			dados_item.email = rs.getString("email");
			dados_item.endereco = rs.getString("endereco");
			dados_item.fone = rs.getString("fone");
			dados_item.insc_estadual = rs.getString("insc_estadual");
			dados_item.obs = rs.getString("obs");
			dados_item.sexo = rs.getInt("sexo");
			dados_item.tipoPessoa = rs.getInt("tipoPessoa");
			dados_item.UF = rs.getString("UF");
			dados_item.url = rs.getString("URL");
			dados_item.limCredito = rs.getDouble("limCredito");
			clientes.add(dados_item);
			i++;
		}
		return clientes;
	}

	public ArrayList<ClienteVO> getClientes() {

		String sql = "select *,date_format(datacadastro, '%m/%d/%Y') as dCadastro, date_format(datanascimento, '%m/%d/%Y') as dNascimento from cliente";
		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		ResultSet rs = banco.executar(sql);
		int i = 0;
		while (rs.next()) {

			ClienteVO dados_item = new ClienteVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.nome = rs.getString("nome");
			dados_item.bairro = rs.getString("bairro");
			dados_item.cep = rs.getString("cep");
			dados_item.cidade = rs.getString("cidade");
			dados_item.contato = rs.getString("contato");
			dados_item.cpf_cnpj = rs.getString("cpf_cnpj");
			dados_item.dataCadastro = rs.getString("dCadastro");
			dados_item.dataNascimento = rs.getString("dNascimento");
			dados_item.email = rs.getString("email");
			dados_item.endereco = rs.getString("endereco");
			dados_item.fone = rs.getString("fone");
			dados_item.insc_estadual = rs.getString("insc_estadual");
			dados_item.obs = rs.getString("obs");
			dados_item.sexo = rs.getInt("sexo");
			dados_item.tipoPessoa = rs.getInt("tipoPessoa");
			dados_item.UF = rs.getString("UF");
			dados_item.url = rs.getString("URL");
			dados_item.limCredito = rs.getDouble("limCredito");
			clientes.add(i, dados_item);
			i++;
		}

		return clientes;
	}

	public ClienteVO atualizarCliente(ClienteVO cliente) {
		int i = 0;
		String sql = "UPDATE cliente SET nome = '" + cliente.nome
				+ "', tipoPessoa = " + cliente.tipoPessoa + ", sexo = "
				+ cliente.sexo + ", dataNascimento = '"
				+ cliente.dataNascimento + "',dataCadastro = '"
				+ cliente.dataCadastro + "',endereco='" + cliente.endereco
				+ "',bairro='" + cliente.bairro + "',cidade='" + cliente.cidade
				+ "',UF='" + cliente.UF + "',cep='" + cliente.cep + "',"
				+ "cpf_cnpj='" + cliente.cpf_cnpj + "',insc_estadual='"
				+ cliente.insc_estadual + "',fone='" + cliente.fone
				+ "',contato='" + cliente.contato + "',email='" + cliente.email
				+ "',url='" + cliente.url + "',obs='" + cliente.obs
				+ "' where codigo=" + cliente.codigo + "";

		i = this.banco.executarNoQuery(sql);
		if (i == 0) {
			try {
				throw new ClienteJaexisteException();
			} catch (ClienteJaexisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.banco.close();
		return cliente;

	}
}
