<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./StudentPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
	var str = window.location.pathname;
	var res = str.split("/");
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
        return LatDeg +""+String.fromCharCode(176)+" "+ LatCardinal +" "+ LatSec+"'"
	}
	function convertLongDMS(lng) {
	var convertLng = Math.abs(lng);
    var LngDeg = Math.floor(convertLng);
    var LngSec = (Math.floor((convertLng - LngDeg) * 60));
    var LngCardinal = ((lng > 0) ? "E" : "W");
    return LngDeg +""+String.fromCharCode(176)+" "+ LngCardinal +" "+ LngSec+"'"
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
			var position = new google.maps.LatLng(LatitudeData[i],
					LongitudeData[i]);
			LatitudeData[i] = convertDMS(LatitudeData[i]);
			LongitudeData[i] = convertLongDMS(LongitudeData[i]);
			var infowindow = new google.maps.InfoWindow();
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
						+ LongitudeData[i],
				icon : getIcon(rockTypeValue)

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

	function callMyStuffMapsforRocktype(rockTypeValue) {
		var userIdInformation = document.getElementById("tempUserId").value;
		//alert(userIdInformation)
		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/student/byrocktype/"+ userIdInformation + "/" + rockTypeValue,
					url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/byrocktype/"+ userIdInformation + "/" + rockTypeValue,
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						var lat;
						var lon;
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

							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found for "
									+ rockTypeValue
									+ " Rock";
							initializeMap();
						}
					}
				});
	}
	function callMyStuffAllMaps() {
	var userIdInformation = document.getElementById("tempUserId").value;
	//alert(window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/" + userIdInformation)

		$
				.ajax({
				//url : "/ds/geomap/allimagesinfo/student/"+ userIdInformation,
					url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/" + userIdInformation,
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						var lat;
						var lon;
						$.each(data, function(index, value) {
							if (value.isBest == "Y" && value.isShow == "Y") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
								

							}
						});
						if (LatitudeData.length == 0) {
							document.getElementById('pinfound').innerHTML = "No Pin Info found for Student";
							defaultinitializeMap();
						} else {

							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found";
							initializeMap();
						}
					}
				});

	}
	
	function callMyClassMapsforRocktype(rockTypeValue) {
		var userIdInformation = document.getElementById("tempUserId").value;
		//alert("In MY Class All Maps by RockType")
		//alert(userIdInformation)
		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/student/byrocktype/"+ userIdInformation + "/" + rockTypeValue,
					url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/class/byrocktype/"+ userIdInformation + "/" + rockTypeValue,
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						var lat;
						var lon;
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

							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ "Pin Info found for "
									+ rockTypeValue
									+ " Rock";
							initializeMap();
						}
					}
				});
	}
	
	function callMyClassAllMaps() {
		var userIdInformation = document.getElementById("tempUserId").value;
		//alert("In MY Class All Maps")
		//alert(window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/" + userIdInformation)

			$
					.ajax({
					//url : "/ds/geomap/allimagesinfo/student/"+ userIdInformation,
						url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/class/" + userIdInformation,
						type : "GET",
						contentType : 'application/json',
						dataType : "json",
						data : "",
						success : function(data) {
							var lat;
							var lon;
							$.each(data, function(index, value) {
								if (value.isBest == "Y" && value.isShow == "Y") {
									LatitudeData.push(value.latitude)
									LongitudeData.push(value.longitude)
									ImageByteArray.push(value.image)
									

								}
							});
							if (LatitudeData.length == 0) {
								document.getElementById('pinfound').innerHTML = "No Pin Info found for Student";
								defaultinitializeMap();
							} else {

								document.getElementById('pinfound').innerHTML = LatitudeData.length
										+ " Pin Info found";
								initializeMap();
							}
						}
					});

		}
	
	function callBaseAllMaps() {
	
		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/student/Base",
					url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/Base",
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						var lat;
						var lon;
						$.each(data, function(index, value) {
							if (value.isBest == "I" || value.isBest == "M"
									|| value.isBest == "S") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
								

							}
						});
						if (LatitudeData.length == 0) {
							document.getElementById('pinfound').innerHTML = "No Pin Info found for Student";
							defaultinitializeMap();
						} else {

							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found";
							initializeMap();
						}
					}
				});

	}
	function callBaseMapsByRockTpe(rockTypeValue) {

		$
				.ajax({
					//url : "/ds/geomap/allimagesinfo/student/Base",
					url : window.location.pathname.substring(0,window.location.pathname.indexOf('/client',0))+"/ds/geomap/allimagesinfo/student/Base",
					type : "GET",
					contentType : 'application/json',
					dataType : "json",
					data : "",
					success : function(data) {
						var lat;
						var lon;
						$.each(data, function(index, value) {
							if (value.isBest == "I"
									&& rockTypeValue == "Igneous") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
								

							} else if (value.isBest == "M"
									&& rockTypeValue == "Metamorphic") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
								

							} else if (value.isBest == "S"
									&& rockTypeValue == "Sedimentary") {
								LatitudeData.push(value.latitude)
								LongitudeData.push(value.longitude)
								ImageByteArray.push(value.image)
								

							}

						});
						if (LatitudeData.length == 0) {
							document.getElementById('pinfound').innerHTML = "No Pin Info found for Student";
							defaultinitializeMap();
						} else {

							document.getElementById('pinfound').innerHTML = LatitudeData.length
									+ " Pin Info found for "
									+ rockTypeValue
									+ " Rock";
							initializeMap();
						}
					}
				});

	}
	
	
	function getIcon(rockTypeValue) {

		var iconURLPrefix = 'http://maps.google.com/mapfiles/ms/icons/';
		var icons = [ iconURLPrefix + 'blue-dot.png',
				iconURLPrefix + 'pink-dot.png',
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
	function doBaseAll() {
		document.getElementById('pageHeadInfo').innerHTML = "Sample - Allrocks"
		rockTypeValue = '';
		callBaseAllMaps();
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doBaseIgneous() {
		document.getElementById('pageHeadInfo').innerHTML = "Sample - Igneous Rocks"
		rockTypeValue = 'Igneous';
		callBaseMapsByRockTpe(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doBaseMetamorphic() {
		document.getElementById('pageHeadInfo').innerHTML = "Sample - Metamorphic Rocks"
		rockTypeValue = 'Metamorphic';
		callBaseMapsByRockTpe(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doBaseSedimentary() {
		document.getElementById('pageHeadInfo').innerHTML = "Sample - Sedimentary Rocks"
		rockTypeValue = 'Sedimentary';
		callBaseMapsByRockTpe(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyStuffAll() {
		document.getElementById('pageHeadInfo').innerHTML = "My Submissions - All Rocks"
		rockTypeValue = '';
		callMyStuffAllMaps();		
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyStuffIgneous() {
		document.getElementById('pageHeadInfo').innerHTML = "My Submissions - Igneous Rocks"
		rockTypeValue = 'Igneous';
		callMyStuffMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyStuffSedimentary() {
		document.getElementById('pageHeadInfo').innerHTML = "My Submissions - Sedimentary Rocks"
		rockTypeValue = 'Sedimentary';
		callMyStuffMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyStuffMetamorphic() {
		document.getElementById('pageHeadInfo').innerHTML = "My Submissions - Metamorphic Rocks"
		rockTypeValue = 'Metamorphic';
		callMyStuffMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyClassAll() {
		document.getElementById('pageHeadInfo').innerHTML = "Class - All Rocks"
		rockTypeValue = '';
		callMyClassAllMaps();		
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();		
	}

	function doMyClassIgneous() {
		document.getElementById('pageHeadInfo').innerHTML = "Class - Igneous Rocks"
		rockTypeValue = 'Igneous';
		callMyClassMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyClassSedimentary() {
		document.getElementById('pageHeadInfo').innerHTML = "Class - Sedimentary Rocks"
		rockTypeValue = 'Sedimentary';
		callMyClassMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
	function doMyClassMetamorphic() {
		document.getElementById('pageHeadInfo').innerHTML = "Class - Metamorphic Rocks"
		rockTypeValue = 'Metamorphic';
		callMyClassMapsforRocktype(rockTypeValue);
		LongitudeData = new Array();
		LatitudeData = new Array();
		ImageByteArray = new Array();
		
	}
</script>

<aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu"
			style="height: 10%; float: bottom; margin-top: 50px">
			<li>
				<div class="dropdown">
					<a href="#" data-toggle="dropdown" class="dropdown-toggle"><span
						style="font-size: x-large;">Sample Rocks</span><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<!-- Optionally, you can add icons to the links -->
						<li onclick="doBaseAll()" role="presentation"
							style="margin-top: 0px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">All</span></a></li>
						<li onclick="doBaseIgneous()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large;">Igneous</span></a></li>
						<li onclick="doBaseSedimentary()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Sedimentary</span></a></li>
						<li onclick="doBaseMetamorphic()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Metamorphic</span></a></li>
					</ul>
				</div>
			</li>
			<li>
				<div class="dropdown">
					<a href="#" data-toggle="dropdown" class="dropdown-toggle"><span
						style="font-size: x-large;">My Rocks</span><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<!-- Optionally, you can add icons to the links -->
						<li onclick="doMyStuffAll()" role="presentation"
							style="margin-top: 0px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">All</span></a></li>
						<li onclick="doMyStuffIgneous()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large;">Igneous</span></a></li>
						<li onclick="doMyStuffSedimentary()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Sedimentary</span></a></li>
						<li onclick="doMyStuffMetamorphic()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Metamorphic</span></a></li>
					</ul>
				</div>
			</li>
			<li>
				<div class="dropdown">
					<a href="#" data-toggle="dropdown" class="dropdown-toggle"><span
						style="font-size: x-large;">My Class Rocks</span><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<!-- Optionally, you can add icons to the links -->
						<li onclick="doMyClassAll()" role="presentation" style="margin-top: 0px"><a
							href="#"><i class="fa fa-link"></i> <span
								style="font-size: large;">All</span></a></li>
						<li onclick="doMyClassIgneous()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large;">Igneous</span></a></li>
						<li onclick="doMyClassSedimentary()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Sedimentary</span></a></li>
						<li onclick="doMyClassMetamorphic()" role="presentation"
							style="margin-top: 20px"><a href="#"><i
								class="fa fa-link"></i> <span style="font-size: large">Metamorphic</span></a></li>
					</ul>
				</div>
			</li>
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
	<!-- Content Header (Page header) -->
	<c:set var="userInfoId" value="${studentuserInfo.userID}"
									scope="page" />
	<section class="content-header">
		<div class="row">
			<div class="col-sm-8">
				<span id="pageHeadInfo" style="font-size: 150%;"> </span>
				<input type="hidden" id="tempUserId" value=<c:out value="${userInfoId}"/> />
			</div>
			<div class="col-sm-4"><span id="pinfound" style="font-size: 150%;"> </span></div>
		</div>
	</section>
	<section class="content">
		<div class="container text-center" id="googleMap"
			style="width: 900px; height: 400px;"></div>

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