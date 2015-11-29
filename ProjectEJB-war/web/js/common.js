function getURLVar(key) {
	var value = [];

	var query = String(document.location).split('?');

	if (query[1]) {
		var part = query[1].split('&');

		for (i = 0; i < part.length; i++) {
			var data = part[i].split('=');

			if (data[0] && data[1]) {
				value[data[0]] = data[1];
			}
		}

		if (value[key]) {
			return value[key];
		} else {
			return '';
		}
	}
}

$(document).ready(function() {
	// Highlight any found errors
	$('.text-danger').each(function() {
		var element = $(this).parent().parent();
		
		if (element.hasClass('form-group')) {
			element.addClass('has-error');
		}
	});
        $.ajax({
			url: '/ProjectEJB-war/web/getBasket',
			type: 'post',
			dataType: 'json',
			beforeSend: function() {
				$('#cart > button').button('loading');
			},
			success: function(json) {
				$('#cart > button').button('reset');
                                
                                $inter="";
                                if(json["lang"]==="fr"){
                                    if(json['item']===1) $inter = "1 objet - "+json['total']+" &euro;";
                                    else if(json['item']===0) $inter = "0 objet - "+0+" &euro;";
                                    else $inter = json["item"]+" objets - "+json['total']+" &euro;";
                                }else if(json["lang"]==="en"){
                                    if(json['item']===1) $inter = "1 item - "+"$"+json['total'];
                                    else if(json['item']===0) $inter = "0 item - $"+0;
                                    else $inter = json["item"]+" items - "+"$"+json['total'];
                                }
                                $truc = "<button type=\"button\" data-loading-text=\"Loading...\" class=\"btn btn-inverse btn-block btn-lg\"><i class=\"fa fa-shopping-cart\"></i> <span id=\"cart-total\">"+$inter+"</span></button>";
                                $("#cart").html($truc);
			}
		});
	/* Search */
/*	$('#search input[name=\'search\']').parent().find('button').on('click', function() {
		url = 'index?route=product/search';

		var value = $('header input[name=\'search\']').val();

		if (value) {
			url += '&search=' + encodeURIComponent(value);
		}

		location = url;
	});

	$('#search input[name=\'search\']').on('keydown', function(e) {
		if (e.keyCode == 13) {
			$('header input[name=\'search\']').parent().find('button').trigger('click');
		}
	});
*/
	// Menu
	$('#menu .dropdown-menu').each(function() {
		var menu = $('#menu').offset();
		var dropdown = $(this).parent().offset();

		var i = (dropdown.left + $(this).outerWidth()) - (menu.left + $('#menu').outerWidth());

		if (i > 0) {
			$(this).css('margin-left', '-' + (i + 5) + 'px');
		}
	});

	// Product List
	$('#list-view').click(function() {
		$('#content .product-layout > .clearfix').remove();

		$('#content .product-layout').attr('class', 'product-layout product-list col-xs-12');

		localStorage.setItem('display', 'list');
	});

	// Product Grid
	$('#grid-view').click(function() {
		$('#content .product-layout > .clearfix').remove();

		// What a shame bootstrap does not take into account dynamically loaded columns
		cols = $('#column-right, #column-left').length;

		if (cols == 2) {
			$('#content .product-layout').attr('class', 'product-layout product-grid col-lg-6 col-md-6 col-sm-12 col-xs-12');

			$('#content .product-layout:nth-child(2)').after('<div class="clearfix visible-md visible-sm"></div>');
		} else if (cols == 1) {
			$('#content .product-layout').attr('class', 'product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12');

			$('#content .product-layout:nth-child(3)').after('<div class="clearfix visible-lg"></div>');
		} else {
			$('#content .product-layout').attr('class', 'product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12');

			$('#content .product-layout:nth-child(4)').after('<div class="clearfix"></div>');
		}

		 localStorage.setItem('display', 'grid');
	});

	if (localStorage.getItem('display') == 'list') {
		$('#list-view').trigger('click');
	} else {
		$('#grid-view').trigger('click');
	}

	// tooltips on hover
	$('[data-toggle=\'tooltip\']').tooltip({container: 'body'});

	// Makes tooltips work on ajax generated content
	$(document).ajaxStop(function() {
		$('[data-toggle=\'tooltip\']').tooltip({container: 'body'});
	});
        /*$('#cart-total').empty();
        console.log($('#cart-total').html());
        $('#cart-total').html("youpi");*/
        
});

