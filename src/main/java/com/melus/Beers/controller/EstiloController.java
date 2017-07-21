package com.melus.Beers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melus.Beers.model.Estilo;
import com.melus.Beers.service.EstiloService;
import com.melus.Beers.service.exception.EstiloJaCadastradoException;

@Controller
@RequestMapping("/estilo")
public class EstiloController {
	
	@Autowired
	private EstiloService estilos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo){
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");		
		return mv;
	}	
	
	@RequestMapping(value = "/estilo/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Validated Estilo estilo, Errors result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){			
			return novo(estilo);
		}
		try {
			estilos.salvar(estilo);
		} catch (EstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(),e.getMessage());
			return novo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "Estilo salva com sucesso!");
		return new ModelAndView("redirect:/estilo/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Validated Estilo estilo, Errors result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		try {
			estilo = estilos.salvar(estilo);
		} catch (EstiloJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(estilo);
	}
	
}
