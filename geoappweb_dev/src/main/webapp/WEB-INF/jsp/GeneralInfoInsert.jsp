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
<style type="text/css">
#save {
	display: none;
}
</style>
<script type="text/javascript">
<!--
	//-->

	//var controllerUrl = "/client/generalinfo/";
	var controllerUrl = "/GeoApp/client/generalinfo/";
	$(document).ready(
			function() {
				$("#link_insert").addClass("active");
				$("#link_geninfo").addClass("active");
				$("#metallic").click(
						function() {
							
							$("#save").show();
							identify();
						});
				$("#nonmetallic").click(
						function() {
							
							$("#save").show();
							identify();
						});
			});


</script>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style="padding-top: 50px;min-height: 452px;margin-left: 0px;">
	<!-- Main content -->
	<section class="content">
		<div th:if="${message}">
			<h2 th:text="${message}" />
		</div>

		<div>
			<form method="POST" enctype="multipart/form-data" action="/GeoApp/client/generalinfo/upload">
				<h3>Please select the table to insert data :</h3>
				<input type="radio" id="metallic" name="collections"
					value="metallicluster"> Metallic luster table <br /> <input
					type="radio" id="nonmetallic" name="collections"
					value="nonmetallicluster"> Non-Metallic luster table <br />
					<input type="radio" id="types" name="collections"
					value="types"> Mineral Types <br />
					<input type="radio" id="properties" name="collections"
					value="properties"> Mineral Properties <br />
					<input type="radio" id="rocks" name="collections"
					value="rocks"> Rock types <br />
					<br/>
				<div id="save">
				<table>
					<tr>
						<td>File to upload:</td>
						<td><input type="file" name="file" /></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Upload" /></td>
					</tr>
				</table>
				</div>
			</form>
		</div>

		<div>
			<ul>
				<li th:each="file : ${files}" th:text="${file}"></li>
			</ul>
		</div>
		<h3 id="result"></h3>
	</section>






</div>

</c:when>
	<c:otherwise>
		<c:redirect url="/view/login"/>
	</c:otherwise>
</c:choose>
<%@ include file="./Footer.jsp"%>