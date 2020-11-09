package br.bancoeveris.app.spec;

import java.util.List;

import br.bancoeveris.app.model.BaseResponse;
import br.bancoeveris.app.model.Operacao;

public class OperacaoList extends BaseResponse {
	
	private List<Operacao> Operacoes;

	public List<Operacao> getOperacoes() {
		return Operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		Operacoes = operacoes;
	}

}