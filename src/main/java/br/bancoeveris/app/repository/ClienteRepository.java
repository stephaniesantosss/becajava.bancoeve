package br.bancoeveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bancoeveris.app.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByCpf(String cpf);

}
