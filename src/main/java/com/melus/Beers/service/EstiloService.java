package com.melus.Beers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melus.Beers.model.Estilo;
import com.melus.Beers.repository.EstiloRepository;
import com.melus.Beers.service.exception.EstiloJaCadastradoException;

@Service
public class EstiloService {

	@Autowired
	private EstiloRepository estilos;
	
	public Estilo salvar(Estilo estilo){
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		
		if(estiloOptional.isPresent()){
			throw new EstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		return estilos.saveAndFlush(estilo);
	}
}
