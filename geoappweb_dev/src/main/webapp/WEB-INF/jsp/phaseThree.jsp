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
				<h1>Phase 3-Rock Information</h1>
				<p><b>Submit Rock details</b></p>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
    <form:form action="phasethree/submit" modelAttribute="phaseThreeForm" method="post">
    <div class="rname">
        <p>Rock Type:
        <form:select path="rockType">
             <form:option value="NONE" label="Select"/>
            <form:option value="Metamorphic"></form:option>
            <form:option value="Igneous"></form:option>
        </form:select>
        </p>
        <p>Rock Name:
            <form:input type="text" path="rockName" size="35"></form:input>
         </p>   
         <br/>
         <c:out value="${comment}"/>
         
        <br/><br/>
        <form:input type="hidden" path="submitId" ></form:input>
        <form:input type="hidden" path="studentID" />
		<form:input type="hidden" path="assignID" />
             <input type="submit" value="Submit"></input>

         
    </div>
        
</form:form>
</section>
</div>
   		<%@ include file="./Footer.jsp" %>
   		
   		</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>