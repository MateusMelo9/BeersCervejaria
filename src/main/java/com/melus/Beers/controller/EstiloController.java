package com.melus.Beers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melus.Beers.model.Estilo;
import com.melus.Beers.repository.EstiloRepository;

@Controller
@RequestMapping("/estilo")
public class EstiloController {
	
	@Autowired
	private EstiloRepository estilos;
	
	@RequestMapping("/novo")
	public String novo(Estilo estilo){
		return "estilo/CadastroEstilo";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(@Validated Estilo estilo, Errors result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			model.addAttribute("mensagem", "Erro no formulário");
			return novo(estilo);
		}
		estilos.save(estilo);
		model.addAttribute("menssagem", "Cerveja salva com sucesso!");
		return "estilo/CadastroEstilo";
	}
}
