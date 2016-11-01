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
<<style>
#rocks{
display: none;
}
</style>	
<script type="text/javascript">
<!--
	//-->
	//var controllerUrl = "/client/generalinfo/";
	var controllerUrl = "/GeoApp/client/generalinfo/";

	$(document).ready(function() {
		$("#link_rocks").addClass("active");
		$("#link_geninfo").addClass("active");
		$("#sedi").click(function(){
		       $("#rocks").show();
		       $.ajax({
					type : "GET",
					url : controllerUrl + "/Rocktypes/all",
					data : "",
					//cache: false,
					success : function(data) {
						console.log(Object.keys(data[0]));
						$('#sedimentary').empty();
						$('#sedimentary').append(data[0]['sedimentary']);
						
						}
				});
		   });
		$("#ign").click(function(){
			$.ajax({
				type : "GET",
				url : controllerUrl + "/Rocktypes/all",
				data : "",
				//cache: false,
				success : function(data) {
					console.log(Object.keys(data[0]));
					
					$('#igneous').empty();
					$('#igneous').append(data[0]['igneous']);
					
					}
			});
		});
				
			
		$("#meta").click(function(){
			$.ajax({
				type : "GET",
				url : controllerUrl + "/Rocktypes/all",
				data : "",
				//cache: false,
				success : function(data) {
					console.log(Object.keys(data[0]));
					$('#metamorphic').empty();
					$('#metamorphic').append(data[0]['metamorphic']);
					}
			});	
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
						<h2 class="box-title" style="font-size: x-large; font-family: inherit; font-weight: bold;">Rock Types</h2>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div id="Information">
                    <h2>Please click on the rock type in the image to read about it.</h2>
                </div>
                <table>
                    <tr>
                        <td rowspan="2">
                            <img src="/GeoApp/template/pics/rockcycle.png" alt="" usemap="#Map" style="float: left;" height="500" width="500"/>
                <map name="Map" id="Map">
                    <area alt="" id="ign" title="" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"  shape="rect" coords="36,302,163,358" />
                    <area alt="" id="meta" title=""  data-toggle="collapse" data-parent="#accordion" href="#collapseThree" shape="rect" coords="282,302,477,365" />
                    <area alt="" id="sedi" title=""  data-toggle="collapse" data-parent="#accordion" href="#collapseOne" shape="rect" coords="145,2,324,52" />
                </map>
                        </td>
                        <td style="float: top" rowspan="2">
                        <div id="rocks">
                            <div class="box-group" id="accordion">
							<!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
							<div class="panel box box-success" id="acc1">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne"> Sedimentary </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="box-body" id="sedimentary">sedimentary</div>
								</div>
							</div>
							<div class="panel box box-success" id="acc2">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseTwo"> Igneous </a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse">
									<div class="box-body" id="igneous">igneous</div>
								</div>
							</div>
							<div class="panel box box-success" id="acc3">
								<div class="box-header with-border">
									<h4 class="box-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseThree"> Metamorphic </a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse">
									<div class="box-body" id="metamorphic">metamorphic</div>
								</div>
							</div>
							</div>
						</div>	
                        </td>
                    </tr>
                </table>
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