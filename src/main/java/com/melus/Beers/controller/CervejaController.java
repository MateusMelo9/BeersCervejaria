package com.melus.Beers.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.model.Origem;
import com.melus.Beers.model.Sabor;
import com.melus.Beers.repository.CervejaRepository;
import com.melus.Beers.repository.EstiloRepository;
import com.melus.Beers.repository.filter.CervejaFilter;
import com.melus.Beers.service.CervejaService;

@Controller
@RequestMapping("/cerveja")
public class CervejaController {
	
	@Autowired
	private CervejaService cervejas;
	
	@Autowired
	private EstiloRepository estilos;
	
	@Autowired
	private CervejaRepository cervejasRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Validated Cerveja cerveja, Errors result, Model model, RedirectAttributes attributes){
		if(result.hasErrors()){			
			return novo(cerveja);
		}
		cervejas.salvar(cerveja);		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		return new ModelAndView("redirect:/cerveja/novo");
	}	
	
	@RequestMapping
	public ModelAndView pesquisa(CervejaFilter cervejaFilter, BindingResult result ){
				
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCerveja");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		mv.addObject("cervejas", cervejas.filtrar(cervejaFilter));
		
		return mv;
	}
}
