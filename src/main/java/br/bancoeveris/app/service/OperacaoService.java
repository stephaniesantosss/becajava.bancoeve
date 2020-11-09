package br.bancoeveris.app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.bancoeveris.app.model.BaseResponse;
import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.model.Operacao;
import br.bancoeveris.app.repository.ContaRepository;
import br.bancoeveris.app.repository.OperacaoRepository;

import br.bancoeveris.app.spec.OperacaoList;
import br.bancoeveris.app.spec.OperacaoSpec;
import br.bancoeveris.app.spec.TransferenciaSpec;

@Service
public class OperacaoService {

	final OperacaoRepository _repository;
	final ContaRepository _contaRepository;

	public OperacaoService(OperacaoRepository repository, ContaRepository contaRepository) {
		_repository = repository;
		_contaRepository = contaRepository;
	}

	public BaseResponse inserir(OperacaoSpec operacaoSpec) {
		Operacao operacao = new Operacao();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;
		List<Conta> lista =_contaRepository.findByHash(operacaoSpec.getHash());
		
		if (lista.size() == 0) {
			base.StatusCode = 404;
			base.Message = "Conta não encontrada!";
			return base;
		}		
			
		if (operacaoSpec.getTipo() == "") {
			base.Message = "O Tipo da operação não foi preenchido.";
			return base;
		}

		if (operacaoSpec.getValor() == 0) {
			base.Message = "O valor da operação não foi preenchido.";
			return base;
		}

		operacao.setTipo(operacaoSpec.getTipo());
		operacao.setValor(operacaoSpec.getValor());

		switch (operacaoSpec.getTipo()) {

        case "D":
            operacao.setContaDestino(lista.get(0));
            break;

        case "S":
            operacao.setContaOrigem(lista.get(0));
            break;
        }		
				
		_repository.save(operacao);
		base.StatusCode = 201;
		base.Message = "Operacão inserida com sucesso.";
		return base;
	}
	
       public double saldo(Long contaId) {
		
		double saldo = 0;
		
		Conta contaOrigem = new Conta();
		contaOrigem.setId(contaId);
		
		Conta contaDestino = new Conta();
		contaDestino.setId(contaId);
		
		List<Operacao> listaOrigem = _repository.findByContaOrigem(contaOrigem);
		List<Operacao> listaDestino = _repository.findByContaDestino(contaDestino);
		
		for(Operacao o : listaOrigem) {			
			switch(o.getTipo()) {				
				case "D":
					saldo += o.getValor();
					break;
				case "S":
					saldo -= o.getValor();
					break;					
				case "T":
					saldo -= o.getValor();
					break;					
				default:					
					break;				
			}
			
		}
		
		for(Operacao o : listaDestino) {			
			switch(o.getTipo()) {				
				case "D":
					saldo += o.getValor();
					break;
				case "S":
					saldo -= o.getValor();
					break;					
				case "T":
					saldo += o.getValor();
					break;					
				default:					
					break;				
			}	
		}		
		
		return saldo;
	}

//	public Conta obter(Long id) {		
//		Optional<Conta> conta = _repository.findById(id);
//		Conta response = new Conta();
//		
//		
//		if (conta == null) {
//			response.Message = "Conta não encontrada";
//			response.StatusCode = 404;
//			return response;
//		}						
//		
//		response.Message = "Conta obtida com sucesso";
//		response.StatusCode = 200;		
//		return response;
//	}	
//
//	public OperacaoList listar() {
//
//		List<Operacao> lista = _repository.findAll();
//
//		OperacaoList response = new OperacaoList();
//		response.setOperacoes(lista);
//		response.StatusCode = 200;
//		response.Message = "Operacoes obtidas com sucesso.";
//
//		return response;
//	}

	public BaseResponse atualizar(Long id, OperacaoSpec operacaoSpec) {
		Operacao operacao = new Operacao();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (operacaoSpec.getTipo() == "") {
			base.Message = "O tipo da Operação não foi preenchido.";
			return base;
		}

		if (operacaoSpec.getValor() == 0) {
			base.Message = "O Valor da operação não foi preenchido.";
			return base;
		}

		operacao.setId(id);
		operacao.setTipo(operacaoSpec.getTipo());
		operacao.setValor(operacaoSpec.getValor());

		_repository.save(operacao);
		base.StatusCode = 200;
		base.Message = "Operacao atualizada com sucesso.";
		return base;
	}

//	public BaseResponse deletar(Long id) {
//		BaseResponse response = new BaseResponse();
//
//		if (id == null || id == 0) {
//			response.StatusCode = 400;
//			return response;
//		}
//
//		_repository.deleteById(id);
//		response.StatusCode = 200;
//		return response;
//	}
	
	public BaseResponse transferencia(TransferenciaSpec transferenciaSpec) {
		BaseResponse response = new BaseResponse();
		Operacao operacao = new Operacao();
		List<Conta> listaDestino =_contaRepository.findByHash(transferenciaSpec.getHashDestino());
		List<Conta> listaOrigem =_contaRepository.findByHash(transferenciaSpec.getHashOrigem());
		
		if (listaDestino.size() == 0) {
			response.StatusCode = 404;
			response.Message = "Conta destino não encontrada!";
			return response;
		}
		if (listaOrigem.size() == 0) {
			response.StatusCode = 404;
			response.Message = "Conta origem não encontrada!";
			return response;
		}
		
		operacao.setContaDestino(listaDestino.get(0));
		operacao.setContaOrigem(listaOrigem.get(0));
		operacao.setTipo("T");
		operacao.setValor(transferenciaSpec.getValor());
		
		_repository.save(operacao);
		response.StatusCode = 200;
		response.Message = "Transferencia realizada com sucesso!";
		return response;
		
			
	}

}
