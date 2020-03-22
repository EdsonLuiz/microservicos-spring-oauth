package com.edson.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edson.app.commons.usuarios.models.entities.Usuario;

@FeignClient(name="servico-usuarios")
public interface UsuarioFeignClient {
	@GetMapping("/usuarios/search/findusername")
	public Usuario findByUserName(@RequestParam String username); 
}
