package com.melus.Beers.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melus.Beers.model.Cerveja;

@Controller
@RequestMapping("/cerveja")
public class CervejaController {
	
	@RequestMapping("/novo")
	public String novo(Cerveja cerveja){
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(@Validated Cerveja cerveja, Errors result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(cerveja);
		}
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso!");
		return "cerveja/CadastroCerveja";
	}	
	
}
