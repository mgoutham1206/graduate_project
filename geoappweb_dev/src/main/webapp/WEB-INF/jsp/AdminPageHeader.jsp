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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  </head>

  <body class="hold-transition skin-green sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">

        <!-- Logo -->
        <a href="/GeoApp/geoapp/admin/welcomeAdmin" class="logo" id="geoapplogo" style="height: 140px">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>Geo</b><br>App</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><img id="logopic"
					style="border: 4px solid #ffffff;border-radius: 60px;float: left;margin: 10px 10px 0 34px;"
					src="/GeoApp/template/pics/logo.png" alt="doc" height="100" width="100" href=""/></span>
        </a>
       <!--  <a href="index2.html" class="logo" 
				> mini logo for sidebar mini 50x50 pixels
				<span class="logo-mini"><b>Geo</b><br />APP</span> logo for regular state and mobile devices
				<span class="logo-lg"></span>
			</a> -->

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          
          
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
                  <span class="hidden-xs">Admin - ${userdetailsinfo.loginName}</span>
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
                      <a href = "/GeoApp/view/login" class="btn btn-default btn-flat" >Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>

            </ul>
            
            
          </div>
          <center>
          <!-- <h3 style="color:white; font-size: xx-large;">Geographical Rock App.</h3><span style="color:white; font-style: italic;">->Learning the life of rock.</span> -->
          
          <h3 style="color:white; font-size: xx-large;">Learning the life of rock</h3>
			
			</center>
								
								<ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
			
							
								
              <!-- User Account Menu -->
              <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                   <li class="dropdown user user-menu">
                   <a href="/GeoApp/geoapp/admin/createInstructor" onclick="selectMenu(this)"><i class="glyphicon glyphicon-list-alt">
                   </i><span class="hidden-xs" style="font-size: larger;">Create Instructor</span>
                   </a>
                   </li>  
              
            </ul>
            </div>
								
        </nav>
      </header>
      