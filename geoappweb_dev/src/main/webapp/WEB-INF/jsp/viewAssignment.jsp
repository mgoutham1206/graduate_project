<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>


<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>

<style type="text/css">
.assign {
	text-align: centre
}
#assign1 {
	float:left; 
	font-size: 17px;
	margin: 20; 
	padding: 0; 
	list-style-type: none;
	display: inline; 
	padding: 0 0 10px 10px;
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	border-radius: 20px;
	padding: 15px 25px; 
	text-decoration: none; 
}
#assign1 a:hover { 
	background: #eee; 
	color: #000; 
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


			<!-- Content Header  (Page header) -->
			<section class="content-header">
				<h1>List of assignments:</h1>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->	
		 <table>
		<c:forEach items="${model.assignments}" var="assignment">
 			<tr>
 				<td>
 				<div class="assign">
 			        <p><c:out value="${assignment.name}"></c:out> 
			          <input type="button" value="View Details" name="view" onclick="window.location.href='openassignment?studentID=${model.studentID}&assignID=${assignment.assignID}'" />
			    	  <input type="button" value="View Progress" name="progress" onclick="window.location.href='viewprogress?studentID=${model.studentID}&assignID=${assignment.assignID}'"/>
			    	</p>
			    </div>   
			    </td>   
    		</tr>
    </c:forEach>
		 </table>
		 </section>
    </div>
    <%@ include file="./Footer.jsp" %> 
    
    </c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>