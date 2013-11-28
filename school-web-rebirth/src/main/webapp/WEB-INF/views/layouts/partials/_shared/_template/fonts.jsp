<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${site.remote_fonts}">
    		<link rel="stylesheet" href="${site.protocol}//fonts.googleapis.com/css?family=Open+Sans:400,300" />
</c:if>

<c:if test="${site.remote_fonts==false}">
    		<link rel="stylesheet" href="${path.assets}/css/ace-fonts.css" />
</c:if>