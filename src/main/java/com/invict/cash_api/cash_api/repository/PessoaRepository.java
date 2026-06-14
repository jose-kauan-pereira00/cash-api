package com.invict.cash_api.cash_api.repository;

import  com.invict.cash_api.cash_api.model.Pessoa;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	
}