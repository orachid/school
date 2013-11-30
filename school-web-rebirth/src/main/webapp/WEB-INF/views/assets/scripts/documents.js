jQuery(function($) {

	var DataSourceTree = function(options) {
		this.url = options.url;
	};

	DataSourceTree.prototype.data = function(options, callback) {
		var self = this;
		var $data = null;

		var param = null;

		if (!("name" in options) && !("type" in options)) {
			param = 0;// load the first level
		} else if ("type" in options && options.type == "folder") {
			if ("additionalParameters" in options
					&& "children" in options.additionalParameters) {
				param = options.additionalParameters["id"];
			}
		}

		if (param != null) {
			$.ajax({
				url : this.url,
				data : 'id=' + param,
				type : 'GET',
				dataType : 'json',
				success : function(response) {
					alert(response);
					if (response.status == "OK")
						callback({
							data : response.data
						});
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
	};

	$('#private-documents-tree')
			.tree(
					{
						dataSource : new DataSourceTree({
							url : '/rest/documents/private'
						}),
						multiSelect : false,
						loadingHTML : '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
						'open-icon' : 'icon-minus',
						'close-icon' : 'icon-plus',
						'selectable' : true,
						'selected-icon' : 'icon-ok',
						'unselected-icon' : 'icon-remove'
					});
	$('#public-documents-tree')
			.tree(
					{
						dataSource : new DataSourceTree({
							url : '[PATH TO SERVICE]'
						}),
						multiSelect : false,
						loadingHTML : '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
						'open-icon' : 'icon-minus',
						'close-icon' : 'icon-plus',
						'selectable' : true,
						'selected-icon' : 'icon-ok',
						'unselected-icon' : 'icon-remove'
					});
});