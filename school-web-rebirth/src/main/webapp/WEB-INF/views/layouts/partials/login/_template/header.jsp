<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="utf-8" />
	<title>${page.title} - ${site.title}</title>
	<meta name="description" content="${page.description}" />

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- basic styles -->
	<link href="${path.assets}/css/bootstrap.min.css" rel="stylesheet" />

	<link rel="stylesheet" href="${path.assets}/css/font-awesome.min.css" />
	<!--[if IE 7]>
	  <link rel="stylesheet" href="${path.assets}/css/font-awesome-ie7.min.css" />
	<![endif]-->


	<!-- page specific plugin styles -->
	<c:if test="${not empty page.styles}">
    		<c:forEach var="entry" items="${page.styles}" >
				<link rel="stylesheet" href="${path.assets}/css/${entry}" />
			</c:forEach>
	</c:if>
	
	<!-- fonts -->
	<jsp:include
	page="/WEB-INF/views/layouts/partials/_shared/_template/fonts.jsp"/>
	
	<!-- ace styles -->
	<link rel="stylesheet" href="${path.assets}/css/ace.min.css" />
	<link rel="stylesheet" href="${path.assets}/css/ace-rtl.min.css" />
	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="${path.assets}/css/ace-ie.min.css" />
	<![endif]-->
	
	<!-- inline styles related to this page -->
	<c:if test="${not empty page.inline_styles}">
    	<style>
    		${page.inline_styles}
    	</style>
	</c:if>
	


	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="${path.assets}/js/html5shiv.js"></script>
	<script src="${path.assets}/js/respond.min.js"></script>
	<![endif]-->

	<script src="${path.assets}/js/jquery-2.0.3.min.js"></script>
	<script src="${path.assets}/js/bootstrap.min.js"></script>
</head>