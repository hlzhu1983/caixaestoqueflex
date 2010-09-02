package services;

import arquitetura.BancoDeDados;

public abstract class Servico {
	
	protected BancoDeDados banco;

	public Servico() {
		this.banco = new BancoDeDados();
	}

	public BancoDeDados getBanco() {
		return banco;
	}

	public void setBanco(BancoDeDados banco) {
		this.banco = banco;
	}

}
