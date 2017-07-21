package com.melus.Beers.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.melus.Beers.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@PostMapping
	public DeferredResult<String> upload(@RequestParam("files[]")MultipartFile[] file){
		DeferredResult<String> result = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(file,result));
		thread.start();
		
		System.out.println(">>>>Files " + file[0].getSize());
		return result;
	}
}
