<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.nwmissouri.geoapp.model.TblSubmission"%>
<%@ page import="edu.nwmissouri.geoapp.model.TblStudent"%>
<%@ page import="edu.nwmissouri.geoapp.model.TblUser"%>
<%@ page import="edu.nwmissouri.geoapp.repository.UserRepository"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="java.util.Base64"%>
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<aside class="main-sidebar">

	<!-- side bar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Side bar Menu -->
		<ul class="sidebar-menu"
			style="height: 10%; float: bottom; margin-top: 50px">

			<!-- Optionally, you can add icons to the links -->
			<li  role="presentation"><a
				href="/GeoApp/client/geomap/instructor/<c:out value="${userInfo.userID}"/>?rockType=A"><i class="fa fa-link"></i> <span
					style="font-size: larger">All</span></a></li>
			<li  role="presentation"
				style="margin-top: 20px"><a href="/GeoApp/client/geomap/instructor/<c:out value="${userInfo.userID}"/>?rockType=I"><i
					class="fa fa-link"></i> <span style="font-size: larger">Igneous</span></a></li>
			<li  role="presentation"
				style="margin-top: 20px"><a href="/GeoApp/client/geomap/instructor/<c:out value="${userInfo.userID}"/>?rockType=S"><i
					class="fa fa-link"></i> <span style="font-size: larger">Sedimentary</span></a></li>
			<li  role="presentation"
				style="margin-top: 20px"><a href="/GeoApp/client/geomap/instructor/<c:out value="${userInfo.userID}"/>?rockType=M"><i
					class="fa fa-link"></i> <span style="font-size: larger">Metamorphic</span></a></li>
			<li role="presentation" style="margin-top: 20px"><a href="/GeoApp/client/geomap/instructor/changeClassAccess/<c:out value="${userInfo.userID}"/>"><i
					class="fa fa-link"></i> <span style="font-size: larger">Change
						Class Access </span></a></li>
		</ul>
		<!-- /.side bar-menu -->
	</section>
	<!-- /.side bar -->
