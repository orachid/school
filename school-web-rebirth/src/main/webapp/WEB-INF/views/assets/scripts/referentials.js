$(document).ready(
		function() {
			// etablissement ref select component
			$.getJSON(
					"/rest/etablissements/list",
					function(data) {
						var sel = $("#etablissements-chosen");
						sel.empty();
						sel.append('<option>&nbsp;</option>');
						for (var i = 0; i < data.length; i++) {
							sel.append('<option value="' + data[i].id + '">'
									+ data[i].code + '</option>');
						}
					}).done(function(data) {
				$("#etablissements-chosen").chosen();
			});

			// Classes ref select component
			$.getJSON(
					"/rest/classes/list",
					function(data) {
						var sel = $("#classes-chosen");
						sel.empty();
						sel.append('<option>&nbsp;</option>');
						for (var i = 0; i < data.length; i++) {
							sel.append('<option value="' + data[i].id + '">'
									+ data[i].code + '</option>');
						}
					}).done(function(data) {
				$("#classes-chosen").chosen();
			});
			// Matieres ref select component
			/*$.getJSON(
					"/rest/matieres/list",
					function(data) {
						var sel = $("#matieres-chosen");
						sel.empty();
						sel.append('<option>&nbsp;</option>');
						for (var i = 0; i < data.length; i++) {
							sel.append('<option value="' + data[i].id + '">'
									+ data[i].code + '</option>');
						}
					}).done(function(data) {
				$("#matieres-chosen").chosen();
			});*/
			// Salles ref select component
			$.getJSON(
					"/rest/salles/list",
					function(data) {
						var sel = $("#salles-chosen");
						sel.empty();
						sel.append('<option>&nbsp;</option>');
						for (var i = 0; i < data.length; i++) {
							sel.append('<option value="' + data[i].id + '">'
									+ data[i].code + '</option>');
						}
					}).done(function(data) {
				$("#salles-chosen").chosen();
			});
		});