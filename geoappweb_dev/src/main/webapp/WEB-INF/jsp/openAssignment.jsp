<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>

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
				<h1>Assignment Details:</h1>

			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
				
				<!-- -- Added function to check the no.of attempts on the Take Quiz link 
				-- Added highest score in Quiz 
			       -- updated by Krishna  4/21/16 -->
			       
			<a href="#" id=quiz onclick="retakeChecker()">Take Quiz</a>
				<p>
				<c:if test="${not empty assignmentmodel.MaxScore }">
					<b>Your highest score in the Quizzes taken : </b>
   					<c:out value="${assignmentmodel.MaxScore}"></c:out>
				</c:if>
				</p>
			
				<p>  
					<c:if test="${not empty assignmentmodel.Status }">
					<b>Quiz Status: </b>
   					<c:out value="${assignmentmodel.Status}"></c:out>
				</c:if>	
				</p>
				
				
				<p>
					<b>Assignment name:</b>
   					<c:out value="${assignmentmodel.assignment.name}"></c:out>
				</p>
				<p>
					<b>Description:</b>
  				 	<c:out value="${assignmentmodel.assignment.description}"></c:out>
  				 </p>
  				 <p>
					<b>Due date for submitting assignment:</b>
  				 	<c:out value="${assignmentmodel.assignment.due_date}"></c:out>
  				 </p>
  				 <p>
					<b>Point for phase one:</b>
  				 	<c:out value="${assignmentmodel.assignment.possiblepointsphase1}"></c:out>
  				 </p>
  				 <p>
					<b>Point for phase two:</b>
  				 	<c:out value="${assignmentmodel.assignment.possiblepointsphase2}"></c:out>
  				 </p>
  				 <p>
					<b>Point for phase three:</b>
  				 	<c:out value="${assignmentmodel.assignment.possiblepointsphase3}"></c:out>
  				 </p>
			<input type="button" value="View Progress" name="view" onclick="window.location.href='/GeoApp/student/viewprogress?studentID=${assignmentmodel.studentID}&assignID=${assignmentmodel.assignment.assignID}'">
		 </section>
		 
		 
		<!-- Changed code --> 
	
		 
		 
    </div>
    <%@ include file="./Footer.jsp" %>
    
    <script>
		function retakeChecker() { 
    	
    	var assignID = ${assignmentmodel.assignment.assignID};
     	var studentID = ${assignmentmodel.studentID};
     	
    	$.ajax({
    		type : "GET",
    		url : "/GeoApp/Pool/checkattemptsBeforeTakeQuiz/"+studentID+"/"+assignID,
    		data : "",
    		success : function(data) {
    			
    			if (data == true) {
    				window.location.href='/GeoApp/Pool/home?studentID=${assignmentmodel.studentID}&assignID=${assignmentmodel.assignment.assignID}';    				
    			}
    			else {
    				alert("You have exceeded the maximum attempts allowed");
    			}
    		}

    	});
      
    }
    
    </script>
    
    </c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>