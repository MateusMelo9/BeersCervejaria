package com.melus.Beers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estado")
public class EstadoController {

	@RequestMapping("/novo")
	public String novo(){
		return "estado/CadastroEstado";
	}
}
