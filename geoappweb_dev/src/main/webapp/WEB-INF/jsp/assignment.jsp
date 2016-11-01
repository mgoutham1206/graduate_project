<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
	function goToAssgnList() {
		location.href = "/GeoApp/view/instructor/openSection/listAssignments?secId=${secId}";
	}

	function getvalueDate() {

		var stringDate = document.getElementById("dateId").value.toString();
		var res = stringDate.split("-");
		var dateStringAppend = res[1] + "/" + res[2] + "/" + res[0];
		document.getElementById("hiddenDateId").value = dateStringAppend;
	}
	
	window.onload = function() {

	    document.getElementById("dateId").value = document.getElementById("hiddenDateId").value.substring(0, 10);
	
		var oldDate = document.getElementById("hiddenDateId").value;
		var oldDateObj = new Date(oldDate);
		if (oldDate == "") {
			oldDateObj = new Date();
		}

		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		var maxyyyy = today.getFullYear() + 1;
		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}
		today = yyyy + '-' + mm + '-' + dd;
		var todayDateObj = new Date(today);
		var maxDate = new Date();
		maxDate = maxyyyy + '-' + mm + '-' + dd;

		if (todayDateObj <= oldDateObj)
			document.getElementById("dateId").min = yyyy + '-' + mm + '-' + dd;
		else
			document.getElementById("dateId").min = document
					.getElementById("hiddenDateId").value;
		document.getElementById("dateId").max = maxyyyy + '-' + mm + '-' + dd;

		var possiblepointsphase1 = $('#possiblepointsphase1').val() + '';
		var possiblepointsphase2 = $('#possiblepointsphase2').val() + '';
		var possiblepointsphase3 = $('#possiblepointsphase3').val() + '';
		possiblepointsphase1 = possiblepointsphase1.replace(
				new RegExp('\.00$'), '');
		$('#possiblepointsphase1').val(possiblepointsphase1)

		possiblepointsphase2 = possiblepointsphase2.replace(
				new RegExp('\.00$'), '');
		$('#possiblepointsphase2').val(possiblepointsphase2)

		possiblepointsphase3 = possiblepointsphase3.replace(
				new RegExp('\.00$'), '');
		$('#possiblepointsphase3').val(possiblepointsphase3)
	};
</script>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
		<form:form method="POST"
			action="/GeoApp/view/instructor/openSection/updateAssignment?sectionId=${secId}&quizID=${assignment.getTblQuiz().getQuizID()}"
			modelAttribute="assignment" style="float: right; margin-right: 50%;">
			<table>
				<tr>
					<td>Assignment Name:</td>
					<td><form:input path="name"
							placeholder="Assignment Name" pattern="^[a-zA-Z]+*+[0-9]$"
							required="true" maxlength = "100"
							oninvalid="setCustomValidity('Assignment name should be alphanumeric Ex: Assignment 1')"
							onchange="try{setCustomValidity('')}catch(e){}" /></td>
							

				</tr>
				<tr>
					<td>Possible Points Phase1:</td>
					<td><form:input path="possiblepointsphase1"
							placeholder="Enter only integers" pattern="^[0-9][0-9]?$|^100$"
							required="true"
							oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integers Ex :10')"
							onchange="try{setCustomValidity('')}catch(e){}" /></td>
				</tr>
				<tr>
					<td>Possible Points Phase2:</td>
					<td><form:input path="possiblepointsphase2"
							placeholder="Enter only integers" pattern="^[0-9][0-9]?$|^100$"
							required="true"
							oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integers Ex :10')"
							onchange="try{setCustomValidity('')}catch(e){}" /></td>
				</tr>

				<tr>
					<td>Possible Points Phase3:</td>
					<td><form:input path="possiblepointsphase3"
							placeholder="Enter only integers" pattern="^[0-9][0-9]?$|^100$"
							required="true"
							oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integers Ex :10')"
							onchange="try{setCustomValidity('')}catch(e){}" /></td>
				</tr>
				<tr>
					<td>Is Active:</td>
					<td><form:radiobutton path="isActive" value="Y" /> Yes <form:radiobutton
							path="isActive" value="N" required="true" /> No</td>
				</tr>
				<tr>
					<td>Due Date:</td>
					<!--  new SimpleDateFormat("MM/dd/yyyy").format(new Date()); -->
					<td><input type="date" id="dateId" onchange="getvalueDate()"
						required="true" /> <form:input type="hidden" path="due_date"
							id="hiddenDateId" /></td>
				</tr>
				<tr>
					<td>Instructions:</td>
					<td><form:textarea style="resize:none" rows="5" cols="40"
							path="description" maxlength = "5000" placeholder="Maximum limit is 5000 characters"/></td>
				</tr>
				<tr>
					<td><form:hidden path="assignID"
							value="${assignment.getAssignID()}" /></td>
					<td><c:choose>
							<c:when test="${assignment.getAssignID() == 0}">
								<input type="submit" class="btn" value="Save" />
							</c:when>
							<c:otherwise>
								<input type="submit" class="btn" value="Update"
									onclick="getvalueDate()" />
							</c:otherwise>
						</c:choose> <input type="button" onclick="goToAssgnList()" class="btn"
						value="Cancel" /></td>
				</tr>
			</table>
		</form:form>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>