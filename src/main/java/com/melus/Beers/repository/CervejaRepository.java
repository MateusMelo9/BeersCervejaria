package com.melus.Beers.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.melus.Beers.model.Cerveja;
import com.melus.Beers.model.Estilo;

public interface CervejaRepository extends JpaRepository<Cerveja, Long>{
	
	public List<Cerveja> findBySkuContaining(String sku);
	
	public List<Cerveja> findByNomeContaining(String nome);
	
	public List<Cerveja> findBySabor(Enum sabor);
	
	@Query(value="select * from estilo", nativeQuery = true)
	public List<Cerveja> findByEstilo();
	
	public List<Cerveja> findByOrigem(Enum origem);
	
	public List<Cerveja> findByPrecoBetween(BigDecimal precoInit, BigDecimal precoFim);
}
