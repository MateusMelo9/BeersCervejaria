package com.melus.Beers.service.event.cerveja;

import org.springframework.util.StringUtils;

import com.melus.Beers.model.Cerveja;

public class CervejaSalvaEvent {

	private Cerveja cerveja;

	public CervejaSalvaEvent(Cerveja cerveja) {
		super();
		this.cerveja = cerveja;
	}

	public Cerveja getCerveja() {
		return cerveja;
	}	
	
	public boolean temFoto(){
		return !StringUtils.isEmpty(cerveja.getFoto());
	}
	
}
