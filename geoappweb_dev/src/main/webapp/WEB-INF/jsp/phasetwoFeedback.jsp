<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>


<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>


<style type="text/css">
.add{

}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
</script>

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
				<h1>Phase 2-Mineral Submissions</h1>
				<h2>Add mineral details</h2>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
		
		<div class="rname">

			<br />
			<script type="text/javascript">
			var form=${phaseTwoForm};
			var size=document.form.mineralID.length;
			if(size!=0){
				$('.tab').show;
			}
			</script>
			<div class="tab">
		
			<table border="1">
			
			<tr>
			<th>Luster</th>
			<th>Hardness</th>
			<th>Streak Color</th>
			<th>Cleavage</th>
			<th>Specific Gravity</th>
			<th>Color</th>
			<th>Mineral Name</th>
			<th>Actions</th>
			</tr>
			<c:forEach items="${phaseTwoForm.minerals}" var="mineral" varStatus="status">
			<tr id="rowcount">
				<td width="12.5%"><c:out value="${mineral.luster} "/></td>
				<td width="12.5%"><c:out value="${mineral.hardness} "/></td>
				<td width="12.5%"><c:out value="${mineral.streakColor} "/></td>
				<td width="12.5%"><c:out value="${mineral.geometry} "/></td>
				<td width="12.5%"><c:out value="${mineral.specificGravity} "/></td>
				<td width="12.5%"><c:out value="${mineral.color} "/></td>
				<td width="12.5%"><c:out value="${mineral.minName} "/></td>
				<td width="12.5%"><a href='/GeoApp/student/phasetwofeedbackview?action=view&tempMinId=${mineral.tempMinId}'>View Detailed Feedback </a></td>
			</tr>
			</c:forEach>
			</table>
			
			</div>
			<br/>
			
			<c:if test="${not empty mineral.submitId}">
			<form:form action="viewprogress" modelAttribute="mineral" method="post">
			
				<h1><c:out value="${mineral.minName}"></c:out></h1> 
				<ol>
					<li>Luster: <c:out value="${mineral.luster}"></c:out>
						<br/>
						<c:out value="${mineral.evaluation[0].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[0].isAccepted == 'Y'}">
								Luster is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[0].isAccepted == 'N'}">
								Luster is not accepted.
							</c:when>
						</c:choose>
						<br/><br/></li>

					<li>Hardness: <c:out value="${mineral.hardness}"></c:out>
					<br/>
					<c:out value="${mineral.evaluation[1].comments}"></c:out>
					<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[1].isAccepted == 'Y'}">
								Hardness is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[1].isAccepted == 'N'}">
								Hardness is not accepted.
							</c:when>
						</c:choose>
						<br/><br/>
					</li>

						
					<li>Steak Color: <c:out value="${mineral.streakColor}"></c:out>
					<br/>
						<c:out value="${mineral.evaluation[2].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[2].isAccepted == 'Y'}">
								Streak Color is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[2].isAccepted == 'N'}">
								Streak Color is not accepted.
							</c:when>
						</c:choose>
						<br /><br/>
					</li>
					
					<li>Cleavage: <c:out value="${mineral.geometry}"></c:out>
					<br/>
						<c:out value="${mineral.evaluation[3].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[3].isAccepted == 'Y'}">
								Cleavage is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[3].isAccepted == 'N'}">
								Cleavage is not accepted.
							</c:when>
						</c:choose>
						<br/><br/>
					</li>

					<li>Specific Gravity: <c:out value="${mineral.specificGravity}"></c:out>
					<br/>
						<c:out value="${mineral.evaluation[4].comments}"></c:out>
						<br/>
					<c:choose>
							<c:when test="${mineral.evaluation[4].isAccepted == 'Y'}">
								Specific Gravity is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[4].isAccepted == 'N'}">
								Specific Gravity is not accepted.
							</c:when>
						</c:choose>
						<br/><br/>
					</li>
					
					<li>Color: <c:out value="${mineral.color}"></c:out>
					<br/>
						<c:out value="${mineral.evaluation[5].comments}"></c:out>
						<br/>
							<c:choose>
							<c:when test="${mineral.evaluation[5].isAccepted == 'Y'}">
								Mineral Color is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[5].isAccepted == 'N'}">
								Mineral Color is not accepted.
							</c:when>
						</c:choose>
							<br/><br/>
					</li>
					
					<li>Mineral Name: 
						<c:out value="${mineral.minName}"></c:out>	
						<br/>
						<p  id="minnameeval">
						<br/>
						<c:out value="${mineral.evaluation[6].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[6].isAccepted == 'Y'}">
								Mineral Name is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[6].isAccepted == 'N'}">
								Mineral Name is not accepted.
							</c:when>
						</c:choose>
						<br/><br/>
						 </p><br /> 
						</li>
						</ol>
						<form:input type="hidden" path="submitId" />
						<form:input type="hidden" path="mineralId" />
						<form:input type="hidden" path="tempMinId"/>
						
			</form:form>
			</c:if>
			<form:input type="hidden" path="studentID"/>
			<input type="button" value="Okay" onclick="window.location.href='/GeoApp/student/viewprogress?studentID=${studentID}&assignID=${phaseTwoForm.assignID}'"/>
			<br/><br/>
		<c:out value="${comment}"/>
		</div>
		</section>
		</div>
		<%@ include file="./Footer.jsp" %>
		
		</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>