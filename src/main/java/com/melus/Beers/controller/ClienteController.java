package com.melus.Beers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.melus.Beers.model.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@RequestMapping("/novo")
	public String novo(Cliente cliente){
		return "cliente/CadastroCliente";
	}
}
