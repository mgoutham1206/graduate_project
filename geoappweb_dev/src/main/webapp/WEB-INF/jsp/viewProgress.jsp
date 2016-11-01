<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>

<style type="text/css">
.text {
      position: relative;
      top: 50%;
      left: 50%;
      transform:translate(-50%,-50%);
      color: #fff;
    }
 #phase1{
 		position:relative;
 		
 }
</style>

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

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Progress of Assignment:
				<c:out value="${progressmodel.assignmentName}"></c:out>
				</h1>
			</section>

			<!-- Main content -->
						<section class="content">
			<!-- Your Page Content Here -->			
			
        <div id="phase1">
        	<c:choose>
        		<c:when test="${progressmodel.Phase1 == 'Y'}">
            		 <c:out value="Phase 1-Completed."></c:out>
            		 <br/>
            		 <c:out value="Continue to Phase 2."></c:out>
            		 <br/>
            		 <c:out value="Points: ${progressmodel.phase1Points}"></c:out>
            		 <br/>
            		 <a href="phaseoneFeedback?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Feedback for Phase 1</a>
            		 <br/>
             	</c:when>
             	<c:otherwise>
             		<a href="phaseone?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Phase1</a>
             	</c:otherwise>
             </c:choose>
        </div>
        <div id="phase2">
        	<c:choose>
				<c:when test="${progressmodel.Phase2 == 'Y'}">
            		<c:out value="Phase 2-Completed."></c:out>
            		<br/>
            		<c:out value="Continue to Phase 3."></c:out>
            		<br/>
            		<c:out value="Points: ${progressmodel.phase2Points}"></c:out>
            		<br/>
            		<a href="phasetwofeedback?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Feedback for Phase 2</a>
            	</c:when>
            	<c:when test="${progressmodel.Phase1 == 'Y'}">
            		<a href="phasetwo?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Phase 2</a>
            	</c:when>
            	<c:otherwise>
            		<a>Phase 2 - Complete phase 1</a>
            	</c:otherwise>
             </c:choose>
             <br/>
        </div>
        <div id="phase3">
        <c:choose>
            	<c:when test="${progressmodel.Phase3 == 'Y'}">
            		<c:out value="Phase 3-Completed."></c:out>
            		<br/>
            		<c:out value="Completed the assignment."></c:out>
            		<br/>
            		<c:out value="Points: ${progressmodel.phase3Points}"></c:out>
            		<br/>
            		<a href="phasethreefeedback?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Feedback for Phase 3</a>
            		<br/>
            		<c:out value="Total Points: ${progressmodel.phase1Points + progressmodel.phase2Points + progressmodel.phase3Points}"></c:out>
					<input type="button" value="Assignments" onclick="window.location.href='viewassignment?studentID=${progressmodel.studentID}'"/>
            	</c:when>
            	    <c:when test="${progressmodel.Phase2 == 'Y'}">
            		<a href="phasethree?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Phase3</a>
            	</c:when>
            	<c:otherwise>
            		<a>Phase 3 - Complete phase 2</a>
            	</c:otherwise>
			</c:choose>
             <br/>
        </div>
	</section>
    </div>
    <%@ include file="./Footer.jsp" %>
    
    </c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>