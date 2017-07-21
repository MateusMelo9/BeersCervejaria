package com.melus.Beers.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable{

	private MultipartFile[] file;
	private DeferredResult<String> result;
	
	public FotoStorageRunnable(MultipartFile[] file, DeferredResult<String> result) {
		this.file = file;
		this.result = result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(">>>>Files " + file[0].getSize());
		result.setResult("Ok! foto recebida");
	}
	
		
}
