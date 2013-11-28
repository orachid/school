<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/_template/header.jsp"/>
	<body>
	<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/topbar.jsp"/>

		<div class="main-container" id="main-container">
		 <script type="text/javascript">
		 try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		 </script>
		 <div class="main-container-inner">
		<jsp:include
			page="/WEB-INF/views/layouts/partials/_shared/sidenav.jsp"/>
			<div class="main-content">
				<jsp:include
					page="/WEB-INF/views/layouts/partials/default/breadcrumbs.jsp"/>
				<div class="page-content">
					<!-- {{^page.no-header}}{{!if no such thing as "no-header", then print header}} -->
					<div class="page-header">
						<h1>${page.title} 
							<c:if test="${not empty page.description}">
    							<small><i class="icon-double-angle-right"></i> ${page.description}</small>
							</c:if>
							
						</h1>
					</div><!--/.page-header-->
					<!-- {{/page.no-header}} -->

					<div class="row">
					 <div class="col-xs-12">
<!-- PAGE CONTENT BEGINS -->
	<jsp:include page="/WEB-INF/views/${page.content}.jsp"></jsp:include>
<!-- PAGE CONTENT ENDS -->
					 </div><!--/.col-->
					</div><!--/.row-->

				</div><!--/.page-content-->

			</div><!--/.main-content -->
			<jsp:include
				page="/WEB-INF/views/layouts/partials/_shared/settings.jsp"/>
		 </div><!--/.main-container-inner-->

		 <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		 </a>
		</div><!--/.main-container-->
		
		<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/_template/footer.jsp"/>
	
	</body>
</html>
