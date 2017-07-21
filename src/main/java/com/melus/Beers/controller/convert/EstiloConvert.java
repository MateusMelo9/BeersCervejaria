package com.melus.Beers.controller.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import com.melus.Beers.model.Estilo;

public class EstiloConvert implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String codigo) {

		if (!StringUtils.isEmpty(codigo)) {
			Estilo estilo = new Estilo();
			estilo.setId(Long.valueOf(codigo));
			return estilo;
		}
		return null;
	}
}
