<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:out value="${navItem.clazz}">
</c:out> --%>
<li <c:if test="${not empty navItem.clazz}"> class="${navItem.clazz}"</c:if>><!-- {{! print class name (active, open, etc) if it exists }} -->
  <a href=<c:if test="${navItem.haslink}">"${navItem.link}"</c:if> <c:if test="${not navItem.haslink}">"#"</c:if><c:if test="${navItem.hassubmenu}"> class="dropdown-toggle" </c:if>>
	<c:if test="${not empty navItem.icon}">
		<i class="${navItem.icon}"></i>
	</c:if>
	
	<c:if test="${navItem.level1}">
	 	<span class="menu-text">
	</c:if>

	<c:if test="${navItem.level2}">
	 	<c:if test="${not empty navItem.icon}"><i class="icon-double-angle-right"></i></c:if>
	</c:if>

	  ${navItem.title}
	 <!--  {{#badge}}
			<span class="badge {{badge-class}} {{tooltip-class}}"{{#tooltip}} title="{{{tooltip}}}"{{/tooltip}}>{{{badge}}}</span>
	  {{/badge}} -->
	 <%--  <c:if test="${navItem.label}">
		<span class="label {{label-class}}"{{#label-title}} title="{{label-title}}"{{/label-title}}>${navItem.label}</span>  
	  </c:if> --%>
	
	<c:if test="${navItem.level1}">
	 	</span>
	</c:if>

	 <c:if test="${navItem.hassubmenu}"><b class="arrow icon-angle-down"></b></c:if>
  </a>
  
  <c:if test="${navItem.hassubmenu}">
	 	<ul class="submenu">
		<c:forEach var="item" items="${navItem.submenu}" >
			<c:set var="navItem" value="${item}" scope="request"/>
			<jsp:include
				page="/WEB-INF/views/layouts/partials/_shared/sidenav/items.jsp"/>
				<%-- <jsp:param name="navItem" value="item" />
			</jsp:include> --%>
		</c:forEach>
		</ul>
</c:if>

</li>