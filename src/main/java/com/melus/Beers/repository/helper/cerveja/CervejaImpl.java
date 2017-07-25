package com.melus.Beers.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.repository.filter.CervejaFilter;

@Repository
public class CervejaImpl implements CervejaQueries{

	@PersistenceContext
	private EntityManager manager; 
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		if(cervejaFilter != null){
			if(!StringUtils.isEmpty(cervejaFilter.getSku())){
				criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));
			}
		}
		return criteria.list();
	}
}
