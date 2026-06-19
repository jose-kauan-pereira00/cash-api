package com.invict.cash_api.cash_api.repository.lancamento;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery{

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
