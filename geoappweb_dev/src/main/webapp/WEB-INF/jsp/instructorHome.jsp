<%@page import="edu.nwmissouri.geoapp.model.TblInstructorsection"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test= "${ sessionScope.userdetailsinfo != null }">
<%@ include file="./InstructorHomeHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp"%>
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
	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
		<div style="width: 50%; margin: 10px auto;">

			<table style="width: 100%;">
				<tr class="content">
					<td>
					<br/>
					
						<div id="loginDiv" >
						<h1>Welcome <c:out value="${userdetailsinfo.getName()}" />!!!<br /> Sections are below</h1>
						<br />
						
						
							<table border="1" style="font-size: 20px"  >
								<tr>
									<!--  <td style="font-weight: bold;">Section ID</td> -->
									<td style="font-weight: bold;">Title</td>
									<td style="font-weight: bold;">Basic Description</td>
									<td style="font-weight: bold;">Year</td>
								</tr>
								<c:forEach items="${sections}" var="section">
									<tr>
										<!--  <td>${section.sectionID}</td>	-->									
										<td><a href="/GeoApp/view/instructor/openSection?secId=${section.getSectionID()}">
												${section.getTitle()} </a></td>
												<td class="description"><span> <c:out
													value="${section.getBasicdescription()}" />
										</span></td>
										<td>${section.year}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>
	</c:when>
	<c:otherwise>
		<c:redirect url="/view/login"/>
	</c:otherwise>
</c:choose>