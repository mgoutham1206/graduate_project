<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css' />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description"
	content="Simple Template #2 from simpletemplates.org" />
<meta name="keywords" content="simple #2, template, simpletemplates.org" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/facultyDetails.css" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>General Information</title>
</head>
<c:choose>
	<c:when test="${ sessionScope.userdetailsinfo != null }">
		<%@ include file="./AdminPageHeader.jsp"%>

		<%
			response.setHeader("Cache-Control", "no-cache");

					//Forces caches to obtain a new copy of the page from the origin server
					response.setHeader("Cache-Control", "no-store");

					//Directs caches not to store the page under any circumstance
					response.setDateHeader("Expires", 0);

					//Causes the proxy cache to see the page as "stale"
					response.setHeader("Pragma", "no-cache");
					//HTTP 1.0 backward enter code here

					String loginName = "";
					int sesId = 0;
					TblUser sesUser = null;
					if (session.getAttribute("userdetailsinfo") != null) {
						sesUser = (TblUser) session.getAttribute("userdetailsinfo");
						sesId = sesUser.getUserID();
						loginName = sesUser.getLoginName();
					}
					//String userName = session.getAttribute("userdetailsinfo");
					if (null == sesUser) {
						//request.setAttribute("Error", "Session has ended.  Please logenter code herein.");
						RequestDispatcher rd = request.getRequestDispatcher("redirect:/GeoApp/view/login");
						rd.include(request, response);
						out.println("Session has ended.  Please login.");
					}
		%>
		<body>
			<!-- <form action="login/CreateInstructor" method="post"
		modelAttribute="CreateInstructorForm" enctype="multipart/form-data"> -->

			<div class="content-wrapper">
				<!-- Content Header (Page header) -->

				<section class="content"> <section class="content-header">
				<span id="pinfound" style="font-size: 250%;"> </span> </section>

				<div id="loginDiv">
					<div class="marginStyle"></div>
					<div style="margin: 20%;">
						<div style="text-align: center;">
							<span>Faculty Address E-Mail :</span> <input type="text"
								name="emailAddress" required id="emailTextField"
								onclick="messageChange()" onkeypress="runKeyFun(event)" />
						</div>

						<div style="text-align: center; margin-top: 5em;"
							id="submitButton">
							<input type="button" value="Grant Permission"
								onclick="validEmail()" /><br />

							<p id="succMessage"></p>
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<script>
				function runKeyFun(e) {
					if (e.keyCode == 13) {
						validEmail()
					}
				}
				function loadJSON(email) {

					var flag;
					//var email = document.getElementById("emailTextField").value;
					var data_file = window.location.pathname.substring(0,
							window.location.pathname.lastIndexOf('/'))
							+ "/ds/validateEmail/"
							//"geoapp/admin/ds/validateEmail/"
							+ email;
					//alert(data_file);
					var http_request = new XMLHttpRequest();

					http_request.onreadystatechange = function() {

						if (http_request.readyState == 4) {
							// Javascript function JSON.parse to parse JSON data
							document.getElementById("succMessage").innerHTML = http_request.responseText;
							var jsonObj = JSON.parse(http_request.responseText);
							//alert(jsonObj);
							flag = jsonObj;
						}
					}

					http_request.open("GET", data_file, false);
					http_request.send();

					return flag;
				}

				function validEmail() {
					var email = document.getElementById("emailTextField").value;
					if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
							.test(email)) {
						//alert("You have entered an valid email address!")
						var flag = loadJSON(email);
						//alert(flag);
						if (flag) {

							$.ajax({

								url : window.location.pathname.substring(0,
										window.location.pathname
												.lastIndexOf('/'))
										+ "/ds/createInstructor/"
										+ email
										+ "/testadmin"
							});
							document.getElementById("succMessage").innerHTML = "The Instructor Premissions are granted to "
									+ email
									+ " <br/>Email has been sent to the instructor.";
						} else {
							document.getElementById("succMessage").innerHTML = "Email Already Existed.";
							//	alert("Email Already Existed.");
						}
					} else {
						document.getElementById("succMessage").innerHTML = "Invalid Email address.";
						//alert( "Invalid Email address.");
					}

				}
				function messageChange() {
					document.getElementById("succMessage").innerHTML = "";
				}
			</script>
		</body>
		<%@ include file="./Footer.jsp"%>

	</c:when>
	<c:otherwise>
		<c:redirect url="/view/login" />
	</c:otherwise>
</c:choose>
</html>