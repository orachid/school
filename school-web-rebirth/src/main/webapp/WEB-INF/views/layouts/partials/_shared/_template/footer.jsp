<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- basic scripts -->
<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/_template/jquery.jsp"/>

<script src="${path.assets}/js/bootstrap.min.js"></script>
<script src="${path.assets}/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->
<!-- {{!put IE only scripts here, currently we only use ExCanvas.js from time to time}} -->
<c:if test="${not empty page.ie_scripts}">
	<!--[if lte IE {{version}}]>
  <script src="{{{path.assets}}}/js/{{{file_name}}}"></script>
<![endif]-->
</c:if>

<c:if test="${not empty page.scripts}">
    		<c:forEach var="entry" items="${page.scripts}" >
				<script src="${path.assets}/js/${entry}"></script>
			</c:forEach>
</c:if>

<!-- ace scripts -->
<script src="${path.assets}/js/ace-elements.min.js"></script>
<script src="${path.assets}/js/ace.min.js"></script>


<!-- inline scripts related to this page -->
<c:if test="${not empty page.inline_scripts}">
    	<script type="text/javascript">
    		${page.inline_scripts}
    	</script>
</c:if>
