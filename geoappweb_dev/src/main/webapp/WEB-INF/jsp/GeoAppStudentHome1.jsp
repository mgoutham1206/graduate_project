<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GeoApp</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="/GeoApp/template/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style 
        <link rel="stylesheet" href="/GeoApp/template/dist/css/AdminLTE.min.css">
        -->
<link rel="stylesheet" href="/GeoApp/template/dist/css/AdminLTE.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect.
        -->
<link rel="stylesheet"
	href="/GeoApp/template/dist/css/skins/skin-green.min.css">

<!-- functionality scripts -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<style type="text/css">
#properties {
	display: none;
}

#mc {
	display: none;
}

#sc {
	display: none;
}

#sg {
	display: none;
}

#mh {
	display: none;
}

#cleav {
	display: none;
}

#diaph {
	display: none;
}

#result {
	display: none;
}

#reset {
	display: none;
}
</style>
<script type="text/javascript">
	function result() {
		$("#reset").show();
		$("#result").show();
	}
	function reset() {
		$("#mc").hide();
		$("#sc").hide();
		$("#sg").hide();
		$("#mh").hide();
		$("#cleav").hide();
		$("#diaph").hide();
		$("#result").hide();
		$("#reset").hide();
		$("#res").empty();
		$('#streakCheckbox').empty();
		$('#mineralCheckbox').empty();
	}
	//var controllerUrl = "/client/general-info/identification/";
	var controllerUrl = "/GeoApp/client/general-info/identification/";
	function identify() {
		var lusterType = $("input[name=luster]:checked").val();
		var pp = $("#property").val();
		if (pp === "hardness") {
			reset();
			$("#mh").show();
			mh();
		} else if (pp === "streak") {
			reset();
			$.ajax({
				type : "GET",
				url : controllerUrl + "" + lusterType + "/allstreak",
				data : "",
				//cache: false,
				success : function(data) {
					console.log(data.length);
					$('#streakCheckbox').empty();
					$.each(data, function(index, value) {
						$('#streakCheckbox').append(
								'<input type="checkbox" value="' + value + '" /> '
										+ value + '<br />');
					});

				}
			});

			$("#sc").show();
			sc();
		} else if (pp === "mcolor") {
			reset();
			$.ajax({
				type : "GET",
				url : controllerUrl + "" + lusterType + "/allcolor",
				data : "",
				//cache: false,
				success : function(data) {
					console.log(data.length);
					$('#mineralCheckbox').empty();
					$.each(data, function(index, value) {
						$('#mineralCheckbox').append(
								'<input type="checkbox" value="' + value + '" /> '
										+ value + '<br />');
					});

				}
			});
			$("#mc").show();
			mc();
		} else if (pp === "sg") {
			reset();
			$("#sg").show();
			sg();
		} else if (pp === "cleavage") {
			reset();
			$("#cleav").show();
			$("#set").hide();
			$("#angle").hide();
		}

	}
	$(document).ready(
			function() {
				$('.sidebar-menu li a').click(function(e) {

					$('.sidebar-menu li').removeClass('active');

					var $parent = $(this).parent();
					if (!$parent.hasClass('active')) {
						$parent.addClass('active');
					}
					e.preventDefault();
				});

				$('.nav-tabs li a').click(function(e) {

					$('.nav-tabs li').removeClass('active');

					var $parent = $(this).parent();
					if (!$parent.hasClass('active')) {
						$parent.addClass('active');
					}
					e.preventDefault();
				});

				$("#luster1").click(
						function() {
							console.log("luster type selected is :  "
									+ $("input[name=luster]:checked").val());
							reset();
							$("#properties").show();
							$('#property').find('option:first').attr(
									'selected', 'selected');
							$("#property").change(function() {
								identify();
							});
						});

				$("#luster2").click(
						function() {
							console.log("luster type selected is :  "
									+ $("input[name=luster]:checked").val());
							reset();
							$("#properties").show();
							$('#property').find('option:first').attr(
									'selected', 'selected');
							$("#property").change(function() {
								identify();
							});
						});

				$("#hardness").change(
						function() {
							var lusterType = $("input[name=luster]:checked")
									.val();
							var range = $("#hardness").val();
							$.ajax({
								type : "GET",
								url : controllerUrl + "" + lusterType
										+ "/hardness=" + range,
								data : "",
								//cache: false,
								success : function(data) {
									console.log(data);
									result();
									var dataString = "";
									$.each(data, function(index, value) {
										dataString += (index + 1) + ". "
												+ value + "\n ";
									})
									$("#res").empty();
									$("#res")
											.append(
													"Minerals with hardness "
															+ range
															+ ": \n <br/>"
															+ dataString);
								}
							});
						});

				$("#reset").click(function() {
					reset();
				});

				$("#streak").click(
						function() {
							var lusterType = $("input[name=luster]:checked")
									.val();
							var selected = "";
							$('#streakCheckbox input:checked').each(function() {
								console.log("checked");
								//selected.push($(this).attr('value'));
								selected += $(this).attr('value') + ",";
							});
							selected = selected.slice(0, -1);
							console.log(selected);
							$.ajax({
								type : "GET",
								url : controllerUrl + "" + lusterType
										+ "/streak=" + selected,
								data : "",
								//cache: false,
								success : function(data) {
									console.log(data);
									result();
									var dataString = "";
									$.each(data, function(index, value) {
										dataString += (index + 1) + ". "
												+ value + "\n ";
									})
									$("#res").empty();
									$("#res").append(
											"Minerals with selected Streak Color: \n <br/>"
													+ dataString);
								}
							});
						});

				$("#color").click(
						function() {
							var lusterType = $("input[name=luster]:checked")
									.val();
							var selected = "";
							$('#mineralCheckbox input:checked')
									.each(
											function() {
												console.log("checked");
												//selected.push($(this).attr('value'));
												selected += $(this).attr(
														'value')
														+ ",";
											});
							selected = selected.slice(0, -1);
							console.log(selected);
							$.ajax({
								type : "GET",
								url : controllerUrl + "" + lusterType
										+ "/color=" + selected,
								data : "",
								//cache: false,
								success : function(data) {
									console.log(data);
									result();
									var dataString = "";
									$.each(data, function(index, value) {
										dataString += (index + 1) + ". "
												+ value + "\n ";
									});
									$("#res").empty();
									$("#res").append(
											"Minerals with selected Mineral Color: \n <br/>"
													+ dataString);
								}
							});
						});

				$("input[name=sg]").click(
						function() {
							var lusterType = $("input[name=luster]:checked")
									.val();
							var range = $("input[name=sg]:checked").val();
							$.ajax({
								type : "GET",
								url : controllerUrl + "" + lusterType + "/sg="
										+ range,
								data : "",
								//cache: false,
								success : function(data) {
									console.log(data);
									result();
									var dataString = "";
									$.each(data, function(index, value) {
										dataString += (index + 1) + ". "
												+ value + "\n ";
									});
									$("#res").empty();
									$("#res").append(
											"Minerals with specific gravity "
													+ range + ": \n <br/>"
													+ dataString);
								}
							});
						});
				$("input[name=cle]")
						.click(
								function() {
									var status = $("input[name=cle]:checked")
											.val();
									if (status == "yes") {
										$("#set").show();
										$("#angle").show();
									} else {
										$("#set").hide();
										$("#angle").hide();
										var lusterType = $(
												"input[name=luster]:checked")
												.val();
										$.ajax({
											type : "GET",
											url : controllerUrl + ""
													+ lusterType
													+ "/nocleavage",
											data : "",
											//cache: false,
											success : function(data) {
												console.log(data);
												result();
												var dataString = "";
												$.each(data, function(index,
														value) {
													dataString += (index + 1)
															+ ". " + value
															+ "\n ";
												});
												$("#res").empty();
												$("#res").append(
														"Minerals with no cleavage: \n <br/>"
																+ dataString);
											}
										});
									}
								});

				$("input[name=ang]").click(
						function() {
							var lusterType = $("input[name=luster]:checked")
									.val();
							var set = $("#sets").val();
							var angle = $("input[name=ang]:checked").val();
							$.ajax({
								type : "GET",
								url : controllerUrl + "" + lusterType
										+ "/sets=" + set + "," + angle,
								data : "",
								//cache: false,
								success : function(data) {
									console.log(data);
									result();
									var dataString = "";
									$.each(data, function(index, value) {
										dataString += (index + 1) + ". "
												+ value + "\n ";
									});
									$("#res").empty();
									$("#res").append(
											"Minerals with cleavage: \n <br/>"
													+ dataString);
								}
							});
						});
			});
