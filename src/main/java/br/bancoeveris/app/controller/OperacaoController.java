package br.bancoeveris.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bancoeveris.app.model.BaseResponse;
import br.bancoeveris.app.model.Cliente;
import br.bancoeveris.app.model.Operacao;
import br.bancoeveris.app.service.ClienteService;
import br.bancoeveris.app.service.OperacaoService;
import br.bancoeveris.app.spec.ClienteList;
import br.bancoeveris.app.spec.ClienteSpec;
import br.bancoeveris.app.spec.OperacaoList;
import br.bancoeveris.app.spec.OperacaoSpec;
import br.bancoeveris.app.spec.TransferenciaSpec;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController extends BaseController {
	
	private final OperacaoService _service;
	
	@Autowired
	public OperacaoController(OperacaoService service) {
		_service = service;
	}
	
	@PostMapping (path = "/depositoSaque")
    public ResponseEntity inserir(@RequestBody OperacaoSpec operacaoSpec) {
		try {
			BaseResponse response = _service.inserir(operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
    }
	@PostMapping (path = "/tranferencia")
    public ResponseEntity transferencia(@RequestBody TransferenciaSpec transferenciaSpec) {
		try {
			BaseResponse response = _service.transferencia(transferenciaSpec);
			return ResponseEntity.status(response.StatusCode).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
    }
	
	
//	@GetMapping(path = "/{id}")
//    public ResponseEntity obter(@PathVariable Long id) {		
//		try {
//			Operacao response = _service.obter(id);
//			return ResponseEntity.status(response.StatusCode).body(response);	
//		} catch (Exception e) {
//			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
//		}   	
//    }

//	@GetMapping
//    public ResponseEntity listar() {		
//		try {
//			OperacaoList operacoes = _service.listar();  		
//	    	return ResponseEntity.status(HttpStatus.OK).body(operacoes);			
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
//	
	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody OperacaoSpec operacaoSpec, @PathVariable Long id) {
		try {
			BaseResponse response = _service.atualizar(id, operacaoSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}

}