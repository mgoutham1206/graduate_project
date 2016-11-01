<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GeoApp</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="/GeoApp/template/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style 
        <link rel="stylesheet" href="/GeoApp/template/dist/css/AdminLTE.min.css">
        -->
<link rel="stylesheet" href="/GeoApp/template/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect.
        -->
<link rel="stylesheet"
	href="/GeoApp/template/dist/css/skins/skin-green.min.css">




<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"></link>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body class="hold-transition skin-green sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a class="logo" id="geoapplogo"
				style="height: 125px"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>Geo</b>App</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><img id="logopic"
					style="float: left; margin: 23px 10px 0 34px;"
					src="/GeoApp/template/pics/logo.png" alt="doc" height="100"
					width="100" /></span>
			</a>
			<!--  <a href="index2.html" class="logo" 
				> mini logo for sidebar mini 50x50 pixels
				<span class="logo-mini"><b>Geo</b><br />APP</span> logo for regular state and mobile devices
				<span class="logo-lg"></span>
			</a> -->

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->



						<!-- User Account Menu -->
				</div>
				<center>
					<h3 style="color: white">Geographical Rock App.</h3>
					<span style="color: white">->Learning the life of rock.</span>

				</center>

				<ul class="nav navbar-nav">
					<!-- Messages: style can be found in dropdown.less-->



					<!-- User Account Menu -->
			</nav>
		</header>


		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" style="padding-top: 125px">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu"
					style="height: 10%; float: bottom; margin-top: 50px">

					<!-- Optionally, you can add icons to the links -->

					<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- <form action="login/CreateInstructor" method="post"
		modelAttribute="CreateInstructorForm" enctype="multipart/form-data"> -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->

			<section class="content">

				<section class="content-header">
					<span id="pinfound" style="font-size: 250%;"> </span>
				</section>
				<form action="">

					<div id="loginDiv">
						<div>
							<c:set var="userInfo" value="${userInfo}" scope="page" />

							Hi "
							<c:out value="${userInfo.loginName}" />
							".<br /> This is your email :
							<c:out value="${userInfo.emailAddress}" />
							<br />
							<br />
							<br />
							<br />

						</div>
						<div style="text-align: center;">
						<table align="center">
						<tr><td align="right">
							<span>Temporary Password :</span>
							</td><td>
							 <input type="password"
								name= "tempPasswordTF" required id="tempPasswordTFId"
								onclick="messageChange()" /><br />
								</td><td></td>
								<tr>
								<td align="right">
								 <span>   New Password :</span>
							</td>
							<td>
							<input type="password" name="newPasswordTF" required
								id="newPasswordTFId" onclick="messageChange()" /><br />
								</td>
								<td><font color="red">*<font>Password should contain an Uppercase, lowercase and an Integer</td>
								</tr>
								<tr>
								<td align="right">
								 <span>  Confirm
								Password :
								</td><td></span> <input type="password" name="confirmPasswordTF"
								required id="confirmPasswordTFId" onclick="messageChange()" />
								</td><td></td>
								</tr>
								<tr>
								<td></td>
								<td><input type="button" value="Change" onclick="validTempPassword()" /><br /></td>
								</tr>
								</table>
								
								
							<input type="hidden" name="oldPasswordTF" required
								id="oldPasswordTFId"
								value=<c:out value="${userInfo.userPassword}"/> /> 
								<input type="hidden" name="userIDF" required id="userIDId"
								value=<c:out value="${userInfo.userID}"/> />
								
						</div>

						<div style="text-align: center; margin-top: 5em;"
							id="submitButton">
							

							<p id="succMessage"></p>
						</div>
					</div>
				</form>

				<script>
				
				window.onload = function() {
					document.getElementById("geoapplogo").href = window.location.pathname.substring(0,
																	window.location.pathname.indexOf('/instructor',0))+"/view/login";
				}
				
					function loadJSON(urlStr) {

						var flag;
						//var email = document.getElementById("emailTextField").value;
						var data_file = urlStr;
						//alert(data_file);
						var http_request = new XMLHttpRequest();

						http_request.onreadystatechange = function() {

							if (http_request.readyState == 4) {
								// Javascript function JSON.parse to parse JSON data
								var jsonObj = JSON
										.parse(http_request.responseText);
								alert(jsonObj);
								//console.log(jsonObj);
								flag = jsonObj;
							}
						}

						http_request.open("GET", data_file, false);
						http_request.send();

						return flag;
					}

					/*
					function validEmail() {
					var email = document.getElementById("emailTextField").value;
					if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
						//alert("You have entered an valid email address!")
						var flag = loadJSON(email);
						//alert(flag);
						if (flag) {

							$
									.ajax({

										url : "http://localhost:8080/geoapp/admin/ds/createInstructorSucceeded/"
												+ email + "/testadmin"
									});
							document.getElementById("succMessage").innerHTML = "The Instructor Premissions are granted to "
									+ email
									+ " <br/>Email has been sent to the instructor.";
						} else {
							document.getElementById("succMessage").innerHTML = "Email Already Existed.";
						}
					} else {
						document.getElementById("succMessage").innerHTML = "Invalid Email address.";
					}

					} */

					function validTempPassword() {
						var tempPassword = document
								.getElementById("tempPasswordTFId").value;
						var oldPassword = document
								.getElementById("oldPasswordTFId").value;
						var userID = document.getElementById("userIDId").value;
						if (tempPassword == oldPassword) {
							var newPassword = document
									.getElementById("newPasswordTFId").value;
							var confirmPassword = document
									.getElementById("confirmPasswordTFId").value;
							if (validatePassword(newPassword)) {
								if (newPassword == confirmPassword) {

									var urlString = window.location.pathname
											.substring(0,window.location.pathname.indexOf('/change',0))
											+ "/ds/PasswordUpdate/"+ newPassword + "/" + userID;
									$.ajax({

										url : urlString
									});
										
									document.getElementById("succMessage").innerHTML = "Password Changed Success.";
									alert("Password Changed Success. Please click OK to Go and Login.");
									window.location = window.location.pathname.substring(0,window.location.pathname.indexOf('/inst',0))+"/view/login";	
			      					
								} else {
									document.getElementById("succMessage").innerHTML = "The new Password <span style='color:red'>mismatched</span> with confirm password";
								}

							} else {
								document.getElementById("succMessage").innerHTML = "The Password strength is too weak. Password should atleast contains 1 from each of uppercase, lowercase and number. And the password length should be bewteen 6-20 characters";
							}
						} else {
							document.getElementById("succMessage").innerHTML = "The Temporary Password you entered is invalid.";
						}
					}

					function messageChange() {
						document.getElementById("succMessage").innerHTML = "";
					}
					
					function validatePassword(newPassword){
						var passwordPatternMatch = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
						if(newPassword.match(passwordPatternMatch)){
							return true;
						}
						return false;
					}
				</script>
			</section>
			<!-- /.content -->
		</div>
</body>
</html>
<%@ include file="./Footer.jsp"%>