</script>

<!-- end of functionality  -->


</head>
<body class="hold-transition skin-green sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="index2.html" class="logo" id="geoapplogo"
				style="height: 125px"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>Geo</b><br />APP</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><img id="logopic"
					style="float: left; margin: 23px 10px 0 34px;"
					src="/GeoApp/template/pics/logo.png" alt="doc" height="100" width="100" /></span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>


				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">

						<li class="app-heading" id="appheading" style="">
							<!-- Menu toggle button -->
							<div class="col-sm-12 col-md-12 col-lg-12">
								<h1>Geographical Rock App.</h1>
							</div> <span
							style="font-family: cursive; font-size: 150%; padding-left: 40%">->Learning
								the life of rock.</span>
						</li>

						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="/GeoApp/template/dist/img/user2-160x160.jpg"
								class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">Student</span>
						</a>
							<ul class="dropdown-menu" id="dropdownmenu">

								<!-- Menu Footer-->
								<li class="user-footer" id="userfooter">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
					<ul class="nav nav-tabs" style="margin-left: -250px">
						<li class="active" role="presentation" style="font-size: larger"><a
							href="#"><span class="glyphicon glyphicon-home geo-icons"></span>
								Home </a></li>
						<li role="presentation" style="font-size: larger"><a href="#"><span
								class="glyphicon glyphicon-folder-close"></span> General
								Information </a></li>
						<li role="presentation" style="font-size: larger"><a href="#"><span
								class="glyphicon glyphicon-list-alt"></span> Assignment </a></li>
						<!-- glyphicon glyphicon-pencil -->
						<li role="presentation" style="font-size: larger"><a href="#"><span
								class="glyphicon glyphicon-pencil"></span> Quiz </a></li>
						<!--<li role="presentation"><a href="#">Evaluation</a></li>-->
						<li role="presentation" style="font-size: larger"><a href="#"><span
								class="glyphicon glyphicon-globe"></span> Geo-Map </a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu"
					style="height: 10%; float: bottom; margin-top: 50px">

					<!-- Optionally, you can add icons to the links -->
					<li class="active" role="presentation"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Types</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Properties</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Mineral
								Identification</span></a></li>
					<li role="presentation" style="margin-top: 20px"><a href="#"><i
							class="fa fa-link"></i> <span style="font-size: larger">Rock
								Cycle</span></a></li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Mineral Identification based on a specific mineral property</h1>
				<h2>Please select one luster :</h2>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->


				<input type="radio" id="luster1" name="luster" value="metallic">
				Minerals with metallic luster <br /> <input type="radio"
					id="luster2" name="luster" value="non-metallic"> Minerals
				with non-metallic luster <br />

				<div id="properties">
					<h2>Please select a mineral property :</h2>
					<select id="property">
						<option class="placeholder" selected="" disabled="">Select
							one mineral property</option>
						<option value="hardness">Mineral Hardness</option>
						<option value="streak">Streak Color</option>
						<option value="mcolor">Mineral Color</option>
						<option value="sg">Specific Gravity</option>
						<option value="cleavage">Cleavage</option>
					</select>
				</div>
				<div id="mh">
					<h2>Mineral Hardness :</h2>
					<select id="hardness">
						<option class="placeholder" selected="" disabled="">Select
							one</option>
						<option value="range0" />2.2 - 3.1
						<br>
						<option value="range1" />3.2 - 4.9
						<br>
						<option value="range2" />5 - 5.4
						<br>
						<option value="range3" />5.5 - 7.0
						<br>
					</select>
					<h4>(note : Fingernail: ~2.2; Penny: ~3.2; Steel Nail or
						pocket knife blade: ~5.0; Glass Plate: ~5.5; Streak Plate: ~7.0)</h4>
				</div>

				<div id="sc">
					<h2>Streak Color :</h2>
					<form action="">
						<div id="streakCheckbox"></div>
					</form>
					<button id="streak">GO</button>
				</div>

				<div id="mc">
					<h2>Mineral Color :</h2>
					<form action="">
						<div id="mineralCheckbox"></div>
					</form>
					<button id="color">GO</button>
				</div>
				<div id="sg">
					<h2>Specific Gravity :</h2>
					<form action="" id="sgg">
						<input type="radio" name="sg" value="range2" />Heavy <input
							type="radio" name="sg" value="range1" />Moderately Heavy <input
							type="radio" name="sg" value="range0" />Not Heavy
					</form>
				</div>
				<div id="cleav">
					<h2>Cleavage :</h2>
					<form action="" id="cleavid">
						<input type="radio" name="cle" value="yes" />Present <input
							type="radio" name="cle" value="no" />Not Present
					</form>
					<div id="set">
						<h2>Number of sets :</h2>
						<select id="sets">
							<option value="0" />0
							<br>
							<option value="1" />1
							<br>
							<option value="2" />2
							<br>
							<option value="3" />3
							<br>
							<option value="4" />4 or more
							<br>
						</select>
					</div>
					<div id="angle">
						<h2>Angular value :</h2>
						<form action="" id="angleid">
							<input type="radio" name="ang" value="90" />90 <input
								type="radio" name="ang" value="60" />60 / 120 <input
								type="radio" name="ang" value="1" />others <br />
						</form>
					</div>

				</div>


				<div id="result">
					<h2>List of Minerals Result :</h2>
					<textarea type="text" id="res" cols="50" rows="10" readonly> </textarea>
				</div>
				<div id="reset">
					<br />
					<button id="resetbutton" type="reset23">Reset</button>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- Default to the left -->
			<strong>GeoApp © 2015<a href="http://www.nwmissouri.edu/"> NWMSU</a>.
			</strong> All rights reserved.
		</footer>

	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.1.4 -->
	<script src="/GeoApp/template/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="/GeoApp/template/bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/GeoApp/template/dist/js/app.min.js"></script>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. Slimscroll is required when using the
             fixed layout. -->
</body>
</html>
