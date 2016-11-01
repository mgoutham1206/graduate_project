
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
<!--

//-->

$(document).ready(function() {
	$("#link_types").click(function(e){
		console.log("clicked");
		$("#link_types").class = "active";
		window.location.href="/GeoApp/client/generalinfo/types";
	});
	$("#link_properties").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/properties";
	});
	$("#link_identification").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/identification/";
	});
	$("#link_rocks").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/Rocktypes";
	});
	
});
</script>

<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" style="padding-top: 225px">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu"
					style="height: 10%; float: bottom; margin-top: 50px">

					<!-- Optionally, you can add icons to the links -->
					<li role="presentation" id="link_types" ><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Types</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_properties"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Properties</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_identification"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Identification</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_rocks"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Rock
								Cycle</span></a></li>
								
 							
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>
