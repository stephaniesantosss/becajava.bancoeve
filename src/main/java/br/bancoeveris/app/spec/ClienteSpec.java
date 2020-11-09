package br.bancoeveris.app.spec;

public class ClienteSpec {
	
	private String Nome;
	private String Telefone;
	private String Cpf;
	private String DataNasc;
	private String Endereco;
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public String getDataNasc() {
		return DataNasc;
	}
	public void setDataNasc(String dataNasc) {
		DataNasc = dataNasc;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
	
}

