package com.invict.cash_api.cash_api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.repository.filter.LancamentoFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT l FROM Lancamento l WHERE 1=1 ");

        if (lancamentoFilter.getDescricao() != null && !lancamentoFilter.getDescricao().isEmpty()) {
            jpql.append("AND l.descricao LIKE :descricao ");
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            jpql.append("AND l.dataVencimento >= :dataVencimentoDe ");
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            jpql.append("AND l.dataVencimento <= :dataVencimentoAte ");
        }

        var query = entityManager.createQuery(jpql.toString(), Lancamento.class);

        if (lancamentoFilter.getDescricao() != null && !lancamentoFilter.getDescricao().isEmpty()) {
            query.setParameter("descricao", "%" + lancamentoFilter.getDescricao() + "%");
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            query.setParameter("dataVencimentoDe", lancamentoFilter.getDataVencimentoDe());
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            query.setParameter("dataVencimentoAte", lancamentoFilter.getDataVencimentoAte());
        }

        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(l) FROM Lancamento l WHERE 1=1 ");

        if (lancamentoFilter.getDescricao() != null && !lancamentoFilter.getDescricao().isEmpty()) {
            jpql.append("AND l.descricao LIKE :descricao ");
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            jpql.append("AND l.dataVencimento >= :dataVencimentoDe ");
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            jpql.append("AND l.dataVencimento <= :dataVencimentoAte ");
        }

        var query = entityManager.createQuery(jpql.toString(), Long.class);

        if (lancamentoFilter.getDescricao() != null && !lancamentoFilter.getDescricao().isEmpty()) {
            query.setParameter("descricao", "%" + lancamentoFilter.getDescricao() + "%");
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            query.setParameter("dataVencimentoDe", lancamentoFilter.getDataVencimentoDe());
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            query.setParameter("dataVencimentoAte", lancamentoFilter.getDataVencimentoAte());
        }

        return query.getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
         int paginaAtual = pageable.getPageNumber();
         int limite = pageable.getPageSize();
         int primeiroRegistro = paginaAtual * limite;
         query.setFirstResult(primeiroRegistro);
         query.setMaxResults(limite);
    }
    
    
}
