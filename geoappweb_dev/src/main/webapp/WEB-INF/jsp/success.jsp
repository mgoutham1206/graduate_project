	<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function goToAssgnList() {
		location.href = "/GeoApp/view/instructor/openSection/listAssignments?secId=${secId}";
	}
	</script>
	<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">
	<form action="">
		
		
		<div style="float: right; margin-right: 50%; margin-top: 10%; font-size: xx-large;">
			THANK YOU !!!
			<br>
			ASSIGNMENT  
			<c:choose>
				<c:when test="${assignID == 0}">
					POSTED
				</c:when>
				<c:otherwise>
					UPDATED
				</c:otherwise>
			</c:choose> SUCCESSFULLY
			<br><br>
			<input type="button" onclick="goToAssgnList()" class="btn"
			value="Return to Assignments" />
		</div>	
	</form>

	</section>
	</div>
		
	<%@ include file="./Footer.jsp"%>