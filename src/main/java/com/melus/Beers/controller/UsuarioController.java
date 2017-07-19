package com.melus.Beers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@RequestMapping("/novo")
	public String novo(){
		return "usuario/CadastroUsuario";
	}
}
