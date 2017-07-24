package com.melus.Beers.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import com.melus.Beers.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage{

	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this.local = getDefault().getPath("C:\\", "beerfotos");
		criarPastas();
	}

	private void criarPastas() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro criando pasta para salvar foto");
		}
	}

	@Override
	public void salvarTemporariamente(MultipartFile[] files) {
		if(files != null && files.length > 0){
			MultipartFile arquivo = files[0];
			
			try {
				arquivo.transferTo((new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator()+arquivo.getOriginalFilename())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao salvar temporariamente.", e);
			}
		}
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		// TODO Auto-generated method stub
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar a foto. ", e);
		}
	}

	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao mover foto para destino final " + e);			
		}
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao redimensionar a foto "+ e);
		}
	}

	@Override
	public byte[] recuperarFoto(String foto) {
		// TODO Auto-generated method stub
		try {
			return Files.readAllBytes(this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar a foto. ", e);
		}
	}
}
