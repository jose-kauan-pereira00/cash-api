package com.invict.cash_api.cash_api.repository.lancamento;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.repository.filter.LancamentoFilter;
import com.invict.cash_api.cash_api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery{

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
