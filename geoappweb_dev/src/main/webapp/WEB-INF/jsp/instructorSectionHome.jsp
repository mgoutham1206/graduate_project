<%@page import="edu.nwmissouri.geoapp.model.TblStudent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test= "${ sessionScope.userdetailsinfo != null }">
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
		<form action="">

			<div id="loginDiv" style="font-size: xx-large;" class="col-md-6 ">
				Welcome
				<c:out value="${userdetailsinfo.getName()}" />
				!!!<br /> Section Details

				<table class="table " style="margin-top: 12px;">

					<tr>
						<td style="font-weight: bold;">Title</td>
						<td><c:out value="${section.getTitle()}" /></td>
					</tr>
					<tr>
						<td style="font-weight: bold;">Basic Description</td>
						<td><c:out value="${section.getBasicdescription()}" /></td>
					</tr>
					<tr>
						<td style="font-weight: bold;">Expected No Of Students</td>
						<td><c:out value="${section.getExpectedNoofStudents()}" /></td>
					</tr>
					<tr>
						<td style="font-weight: bold;">Year</td>
						<td><c:out value="${section.getYear()}" /></td>
					</tr>
				</table>

			</div>
		</form>
		<br /> <br /> <br /> <br />


		<table class="table" style="margin-top: 12px;">
			<c:set var="count" value="0" scope="page" />

			<caption>
				<h3>Students Enrolled</h3>
			</caption>

			<tr>
				<!--  <td style="font-weight: bold;">S.NO</td>
				<td style="font-weight: bold;">StudentID</td> -->
				<td style="font-weight: bold;">Student Name</td>
				<td style="font-weight: bold;">Email Address</td>
				<td style="font-weight: bold;">Active Status</td>
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="user" items="${EnrolleduserList}">


				<tr>
					<!--  <td><c:out value="${count + 1}" /></td> -->
					<input type="hidden" id="studentid-${count}"
						value=<c:out value="${user.getUserID()}" /> />
				<!-- 	<td><c:out value="${user.getUserID()}" /></td> -->
					<td><c:out value="${user.getName()}" /></td>
					<td><c:out value="${user.getEmailAddress()}" /></td>

					<!-- <td><c:out value="${EnrolledstudentList[count].isActive }" /> </td> -->

					<c:choose>
						<c:when
							test="${(EnrolledstudentList[count].isActive=='Y') || (EnrolledstudentList[count].isActive=='y')}">
							<td><input type="checkbox" class="modifiedList"
								id="rowCheckID_${count}" checked></td>
						</c:when>
						<c:otherwise>
							<td><input type="checkbox" class="modifiedList"
								id="rowCheckID_${count}"></td>
						</c:otherwise>
					</c:choose>


				</tr>




				<c:set var="count" value="${count + 1}" scope="page" />

			</c:forEach>
		</table>
		<input type="button" class="btn btn-md btn-primary center"
			id="btnstatus" value="ChangeStatus" onclick="sendDataToUpdate()" />
		<input type="hidden" id="totalStudCountID"
			value="<c:out value="${count}" />">
		<p id="message" />
</div>



</section>
<!-- /.content -->
</div>
<script type="text/javascript">
	function generateData() {
		//alert("generating Data")
		var studentIdGen = "studentid-";
		var rowId = "rowCheckID_";
		var totalStudentCount = document.getElementById("totalStudCountID").value;
		var dataArray = new Array();
		for (var i = 0; i < totalStudentCount; i++) {
			var tempStuId = studentIdGen + i;
			var tempRowId = rowId + i;

			var tempData = document.getElementById(tempStuId).value + "QQ";

			if (document.getElementById(tempRowId).checked) {
				dataArray[i] = tempData + "Y";
			} else {
				dataArray[i] = tempData + "N";
			}
		}

		return dataArray;
	}

	function sendDataToUpdate() {
		var dataArray = generateData();

		/* for(var i = 0; dataArray.length; i++){
			console.log(dataArray[i]);
		} */
		$
				.ajax(
						{
							url : window.location.pathname
									.substring(
											0,
											window.location.pathname
													.indexOf(
															'/instructor/',
															0))
									+ "/ds/UpdateStudents?dataArray="
									+ dataArray,

						})
				.then(
						function(data, status, jqxhr) {

							//console.log(data + "***************");
							//alert("Enrolled Successfully");
							if (data) {
								document.getElementById("message").innerHTML = "Students Activation/Deactivation done Successfully!";

							} else {

								document.getElementById("message").innerHTML = "Some error, Try agaian";
							}

						});

		event.preventDefault();

	}
</script>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>
</c:when>
	<c:otherwise>
		<c:redirect url="/view/login"/>
	</c:otherwise>
</c:choose>