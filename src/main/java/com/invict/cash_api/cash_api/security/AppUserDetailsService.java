package com.invict.cash_api.cash_api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.invict.cash_api.cash_api.model.Usuario;
import com.invict.cash_api.cash_api.repository.UsuarioRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public AppUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

       usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("User or Password incorrects"));

       Usuario usuario = usuarioOptional.get();

       return new User(email, usuario.getSenha(), getPermissoes(usuario));
      
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
       Set<SimpleGrantedAuthority> authorities = new HashSet<>();
       usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
       return authorities;
    }

}
