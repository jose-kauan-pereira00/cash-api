package com.invict.cash_api.cash_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.model.Pessoa;
import com.invict.cash_api.cash_api.repository.LancamentoRepository;

@Service
public class LancamentoService {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaService.buscarPeloCodigo(lancamento.getPessoa().getCodigo())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        if (!pessoa.isAtivo()) {
            throw new RuntimeException("Pessoa está inativa");      
        }
        
        return lancamentoRepository.save(lancamento);
    }

}
