package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.FornecedorVO;

public class ServicosFornecedor {

	public FornecedorVO addFornecedor(FornecedorVO item) {

		String sql = "insert into fornecedor (nome,endereco"
				+ ",bairro ,cidade ,UF ,cep ,cpf_cnpj ,insc_estadual"
				+ ",fone ,contato ,email ,url ,obs,avaliacao) values ('"
				+ item.nome + "','"	+ item.endereco	+ "','"	+ item.bairro+ "' ,'"
				+ item.cidade + "','"+ item.UF+ "' , '" + item.cep+ "','"
				+ item.cpf_cnpj	+ "' , '"+ item.insc_estadual + "','"
				+ item.fone	+ "' , '"+ item.contato	+ "','"
				+ item.email + "' , '"+ item.url
				+ "' ,'" + item.obs	+ "','"
				+ item.avaliacao + "')";

		Statement st;
		if (this.isExiste(item)) {
			throw new RuntimeException("Fornecedor ja existe!");
		}
		this.banco.conectar();
		this.banco.getConexao().setAutoCommit(false);
		st = this.banco.getConexao().createStatement();
		if (st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS) == 0) {
			throw new RuntimeException("Erro ao salvar fornecedor!");
		}
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next())
			item.codigo = rs.getInt(1);
		else
			throw new RuntimeException("Erro ao salvar fornecedor!");
		this.banco.getConexao().commit();
		return item;

	}

	public boolean removerFornecedor(FornecedorVO item) {
		String sql = "delete from fornecedor  where codigo = " + item.codigo
				+ "";

		if (this.banco.executarNoQuery(sql) == 0) {
			return false;
		}
		return true;
	}

	public FornecedorVO atualizarFornecedor(FornecedorVO fornecedor) {
		String sql = "UPDATE fornecedor SET nome = '" + fornecedor.nome
				+ "',endereco='" + fornecedor.endereco + "',bairro='"
				+ fornecedor.bairro + "',cidade='" + fornecedor.cidade
				+ "',UF='" + fornecedor.UF + "',cep='" + fornecedor.cep
				+ "',cpf_cnpj='" + fornecedor.cpf_cnpj + "',insc_estadual='"
				+ fornecedor.insc_estadual + "',fone='" + fornecedor.fone
				+ "',contato='" + fornecedor.contato + "',email='"
				+ fornecedor.email + "',url='" + fornecedor.url + "',obs='"
				+ fornecedor.obs + "',avaliacao='" + fornecedor.avaliacao
				+ "' where codigo=" + fornecedor.codigo + "";

		if (!this.isExiste(fornecedor)) {
			throw new RuntimeException("Fornecedor não existe!");
		}
		if (banco.executarNoQuery(sql) == 0) {
			throw new RuntimeException("Fornecedor forma de pagamento!");
		}
		return fornecedor;
	}

	public ArrayList<FornecedorVO> pesquisarFornecedor(String texto,
			String coluna) {
		String sql = "select * from fornecedor where " + coluna + " like '%"
				+ texto + "%'";

		ResultSet rs = banco.executar(sql);
		return this.toFornecedor(rs);
	}

	public ArrayList<FornecedorVO> getFornecedores() {
		String sql = "select * from fornecedor";
		ResultSet rs = banco.executar(sql);
		return this.toFornecedor(rs);
	}
	
	public ArrayList<FornecedorVO> getFornecedores(String sql) {
		ResultSet rs = banco.executar(sql);
		return this.toFornecedor(rs);
	}

	public boolean isExiste(FornecedorVO fornecedor) {
		String sql = "select * from fornecedor where codigo = '"
				+ fornecedor.codigo + "'";
		ResultSet rs;
		rs = this.banco.executar(sql);
		if (rs.next())
			return true;
		else
			return false;

	}

	private ArrayList<FornecedorVO> toFornecedor(ResultSet rs)
			throws SQLException {
		ArrayList<FornecedorVO> fo = new ArrayList<FornecedorVO>();
		while (rs.next()) {
			FornecedorVO dados_item = new FornecedorVO();
			dados_item.codigo = rs.getInt("codigo");
			dados_item.nome = rs.getString("nome");
			dados_item.bairro = rs.getString("bairro");
			dados_item.cep = rs.getString("cep");
			dados_item.cidade = rs.getString("cidade");
			dados_item.contato = rs.getString("contato");
			dados_item.cpf_cnpj = rs.getString("cpf_cnpj");
			dados_item.email = rs.getString("email");
			dados_item.endereco = rs.getString("endereco");
			dados_item.fone = rs.getString("fone");
			dados_item.insc_estadual = rs.getString("insc_estadual");
			dados_item.obs = rs.getString("obs");
			dados_item.UF = rs.getString("UF");
			dados_item.url = rs.getString("URL");
			dados_item.avaliacao = rs.getString("avaliacao");
			fo.add(dados_item);
		}
		return fo;
	}

}
