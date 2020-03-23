package com.edson.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edson.app.commons.usuarios.models.entities.Usuario;
import com.edson.app.oauth.clients.UsuarioFeignClient;

@Service
public class UsuarioService implements UserDetailsService{
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUserName(username);
		
		if(usuario.equals(null)) {
			log.error("Erro ao logar, usuário não existe.");
			throw new UsernameNotFoundException("Erro ao logar, usuário não existe.");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		log.info(usuario.getUserName());
		
		return new User(usuario.getUserName(), 
				usuario.getPassword(), 
				usuario.getEnabled(), 
				true, 
				true, 
				true, 
				authorities);
	}

}
