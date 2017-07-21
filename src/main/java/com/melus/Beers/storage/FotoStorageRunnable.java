package com.melus.Beers.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.melus.Beers.DTO.FotoDTO;

public class FotoStorageRunnable implements Runnable{

	private MultipartFile[] file;
	private DeferredResult<FotoDTO> result;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] file, DeferredResult<FotoDTO> result, FotoStorage fotoStorage) {
		this.file = file;
		this.result = result;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.fotoStorage.salvarTemporariamente(file);
		
		String nomeFoto = file[0].getOriginalFilename();
		String contentType = file[0].getContentType();
 		result.setResult( new FotoDTO(nomeFoto, contentType));	}
	
		
}
