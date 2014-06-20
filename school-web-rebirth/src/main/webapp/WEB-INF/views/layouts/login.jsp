<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include
	page="/WEB-INF/views/layouts/partials/login/_template/header.jsp"/>

<body class="login-layout light-login">

	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<jsp:include page="/WEB-INF/views/${pageContent}.jsp"></jsp:include>
				</div>
				<!--/.col-->
			</div>
			<!--/.row-->
		</div>
	</div>
	<!--/.main-container-->

	<jsp:include
		page="/WEB-INF/views/layouts/partials/login/_template/footer.jsp"></jsp:include>

</body>
</html>
