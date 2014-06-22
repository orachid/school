<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- basic scripts -->
<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/_template/jquery.jsp"/>

<!-- inline scripts related to this page -->
<c:if test="${not empty page.inline_scripts}">
    	<script type="text/javascript">
    		${page.inline_scripts}
    	</script>
</c:if>