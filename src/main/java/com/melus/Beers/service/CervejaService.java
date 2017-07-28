package com.melus.Beers.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
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
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Cerveja cerveja){
		cervejas.save(cerveja);
		
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter){

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		System.out.println(">>>>>>>>>"+criteria.list());
		
		if(cervejaFilter != null){
			if(!StringUtils.isEmpty(cervejaFilter.getSku())){
				criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));
			}
			if(!StringUtils.isEmpty(cervejaFilter.getNome())){
				criteria.add(Restrictions.ilike("nome", cervejaFilter.getNome(),MatchMode.ANYWHERE));
			}
			if (cervejaFilter.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", cervejaFilter.getSabor()));
			}
			if (cervejaFilter.getEstilo() != null) {
				criteria.add(Restrictions.eq("estilo", cervejaFilter.getEstilo()));
			}
			if (cervejaFilter.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", cervejaFilter.getOrigem()));
			}
			if (cervejaFilter.getPrecoInit() != null) {
				criteria.add(Restrictions.ge("valor", cervejaFilter.getPrecoInit()));
			}
			if (cervejaFilter.getPrecoFim() != null) {
				criteria.add(Restrictions.le("valor", cervejaFilter.getPrecoFim()));
			}
		}
		return criteria.list();
		
		/*List<Cerveja> listCervejas = cervejas.findAll();	
			
		if(!StringUtils.isEmpty(cervejaFilter.getSku())){
			String sku = cervejaFilter.getSku() == null ? "%" : cervejaFilter.getSku();
			listCervejas = cervejas.findBySkuContaining(sku);
			System.out.println(">>>>>>>>>>>>>SKU");
			return listCervejas;
		}
		if(!StringUtils.isEmpty(cervejaFilter.getNome())){
			String nome = cervejaFilter.getNome() == null ? "%" : cervejaFilter.getNome();
			listCervejas = cervejas.findByNomeContaining(nome);
			System.out.println(">>>>>>>>>>>>>Nome");
			return listCervejas;
		}
		if(cervejaFilter.getSabor() != null){
			Enum sabor = cervejaFilter.getSabor();
			listCervejas = cervejas.findBySabor(sabor);
			System.out.println(">>>>>>>>>>>>>Sabor");
			return listCervejas;
		}
		if(cervejaFilter.getEstilo() != null){
			Estilo estlio = cervejaFilter.getEstilo();
			listCervejas = cervejas.findByEstilo(estlio);
			System.out.println(">>>>>>>>>>>>>Estilo");
			return listCervejas;
		}
		if(cervejaFilter.getOrigem() != null){
			Enum origem = cervejaFilter.getOrigem();
			listCervejas = cervejas.findByOrigem(origem);
			System.out.println(">>>>>>>>>>>>>Origem");
			return listCervejas;
		}
		if(cervejaFilter.getPrecoInit() != null && cervejaFilter.getPrecoFim() != null){
			BigDecimal precoInit = cervejaFilter.getPrecoInit();
			BigDecimal precoFim = cervejaFilter.getPrecoFim();
			listCervejas = cervejas.findByPrecoBetween(precoInit, precoFim);
			System.out.println(">>>>>>>>>>>>>Preco");
			return listCervejas;
		}
			
		return listCervejas;
*/	}
}
