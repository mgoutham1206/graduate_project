<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content" style="margin-left: 20%">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
				
		<a href="/GeoApp/view/instructor/openSection/newAssignment?sectionId=${section.sectionID}"
			style="color: blue;">New Assignment</a><br><br>
		<table border="1">
			<tr>
				<td style="font-weight: bold;">Assignment Name</td>
				<td style="font-weight: bold;">Due Date</td>
				<td style="font-weight: bold;">Is Active</td>
				<td style="font-weight: bold;">Edit/Post</td>
			</tr>
			<c:forEach items="${assignments}" var="assignment">
				<tr>
					<td class="title" align="center"><span> <c:out
								value="${assignment.getName()}" />
					</span></td>
					<td class="description" align="center"><span> <c:out
								value="${assignment.getDue_date()}" /></span></td>
					<td class="isactive" align="center"><span> <c:out
								value="${assignment.getIsActive()}" /></span></td>
					<td class="description" align="center"><a
						href="/GeoApp/view/instructor/openSection/openAssignment?assignId=${assignment.getAssignID()}&sectionId=${section.sectionID}">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>