package com.invict.cash_api.cash_api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import com.invict.cash_api.cash_api.model.Lancamento;
import com.invict.cash_api.cash_api.repository.filter.LancamentoFilter;
import com.invict.cash_api.cash_api.repository.projection.ResumoLancamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteriaQuery = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        criteriaQuery.select(builder.construct(ResumoLancamento.class,
                root.get("codigo"),
                root.get("descricao"),
                root.get("dataVencimento"),
                root.get("dataPagamento"),
                root.get("valor"),
                root.get("tipo"),
                root.get("categoria").get("nome"),
                root.get("pessoa").get("nome")
        ));

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteriaQuery);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, totalResumido(lancamentoFilter));
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (lancamentoFilter.getDescricao() != null && !lancamentoFilter.getDescricao().isEmpty()) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), 
                    "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                    root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()
            ));
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                    root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private Long totalResumido(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
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

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
         int paginaAtual = pageable.getPageNumber();
         int limite = pageable.getPageSize();
         int primeiroRegistro = paginaAtual * limite;
         query.setFirstResult(primeiroRegistro);
         query.setMaxResults(limite);
    }
}