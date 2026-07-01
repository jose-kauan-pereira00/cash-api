package com.invict.cash_api.cash_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invict.cash_api.cash_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
