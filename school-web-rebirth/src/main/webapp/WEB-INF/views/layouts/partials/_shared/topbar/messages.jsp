<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<li class="green"><a data-toggle="dropdown" class="dropdown-toggle"
	href="#"> <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
		<span class="badge badge-success">${layouttopbar_messagescount}</span>
</a>
	<ul
		class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
		<li class="dropdown-header"><i class="ace-icon fa fa-envelope-o"></i>
			${layouttopbar_messagescount} Messages</li>

		<!--  COMMENTED BY RACHID  -->
		{{#layout.topbar_messages.latest}}
		<li>
			<a href="#">
						<img src="{{path.assets}}/avatars/{{img}}" class="msg-photo" alt="{{name}}'s Avatar">
						<span class="msg-body">
							<span class="msg-title">
								<span class="blue">{{name}}:</span>
								{{summary}}
							</span>
							<span class="msg-time">
								<i class="ace-icon fa fa-clock-o"></i> <span>{{time}}</span>
							</span>
						</span>
					</a>
		</li>
		{{/layout.topbar_messages.latest}} 

		<li><a href="{{#createLinkFunction}}inbox{{/createLinkFunction}}">
				See all messages <i class="ace-icon fa fa-arrow-right"></i>
		</a></li>

	</ul></li>