
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">


<%@ include file="./SectionHeader.jsp"%>

<%@ include file="./InstructorSideBar.jsp" %>

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
  RequestDispatcher rd = request.getRequestDispatcher("redirect:/GeoApp/view/login");
  rd.include(request, response);
  out.println("Session has ended.  Please login.");
}
%>
            
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
					<center><h1 >All Sections</h1></center>
					<br><br><br>
					<c:forEach items="${Section}" var="sec">
					<a href="updateSection/${sec.sectionID}"><div id="${sec.sectionID}" href="#" class="well">
						<div>
							<center><h3><u style="color:red">${sec.title}</u></h3></center>
							<a class="pull-right" href="deleteSectionByID/${sec.sectionID}">Delete</a>
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
		<!-- /.content-wrapper -->

<%@ include file="./Footer.jsp" %>
</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>



