</aside>
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
	<c:set var="userInfoId" value="${userInfo.userID}" scope="page" />
	<%-- --%>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-8">
				<span id="pinfound" style="font-size: 150%;"> Change Pin
					Visibility for Students</span> <input type="hidden" id="tempUserId"
					value=<c:out value="${userInfoId}"/> />
			</div>
			<div class="col-sm-4">
				<span id="pageHeadInfo" style="font-size: 150%;"> </span>
			</div>
		</div>
	</section>
	<section class="content">
		<form name="changeclass" onchange="react()">
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr class="rowHead">
							<th>Select</th>
							<th>Latitude</th>
							<th>Longitude</th>
							<th>Rock Name</th>
							<th>Rock Type</th>
							<th>Rock Image</th>
						</tr>
					</thead>
					<c:set var="count" value="0" scope="page" />
					<c:forEach items="${sectionKeyValueForSubmission}"
						var="sectionInfo">
						<c:set var="sectionName" value="${sectionInfo.key}" scope="page" />
						<tbody>
							<tr>
								<td align="center" colspan="6"
									style="color: blue; font-style: oblique; font-size: 150%;">Section:
									<c:out value="${sectionName}" />
								</td>
							</tr>

							<c:forEach items="${sectionInfo.value}" var="sectionValueInfo">

								<c:set var="count" value="${count + 1}" scope="page" />
								<c:set var="rockName" value="${sectionValueInfo.rockName}"
									scope="page" />
								<c:set var="rockType" value="${sectionValueInfo.rockType}"
									scope="page" />
								<c:set var="createdByUser" value="${sectionValueInfo.createdBy}"
									scope="page" />

								<c:forEach items="${sectionValueInfo.tblImagesubmissions}"
									var="valuelist" varStatus="innerLoop">
									<c:set var="imagepic" value="${valuelist.image}" scope="page" />
									<input type="hidden" id="imageRecId_${count}"
										value="${valuelist.imageID}">
									<c:choose>
										<c:when test="${valuelist.isBest=='Y'}">
											<tr class="rowInfo">
												<c:choose>
													<c:when test="${valuelist.isShow=='Y'}">
														<td align="center"><input type="checkbox"
															class="modifiedList" id="rowCheckID_${count}" checked></td>
													</c:when>
													<c:otherwise>
														<td align="center"><input type="checkbox"
															class="modifiedList" id="rowCheckID_${count}"></td>
													</c:otherwise>
												</c:choose>

												<%
													String createdBy = (String) pageContext.getAttribute("createdByUser");
																		pageContext.setAttribute("createdBy", createdBy);
												%>
												<%
													pageContext.setAttribute("base64image", Base64.getEncoder()
																				.encodeToString((byte[]) pageContext.getAttribute("imagepic")));
												%>

												 <c:set var="lat" value="${valuelist.latitude}" scope="page" />
												<c:set var="lon" value="${valuelist.longitude}" scope="page" />
												
												<td id="displayLat_${count}"></td>
												<td id="displayLon_${count}"></td>
												<input type="hidden" value="<c:out value="${lat}" />"
													id="latId_${count}" onwaiting ="convertDMS()" />
												<input type="hidden" value="<c:out value="${lon}" />"
													id="lonId_${count}" oninput="convertLongDMS()" />
												<td><c:out value="${rockName}" /></td>
												<td><c:out value="${rockType}" /></td>
												<td>

													<button type="button" data-toggle="modal"
														data-target="#myModal${count}"
														style="padding: 0px 0px 0px 0px; border: 0px 0px 0px 0px">
														<img src="data:image/png;base64,${base64image}" width="80"
															height="60" />
													</button> <!-- Modal -->
													<div id="myModal${count}" class="modal fade" role="dialog">
														<div class="modal-dialog">

															<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal">&times;</button>
																	<h4 class="modal-title"
																		style="color: green; font-style: italic;; font-size: 150%;">
																		Rock Name:
																		<c:out value="${rockName}" />
																	</h4>
																	<h4 class="modal-title"
																		style="color: blue; font-style: oblique; font-size: 150%;">
																		Rock Type:
																		<c:out value="${rockType}" />
																	</h4>
																</div>
																<div class="modal-body">
																	<p>
																		<img src="data:image/png;base64,${base64image}"
																			width="700" height="600" class="img-thumbnail" />
																	</p>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Close</button>
																</div>
															</div>

														</div>
													</div>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:set var="count" value="${count - 1}" scope="page"></c:set>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:forEach>
						</tbody>
					</c:forEach>

				</table>
			</div>
			<input type="hidden" value="${count}" id="totalImagesCountId" /> <input
				type="button" value="Reset" name="reset" class="btn btn-primary"
				onclick="clearSelectionCheckbox()" /> <input type="button"
				value="Submit" name="submit" onclick="ChangedShowingImages()"
				class="btn btn-primary" /> <span id="message"
				style="color: red; font-size: 150%;" />

		</form>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
	var somLanCount = 1;
	var somLonCount = 1;
	
	
	window.onload = function () {
		var totalImagesCount = document.getElementById("totalImagesCountId").value;
		for (var i = 1; i <= totalImagesCount; i++) {
			convertDMS();
			convertLongDMS();

		}
	}

	function convertDMS() {
		var latId = "latId_" + somLanCount;
		var lat = document.getElementById(latId).value;
		var displayTagId = "displayLat_" + somLanCount;
		var convertLat = Math.abs(lat);
		var LatDeg = Math.floor(convertLat);
		var LatSec = (Math.floor((convertLat - LatDeg) * 60));
		var LatCardinal = ((lat > 0) ? "N" : "S");
		console.log(LatDeg + "" + String.fromCharCode(176) + " " + LatCardinal
				+ " " + LatSec + "'");
		var displayTag = document.getElementById(displayTagId)
		var para = document.createElement("SPAN"); // Create a <span> element
		var t = document.createTextNode(LatDeg + "" + String.fromCharCode(176) + " " + LatCardinal
				+ " " + LatSec + "'");
		para.appendChild(t);
		displayTag.appendChild(para);
		somLanCount++;
	}
	function convertLongDMS() {
		var longId = "lonId_" + somLonCount;
		var lng = document.getElementById(longId).value;
		var displayTagId = "displayLon_" + somLonCount;
		var convertLng = Math.abs(lng);
		var LngDeg = Math.floor(convertLng);
		var LngSec = (Math.floor((convertLng - LngDeg) * 60));
		var LngCardinal = ((lng > 0) ? "E" : "W");
		console.log(LngDeg + "" + String.fromCharCode(176) + " " + LngCardinal
				+ " " + LngSec + "'");
		var displayTag = document.getElementById(displayTagId)
		var para = document.createElement("SPAN");
		var t = document.createTextNode(LngDeg + "" + String.fromCharCode(176) + " " + LngCardinal
				+ " " + LngSec + "'");
		para.appendChild(t);
		displayTag.appendChild(para);
		somLonCount++
	}

	function clearSelectionCheckbox() {

		var totalImagesCount = document.getElementById("totalImagesCountId").value;
		for (var i = 1; i <= totalImagesCount; i++) {
			var CheckIdGen = "rowCheckID_" + i;
			document.getElementById(CheckIdGen).checked = false;

		}
	}
	function ChangedShowingImages() {

		var imagesRecIdArray = new Array();
		var totalImagesCount = document.getElementById("totalImagesCountId").value;

		var changeCount = 0;

		for (var i = 1; i <= totalImagesCount; i++) {
			var CheckIdGen = "rowCheckID_" + i;
			var isShowCheckedTemp = document.getElementById(CheckIdGen).checked;

			if (isShowCheckedTemp) {

				var rowImageId = "imageRecId_" + i;

				imagesRecIdArray[changeCount] = parseInt(document
						.getElementById(rowImageId).value);

				changeCount++;
			}
		}

		sendData(imagesRecIdArray);

	}

	function sendData(imagesRecIdArray) {
		var tempUserId = document.getElementById("tempUserId").value;

		$
				.ajax(
						{
							url : window.location.pathname.substring(0,
									window.location.pathname.indexOf('/client',
											0))
									+ "/ds/geomap/changeclassAccess/save?userId="
									+ tempUserId
									+ "&imageIDData="
									+ imagesRecIdArray,

						})
				.then(
						function(data, status, jqxhr) {

							if (data) {
								document.getElementById("message").innerHTML = "Changes has been done to the map visibility.";
							}

						});
	}

	function react() {
		document.getElementById("message").innerHTML = "";
	}
</script>
<%@ include file="./Footer.jsp"%>

</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>
