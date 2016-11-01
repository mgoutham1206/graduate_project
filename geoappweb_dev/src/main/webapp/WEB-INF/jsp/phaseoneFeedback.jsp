<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>

<style type="text/css">
.lat {
	top: 60%; position: relative;
	}
.long {
	top: 70%; position: relative;
	}
.pic1 {	
	top: 40%; position: relative;
	}
.lat1 {
	top: 40%; position: relative;
	}
.lon1 {
	top: 40%; position: relative;
	}
.pic2 {
	top: 50%; position: relative;
	}

.pic3 {
	top: 60%; position: relative;
	}

.pic4 {
	top: 70%; position: relative;
	}

.pic5 {
	top: 80%; position: relative;
	}

.desc {
	top: 90%; position: relative;
	}

.submit {
	top: 95%; left: 50%; position: relative; font-size: medium;
	}
.comments {
	position: relative; font-size: medium;
	}
</style>

<script type="text/javascript">  

function imagedelete(imageID){
	
	 $.ajax({url : "/student/phaseone/deleteimage?imageID="+imageID}).always(function() {			
		 var image = document.getElementById(imageID);
		 image.parentNode.removeChild(image);
						var delBut = document.getElementById("del"+imageID);
						delBut.parentNode.removeChild(delBut);
		 });	
}	
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
			<h1>Phase 1-Rock Submission</h1>
			<h2>Feedback</h2>

		<form:form action="viewprogress" method="get"
			modelAttribute="phaseOneForm" enctype="multipart/form-data">
			
			<div class="pic1">
				<c:if test="${not empty phaseOneForm.imageIDs[0]}" >
				<img id="${phaseOneForm.imageIDs[0]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[0]}" height=100 width="100">
				</br>
				<c:out value="${phaseOneForm.evalComments[0]}"></c:out>
				</br>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[0] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[0] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:if>
			</div>
			<br/>
			<div class="pic2">
				<c:if test="${not empty phaseOneForm.imageIDs[1]}" >
				<img id="${phaseOneForm.imageIDs[1]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[1]}" height=100 width="100">
				</br>
				<c:out value="${phaseOneForm.evalComments[1]}"></c:out>
				</br>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[1] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[1] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:if>
			</div>
			<br/>
			<div class="pic3">
				<c:if test="${not empty phaseOneForm.imageIDs[2]}" >
				<img id="${phaseOneForm.imageIDs[2]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[2]}" height=100 width="100">
				</br>
				<c:out value="${phaseOneForm.evalComments[2]}"></c:out>
				</br>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[2] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[2] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:if>
			</div>
			<br/>
			<div class="pic4">
				<c:if test="${not empty phaseOneForm.imageIDs[3]}" >
				<img id="${phaseOneForm.imageIDs[3]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[3]}" height=100 width="100">
				</br>
				<c:out value="${phaseOneForm.evalComments[3]}"></c:out>
				</br>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[3] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[3] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:if>
			</div>
			<br/>
			<div class="pic5">
				<c:if test="${not empty phaseOneForm.imageIDs[4]}" >
				<img id="${phaseOneForm.imageIDs[4]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[4]}" height=100 width="100">
				</br>
				<c:out value="${phaseOneForm.evalComments[4]}"></c:out>
				</br>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[4] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[4] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:if>
			</div>
			<br/>
			<div class="lat">
				<p>Latitude:
				<c:out value="${phaseOneForm.latitude}"></c:out>
			</div>
			<br/>
			<div class="long">
				<p>Longitude:
				<c:out value="${phaseOneForm.longitude}"></c:out>
			</div>
			<br/>
			<div class="desc">
				<p>Description:
				<c:out value="${phaseOneForm.description}"></c:out>
				</p>
			</div>
			<br/>
			<div class="comments">	
				<p>
				<c:out value="${phaseOneForm.comment}"></c:out>
				</p>
			</div>
			<br/>
			<form:input type="hidden" path="assignID" />
			<form:input type="hidden" path="userId" />
			<form:input type="hidden" path="submitId" />
			<div class="submit">
				<input type="submit" value="Okay"
					style="background-color: lightgreen" />
			</div>
			<br/>

		</form:form>
		</section>
	</div>
<%@ include file="./Footer.jsp" %>

</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>