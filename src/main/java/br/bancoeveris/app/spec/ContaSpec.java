package br.bancoeveris.app.spec;

public class ContaSpec {

	private String hash;
	private String numConta;
	private String agencia;
	private ClienteSpec cliente;
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getNumConta() {
		return numConta;
	}
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public ClienteSpec getCliente() {
		return cliente;
	}
	public void setCliente(ClienteSpec cliente) {
		this.cliente = cliente;
	}
	
	
}
