package br.bancoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.bancoeveris.app.model.BaseResponse;
import br.bancoeveris.app.model.Cliente;
import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.repository.ContaRepository;

import br.bancoeveris.app.spec.ContaList;
import br.bancoeveris.app.spec.ContaSpec;

@Service
public class ContaService {

	final ContaRepository _repository;
	final OperacaoService _operacaoService;
	final ClienteService _clienteService;

	public ContaService(ContaRepository repository, OperacaoService operacaoService, ClienteService clienteService) {
		_repository = repository;
		_operacaoService = operacaoService;
		_clienteService = clienteService;
	}

	public Conta saldo(String hash) {
		Conta conta = new Conta();
		conta.StatusCode = 400;
		
		List<Conta> lista = _repository.findByHash(hash);

		if (lista.size() == 0)
			conta.Message = "Conta não encontrada!";
		
		
		double saldo =  _operacaoService.saldo(lista.get(0).getId());
		conta.setSaldo(saldo);
		conta.setHash(lista.get(0).getHash());
		conta.setAgencia(lista.get(0).getAgencia());
		conta.setNumConta(lista.get(0).getNumConta());
		conta.setCliente(lista.get(0).getCliente());
		
		conta.StatusCode = 200;
		conta.Message = "Consulta de saldo ok!";
		return conta;
	}

	public BaseResponse inserir(ContaSpec contaSpec) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaSpec.getHash() == "") {
			base.Message = "O Hash do cliente não foi preenchido.";
			return base;
		}
		if (contaSpec.getNumConta() == "") {
			base.Message = "O Número da conta do cliente não foi preenchido.";
			return base;
		}
		if (contaSpec.getAgencia() == "") {
			base.Message = "A agencia do cliente não foi preenchido.";
			return base;
		}

		conta.setHash(contaSpec.getHash());
		conta.setNumConta(contaSpec.getNumConta());
		conta.setAgencia(contaSpec.getAgencia());
		Cliente cliente = _clienteService.obterByCpf(contaSpec.getCliente().getCpf());

		if (cliente.StatusCode == 404) {

			_clienteService.inserir(contaSpec.getCliente());
			cliente = _clienteService.obterByCpf(contaSpec.getCliente().getCpf());

		}
		conta.setCliente(cliente);

		_repository.save(conta);
		base.StatusCode = 201;
		base.Message = "Conta inserida com sucesso.";
		return base;
	}

	public Conta obter(Long id) {
		Optional<Conta> conta = _repository.findById(id);
		Conta response = new Conta();

		if (conta == null) {
			response.Message = "Conta não encontrada";
			response.StatusCode = 404;
			return response;
		}

		response.Message = "Conta obtida com sucesso";
		response.StatusCode = 200;
		return response;
	}

//	public ContaList listar() {
//
//		List<Conta> lista = _repository.findAll();
//
//		ContaList response = new ContaList();
//		response.setContas(lista);
//		response.StatusCode = 200;
//		response.Message = "Contas obtidas com sucesso.";
//
//		return response;
//	}

	public BaseResponse atualizar(Long id, ContaSpec contaSpec) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaSpec.getHash() == "") {
			base.Message = "O Hash da conta não foi preenchido.";
			return base;
		}
		if (contaSpec.getNumConta() == "") {
			base.Message = "O número da conta não foi preenchido";
			return base;
		}
		if (contaSpec.getAgencia() == "") {
			base.Message = "A agencia da conta não foi preenchida";
			return base;
		}

		conta.setId(id);
		conta.setHash(contaSpec.getHash());
		conta.setAgencia(contaSpec.getAgencia());
		conta.setNumConta(contaSpec.getNumConta());
		_clienteService.inserir(contaSpec.getCliente());

		_repository.save(conta);
		base.StatusCode = 200;
		base.Message = "Conta atualizada com sucesso.";
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

}
