package br.bancoeveris.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.model.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao,Long> {
	
	List<Operacao> findByContaDestino(Conta contaDestino);
	
	List<Operacao> findByContaOrigem(Conta contaOrigem);

}