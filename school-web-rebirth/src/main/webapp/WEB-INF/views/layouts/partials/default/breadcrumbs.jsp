<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
	try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>

	<ul class="breadcrumb">
		<li><i class="icon-home home-icon"></i> <a href="/">Home</a></li>
		<c:forEach var="link" items="${breadcrumbs.links}" >
				<li><a href="${link.link}">${link.title}</a></li>
		</c:forEach>
		<li class="active">${breadcrumbs.title}</li>
	</ul><!--.breadcrumb-->

	<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon">
				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
				<i class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div><!--#nav-search-->

</div>