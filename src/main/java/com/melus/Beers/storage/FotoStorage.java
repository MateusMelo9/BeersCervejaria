package com.melus.Beers.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	public void salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);
}
