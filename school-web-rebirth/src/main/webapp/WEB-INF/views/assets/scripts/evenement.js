jQuery(function($) {

	$('input[name=calendar-date-range-input]').daterangepicker().prev().on(
			ace.click_event, function() {
				$(this).next().focus();
			});

	$('.date-picker').datepicker({
		autoclose : true
	}).next().on(ace.click_event, function() {
		$(this).prev().focus();
	});

	$("#event-type").chosen();
	// chosen plugin inside a modal will have a zero width because the select
	// element is originally hidden
	// and its width cannot be determined.
	// so we set the width after modal is show
	$('#add-event-modal-form').on('shown.bs.modal', function() {
		$(this).find('.chosen-container').each(function() {
			$(this).find('a:first-child').css('width', '210px');
			$(this).find('.chosen-select').css('width', '210px');
			$(this).find('.chosen-drop').css('width', '210px');
			$(this).find('.chosen-search input').css('width', '200px');
		});
	});

	$('#add-event-modal-form').on('shown.bs.modal', function() {
		$(this).find('.datetimepicker-input').each(function() {
			$(this).datetimepicker({
			     	language: 'pt-BR',
			 		pickSeconds: false
		    });
		});
	});
});
