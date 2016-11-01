<!DOCTYPE html>
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
<link rel="stylesheet" href="/GeoApp/template/bootstrap/css/bootstrap.min.css">
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
   
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>   
	
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>



<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">

	   <!-- Main Header -->
      <header class="main-header">

        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/view/login" class="logo" id="geoapplogo" style="height: 125px">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>Geo</b>App</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img id="logopic"
					style="float: left; margin: 23px 10px 0 34px;"
					src="/GeoApp/template/pics/logo.png" alt="doc" height="100" width="100" /></span>
        </a>
       <!--  <a href="index2.html" class="logo" 
				> mini logo for sidebar mini 50x50 pixels
				<span class="logo-mini"><b>Geo</b><br />APP</span> logo for regular state and mobile devices
				<span class="logo-lg"></span>
			</a> -->

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
			
							
								
              <!-- User Account Menu -->
              
            
            
          </div>
          <center>
			<!-- <h3 style="color:white; font-size: xx-large;">Geographical Rock App.</h3><span style="color:white; font-style: italic;">->Learning the life of rock.</span> -->
          
          <h3 style="color:white; font-size: xx-large;">Learning the life of rock</h3>
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
			<br><br><br><br><br><br><br>

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu"
					style="height: 10%; float: bottom; margin-top: 50px">

					<!-- Optionally, you can add icons to the links -->
					
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>   
		
		 <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <!-- <section class="content-header">
          <h1>
            Page Header
            <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
            <li class="active">Here</li>
          </ol>
        </section> -->  
        

        <!-- Main content -->
        <section class="content">

          <!-- Your Page Content Here -->
         <br/>
         <br/>
         <br/>
         <br/>
         
         <div class="row">
         	<div class="col-md-2"></div>
         	<div class="col-md-6">
         	<h3><strong>Please provide your details</strong></h3>
         	</div>
         	<div class="col-md-4"></div>
         </div>
         <div class="row">
			<div class="col-md-2">
			
			</div>
			<div class="col-md-6">
				<form id="submitbtn" method="post" class="form-horizontal">
					 <label>Full Name:</label><input title="FullName must be 3 to 50 characters, includes only alphabets and can include space between alphabets." type="text" placeholder="Ex: FirstName LastName" class="form-control" id="name" name ="uname" pattern="[a-zA-Z ]{3,50}" required /><br>
					 <label>Login Name:</label><input title="must contain 4 to 30 alphanumeric characters and must not contain spaces,special characters." type ="text" placeholder="loginName must be unique" class="form-control" id="loginName" name="lname" required pattern="\w{4,30}"/><br>
					 <label>Password:</label> <input title="Password must be 8 to 30 characters, including UPPER/lowercase and Numbers" type ="password" placeholder="must be at least 8 characters, including UPPER/lowercase & numbers" class="form-control" id="password" name ="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}"/><br>
					 <label>Confirm Password:</label> <input title="Confirm Password must be same as Password" type ="password" placeholder="Confirm Password must be same as Password" class="form-control" id="confirmpassword" name ="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}"/><br>
					 
					 <label>Email Address:</label> <input title="Enter a valid email" type="email" placeholder="Enter your emailID Here...Ex: john@gmail.com" class="form-control" id="email" name="mid" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"/><br>
					 <input type="text" value="<%= request.getParameter("section") %>" id="section" hidden >
					<center> <input type="submit" class="btn btn-lg btn-primary center" id="submit" value="Submit"/></center>
					 </form>
			</div>
			 <br/>
			 <br/>
			 <br/>
			 <br/>
			 <br/>
			 
	          <p id=warning style="color: #9F6000"></p>
	          <br/>
	          <br/>
	          <br/>
	          <br/>
	          <br/>
	          <br/>
	          <br/>
	          <p id=pwdwarning style="color: #9F6000"></p>
	          <br/>
	          <br/>
	          <br/>
	          <p id=warningmail style="color: #9F6000"></p>
			<div class="col-md-2">
			</div>
			
        </div>
	<p id=sucmsg></p>
	
	</section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">
        <!-- Default to the left -->
			<strong>GeoApp © 2015<a href="http://www.nwmissouri.edu/"> NWMSU</a>.
			</strong> All rights reserved.
			 </footer>

   
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.1.4 -->
	<script src="/GeoApp/template/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="/GeoApp/template/bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/GeoApp/template/dist/js/app.min.js"></script>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. Slimscroll is required when using the
             fixed layout. -->
              
              <script>
              
                $( '#submitbtn' ).submit(function(event) {
                	event.preventDefault();	
                	document.getElementById("sucmsg").innerHTML = "Page is loading....Please give us some time";
      			//alert($('#name').val()+$('#loginName').val()+$('#password').val()+$('#email').val());
      			// console.log( "ready!" );	
      			//alert($('#section').val());  
      			
      			var tempPassword = document
								.getElementById("password").value;
			    var tempconfirmPassword = document
								.getElementById("confirmpassword").value;
			    if(tempPassword == tempconfirmPassword){
			    	 var data ={
	      						'name' :$('#name').val(),
	      						'loginName' : $('#loginName').val()+','+$('#section').val(),
	      						'userPassword' : $('#password').val(),						
	      						'emailAddress' : $('#email').val()
	      						
	      				};
	      				console.log(window.location.pathname.substring(0,window.location.pathname.indexOf('/confirm',0))+"/ds/submit");
	      				console.log(window.location.pathname.indexOf('/confirm',0));
	      				$.ajax({
	      				url : window.location.pathname.substring(0,window.location.pathname.indexOf('/confirm',0))+"/ds/submit",
	      				data : JSON.stringify(data),
	      				type : "POST",				
	      				contentType: 'application/json',
	      				dataType: "text",
	      				beforeSend : function(xhr) {
	      					xhr.setRequestHeader("Accept", "application/json");
	      					xhr.setRequestHeader("Content-Type", "application/json");
	      				},
	      				
	      				success : function(data) {
	      					console.log(data+"Testing");
	      					//alert("Enrolled Successfully");
	      					if(data=="LET"){
	      						document.getElementById("warning").innerHTML = " ";
	      						window.location = window.location.pathname.substring(0,window.location.pathname.indexOf('/confirm',0))+"/thankyou";
	      						$.ajax({ url : window.location.pathname.substring(0,window.location.pathname.indexOf('/student',0))+"/GeoApp/instructor/ds/countExceeded?sectionId="+$('#section').val(),});
	      					}else if(data=="LEF"){
	      						//document.getElementById("sucmsg").innerHTML = "";
	      						document.getElementById("sucmsg").innerHTML = "loginName & EmailID Already exists, They must be Unique.";
	      						document.getElementById("pwdwarning").innerHTML = "";
	      					}else if(data=="LF"){
	      						document.getElementById("sucmsg").innerHTML = "";
	      						document.getElementById("warning").innerHTML = "loginName Already exists, loginName must be Unique.";
	      						document.getElementById("warningmail").innerHTML = "";
	      						document.getElementById("pwdwarning").innerHTML = "";
	      					}
	      					
	      					else if(data=="EF"){
	      						document.getElementById("sucmsg").innerHTML = "";
	      						document.getElementById("warningmail").innerHTML = "EmailID Already exists, EmailAddress must be Unique.";
	      						document.getElementById("warning").innerHTML = "";
	      						document.getElementById("pwdwarning").innerHTML = "";
	      					}
	      						      						
	      						},
	      				 
	      			    complete: function () {
	      			    	 
	      			    }
	      			});
	      				event.preventDefault();
			    	
			    } else {
			    	document.getElementById("pwdwarning").innerHTML = "Password & confirm password are not matching..Try again";
			    	document.getElementById("sucmsg").innerHTML = "";
			    	document.getElementById("warning").innerHTML = "";
			    	document.getElementById("warningmail").innerHTML = "";
			    	
			    	
			    }
      			
      							
      			
      		});			   
                   
             
              </script>
              
</body>
</html>