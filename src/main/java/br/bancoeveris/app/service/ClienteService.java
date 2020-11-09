package br.bancoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bancoeveris.app.repository.*;
import br.bancoeveris.app.spec.ClienteList;
import br.bancoeveris.app.spec.ClienteSpec;
import br.bancoeveris.app.model.*;

@Service
public class ClienteService {

	final ClienteRepository _repository;

	@Autowired
	public ClienteService(ClienteRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(ClienteSpec clienteSpec) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (clienteSpec.getNome() == "") {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (clienteSpec.getTelefone() == "") {
			base.Message = "O telefone do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getCpf() == "") {
			base.Message = "O Cpf do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getDataNasc() == "") {
			base.Message = "A data de nascimento do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getEndereco() == "") {
			base.Message = "O endereço do cliente não foi preenchido";
			return base;
		}

		cliente.setNome(clienteSpec.getNome());
		cliente.setTel(clienteSpec.getTelefone());
		cliente.setCpf(clienteSpec.getCpf());
		cliente.setDataNasc(clienteSpec.getDataNasc());
		cliente.setEndereco(clienteSpec.getEndereco());

		_repository.save(cliente);
		base.StatusCode = 201;
		base.Message = "Cliente inserido com sucesso.";
		return base;
	}

	public Cliente obter(Long id) {
		Optional<Cliente> response = _repository.findById(id);		

		if (response == null) {
			response.get().Message = "Cliente não encontrado";
			response.get().StatusCode = 404;
			return response.get();
		}

		response.get().Message = "Cliente obtido com sucesso";
		response.get().StatusCode = 200;
		return response.get();
	}

	public Cliente obterByCpf(String cpf) {
		Cliente response = _repository.findByCpf(cpf);		

		if (response == null) {
			response = new Cliente();
			response.Message = "Cliente não encontrado";
			response.StatusCode = 404;
			return response;
		}

		response.Message = "Cliente obtido com sucesso";
		response.StatusCode = 200;
		return response;
	}

	public ClienteList listar() {

		List<Cliente> lista = _repository.findAll();

		ClienteList response = new ClienteList();
		response.setClientes(lista);
		response.StatusCode = 200;
		response.Message = "Clientes obtidos com sucesso.";

		return response;
	}

	public BaseResponse atualizar(Long id, ClienteSpec clienteSpec) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (clienteSpec.getNome() == "") {
			base.Message = "O nome do cliente não foi preenchido.";
			return base;
		}

		if (clienteSpec.getTelefone() == "") {
			base.Message = "O telefone do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getCpf() == "") {
			base.Message = "O Cpf do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getDataNasc() == "") {
			base.Message = "A data de nascimento do cliente não foi preenchido.";
			return base;
		}
		if (clienteSpec.getEndereco() == "") {
			base.Message = "O endereço do cliente não foi preenchido";
			return base;
		}

		cliente.setId(id);
		cliente.setNome(clienteSpec.getNome());
		cliente.setTel(clienteSpec.getTelefone());
		cliente.setCpf(clienteSpec.getCpf());
		cliente.setDataNasc(clienteSpec.getDataNasc());
		cliente.setEndereco(clienteSpec.getEndereco());

		_repository.save(cliente);
		base.StatusCode = 200;
		base.Message = "Cliente atualizado com sucesso.";
		return base;
	}

	public BaseResponse deletar(Long id) {
		BaseResponse response = new BaseResponse();

		if (id == null || id == 0) {
			response.StatusCode = 400;
			return response;
		}

		_repository.deleteById(id);
		response.StatusCode = 200;
		return response;
	}

}
