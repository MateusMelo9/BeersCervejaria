package com.melus.Beers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.repository.CervejaRepository;
import com.melus.Beers.service.event.cerveja.CervejaSalvaEvent;

@Service
public class CervejaService {

	@Autowired
	private CervejaRepository cervejas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Cerveja cerveja){
		cervejas.save(cerveja);
		
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}
}
