var Beers = Beers || {};

Beers.UploadFoto = (function(){
	
	function UploadFoto(){
		
		this.inputName = $('input[name=foto]'); 
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(htmlFotoCervejaTemplate);
		
		this.uploadDrop = $('#upload-drop');
		this.containerFotoCerveja = $('.js-container-foto-cerveja');
	}
	
	UploadFoto.prototype.iniciar = function(){
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoCerveja.data('url-fotos'),
			complete: onUploadCompleto.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	function onUploadCompleto(resposta){
		this.inputName.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		uploadDrop.addClass('hidden');
		var htmlFotoCerveja = template({nomeFoto: resposta.nome});
		containerFotoCerveja.append(htmlFotoCerveja);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto(){
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputName.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto;
});

$(function(){
	
	var uploadFoto = new Beers.UploadFoto();
	uploadFoto.iniciar();
	
});