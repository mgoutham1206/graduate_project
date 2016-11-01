<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>
<%@ include file="./GeneralInfoSideBar.jsp"%>
<!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
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
  RequestDispatcher rd = request.getRequestDispatcher("redirect:/view/login");
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
		$("#link_properties").addClass("active");
		$("#link_geninfo").addClass("active");
		$.ajax({
			type : "GET",
			url : controllerUrl + "property/id",
			data : "",
			//cache: false,
			success : function(data) {
				console.log(Object.keys(data[0]));
				$('#luster').empty();
				$('#luster').append(data[0]['luster']);
				$('#hardness').empty();
				$('#hardness').append(data[0]['hardness']);
				$('#color').empty();
				$('#color').append(data[0]['color']);
				$('#streak').empty();
				$('#streak').append(data[0]['streak']);
				$('#cleavage').empty();
				$('#cleavage').append(data[0]['cleavage']);
				$('#fracture').empty();
				$('#fracture').append(data[0]['fracture']);
				$('#specificGravity').empty();
				$('#specificGravity').append(data[0]['sg']);
				$('#diapheneity').empty();
				$('#diapheneity').append(data[0]['diapheneity']);
				$('#magnetism').empty();
				$('#magnetism').append(data[0]['magnetism']);
				$('#effervescenceInAcid').empty();
				$('#effervescenceInAcid').append(data[0]['effervescenceInAcid']);
				$('#taste').empty();
				$('#taste').append(data[0]['taste']);
				$('#crystalForm').empty();
				$('#crystalForm').append(data[0]['crystalForm']);
				$('#feel').empty();
				$('#feel').append(data[0]['feel']);
				$('#smell').empty();
				$('#smell').append(data[0]['smell']);
				
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
											href="#collapseOne"> Luster </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="box-body" id="luster">Luster</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseTwo"> Hardness </a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse">
									<div class="box-body" id="hardness">Hardness</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseThree"> Color </a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse">
									<div class="box-body" id="color">Color</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseFour"> Streak </a>
									</h4>
								</div>
								<div id="collapseFour" class="panel-collapse collapse">
									<div class="box-body" id="streak">Streak</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseFive"> Cleavage </a>
									</h4>
								</div>
								<div id="collapseFive" class="panel-collapse collapse">
									<div class="box-body" id="cleavage">Cleavage</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseSix"> Fracture </a>
									</h4>
								</div>
								<div id="collapseSix" class="panel-collapse collapse">
									<div class="box-body" id="fracture">Fracture</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseSeven"> Specific Gravity </a>
									</h4>
								</div>
								<div id="collapseSeven" class="panel-collapse collapse">
									<div class="box-body" id="specificGravity">Specific
										Gravity</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseEight"> Diapheneity </a>
									</h4>
								</div>
								<div id="collapseEight" class="panel-collapse collapse">
									<div class="box-body" id="diapheneity">diapheneity</div>
								</div>
							</div>
							<div class="panel box box-success">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseNine"> Properties </a>
									</h4>
								</div>
								<div id="collapseNine" class="panel-collapse collapse">
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
																			href="#collapseInnerOne"> magnetism </a>
																	</h4>
																</div>
																<div id="collapseInnerOne" class="panel-collapse collapse in">
																	<div class="box-body" id="magnetism">magnetism</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerTwo"> effervescenceInAcid </a>
																	</h4>
																</div>
																<div id="collapseInnerTwo" class="panel-collapse collapse in">
																	<div class="box-body" id="effervescenceInAcid">effervescenceInAcid</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerThree"> taste </a>
																	</h4>
																</div>
																<div id="collapseInnerThree" class="panel-collapse collapse in">
																	<div class="box-body" id="taste">taste</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerFour"> crystalForm </a>
																	</h4>
																</div>
																<div id="collapseInnerFour" class="panel-collapse collapse in">
																	<div class="box-body" id="crystalForm">crystalForm</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerFive"> feel </a>
																	</h4>
																</div>
																<div id="collapseInnerFive" class="panel-collapse collapse in">
																	<div class="box-body" id="feel">feel</div>
																</div>
															</div>
															<div class="panel box box-success">
																<div class="box-header with-border">
																	<h4 class="box-title">
																		<a data-toggle="collapse" data-parent="#accordion1"
																			href="#collapseInnerSix"> smell </a>
																	</h4>
																</div>
																<div id="collapseInnerSix" class="panel-collapse collapse in">
																	<div class="box-body" id="smell">smell</div>
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
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col -->
		</div>
	</section>

</div>

 </c:when>
<c:otherwise>
<c:redirect url="/GeoApp/view/login" />
</c:otherwise>
</c:choose>
<%@ include file="./Footer.jsp"%>