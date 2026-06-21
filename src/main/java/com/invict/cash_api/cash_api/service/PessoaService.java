package com.invict.cash_api.cash_api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invict.cash_api.cash_api.model.Pessoa;
import com.invict.cash_api.cash_api.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar( Long codigo, Pessoa pessoa) {

        Optional<Pessoa> pessoaOptional = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaOptional.get(), "codigo");
		return pessoaRepository.save(pessoaOptional.get());
    }

    

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoa = buscarPeloCodigo(codigo).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setAtivo(ativo);
        pessoaRepository.save(pessoa);
	}

    public Optional<Pessoa> buscarPeloCodigo(Long codigo) {
        if(!pessoaRepository.existsById(codigo)) {
			throw new RuntimeException("Pessoa não encontrada");
		}

		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
        return pessoaOptional;
    }

}
