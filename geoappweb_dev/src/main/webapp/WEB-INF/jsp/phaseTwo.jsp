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
			<th colspan="2">Actions</th>
			</tr>
			<c:forEach items="${phaseTwoForm.minerals}" var="mineral" varStatus="status">
			<tr id="rowcount">
				<td><c:out value="${mineral.luster}"/></td>
				<td><c:out value="${mineral.hardness}"/></td>
				<td><c:out value="${mineral.streakColor}"/></td>
				<td><c:out value="${mineral.geometry}"/></td>
				<td><c:out value="${mineral.specificGravity}"/></td>
				<td><c:out value="${mineral.color}"/></td>
				<td><c:out value="${mineral.minName}"/></td>
				<td><a href='/GeoApp/student/phasetwoUpdate?action=edit&tempMinId=${mineral.tempMinId}'>Edit</a></td>
				<td><a href='/GeoApp/student/phasetwoUpdate?action=delete&tempMinId=${mineral.tempMinId}'>Delete</a></td>
			</tr>
			</c:forEach>
			</table>
			
			</div>
			<br/>
			<input type="button" id="add" value="Add mineral" onclick="window.location.href='/GeoApp/student/phasetwoUpdate?action=add&tempMinId='"/>
			
			
			<c:if test="${not empty mineral.submitId}">
			<form:form action="/GeoApp/student/phasetwo/savemineral" modelAttribute="mineral" method="post">
			
				<h1><c:out value="${mineral.minName}"></c:out></h1> 
				<ol>
					<li>Luster: <form:select path="luster">
							<form:option value="Select"></form:option>
							<form:options items="${luster}" itemValue="lusterName" itemLabel="lusterName" path="lusterName"/>
						</form:select> 
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

					<li>Hardness: <form:select path="hardness">
					<form:option value="none" />Select
					<br/>
					<c:forEach var="hard" items="${hardness}">
        				<form:option value="${hard.hardnessName} (${hard.hardnessMin}-${hard.hardnessMax})"><c:out value="${hard.hardnessName} (${hard.hardnessMin}-${hard.hardnessMax})"/></form:option>
   						 </c:forEach>
<%-- 					<form:options items="${hardness}" itemValue="hardnessTypeID" itemLabel="hardnessName" path="hardnessName"/> --%>
					</form:select>
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
						<br/>
					</li>
						
					<li>Steak Color: <form:select path="streakColor">
							<form:option value="Select"></form:option>
							<form:options items="${streakcolor}" itemValue="streakColorName" itemLabel="streakColorName" path="streakColorName"/>
						</form:select> 
						<br/>
						<c:out value="${mineral.evaluation[2].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[2].isAccepted == 'Y'}">
								StreakColor is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[2].isAccepted == 'N'}">
								StreakColor is not accepted.
							</c:when>
						</c:choose>
						<br /><br/>
					</li>
					<%-- <li>Cleavage:<form:select path="${geometry}" > 
						<form:radiobutton id="geo" path="geometry" value="Y" onclick="funset()"/>Yes
						<form:radiobutton path="geometry" value="N"/>No
						<script type="text/javascript">
						function funset(){
						var g=$("input[name=geo]")
						if(g)
							{
							$("#set").show();
							}
						}
						</script>

						<form:select  id="set" path="geometry" display="hide">
						<form:option value="0" > 0 </form:option>
						<form:option value="1" > 1 </form:option>
						<form:option value="2" > 2 </form:option>
						
						</form:select>

						<form:option value="Select:Sets, angle"></form:option>
						<c:forEach var="geometry" items="${cleavage}">
        				<form:option value=" ${geometry.geometrySets} , ${geometry.angleType}"><c:out value=" ${geometry.geometrySets} , ${geometry.angleType}"/></form:option>
   						 </c:forEach>
							<form:options items="${cleavage}" itemValue="geometryTypeID" itemLabel="geometrySets angleType " path="geometrySets angleType"/>
						</form:select>
						<c:out value="${mineral.evaluation[3].comments}"></c:out>
					</li> --%>
					
					<li>Cleavage: <form:select path="geometry" >
						<form:option value="Select"></form:option>
						<c:forEach var="geometry" items="${cleavage}">
        				<form:option value=" ${geometry.geometrySets} sets, ${geometry.angleType} angle"><c:out value=" ${geometry.geometrySets} sets, ${geometry.angleType}"/></form:option>
   						 </c:forEach>
