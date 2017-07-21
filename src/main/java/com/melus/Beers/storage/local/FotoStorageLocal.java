package com.melus.Beers.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.melus.Beers.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage{

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this.local = getDefault().getPath(System.getenv("C:\\"), "beerfotos");
		criarPastas();
	}

	private void criarPastas() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Pastas criadas para salvar fotos.");
				LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporaria: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro criando pasta para salvar foto");
		}
	}

	@Override
	public void salvarTemporariamente(MultipartFile[] files) {
		System.out.println(">>>>>>>>>>>>>>>>Salvando temporariamente");	
	}
}
