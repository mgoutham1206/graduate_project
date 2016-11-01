<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


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
			<h1>Score Report</h1>
			</section>
			
<!-- Main content -->

			<section class="content">
				<!-- Your Page Content Here -->


				<div>
				
				<p> Hello,  
				<c:out value="${quizresultsmodel.User.name}!"></c:out> 
				
				<p>No. of Questions :
				<c:out value="${quizresultsmodel.questionsInQuiz}"></c:out> </p>
				
				
				<p>No. of Correct Answers : 
				<c:out value="${quizresultsmodel.scoreInQuiz}"></c:out> </p> 
				
				<p> Minimum percentage of marks to pass this Quiz : ${quizresultsmodel.minpercent}% </p>
				
				 <p>Your percentage of marks : 
				<c:out value="${quizresultsmodel.markspercent}%"></c:out> </p> 
				
				<h4>
				<c:out value="${quizresultsmodel.passorfail}"></c:out> </h4> 
				</div>
				
				<div> 
				<br></br>
				<input type="button" onclick= "retakeChecker()" value="Retake" />
				
				<input type="button"  onclick="window.location.href='/GeoApp/student/studentHome'"
					value ="Exit" />
				
				<!-- <input type="button"  onclick="window.location.href='/student/studentHome'"
					onmouseover="bigImg(this)" onmouseout="normalImg(this)"
				value ="Exit" />
				 -->
				
				</div>
				
	
			</section>
	
	</div> 



<%@ include file="./Footer.jsp" %>


<script>

function retakeChecker() { 
	
	var assignID = ${quizresultsmodel.assignID};
 	var studentID = ${quizresultsmodel.studentID};
 	
	$.ajax({
		type : "GET",
		url : "/GeoApp/Pool/checkattempts/"+studentID+"/"+assignID,
		data : "",
		success : function(data) {
			
			if (data == true) 
				{
				window.location.href='/GeoApp/Pool/home?studentID=${quizresultsmodel.studentID}&assignID=${quizresultsmodel.assignID}';
				
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