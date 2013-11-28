<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login-container">


<div class="center">
	<h1><i class="icon-leaf green"></i> <span class="red">School</span> <span class="white">Application</span></h1>
	<h4 class="blue">&copy; Wati</h4>
</div>


<div class="space-6"></div>


<div class="position-relative">

	<jsp:includepage="/WEB-INF/views/pages/partials/login/login_box.jsp"/>
	<jsp:includepage="/WEB-INF/views/pages/partials/login/forgot_box.jsp"/>
	<jsp:includepage="/WEB-INF/views/pages/partials/login/signup_box.jsp"/>
	
</div><!--/position-relative-->


</div>