package com.melus.Beers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.repository.CervejaRepository;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejas;
	
	public void salvar(Cerveja cerveja){
		cervejas.save(cerveja);
	}
}
