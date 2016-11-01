
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
<!--

//-->

$(document).ready(function() {
	$("#link_typesinst").click(function(e){
		console.log("clicked");
		$("#link_typesinst").class = "active";
		window.location.href="/GeoApp/client/generalinfo/types/inst";
	});
	$("#link_propertiesinst").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/properties/inst";
	});
	$("#link_identificationinst").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/identification/inst";
	});
	$("#link_rocksinst").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/Rocktypes/inst";
	});
	$("#link_insert").click(function(e){
		console.log("clicked");
		window.location.href="/GeoApp/client/generalinfo/upload";
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
					<li role="presentation" id="link_typesinst" ><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Types</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_propertiesinst"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Properties</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_identificationinst"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Identification</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_rocksinst"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Rock
								Cycle</span></a></li>
					<li role="presentation" style="margin-top: 20px" id="link_insert"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Insert Data</span></a></li>			
 							
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>
