package com.melus.Beers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cidade")
public class CidadeController {
	
	@RequestMapping("/novo")
	public String novo(){
		return "cidade/CadastroCidade";
	}
}
