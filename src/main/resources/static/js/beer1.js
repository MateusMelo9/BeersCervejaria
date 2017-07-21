var Beer = Beer || {}

Beer.MaskMoney = (function(){
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney();
		this.plain.maskMoney({precision: 0});
		
	}
	
	return MaskMoney;
	
}());
/*{decimal: ',' , thousands: '.'}	
{precision: 0, thousands: '.'}*/
$(function(){
	var maskMoney = new Beer.MaskMoney();
	maskMoney.enable();
});