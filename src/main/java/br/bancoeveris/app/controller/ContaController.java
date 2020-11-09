package br.bancoeveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.bancoeveris.app.model.BaseResponse;
import br.bancoeveris.app.model.Cliente;
import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.service.ClienteService;
import br.bancoeveris.app.service.ContaService;
import br.bancoeveris.app.spec.ClienteList;
import br.bancoeveris.app.spec.ClienteSpec;
import br.bancoeveris.app.spec.ContaList;
import br.bancoeveris.app.spec.ContaSpec;

@RestController
@RequestMapping("/contas")
public class ContaController extends BaseController {
	
	private final ContaService _service;
	
	@Autowired
	public ContaController(ContaService service) {
		_service = service;
	}
	
	@PostMapping
    public ResponseEntity inserir(@RequestBody ContaSpec contaSpec) {
		try {
			BaseResponse response = _service.inserir(contaSpec);
			return ResponseEntity.status(response.StatusCode).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity obter(@PathVariable Long id) {		
		try {
			Conta response = _service.obter(id);
			return ResponseEntity.status(response.StatusCode).body(response);	
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}   	
    }
	
	@GetMapping(path = "/saldo/{hash}")
    public ResponseEntity saldo(@PathVariable String hash) {		
		try {
			Conta response = _service.saldo(hash);
			return ResponseEntity.status(response.StatusCode).body(response);	
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}   	
    }

//	@GetMapping
//    public ResponseEntity listar() {		
//		try {
//			ContaList contas = _service.listar();  		
//	    	return ResponseEntity.status(HttpStatus.OK).body(contas);			
//		} catch (Exception e) {
//			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);			
//		}		
//    }
//	
//	@DeleteMapping(path = "/{id}")
//	public ResponseEntity deletar(@PathVariable Long id) {
//		try {
//			BaseResponse response = _service.deletar(id);
//			return ResponseEntity.status(response.StatusCode).build(); 
//		} catch (Exception e) {
//			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
//		}
//	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody ContaSpec contaSpec, @PathVariable Long id) {
		try {
			BaseResponse response = _service.atualizar(id, contaSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}

}