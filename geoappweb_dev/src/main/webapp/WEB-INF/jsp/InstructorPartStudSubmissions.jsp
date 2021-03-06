<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
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

	<script type="text/javascript">
		var assignmentID = ${assignmentId};

		$.ajax(
						{

							url : "/rest/instructor/findSubmissionsByAssignmentID?assignmentId="
									+ assignmentID,
						})
				.then(
						function(data, status, jqxhr) {

							//console.log(data);

							var submissionData = data;
							document.getElementById("myTable").innerHTML = "";
							document.getElementById("myTable").innerHTML = "<tr>"
								//	+ "<th>Submission ID</th>"
									+ "<th> StudentID</th>" +
									//"<th> IsAccepted </th>"
									"</tr>";

							for (var i = 0; i < submissionData.length; i++) {

								if(submissionData[i].split(",")[3] == '1'){
								
								document.getElementById("myTable").innerHTML += "<tr>"
									//	+ "<td class='id'>"
										//+ submissionData[i].split(",")[0]
									//	+ " </td>"
										+ "<td><a href='phase1?submissionID="
										+ submissionData[i].split(",")[0]
										+ "&assignmentID="+assignmentID+"'"
										+ "style='color: blue;'>"
										+ submissionData[i].split(",")[1]
										+ "</a></td> <td>"+submissionData[i].split(",")[2]+" </td></tr>";

							}
							}

						});

		function onSelectPhase(obj) {

		///	alert(obj.value);
		console.log(assignmentID+"************");
			
			$.ajax(
					{
						

						url : "/rest/instructor/findSubmissionsByAssignmentID?assignmentId="

							+ assignmentID,

								

					})
			.then(
					function(data, status, jqxhr) {

						console.log(data);

						var submissionData = data;
						document.getElementById("myTable").innerHTML = "";
						document.getElementById("myTable").innerHTML = "<tr>"
							//	+ "<th>Submission ID</th>"
								+ "<th> StudentID</th>" +
								//"<th> IsAccepted </th>"
								"</tr>";

						for (var i = 0; i < submissionData.length; i++) {
							console.log(submissionData[i].split(",")[0]);
							if(submissionData[i].split(",")[3] == obj.value){
							
							document.getElementById("myTable").innerHTML += "<tr>"
								//	+ "<td class='id'>"
									//+ submissionData[i].split(",")[0]
								//	+ " </td>"
									+ "<td><a href='phase"+obj.value+"?submissionID="
									+ submissionData[i].split(",")[0]
									+ "&assignmentID="+assignmentID+"'"
									+ "style='color: blue;'>"
									+ submissionData[i].split(",")[1]
									+ "</a></td> <td>"+submissionData[i].split(",")[2]+" </td></tr>";

						}
						}

					});

		}
	</script>

	<section class="content" style="margin-left: 20%">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>

		<%-- <select onchange="onSelectPhase(this)">

			<option value=1>Phase 1</option>
			<option value=2>Phase 2</option>
			<option value=3>Phase 3</option>


		</select>--%>

		<h2>Student Submisssion Phases</h2>
		<table border="1" style="width: 30%" id="myTable">


		</table>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%> 