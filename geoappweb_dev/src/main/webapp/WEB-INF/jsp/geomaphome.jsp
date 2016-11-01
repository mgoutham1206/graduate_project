
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<script src="http://maps.googleapis.com/maps/api/js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
	var LatitudeData = new Array();
	var LongitudeData = new Array();
	var ImageByteArray = new Array();
	var dataMapInfoWindow = new Array();
	var rockTypeValue;
	var myCenter = new google.maps.LatLng(51.508742, -0.120850);

	function convertDMS(lat) {
		var convertLat = Math.abs(lat);
		var LatDeg = Math.floor(convertLat);
		var LatSec = (Math.floor((convertLat - LatDeg) * 60));
		var LatCardinal = ((lat > 0) ? "N" : "S");
		return LatDeg + "" + String.fromCharCode(176) + " " + LatCardinal + " "
				+ LatSec + "'"
	}

	function convertLongDMS(lng) {
		var convertLng = Math.abs(lng);
		var LngDeg = Math.floor(convertLng);
		var LngSec = (Math.floor((convertLng - LngDeg) * 60));
		var LngCardinal = ((lng > 0) ? "E" : "W");
		return LngDeg + "" + String.fromCharCode(176) + " " + LngCardinal + " "
				+ LngSec + "'"
	}
	function initializeMap() {
		var mapProp = {
			center : myCenter,
			zoom : 5,
			mapTypeId : google.maps.MapTypeId.HYBRID
		};

		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);

		for (i = 0; i < LatitudeData.length; i++) {

			var position = new google.maps.LatLng(LatitudeData[i],LongitudeData[i]);
			LatitudeData[i] = convertDMS(LatitudeData[i]);
			LongitudeData[i] = convertLongDMS(LongitudeData[i]);
			var infowindow = new google.maps.InfoWindow();
			var target = "#thisImage_" + i;
			var modalId = "thisImage_" + i;
			var srcLink = "data:image/png;base64," + ImageByteArray[i];

			ImageHTML = '<a data-toggle="modal" data-target="#thisImage_' + i + ' " > ImageInfo </a>'
			dataMapInfoWindow[i] = 'Latitude: '
					+ LatitudeData[i]
					+ '<br>'
					+ 'Longitude: '
					+ LongitudeData[i]
					+ '<br>'
					+ '<img src="data:image/png;base64,'+ImageByteArray[i]+'" width="80"  height="60"/>';

			marker = new google.maps.Marker({
				content : dataMapInfoWindow[i],
				position : position,
				map : map,
				draggable : false,
				title : 'Latitude: ' + LatitudeData[i] + '  ' + 'Longitude: '
						+ LongitudeData[i]
				//		+ '<br>'
				//		+ '<img src="data:image/png;base64,'+ImageByteArray[i]+'" width="80"  height="60"/>'
				/* + '											<button type="button" data-toggle="modal"'
								+ '												data-target="'+target+'" style="padding: 0px 0px 0px 0px; border: 0px 0px 0px 0px">'
				+ '												<img src="data:image/png;base64,'+ImageByteArray[i]+'" width="80"'
								+ '													height="60" />'
				+ '											</button>'
				+ '											<!-- Modal -->'
				+ '											<div id= "'+modalId+'" class="modal fade" role="dialog">'
				+ '												<div class="modal-dialog">'
				+ '													<!-- Modal content-->'
				+ '													<div class="modal-content">'
				+ '														<div class="modal-header">'
				+ '															<button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ '															<h4 class="modal-title"'
				+ '																style="color: green; font-style: italic;; font-size: 150%;">'
				+ '																Image Information'
				+ '															</h4>'
				+ '														</div>'
				+ '														<div class="modal-body">'
				+ '															<p>'
				+ '																<img src="data:image/png;base64,'+ImageByteArray[i]+'"'
								+ '																	width="700" height="600" class="img-thumbnail" />'
				+ '															</p>'
				+ '														</div>'
				+ '														<div class="modal-footer">'
				+ '															<button type="button" class="btn btn-default"'
								+ '																data-dismiss="modal">Close</button>'
				+ '														</div>'
				+ '													</div>'
				+ '												</div>'
				+ '											</div>' */,
				icon : getIcon(rockTypeValue),

			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.setContent(this.content);
				infowindow.open(map, this);
			});
			marker.setMap(map);

		}
	}
	google.maps.event.addDomListener(window, 'load', initializeMap);
	function defaultinitializeMap() {
		var mapProp = {
			center : new google.maps.LatLng(51.508742, -0.120850),
			zoom : 5,
			mapTypeId : google.maps.MapTypeId.HYBRID
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);
	}
	google.maps.event.addDomListener(window, 'load', defaultinitializeMap);

	function callMapsforRocktype(rockTypeValue) {
		var userIdInformation = document.getElementById("tempUserId").value;
		//alert(str.substring(0,str.indexOf("/client",0))+"/ds/geomap/allimagesinfo/instructor/byrocktype/"+ userIdInformation+ "/" + rockTypeValue)
		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/instructor/byrocktype/"+userIdInformation + "/" + rockTypeValue,				
					url : window.location.pathname.substring(0,
							window.location.pathname.indexOf('/client', 0))
							+ "/ds/geomap/allimagesinfo/instructor/byrocktype/"
							+ userIdInformation + "/" + rockTypeValue,
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						$.each(data, function(index, value) {
							if (value.isBest == "Y" && value.isShow == "Y") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
							}
						});
						if (LatitudeData.length == 0) {
							document.getElementById('pinfound').innerHTML = "No PinInfo found for "
									+ rockTypeValue + " Rock";
							defaultinitializeMap();
						} else {
							initializeMap();
							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found for "
									+ rockTypeValue
									+ " Rock";
						}
					}
				});
	}
	function callAllMapsforInstructor() {

		var userIdInformation = document.getElementById("tempUserId").value;
		//alert(window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/instructor/"+userIdInformation);
		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/instructor/"+ userIdInformation,	
					url : window.location.pathname.substring(0,
							window.location.pathname.indexOf('/client', 0))
							+ "/ds/geomap/allimagesinfo/instructor/"
							+ userIdInformation,
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {

						$.each(data, function(index, value) {
							if (value.isBest == "Y" && value.isShow == "Y") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
							}
						});
						document.getElementById('pageHeadInfo').innerHTML = "All Rocks";
						if (LatitudeData.length == 0) {
							document.getElementById('pinfound').innerHTML = "No Pin Info found for Instructor";
							defaultinitializeMap();
						} else {
							initializeMap();
							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found";
						}
					}
				});
	}
	function getIcon(rockTypeValue) {

		var iconURLPrefix = 'http://maps.google.com/mapfiles/ms/icons/';
		var icons = [ iconURLPrefix + 'pink-dot.png',
				iconURLPrefix + 'blue-dot.png',
				iconURLPrefix + 'green-dot.png',
				iconURLPrefix + 'orange-dot.png' ]
		if (rockTypeValue == "Igneous") {
			pinicon = icons[0];
		} else if (rockTypeValue == "Sedimentary") {
			pinicon = icons[1];
		} else if (rockTypeValue == "Metamorphic") {
			pinicon = icons[2];
		} else {
			pinicon = icons[3];
		}
		return pinicon;
	}
	function doAll() {
		document.getElementById('pageHeadInfo').innerHTML = "All Rocks";
		rockTypeValue = '';
		callAllMapsforInstructor();
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();

	}
	function doIgneous() {
		document.getElementById('pageHeadInfo').innerHTML = "Igneous Rocks";
		rockTypeValue = 'Igneous';
		callMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();

	}
	function doSedimentary() {
		document.getElementById('pageHeadInfo').innerHTML = "Sedimentary Rocks";
		rockTypeValue = 'Sedimentary';
		callMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();

	}
	function doMetamorphic() {
		document.getElementById('pageHeadInfo').innerHTML = "Metamorphic Rocks";

		rockTypeValue = 'Metamorphic';
		callMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
	}
	//$(document).ready(callAllMapsforInstructor);

	window.onload = function() {
		var rockInfoType = document.getElementById("temprockTypeId").value;
		if (rockInfoType == "A") {
			callAllMapsforInstructor()
		} else if (rockInfoType == "M") {
			doMetamorphic();
		} else if (rockInfoType == "S") {
			doSedimentary();
		} else {
			doIgneous();
		}
	}
