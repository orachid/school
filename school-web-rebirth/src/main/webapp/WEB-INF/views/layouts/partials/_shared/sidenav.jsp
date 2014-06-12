<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a class="menu-toggler" id="menu-toggler" href="#"><span class="menu-text"></span></a>

<div class="sidebar" id="sidebar">
	<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>
	
	<%-- <jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/sidenav/shortcuts.jsp"/> --%>

	<ul class="nav nav-list">
		
		<!-- Nav menu builder -->
		<c:forEach var="item" items="${layout.sidenav_navList}" >
			<c:set var="navItem" value="${item}" scope="request"/>
			<jsp:include
				page="/WEB-INF/views/layouts/partials/_shared/sidenav/items.jsp"/>
				<%-- <jsp:param name="navItem" value="entry" />	 --%>
			<%-- </jsp:include> --%>
		</c:forEach>
		

	</ul><!--/.nav-list-->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>