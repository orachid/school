<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${event!=null}">
	<table class="table table-condensed table-bordered">
		<tbody>
			<tr>
				<td>Title</td>
				<td>${event.title}</td>
			</tr>
			<c:if test="${event.classe}">
				<tr>
					<td>Classe</td>
					<td>${event.classe}</td>
				</tr>
			</c:if>
			<tr>
				<td>Date de début</td>
				<td>${event.start}</td>
			</tr>
			<tr>
				<td>Date de fin</td>
				<td>${event.end}</td>
			</tr>
			<c:if test="${event.professeur}">
				<tr>
					<td>Proffesseur</td>
					<td>${event.professeur}</td>
				</tr>
			</c:if>
			<c:if test="${event.salle}">
				<tr>
					<td>Salle</td>
					<td>${event.salle}</td>
				</tr>
			</c:if>
			<c:if test="${event.matiere}">
				<tr>
					<td>Matiere</td>
					<td>${event.matiere}</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</c:if>