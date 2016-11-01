
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
  </head>
  
  <body class="hold-transition skin-green sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo" id="geoapplogo" style="height: 125px">
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
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Instructor</span>
                </a>
                
							<!-- Menu toggle button -->
							
			
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>

            </ul>
            
            
          </div>
          <center>
			<h3 style="color:white">Geographical Rock App.</h3><span style="color:white">->Learning the life of rock.</span>
							
								</center>
								
								<ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
			
							
								
              <!-- User Account Menu -->
              <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
              
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">General
								Information</span>
                </a>                
              </li>
              
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Assignment</span>
                </a>                
              </li>
              
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Quiz</span>
                </a>                
              </li>
              
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Geo-Map</span>
                </a>                
              </li>
              
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <i class="fa fa-graduation-cap"></i>
                  
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Instructor</span>
                </a>                
              </li>
              
            </ul>
            </div>
								
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
					<li class="active" role="presentation"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Types</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Properties</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Identification</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Rock
								Cycle</span></a></li>
				</ul>
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
		<c:if test="${!empty Section}">
			
			<div class="container">
        		<div class="row">
					<div class="col-md-3">
					</div>
					<div class="col-md-6">
					<br><br><br>
					<center><h1>All Sections</h1></center>
					<br><br><br>
					<c:forEach items="${Section}" var="sec">
					<a href="updateSection/${sec.sectionID}"><div id="${sec.sectionID}" href="#" class="well">
						<div>
							<center><h3><u>${sec.title}</u></h3></center>
							<span class="pull-left">Year:</span><span class="pull-left">${sec.year}</span>
							<%-- <span class="pull-right">Term:</span><span class="pull-right">${sec.termID}</span> --%><br>
							<p>${sec.basicdescription}</p>
						</div>
						</a>
					
					</div>
					<div class="col-md-3">
					</div>
					</c:forEach>				
			
			</c:if>
		
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









<html>
<head>

</head>

</html>
