package com.melus.Beers.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.model.Estilo;
import com.melus.Beers.repository.CervejaRepository;
import com.melus.Beers.repository.filter.CervejaFilter;
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
	
	@Transactional(readOnly = true)
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter){
		List<Cerveja> listCervejas = cervejas.findAll();	
			
		if(cervejaFilter != null){
			
			if(cervejaFilter.getSku() != null){
				String sku = cervejaFilter.getSku() == null ? "%" : cervejaFilter.getSku();
				listCervejas = cervejas.findBySkuContaining(sku); 
			}
			if(cervejaFilter.getNome() != null){
				String nome = cervejaFilter.getNome() == null ? "%" : cervejaFilter.getNome();
				listCervejas = cervejas.findByNomeContaining(nome); 
			}
			if(cervejaFilter.getSabor() != null){
				Enum sabor = cervejaFilter.getSabor();
				listCervejas = cervejas.findBySabor(sabor);
			}
			if(cervejaFilter.getEstilo() != null){
				Estilo estlio = cervejaFilter.getEstilo();
				listCervejas = cervejas.findByEstilo(estlio);
			}
			if(cervejaFilter.getOrigem() != null){
				Enum origem = cervejaFilter.getOrigem();
				listCervejas = cervejas.findByOrigem(origem);
			}
			if(cervejaFilter.getPrecoInit() != null && cervejaFilter.getPrecoFim() != null){
				BigDecimal precoInit = cervejaFilter.getPrecoInit();
				BigDecimal precoFim = cervejaFilter.getPrecoFim();
				listCervejas = cervejas.findByPrecoBetween(precoInit, precoFim);
			}
		}	
		
		return listCervejas;
	}
}
