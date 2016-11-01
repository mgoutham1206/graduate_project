<%@page import="edu.nwmissouri.geoapp.model.TblInstructorsection"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test= "${ sessionScope.userdetailsinfo != null }">
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./GeneralInfoSideBarInst.jsp"%>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

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
if(session.getAttribute("userdetailsinfo") != null){
	sesUser = (TblUser)session.getAttribute("userdetailsinfo");
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	
<script type="text/javascript">
<!--
	//-->
	//var controllerUrl = "/client/generalinfo/";
	var controllerUrl = "/GeoApp/client/generalinfo/";
	$(document).ready(function() {
		$("#link_typesinst").addClass("active");
		$("#link_geninfoinst").addClass("active");
		$.ajax({
			type : "GET",
			url : controllerUrl + "types/all",
			data : "",
			//cache: false,
			success : function(data) {
				console.log(data);
				$('#mineralintro').empty();
				$('#mineralintro').append(data[0]['mineralintro']);
				$('#typesintro').empty();
				$('#typesintro').append(data[0]['typesintro']);
				$('#economic').empty();
				$('#economic').append(data[0]['economic']);
				$('#industrial').empty();
				$('#industrial').append(data[0]['industrial']);
				
			}
		});
	});
</script>
	
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style="padding-top: 50px;min-height: 452px;margin-left: 0px;">
	<!-- Main content -->
	<section class="content">
	
		<!-- START ACCORDION & CAROUSEL-->
		<!--   <h2 class="page-header">Bootstrap Accordion & Carousel</h2> -->
		<div class="row">
			<div class="col-md-12">
				<div class="box box-solid">
					<div class="box-header with-border">
						<h2 class="box-title" style="font-size: x-large; font-family: inherit; font-weight: bold;">Mineral Properties</h2>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="box-group" id="accordion">
							<!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne"> What is a mineral? </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="box-body" id="mineralintro">mineral</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseTwo"> Mineral Types </a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse">
									<div class="box-body" id="properties">
										<div class="row">
											<div class="col-md-12">
												<div class="box box-solid">
													<!-- /.box-header -->
													<div class="box-body">
														<div class="box-group" id="accordion1">
															<!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerOne"> Minerals: Economic and Industrial Uses </a>
																	</h4>
																</div>
																<div id="collapseInnerOne" class="panel-collapse collapse in">
																	<div class="box-body" id="typesintro">Minerals: Economic and Industrial Uses</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerTwo">Economic Minerals: The Clark Factor </a>
																	</h4>
																</div>
																<div id="collapseInnerTwo" class="panel-collapse collapse in">
																	<div class="box-body" id="economic">Clark Factor</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerThree">Industrial Minerals </a>
																	</h4>
																</div>
																<div id="collapseInnerThree" class="panel-collapse collapse in">
																	<div class="box-body" id="industrial">Industrial Minerals</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>		
				</div>
			</div>
		</div>
	</section>
</div>
</c:when>
	<c:otherwise>
		<c:redirect url="/view/login"/>
	</c:otherwise>
</c:choose>
<%@ include file="./Footer.jsp"%>