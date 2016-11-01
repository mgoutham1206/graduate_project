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
   
  
  
  
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"></link>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

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
          <div class="container">
		<div class="row">
		
		<div class="col-md-12">
		<br>
		<br>

     <br/>
            <br/>

            
           <div id="template"></div>
        
            <br><br><br>
            <p>THANK YOU !!</p>
            <p>AN ACTIVATION LINK WILL BE SENT TO YOUR EMAIL. PLEASE CHECK YOUR EMAIL FOR MORE INFO.
                
            </p>
            <br><br>
            <a href="${pageContext.request.contextPath}/view/login">Click here to go to Login Page</a> 
        
              
              </div>
	
	</div>
	</div>
	</div>
	
	
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
              
             
</body>
</html>