<%-- 							<form:options items="${cleavage}" itemValue="geometryTypeID" itemLabel="angleType geometrySets" path="angleType geometrySets"/> --%>
						</form:select>
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
						<br/>
					</li>
					<li>Specific Gravity: <form:select path="specificGravity">
							<form:option value="Select"></form:option>
							<form:options items="${specificgravity}" itemValue="specificGravityName" itemLabel="specificGravityName" path="specificGravityName"/>
						</form:select>
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
						<br/>
					</li>

					<li>Color: <form:input type="text" path="color" value=""
							size="35" pattern = "[a-zA-Z]*" required= "true" />
							<c:out value="${mineral.evaluation[5].comments}"></c:out>
							<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[5].isAccepted == 'Y'}">
								Color is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[5].isAccepted == 'N'}">
								Color is not accepted.
							</c:when>
						</c:choose>
						<br/>
					</li>
					
					<li>Mineral Name: <form:select path="minName">
							<form:option value="Select"></form:option>
							<form:option value="APATITE"></form:option>
							<form:option value="AUGITE"></form:option>
							<form:option value="BARITE"></form:option>
							<form:option value="BAUXITE"></form:option>
							<form:option value="BIOTITE"></form:option>
							<form:option value="BORNITE"></form:option>
							<form:option value="CALCITE"></form:option>
							<form:option value="CHALCOPYRITE"></form:option>
							<form:option value="CHLORITE"></form:option>
							<form:option value="CORUNDUM"></form:option>
							<form:option value="DOLOMITE"></form:option>
							<form:option value="EPIDOTE"></form:option>
							<form:option value="FLUORITE"></form:option>
							<form:option value="GARNET"></form:option>
							<form:option value="GOETHITE"></form:option>
							<form:option value="GRAPHITE"></form:option>
							<form:option value="GYPSUM"></form:option>
							<form:option value="HALITE"></form:option>
							<form:option value="HEMATITE"></form:option>
							<form:option value="HORNBLENDE"></form:option>
							<form:option value="KAOLINITE"></form:option>
							<form:option value="LIMONITE"></form:option>
							<form:option value="MAGNETITE"></form:option>
							<form:option value="MUSCOVITE"></form:option>
							<form:option value="NEPHELINE"></form:option>
							<form:option value="OLIVINE"></form:option>
							<form:option value="ORTHOCLASE"></form:option>
							<form:option value="PHLOGOPITE"></form:option>
							<form:option value="PLAGIOCLASE"></form:option>
							<form:option value="PYRITE"></form:option>
							<form:option value="PYRRHOTITE"></form:option>
							<form:option value="QUARTZ"></form:option>
							<form:option value="SERPENTINE"></form:option>
							<form:option value="SPHALERITE"></form:option>
							<form:option value="TALC"></form:option>
							<form:option value="TOURMALINE"></form:option>

						</form:select>
						<p  id="minnameeval">
						<c:out value="${mineral.evaluation[6].comments}"></c:out>
						<br/>
						<c:choose>
							<c:when test="${mineral.evaluation[6].isAccepted == 'Y'}">
								Mineral name is accepted.
							</c:when>
							<c:when test="${mineral.evaluation[6].isAccepted == 'N'}">
								Mineral name is not accepted.
							</c:when>
						</c:choose>
						 </p><br /> 
						</li>
						</ol>
						<form:input type="hidden" path="submitId" />
						<form:input type="hidden" path="mineralId" />
						<form:input type="hidden" path="tempMinId"/>
						
						<br/><br/>
						<input type="submit" value="Save Mineral" />
						<br/><br/>
			</form:form>
			</c:if>
			<input type="button" value="Submit" onclick="window.location.href='/GeoApp/student/phasetwo/submit'"/>
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