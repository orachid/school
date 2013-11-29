jQuery(function($) {

/* initialize the external events
	-----------------------------------------------------------------*/

	$('#external-events div.external-event').each(function() {

		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};

		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
	});




	/* initialize the calendar
	-----------------------------------------------------------------*/

//	var date = new Date();
//	var d = date.getDate();
//	var m = date.getMonth();
//	var y = date.getFullYear();

	
	var calendar = $('#calendar').fullCalendar({
		defaultView: 'agendaWeek',
		buttonText: {
			prev: '<i class="icon-chevron-left"></i>',
			next: '<i class="icon-chevron-right"></i>'
		},
	
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		//event sources
		 eventSources: [
		                // your event source
		       {
		         url: '/rest/calendar', // use the `url` property
		         type: 'GEt'
		       }
          ]
		,
		editable: true,
		droppable: true, // this allows things to be dropped onto the calendar !!!
		drop: function(date, allDay) { // this function is called when something is dropped
		
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			var $extraEventClass = $(this).attr('data-class');
			
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign it the date that was reported
			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;
			if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];
			
			// render the event on the calendar
			// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			
			// is the "remove after drop" checkbox checked?
			if ($('#drop-remove').is(':checked')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}
			
		}
		,
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			
			alert(new Date(start));
			alert(formatDate(start));
			$("#start-date-date").val($.fullCalendar.parseDate(start));
			$("#end-date-date").val(end);
			$('#add-event-modal-form').modal('show');
			

			calendar.fullCalendar('unselect');
			
		}
		,
		eventClick: function(calEvent, jsEvent, view) {

			var form = $("<form class='form-inline'><label>Change event name &nbsp;</label></form>");
			form.append("<input class='middle' autocomplete=off type=text value='" + calEvent.title + "' /> ");
			form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> Save</button>");
			
			var div = bootbox.dialog({
				message: form,
			
				buttons: {
					"delete" : {
						"label" : "<i class='icon-trash'></i> Delete Event",
						"className" : "btn-sm btn-danger",
						"callback": function() {
							calendar.fullCalendar('removeEvents' , function(ev){
								return (ev._id == calEvent._id);
							})
						}
					} ,
					"close" : {
						"label" : "<i class='icon-remove'></i> Close",
						"className" : "btn-sm"
					} 
				}

			});
			
			form.on('submit', function(){
				calEvent.title = form.find("input[type=text]").val();
				calendar.fullCalendar('updateEvent', calEvent);
				div.modal("hide");
				return false;
			});
			
		
			//console.log(calEvent.id);
			//console.log(jsEvent);
			//console.log(view);

			// change the border color just for fun
			//$(this).css('border-color', 'red');

		}
		
	});

	function getGMTOffset(localDate) {
	    var positive = (localDate.getTimezoneOffset() > 0);
	    var aoff = Math.abs(localDate.getTimezoneOffset());
	    var hours = Math.floor(aoff / 60);
	    var mins = aoff % 60;
	    var offsetTz = padzero(hours) + ':' + padzero(mins);
	    // getTimezoneOffset() method returns difference between (GMT) and local time, in minutes.
	    // example, If your time zone is GMT+2, -120 will be returned.
	    // This is why we are inverting sign
	    if (!positive) {
	      return '+' + offsetTz;
	    }
	    return '-' + offsetTz;
	}

	function pad2zeros(n) {
	  if (n < 100) {
	      n = '0' + n;
	  }
	  if (n < 10) {
	      n = '0' + n;
	  }
	  return n;
	}
	function padzero(n) {
	    return n < 10 ? '0' + n : n.toString();
	}

	function formatDate(date)  {
	  if (date) {
	    return (date.getFullYear()) +
	           '-' + padzero((date.getMonth() + 1)) +
	           '-' + padzero(date.getDate()) +
	           'T' + padzero(date.getHours()) +
	           ':' + padzero(date.getMinutes()) +
	           ':' + padzero(date.getSeconds()) +
	           '.' + pad2zeros(date.getMilliseconds()) +
	           getGMTOffset(date);
	  }
	  return '';
	}

});