package com.invict.cash_api.cash_api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.repository.LancamentoRepository;
import com.invict.cash_api.cash_api.service.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<Lancamento> listar(){
        return lancamentoRepository.findAll();  
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Validated @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(lancamentoSalvo.getCodigo()).toUri();

		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(lancamentoSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){
        return lancamentoRepository.findById(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); 
    }

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo) {
		Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);
		if (lancamentoOptional.isPresent()) {
			lancamentoRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    
}