</script>
<aside class="main-sidebar">


	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu"
			style="height: 10%; float: bottom; margin-top: 50px">

			<!-- Optionally, you can add icons to the links -->
			<li onclick="doAll()" role="presentation"><a href="#"><i
					class="fa fa-link"></i> <span style="font-size: larger">All</span></a></li>
			<li onclick="doIgneous()" role="presentation"
				style="margin-top: 20px"><a href="#"><i class="fa fa-link"></i>
					<span style="font-size: larger">Igneous</span></a></li>
			<li onclick="doSedimentary()" role="presentation"
				style="margin-top: 20px"><a href="#"><i class="fa fa-link"></i>
					<span style="font-size: larger">Sedimentary</span></a></li>
			<li onclick="doMetamorphic()" role="presentation"
				style="margin-top: 20px"><a href="#"><i class="fa fa-link"></i>
					<span style="font-size: larger">Metamorphic</span></a></li>
			<li role="presentation" style="margin-top: 20px"><a
				href="/GeoApp/client/geomap/instructor/changeClassAccess/<c:out value="${userInfo.userID}"/>"><i
					class="fa fa-link"></i> <span style="font-size: larger">Change
						Class Access </span></a></li>
		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
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
	<c:set var="rockTypetemp" value="${rockType}" scope="page" />
	<input type="hidden" id="temprockTypeId"
		value=<c:out value="${rockTypetemp}"/> />
	<%-- --%>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-8">
				<span id="pageHeadInfo" style="font-size: 150%;"> </span> <input
					type="hidden" id="tempUserId" value=<c:out value="${userInfoId}"/> />
			</div>
			<div class="col-sm-4">
				<span id="pinfound" style="font-size: 150%;"> </span>
			</div>
		</div>
	</section>
	<section class="content">

		<div class="container text-center" id="googleMap"
			style="width: 900px; height: 400px;"></div>
		<div id="marker-tooltip"></div>

	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="./Footer.jsp"%>

</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>