// Cart add remove functions
var cart = {
	'add': function(product_id, quantity) {
		$.ajax({
			url: '/ProjectEJB-war/web/addBasket?id='+product_id,
			type: 'post',
			data: 'product_id=' + product_id + '&quantity=' + (typeof(quantity) != 'undefined' ? quantity : 1),
			dataType: 'json',
			beforeSend: function() {
				$('#cart > button').button('loading');
			},
			success: function(json) {
				$('.alert, .text-danger').remove();

				$('#cart > button').button('reset');

				if (json['redirect']) {
					location = json['redirect'];
				}

				if (json['success']) {
                                        $inter="";
                                        if(json["lang"]==="fr"){
                                            if(json['item']===1) $inter = "1 objet - "+json['total']+" &euro;";
                                            else if(json['item']===0) $inter = "0 objet - "+0+" &euro;";
                                            else $inter = json["item"]+" objets - "+json['total']+" &euro;";
                                        }else if(json["lang"]==="en"){
                                            if(json['item']===1) $inter = "1 item - "+"$"+json['total'];
                                            else if(json['item']===0) $inter = "0 item - $"+0;
                                            else $inter = json["item"]+" items - "+"$"+json['total'];
                                        }
                                        
                                        $truc = "<button type=\"button\" data-loading-text=\"Loading...\" class=\"btn btn-inverse btn-block btn-lg\"><i class=\"fa fa-shopping-cart\"></i> <span id=\"cart-total\">"+$inter+"</span></button>";
                                        $("#cart").html($truc);
                                        
					$('html, body').animate({ scrollTop: 0 }, 'slow');

					//$('#cart > ul').load('index.php?route=common/cart/info ul li');
				}
			}
		});
	},
	'update': function(key, name) {//$("txt").val();
		$.ajax({
			url: '/ProjectEJB-war/web/updateBasket',
			type: 'post',
			data: 'id=' + key + '&quantity=' + (typeof($('#input-bean'+name).val()) != 'undefined' ? $('#input-bean'+name).val() : 1),
			dataType: 'json',
			beforeSend: function() {
				$('#cart > button').button('loading');
			},
			success: function(json) {
                                $('#cart > button').button('reset');

				$inter="";
                                $sub = "#cart-Sub-Total";
                                $vat = "#cart-VAT";
                                $total = "#cart-Total";
                                $tot_bean="#total-bean"+name;
                                
                                if(json["lang"]==="fr"){
                                    if(json['item']===1) $inter = "1 objet - "+json['total']+" &euro;";
                                    else if(json['item']===0) $inter = "0 objet - "+0+" &euro;";
                                    else $inter = json["item"]+" objets - "+json['total']+" &euro;";
                                    $($sub).html(json['sub-total']+' &euro;');
                                    $($vat).html(json['VAT']+' &euro;');
                                    $($total).html(json['total']+' &euro;');
                                    $($tot_bean).html(json['total-bean']+' &euro;');
                                }else if(json["lang"]==="en"){
                                    if(json['item']===1) $inter = "1 item - "+"$"+json['total'];
                                    else if(json['item']===0) $inter = "0 item - $"+0;
                                    else $inter = json["item"]+" items - "+"$"+json['total'];
                                    $($sub).html("$"+json['sub-total']);
                                    $($vat).html("$"+json['VAT']);
                                    $($total).html("$"+json['total']);
                                    $($tot_bean).html("$"+json['total-bean']);
                                }
                                $truc = "<button type=\"button\" data-loading-text=\"Loading...\" class=\"btn btn-inverse btn-block btn-lg\"><i class=\"fa fa-shopping-cart\"></i> <span id=\"cart-total\">"+$inter+"</span></button>";
                                $("#cart").html($truc);
			}
		});
	},
	'remove': function(key, name) {
		$.ajax({
			url: '/ProjectEJB-war/web/removeBasket',
			type: 'post',
			data: 'id=' + key,
			dataType: 'json',
			beforeSend: function() {
				$('#cart > button').button('loading');
			},
			success: function(json) {
                                //location.reload();
				$('#cart > button').button('reset');
				$inter="";
                                
                                $tt = "#div-bean"+name;
                                $($tt+" button").trigger("mouseout");
                                $($tt).remove();
                                $sub = "#cart-Sub-Total";
                                $vat = "#cart-VAT";
                                $total = "#cart-Total";
                                
                                if(json["lang"]==="fr"){
                                    if(json['item']===1) $inter = "1 objet - "+json['total']+" &euro;";
                                    else if(json['item']===0) $inter = "0 objet - "+0+" &euro;";
                                    else $inter = json["item"]+" objets - "+json['total']+" &euro;";
                                    $($sub).html(json['sub-total']+' &euro;');
                                    $($vat).html(json['VAT']+' &euro;');
                                    $($total).html(json['total']+' &euro;');
                                }else if(json["lang"]==="en"){
                                    if(json['item']===1) $inter = "1 item - "+"$"+json['total'];
                                    else if(json['item']===0) $inter = "0 item - $"+0;
                                    else $inter = json["item"]+" items - "+"$"+json['total'];
                                    $($sub).html("$"+json['sub-total']);
                                    $($vat).html("$"+json['VAT']);
                                    $($total).html("$"+json['total']);
                                }

                                $truc = "<button type=\"button\" data-loading-text=\"Loading...\" class=\"btn btn-inverse btn-block btn-lg\"><i class=\"fa fa-shopping-cart\"></i> <span id=\"cart-total\">"+$inter+"</span></button>";
                                $("#cart").html($truc);
                                $tt = "#div-bean"+name;
                                $($tt+" button").trigger("mouseout");
                                $($tt).remove();
                                
				/*if (getURLVar('route') == 'checkout/cart' || getURLVar('route') == 'checkout/checkout') {
					location = 'index.php?route=checkout/cart';
				} else {
					$('#cart > ul').load('index.php?route=common/cart/info ul li');
				}*/
			}
		});
	}
};