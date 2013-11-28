<label for="id-date-range-picker-1">Calendar date range</label>
<div class="row">
	<div class="col-xs-8 col-sm-11">
		<div class="input-group">
			<span class="input-group-addon"> <i
				class="icon-calendar bigger-110"></i>
			</span> <input class="form-control" type="text"
				name="calendar-date-range-input" id="id-date-range-picker-1" />
		</div>
	</div>
	<a href="#add-event-modal-form" role="button" class="blue"
		data-toggle="modal">Add event</a> <span></span>
</div>
<div class="row">
	<div class="col-sm-9">
		<div class="space"></div>
		<div id='calendar'></div>
	</div>

	<div class="col-sm-3">
		<div class="widget-box transparent">
			<div class="widget-header">
				<h4>Filter</h4>
			</div>
			<div class="widget-body">
				<div class="widget-main no-padding">
					<div>
						<label for="form-field-select-3">Etablissement</label> <br /> <select
							class="width-80 chosen-select" id="etablissements-chosen"
							data-placeholder="Select etablissement...">
						</select>
					</div>
					<div>
						<label for="form-field-select-3">Classes</label> <br /> <select
							class="width-80 chosen-select" id="classes-chosen"
							data-placeholder="Select classe ...">
						</select>
					</div>
					<div>
						<label for="form-field-select-3">Salles</label> <br /> <select
							class="width-80 chosen-select" id="salles-chosen"
							data-placeholder="Select salles ...">
						</select>
					</div>

					<hr />
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Add event modal Form -->
<div id="add-event-modal-form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">Please fill the following form fields</h4>
			</div>
			<form id="add-event-form" method="post" action="/rest/calendar">
				<div class="modal-body overflow-visible">
					<!-- Event type -->
					<div class="form-group">
						<label for="form-field-select-3">Event type</label>
						<div>
							<select id="event-type" class="chosen-select"
								data-placeholder="Choose an event type...">
								<option value="EVENEMENT">Evenement</option>
								<option value="COURS">Cours</option>
								<option value="REUNION">Reunion</option>
								<option value="CONSEILLE_DE_CLASSE">Conseille de classe</option>
							</select>
						</div>
					</div>
					<!-- Title -->
					<div class="form-group">
						<label for="title">Title</label>
						<div>
							<input name="title" class="input-large" type="text" id="title"
								placeholder="Title" name="title" />
						</div>
					</div>
					<!-- Start date -->
					<div class="form-group">
						<label for="start-date-date">Start date</label>
						<div id="datetimepicker1"
							class="input-append date datetimepicker-input ">
							<input name="start" id="start-date-date"
								data-format="dd/MM/yyyy hh:mm:ss" type="text"></input> <span
								class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"> </i>
							</span>
						</div>
					</div>
					<!-- End of start date -->
					<!-- End date -->
					<div class="form-group">
						<label for="end-date-date">End date</label>
						<div id="datetimepicker2"
							class="input-append date datetimepicker-input">
							<input id="end-date-date" name="dateFin" data-format="dd/MM/yyyy hh:mm:ss"
								type="text"></input> <span class="add-on"> <i
								data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
							</span>
						</div>
					</div>
					<!-- End of end date -->
					<!-- footer -->
					<div class="modal-footer">
						<button class="btn btn-sm" data-dismiss="modal">
							<i class="icon-remove"></i> Cancel
						</button>
						<button class="btn btn-sm btn-primary" type="submit">
							<i class="icon-ok"></i> Save
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- End add event form -->
