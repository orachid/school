<li class="purple">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
		<i class="ace-icon fa fa-bell icon-animated-bell"></i>
		<span class="badge badge-important">${layouttopbar_notificationscount}</span>
	</a>
	<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
		<li class="dropdown-header">
			<i class="ace-icon fa fa-exclamation-triangle"></i> ${layouttopbar_notificationscount} Notifications
		</li>

		{{#layout.topbar_notifications.latest}}
		<li>
			<a href="#">
				{{#badge}}
				<div class="clearfix">
					<span class="pull-left"><i class="btn btn-xs no-hover {{icon-class}} {{icon}}"></i> {{title}}</span>
					<span class="pull-right badge {{badge-class}}">{{badge}}</span>					
				</div>
				{{/badge}}
				{{^badge}}
					<i class="btn btn-xs {{icon-class}} {{icon}}"></i> {{title}}
				{{/badge}}
			</a>
		</li>
		{{/layout.topbar_notifications.latest}}

		<li>
			<a href="#">
				See all notifications
				<i class="ace-icon fa fa-arrow-right"></i>
			</a>
		</li>
	</ul>
</li>