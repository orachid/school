<li class="grey">
	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
		<i class="ace-icon fa fa-tasks"></i>
		<span class="badge badge-grey">${layouttopbar_taskscount}</span>
	</a>

	<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
		<li class="dropdown-header">
			<i class="ace-icon fa fa-check"></i> ${layouttopbar_taskscount} Tasks to complete
		</li>

		{{#layout.topbar_tasks.latest}}
		<li>
			<a href="#">
				<div class="clearfix">
					<span class="pull-left">{{title}}</span>
					<span class="pull-right">{{percentage}}%</span>
				</div>
				<div class="progress progress-mini {{progress-class}}">
					<div style="width:{{percentage}}%" class="progress-bar {{progress-bar-class}}"></div>
				</div>
			</a>
		</li>
		{{/layout.topbar_tasks.latest}}

		<li>
			<a href="#">
				See tasks with details
				<i class="ace-icon fa fa-arrow-right"></i>
			</a>
		</li>
	</ul>
</li>