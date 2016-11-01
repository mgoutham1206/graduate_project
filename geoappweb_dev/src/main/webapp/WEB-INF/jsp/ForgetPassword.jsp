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
			<a class="logo" id="geoapplogo" style="height: 125px"> <!-- mini logo for sidebar mini 50x50 pixels -->
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
				<br> <br> <br> <br> <br> <br> <br>

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

						<div style="text-align: center;">

							<center>
								<caption>
									<b>Forget Password?</b> Please enter the following details
									correctly to create new Password
								</caption>
								<br>
								<hr>

								<table>


									<tr style="border-bottom: 5px">
										<td><span>Enter Email :</span></td>
										<td><input type="text" name="emailNameTF"
											required="required" id="emailTFId" onclick="messageChange()"
											placeholder="Eg:john@hill.com"
											onkeypress="checkThisKeyAction(event)" /></td>
									</tr>
									<tr style="border-top: 5px">
										<td><span>Enter Login name: </span></td>
										<td><input type="text" name="loginNameTF"
											required="required" id="loginNameTFTd"
											onclick="messageChange()" onchange="return checkloginData()"
											placeholder="john123" onkeypress="checkThisKeyAction(event)" /></td>
									</tr>
								</table>
				</form>
				</center>
		</div>

		<div style="text-align: center; margin-top: 5em;" id="submitButton">
			<input type="button" value="Change" onclick="validEmailAndLogin()" /><br />

			<p id="succMessage"></p>
		</div>
	</div>
	</form>

	<script>
		function checkThisKeyAction(e) {
			if (e.keyCode == 13) {
				validEmailAndLogin();
			}
		}

		function messageChange() {
			document.getElementById("succMessage").innerHTML = "";
		}

		function checkloginData() {
			var x = document.getElementById("loginNameTFTd").value;
			if (x == null || x == "") {

				return false;
			}
			return true;
		}

		function validEmailAndLogin() {
			var flagLog = checkloginData();

			var email = document.getElementById("emailTFId").value;

			var flagEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
					.test(email);
			document.getElementById("succMessage").innerHTML = "";
			if (!flagLog) {

				document.getElementById("succMessage").innerHTML = "Login Name Is incorrect. "
			}
			if (!flagEmail) {
				document.getElementById("succMessage").innerHTML += "<br>Email Is incorrect. "
			}

			if (flagLog && flagEmail) {
				document.getElementById("succMessage").innerHTML = "Will do something";
				var x = document.getElementById("loginNameTFTd").value;
				
				sendData(email, x);
			} else {
				document.getElementById("succMessage").innerHTML += "<br>Please correct the above mistake(s)."
			}
		}

		function sendData(email,loginName) {
				var http_request = new XMLHttpRequest();
				http_request.onreadystatechange = function() {
					if (http_request.readyState == 4) {
						// Javascript function JSON.parse to parse JSON data
						//var jsonObj = JSON.parse();
						flag = http_request.responseText;
						
					}
				}
				
				http_request.open("GET", window.location.pathname.substring(0,window.location.pathname.indexOf('/view', 0))+ "/ds/forgetPasword/UserDetails/check?email="
						+ email
						+ "&loginName="
						+ loginName, false);
				http_request.send();

				process(flag);
			}
		
		function process(data) {
				
				if (data == "F") {
					document.getElementById("succMessage").innerHTML = "Found your record.";
					generateEmailProcess();
				}
				else if(data == "LNNF"){
					document.getElementById("succMessage").innerHTML = "Found your record. But Its seems your Login name is not correct.";
				}
				else {
					document.getElementById("succMessage").innerHTML = "No record found.";
				}

			}
		
		function generateEmailProcess(){
			var email = document.getElementById("emailTFId").value;
			var http_request = new XMLHttpRequest();
			http_request.onreadystatechange = function() {
				if (http_request.readyState == 4) {
					// Javascript function JSON.parse to parse JSON data
					//var jsonObj = JSON.parse();
					flag = http_request.responseText;
				}
			}
			
			http_request.open("GET", window.location.pathname.substring(0,window.location.pathname.indexOf('/view', 0))+ "/view/ds/sendEmail?email="
					+ email, false);
			http_request.send();
			alert(flag);
			processEmailSending(flag);
		}
		
		function processEmailSending(flag) {
			alert("In Progress Email Sending");
			if(flag){
				document.getElementById("succMessage").innerHTML = "Please check your Email To change your password.";
			}else {
				document.getElementById("succMessage").innerHTML = "Our Server is too slow. Please try again.";
			}
		}
		
	</script>
	</section>
	<!-- /.content -->
	</div>
</body>
</html>
<%@ include file="./Footer.jsp"%>

