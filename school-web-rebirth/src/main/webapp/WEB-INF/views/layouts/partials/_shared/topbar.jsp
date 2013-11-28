<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-default" id="navbar">
 <script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
 </script>

  <div class="navbar-container" id="navbar-container">

	<div class="navbar-header pull-left">
	  <a href="#" class="navbar-brand">
		<small>
			<i class="{{site.brand_icon}}"></i>
			${site.brand_text}
		</small>
	  </a><!-- /.brand -->
	</div><!-- /.navbar-header -->

	<div class="navbar-header pull-right" role="navigation">
	  <ul class="nav ace-nav">
			<!-- COMMENTED BY RACHID -->
			
			<jsp:include page="/WEB-INF/views/layouts/partials/_shared/topbar/tasks.jsp"/>
			<jsp:include page="/WEB-INF/views/layouts/partials/_shared/topbar/notifications.jsp"/>
			<jsp:include page="/WEB-INF/views/layouts/partials/_shared/topbar/messages.jsp"/>
			<jsp:include page="/WEB-INF/views/layouts/partials/default/topbar/user_menu.jsp"/>
	  </ul><!-- /.ace-nav -->
	</div><!-- /.navbar-header -->

  </div><!-- /.container -->

</div>