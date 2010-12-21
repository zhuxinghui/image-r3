$(document).ready(function() {
	$("a[name='searchInBox']").mouseenter(function() {	
		var height = $(this).height();
		var top = $(this).offset().top;
		var left = $(this).offset().left + ($(this).width() ) - ($('#shareit-box').width() / 2);	
		$('#shareit-header').height(height);
		$('#shareit-box').show();
		$('#shareit-box').css({'top':top, 'left':left});
		$('#shareit-field').val(field);
	});
 
	$('#shareit-box').mouseleave(function () {
		$('#shareit-field').val('');
		$(this).hide();
	});
	$('#shareit-field').click(function () {
		$(this).select();
	});
});
