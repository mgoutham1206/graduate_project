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

	<script type="text/javascript">
	
	$(document).ready(function() {
		$.getJSON("/GeoApp/rest/instructor/findSubmissionsByAssignmentID?assignmentId="
				+ assignmentID, function(data) {
			console.log(data);
			var submissionData = data;
			for (var i = 0; i < submissionData.length; i++){
			console.log(submissionData[i].split(",")[0]+"NameName")
			
			if(submissionData[i].split(",")[0]==(submissionId)){
				console.log(submissionData[i].split(",")[2]+"jsfhasjfasfsdfsdjfns")
				document.getElementById("stuid").innerHTML=submissionData[i].split(",")[1]
				document.getElementById("stuname").innerHTML=submissionData[i].split(",")[2];
			}
			
		}
			
			
	});
	});
		var submissionId = ${submissionID};
		var assignmentID =  '<%= request.getAttribute("assignmentID") %>';

		$.ajax(
						{

							url : "/GeoApp/rest/instructor/getAllInstructorPhases?submissionID="
									+ submissionId,
						})
				.then(
						function(data, status, jqxhr) {

							//console.log(data);

							var submissionData = data;
							document.getElementById("myTablePhase1").innerHTML = "";
							document.getElementById("myTablePhase1").innerHTML = "<tr>"
									+ "<th>Phase</th>"
									+ "<th> IsAccepted </th>" +
								//	+"<th> IsAccepted </th>"+
									"</tr>";

							for (var i = 0; i < submissionData.length; i++) {

								//if(submissionData[i].split(",")[3] == '1'){
								
								if(submissionData[i].split(",")[1]=="yes"){
									document.getElementById("myTablePhase1").innerHTML += "<tr>"
									//	+ "<td class='id'>"
										//+ submissionData[i].split(",")[0]
									//	+ " </td>"
										+ "<td><a href='phase"+submissionData[i].split(",")[0]+"?submissionID="
										+ submissionData[i].split(",")[3]+"&assignmentID="+${assignmentID}+"'"
										+ "style='color: blue;'>Phase-"
										+ submissionData[i].split(",")[0]
										+ "</a></td>"
										+"<td>"+submissionData[i].split(",")[2]+"</td>"
										+"</tr>";

							}
							}

						});
		
		$( document ).ready(function() {
			 $( "#back" ).click(function() {
				 console.log();
				 window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID") %>';
			 });
		 });
		<%--function onSelectPhase(obj) {

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
								+ "<th> Students </th>" +
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

		} --%>
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

		<h2></h2>
		<a href="/GeoApp/instructor/viewAssign?sectionID=${section.getSectionID()}" style="font-size: 20px;">Assignments</a> >
	<a href="/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId=${assignmentID}" style="font-size: 20px;">Submissions</a> 
 > <span style="font-size: 20px;">Phases</span>
		<table border="1" style="width: 30%" id="myTablePhase1">
<h4>Student Name: <b><span id="stuname"></span></b></h4>
			<h4>Student ID: <b><span id="stuid"></span></b></h4>
		</table></br>
		<!--<div class="form-group">
			<input type="button" style="float: left" class="btn btn-primary" id="back"
					value="Back" /> 
		</div>-->
		
		<table border="1" style="width: 30%" id="myTablePhase2">

		</table></br>
		<table border="1" style="width: 30%" id="myTablePhase3">

		</table></br>
		<h4>Total Grade: <b><span id="totalpoints"></span></b></h4>
		<script>
			window.onload = function() {

			

			$.getJSON("/GeoApp/rest/instructor/getTotalPoints/"
					+ submissionId, function(data){
				console.log("*************++++++++********")
			    console.log("Application Data: " + data);

			    //var jsonData = JSON.parse(data);
			    var totalpoints=0.0;
				for(var i=0;i<data.length;i++){
					//document.getElementById("eachphase").innerHTML = data[i].points;
					totalpoints = totalpoints+data[i].points;
			    console.log("Each Phase Points "+data[i].points);
			    
				}
				console.log("TotalHAHAHAHAHAHHA"+totalpoints);
				
				//$('#totalpoints').val(totalpoints);
				document.getElementById("totalpoints").innerHTML = totalpoints;
			});
		}
		</script>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%> 