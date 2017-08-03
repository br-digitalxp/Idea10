$(document).ready(function() {

	$(function () {
		$(".melhores-da-semana-section .col-md-4").slice(0, 3).show();
		$("#loadMore").on('click', function (e) {
			e.preventDefault();
			$(".melhores-da-semana-section .col-md-4:hidden").slice(0, 3).slideDown();
			if ($(".melhores-da-semana-section .col-md-4:hidden").length == 0) {
				$("#load").fadeOut('slow');
			}
			$('html,body').animate({
				scrollTop: $(this).offset().top
			}, 1500);
		});
	});

	$('a[href=#top]').click(function () {
		$('body,html').animate({
			scrollTop: 0
		}, 600);
		return false;
	});
    		  
			  
	$(window).scroll(function () {
		if ($(this).scrollTop() > 50) {
			$('.totop a').fadeIn();
		} else {
			$('.totop a').fadeOut();
		}
	});			  

});