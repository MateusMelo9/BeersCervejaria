package com.melus.Beers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.repository.helper.cerveja.CervejaQueries;

public interface CervejaRepository extends JpaRepository<Cerveja, Long>{
	
	public List<Cerveja> findBySkuContaining(String sku);
	
}
