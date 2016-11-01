<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
th, td {
	padding: 5px;
}
</style>
	<section class="content" style="margin-left: 20%">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>

		<h2>Assignments</h2>
		<table border="1" style="width: 30%">
<th>Assignment ID</th>
					<th>Assignment Name</th>
			<c:forEach items="${assignments}" var="assignment">
				<tr>
					
				</tr>
				<tr>
					<td class="id"><c:out value="${assignment.getAssignID()}" /></td>
					<td><a
						href="viewSubmissionsByAssignId?assignmentId=${assignment.getAssignID()}"
						style="color: blue;"><c:out value="${assignment.getName()}" />
					</a> <%-- <a href="/GeoApp/viewAssign?sectionID=${assignment.getName()"><div id="${assignment.getName()}" href="#" class="well"></a>--%>
				</tr>
			</c:forEach>
		</table>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>