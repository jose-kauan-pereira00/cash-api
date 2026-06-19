package com.invict.cash_api.cash_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invict.cash_api.cash_api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
