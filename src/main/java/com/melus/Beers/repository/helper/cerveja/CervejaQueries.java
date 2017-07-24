package com.melus.Beers.repository.helper.cerveja;

import java.util.List;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.repository.filter.CervejaFilter;

public interface CervejaQueries {
	 
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter);
	
}
