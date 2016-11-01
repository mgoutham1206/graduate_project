<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test="${ sessionScope.userdetailsinfo != null }">

		<%@ include file="./AdminPageHeader.jsp"%>


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
					if (session.getAttribute("userdetailsinfo") != null) {
						sesUser = (TblUser) session.getAttribute("userdetailsinfo");
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
			<!-- Content Header (Page header) -->

			<section class="content">

				<section class="content-header">
					<span id="pinfound" style="font-size: 250%;"> </span>
				</section>
				<form action="">

					<div id="loginDiv" style="padding-bottom: 355px">Welcome
						Admin - ${userdetailsinfo.loginName}!!!</div>
				</form>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="./Footer.jsp"%>

	</c:when>
	<c:otherwise>
		<c:redirect url="/view/login" />
	</c:otherwise>
</c:choose>