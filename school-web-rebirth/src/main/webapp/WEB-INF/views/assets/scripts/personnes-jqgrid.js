$(function() {

	$.extend($.jgrid.defaults, {
		datatype : 'json',
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "id"
		},

		sortname : 'code',
		sortorder : 'asc',
		height : 'auto',
		autowidth : true,
		viewrecords : true,
		rowList : [ 10, 20, 50, 100 ],
		altRows : true,
		loadError : function(xhr, status, error) {
			alert(error);
		},
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				styleCheckbox(table);

				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		}
	});

	$.extend($.jgrid.edit, {
		closeAfterEdit : true,
		closeAfterAdd : true,
		ajaxEditOptions : {
			contentType : "application/json"
		},
		mtype : 'PUT',
		serializeEditData : function(data) {
			delete data.oper;
			var date=JSON.stringify(new Date(data.dateNaissance));
			data.dateNaissance= date.substring(1,date.length-1);
			return JSON.stringify(data);
		}
	});
	$.extend($.jgrid.del, {
		mtype : 'DELETE',
		serializeDelData : function() {
			return "";
		}
	});

	var editOptions = {
		recreateForm : true,
		beforeShowForm : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
			style_edit_form(form);
		},
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata.id;
		}
	};
	var addOptions = {
		mtype : "POST",
		beforeShowForm : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
			style_edit_form(form);
		}
	};
	var delOptions = {
		beforeShowForm : function(e) {
			var form = $(e[0]);
			if (form.data('styled'))
				return false;

			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
			style_delete_form(form);

			form.data('styled', true);
		},
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata;
		}
	};

	var URL = 'rest/users';
	var options = {
		url : URL,
		editurl : URL,
		colModel : [ {
			name : 'id',
			label : 'ID',
			index : 'id',
			formatter : 'integer',
			width : 40,
			editable : true,
			editoptions : {
				disabled : true,
				size : 5
			}
		}, {
			name : 'nom',
			label : 'Nom',
			index : 'nom',
			width : 300,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'prenom',
			label : 'Prenom',
			index : 'prenom',
			width : 200,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'username',
			label : 'Username',
			index : 'username',
			width : 200,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'password',
			label : 'Password',
			index : 'password',
			width : 200,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'civilite',
			label : 'Civilite',
			index : 'civilite',
			width : 200,
			edittype:"select",
			editoptions: {value:"HOMME:HOMME;FEMME:FEMME"},
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'dateNaissance',
			label : 'Date de naissance',
			index : 'dateNaissance',
			width : 200,
			sorttype:"date",
			unformat: pickDate,
			editable : true,
			editrules : {
				required : true
			}
		}, {
			name : 'email',
			label : 'Email',
			index : 'email',
			width : 200,
			editable : true,
			editrules : {
				required : true
			}
		} ],
		caption : "Matieres Records",
		emptyrecords : "No salles found from server",
		pager : '#pager',
		height : 'auto',
		ondblClickRow : function(id) {
			jQuery(this).jqGrid('editGridRow', id, editOptions);
		}
	};

	$("#grid").jqGrid(options).navGrid(
			'#pager',
			{
				edit : true,
				editicon : 'icon-pencil blue',
				add : true,
				addicon : 'icon-plus-sign purple',
				del : true,
				delicon : 'icon-trash red',
				search : true,
				searchicon : 'icon-search orange',
				refresh : true,
				refreshicon : 'icon-refresh green',
			}, // options
			editOptions,
			addOptions,
			delOptions,
			{
				// search form
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					style_search_form(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : true,
			/**
			 * multipleGroup:true, showQuery: true
			 */

			} // search options
	);

	//enable datepicker
	function pickDate( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=text]')
					.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
		}, 0);
	}
	function style_edit_form(form) {
		// enable datepicker on "sdate" field and switches for "stock" field
		form.find('input[name=dateNaissance]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
//		.end().find('input[name=stock]')
//			  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>')
			  ;

		// update buttons classes
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();// ui-icon,
		// s-icon
		buttons.eq(0).addClass('btn-primary')
				.prepend('<i class="icon-ok"></i>');
		buttons.eq(1).prepend('<i class="icon-remove"></i>');

		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').remove();
		buttons.eq(0).append('<i class="icon-chevron-left"></i>');
		buttons.eq(1).append('<i class="icon-chevron-right"></i>');
	}

	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();// ui-icon,
		// s-icon
		buttons.eq(0).addClass('btn-danger').prepend(
				'<i class="icon-trash"></i>');
		buttons.eq(1).prepend('<i class="icon-remove"></i>');
	}

	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	}

	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable');
		buttons.find('.EditButton a[id*="_reset"]').addClass(
				'btn btn-sm btn-info').find('.ui-icon').attr('class',
				'icon-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass(
				'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
				'icon-comment-alt');
		buttons.find('.EditButton a[id*="_search"]').addClass(
				'btn btn-sm btn-purple').find('.ui-icon').attr('class',
				'icon-search');
	}

	function beforeDeleteCallback(e) {
		var form = $(e[0]);
		if (form.data('styled'))
			return false;

		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
				'<div class="widget-header" />');
		style_delete_form(form);

		form.data('styled', true);
	}

	function beforeEditCallback(e) {
		var form = $(e[0]);
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
				'<div class="widget-header" />');
		style_edit_form(form);
	}

	// it causes some flicker when reloading or navigating grid
	// it may be possible to have some custom formatter to do this as the grid
	// is being created to prevent this
	// or go back to default browser checkbox styles for the grid
	function styleCheckbox(table) {
		/**
		 * $(table).find('input:checkbox').addClass('ace') .wrap('<label />')
		 * .after('<span class="lbl align-top" />')
		 * 
		 * 
		 * $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		 * .find('input.cbox[type=checkbox]').addClass('ace') .wrap('<label
		 * />').after('<span class="lbl align-top" />');
		 */
	}

	// unlike navButtons icons, action icons in rows seem to be hard-coded
	// you can change them like this in here if you want
	function updateActionIcons(table) {
		/**
		 * var replacement = { 'ui-icon-pencil' : 'icon-pencil blue',
		 * 'ui-icon-trash' : 'icon-trash red', 'ui-icon-disk' : 'icon-ok green',
		 * 'ui-icon-cancel' : 'icon-remove red' }; $(table).find('.ui-pg-div
		 * span.ui-icon').each(function(){ var icon = $(this); var $class =
		 * $.trim(icon.attr('class').replace('ui-icon', '')); if($class in
		 * replacement) icon.attr('class', 'ui-icon '+replacement[$class]); })
		 */
	}

	// replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
			'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
			'ui-icon-seek-next' : 'icon-angle-right bigger-140',
			'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
				.each(
						function() {
							var icon = $(this);
							var $class = $.trim(icon.attr('class').replace(
									'ui-icon', ''));

							if ($class in replacement)
								icon.attr('class', 'ui-icon '
										+ replacement[$class]);
						});
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}
});