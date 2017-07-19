package com.melus.Beers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melus.Beers.model.Cerveja;

public interface CervejaRepository extends JpaRepository<Cerveja, Long> {

}
