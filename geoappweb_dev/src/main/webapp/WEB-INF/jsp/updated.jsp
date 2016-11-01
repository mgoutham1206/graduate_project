 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>

<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">
 

<%@ include file="./SectionHeader.jsp"%>

<%@ include file="./InstructorSideBar.jsp" %>
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
  RequestDispatcher rd = request.getRequestDispatcher("redirect:/GeoApp/view/login");
  rd.include(request, response);
  out.println("Session has ended.  Please login.");
}
%> 
  
<div class="container">
<div class="row">
<div class="col-md-12">
<br><br><br><br><br><br>
	<center><h1>Updated Class Successfully.</h1></center>
</div>
</div>

</div>
</div>
<%@ include file="./Footer.jsp" %>
</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>
