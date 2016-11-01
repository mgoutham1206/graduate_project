<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>
<%@ include file="./GeneralInfoSideBar.jsp"%>
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

#tables {
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
		console.log("reset ============");
		$('#property').val("none");
		$("#mc").hide();
		$("#sc").hide();
		$("#sg").hide();
		$("#mh").hide();
		$("#cleav").hide();
		$("#tables").hide();
		$("#result").hide();
		$("#reset").hide();
		$("#res").empty();
		$('#streakCheckbox').empty();
		$('#mineralCheckbox').empty();
	}

	function par_reset() {
		console.log("prereset ============");
		$('#property').val("none");
		$("#mc").hide();
		$("#sc").hide();
		$("#sg").hide();
		$("#mh").hide();
		$("#cleav").hide();
		//$("#result").hide();
		//$("#res").empty();
		//$("#res").hide();
		//$("#tables").hide();
		$('#streakCheckbox').empty();
		$('#mineralCheckbox').empty();
	}
	//var controllerUrl = "/client/generalinfo/identification/";
	var controllerUrl = "/GeoApp/client/generalinfo/identification/";
	function identify() {
		var lusterType = $("input[name=luster]:checked").val();
		var pp = $("#property").val();
		if (pp === "hardness") {
			par_reset();
			$("#mh").show();
			mh();
		} else if (pp === "streak") {
			par_reset();
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
			par_reset();
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
			par_reset();
			$("#sg").show();
			sg();
		} else if (pp === "cleavage") {
			par_reset();
			$("#cleav").show();
			$("#set").hide();
			$("#angle").hide();
		}

	}
	function table() {
		$("#tables").show();
		var lusterType = $("input[name=luster]:checked").val();
		$.ajax({
			type : "GET",
			url : controllerUrl + "" + lusterType + "/table/all",
			data : "",
			//cache: false,
			success : function(data) {
				console.log(data);
				$('#mtable').empty();
				for (var int = 0; int < data.length; int++) {
					$('#mtable').append(
							'<tr><td>' + data[int].hardnessmin + '</td><td>'
									+ data[int].hardnessmax + '</td>' + '<td>'
									+ data[int].sgmin + '</td><td>'
									+ data[int].sgmax + '</td>' + '<td>'
									+ data[int].streakcolor + '</td><td>'
									+ data[int].color + '</td>' + '<td>'
									+ data[int].cleavage + '</td><td>'
									+ data[int].sets + '</td>' + '<td>'
									+ data[int].angle + '</td><td>'
									+ data[int].mineral + '</td>' + '</tr>');
				}
			}

		});
	}
	$(document)
			.ready(

					function() {
						$("#link_identification").addClass("active");
						$("#link_geninfo").addClass("active");
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
											+ $("input[name=luster]:checked")
													.val());
									reset();
									table();
									$("#properties").show();
									$('#property').val("none");
									$("#property").change(function() {
										identify();
									});
								});

						$("#luster2").click(
								function() {
									console.log("luster type selected is :  "
											+ $("input[name=luster]:checked")
													.val());
									reset();
									table();
									$("#properties").show();
									$('#property').val("none");
									$("#property").change(function() {
										identify();
									});
								});

						$("#hardness")
								.change(
										function() {
											var lusterType = $(
													"input[name=luster]:checked")
													.val();
											var range = $("#hardness").val();
											var text = $("#hardness").children(
													":selected").text();
											$
													.ajax({
														type : "GET",
														url : controllerUrl
																+ ""
																+ lusterType
																+ "/hardness="
																+ range,
														data : "",
														//cache: false,
														success : function(data) {
															console.log(data);
															result();
															var dataString = "";
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				dataString += (index + 1)
																						+ ". "
																						+ value
																						+ "\n ";
																			})
															//$("#res").empty();
															$("#res")
																	.append(
																			lusterType
																					+ " Minerals with hardness in range:"
																					+ text
																					+ "\n"
																					+ dataString);
														}
													});
										});

						$("#reset").click(function() {
							reset();
						});

						$("#streak")
								.click(
										function() {
											var lusterType = $(
													"input[name=luster]:checked")
													.val();
											var selected = "";
											$('#streakCheckbox input:checked')
													.each(
															function() {
																console
																		.log("checked");
																//selected.push($(this).attr('value'));
																selected += $(
																		this)
																		.attr(
																				'value')
																		+ ",";
															});
											selected = selected.slice(0, -1);
											console.log(selected);
											$
													.ajax({
														type : "GET",
														url : controllerUrl
																+ ""
																+ lusterType
																+ "/streak="
																+ selected,
														data : "",
														//cache: false,
														success : function(data) {
															console.log(data);
															result();
															var dataString = "";
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				dataString += (index + 1)
																						+ ". "
																						+ value
																						+ "\n ";
																			})
															//$("#res").empty();
															$("#res")
																	.append(
																			lusterType
																					+ " Minerals with selected Streak Color: \n <br/>"
																					+ dataString);
														}
													});
										});

						$("#color")
								.click(
										function() {
											var lusterType = $(
													"input[name=luster]:checked")
													.val();
											var selected = "";
											$('#mineralCheckbox input:checked')
													.each(
															function() {
																console
																		.log("checked");
																//selected.push($(this).attr('value'));
																selected += $(
																		this)
																		.attr(
																				'value')
																		+ ",";
															});
											selected = selected.slice(0, -1);
											console.log(selected);
											$
													.ajax({
														type : "GET",
														url : controllerUrl
																+ ""
																+ lusterType
																+ "/color="
																+ selected,
														data : "",
														//cache: false,
														success : function(data) {
															console.log(data);
															result();
															var dataString = "";
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				dataString += (index + 1)
																						+ ". "
																						+ value
																						+ "\n ";
																			});
															//$("#res").empty();
															$("#res")
																	.append(
																			lusterType
																					+ " Minerals with selected Mineral Color: \n <br/>"
																					+ dataString);
														}
													});
										});

						$("#sgg")
								.change(
										function() {
											var lusterType = $(
													"input[name=luster]:checked")
													.val();
											var range = $("#sgg").val();
											var text = $("#sgg").children(
													":selected").text();
											$
													.ajax({
														type : "GET",
														url : controllerUrl
																+ ""
																+ lusterType
																+ "/sg="
																+ range,
														data : "",
														//cache: false,
														success : function(data) {
															console.log(data);
															result();
															var dataString = "";
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				dataString += (index + 1)
																						+ ". "
																						+ value
																						+ "\n ";
																			});
															//$("#res").empty();
															$("#res")
																	.append(
																			lusterType
																					+ " Minerals with specific gravity range:"
																					+ text
																					+ "\n"
																					+ dataString);
														}
													});
										});
						$("input[name=cle]")
								.click(
										function() {
											var status = $(
													"input[name=cle]:checked")
													.val();
											if (status == "yes") {
												$("#set").show();
											} else {
												$("#set").hide();
												$("#angle").hide();
												var lusterType = $(
														"input[name=luster]:checked")
														.val();
												$
														.ajax({
															type : "GET",
															url : controllerUrl
																	+ ""
																	+ lusterType
																	+ "/nocleavage",
															data : "",
															//cache: false,
															success : function(
																	data) {
																console
																		.log(data);
																result();
																var dataString = "";
																$
																		.each(
																				data,
																				function(
																						index,
																						value) {
																					dataString += (index + 1)
																							+ ". "
																							+ value
																							+ "\n ";
																				});
																//$("#res").empty();
																$("#res")
																		.append(
																				lusterType
																						+ " Minerals with no cleavage: \n <br/>"
																						+ dataString);
															}
														});
											}
										});

						$("#sets")
								.change(
										function() {
											var set = $("#sets").val();
											if (set > 0) {
												$("#angle").show();
											} else {
												$("#angle").hide();
												var lusterType = $(
														"input[name=luster]:checked")
														.val();
												$
														.ajax({
															type : "GET",
															url : controllerUrl
																	+ ""
																	+ lusterType
																	+ "/sets="
																	+ 0 + ","
																	+ 0,
															data : "",
															//cache: false,
															success : function(
																	data) {
																console
																		.log(data);
																result();
																var dataString = "";
																$
																		.each(
																				data,
																				function(
																						index,
																						value) {
																					dataString += (index + 1)
																							+ ". "
																							+ value
																							+ "\n ";
																				});
																//$("#res").empty();
																$("#res")
																		.append(
																				lusterType
																						+ " minerals with cleavage: \n <br/>"
																						+ dataString);
															}
														});
											}
										});

						$("input[name=ang]")
								.click(
										function() {
											var set = $("#sets").val();
											var lusterType = $(
													"input[name=luster]:checked")
													.val();

											var angle = $(
													"input[name=ang]:checked")
													.val();
											$
													.ajax({
														type : "GET",
														url : controllerUrl
																+ ""
																+ lusterType
																+ "/sets="
																+ set + ","
																+ angle,
														data : "",
														//cache: false,
														success : function(data) {
															console.log(data);
															result();
															var dataString = "";
															$
																	.each(
																			data,
																			function(
																					index,
																					value) {
																				dataString += (index + 1)
																						+ ". "
																						+ value
																						+ "\n ";
																			});
															//$("#res").empty();
															$("#res")
																	.append(
																			lusterType
																					+ " minerals with cleavage: \n <br/>"
																					+ dataString);
														}
													});
										});
					});
</script>

<!-- end of functionality  -->

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style="padding-top: 50px;min-height: 452px;margin-left: 0px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">

		<h2>Mineral Identification based on a specific mineral property</h2>

	</section>

	<!-- Main content -->
	<section class="content" style="overflow-y: scroll;">
		<!-- Your Page Content Here -->

		<div  class="col-md-6" style="border: medium;">
			<h3>Please select one luster :</h3>
			<input type="radio" id="luster1" name="luster" value="metallic">
			Minerals with metallic luster <br /> <input type="radio"
				id="luster2" name="luster" value="nonmetallic"> Minerals
			with non-metallic luster <br />

			<div id="properties">
				<h3>Please select a mineral property :</h3>
				<select id="property">
					<option class="placeholder" value="none" selected="" disabled="">Select
						one mineral property</option>
					<option value="hardness">Mineral Hardness</option>
					<option value="streak">Streak Color</option>
					<option value="mcolor">Mineral Color</option>
					<option value="sg">Specific Gravity</option>
					<option value="cleavage">Cleavage</option>
				</select>
			</div>
			<div id="mh">
				<h3>Mineral Hardness :</h3>
				<select id="hardness">
					<option class="placeholder" selected="" disabled="">Select
						one</option>
					<option value="range0" />less than 2.5
					<option value="range1" />2.5 ~ 3.2
					<option value="range2" />3.2 ~ 5.0
					<option value="range3" />5.0 ~ 5.5
					<option value="range4" />5.5 ~ 7.0
					<option value="range5" />greater than 7.0
				</select>
				<h4>(note : Fingernail: ~2.5; Copper Penny: ~3.2; Steel Nail or
					pocket knife blade: ~5.0; Glass Plate: ~5.5; Streak Plate: ~7.0)</h4>
			</div>

			<div id="sc">
				<h3>Streak Color :</h3>
				<form action="">
					<div id="streakCheckbox"></div>
				</form>
				<button id="streak">GO</button>
			</div>

			<div id="mc">
				<h3>Mineral Color :</h3>
				<form action="">
					<div id="mineralCheckbox"></div>
				</form>
				<button id="color">GO</button>
			</div>
			<div id="sg">
				<h2>Specific Gravity :</h2>
				<select id="sgg">
					<option class="placeholder" selected="" disabled="">Select
						one</option>
					<option value="range0" />Light
					<option value="range1" />Average
					<option value="range2" />Above Average
					<option value="range3" />Heavy
					<option value="range4" />Very Heavy
					<option value="range5" />Extremely Heavy
				</select>
				<h4>(Note : < 2.5: Light; 2.5 – 3.0: Average; 3.0 – 4.0: Above
					average; 4.0 – 7.0: Heavy; > 7.0: Very heavy; > 10.0: Extremely
					heavy )</h4>
			</div>
			<div id="cleav">
				<h3>Cleavage :</h3>
				<form action="" id="cleavid">
					<input type="radio" name="cle" value="yes" />Present <input
						type="radio" name="cle" value="no" />Not Present
				</form>
				<div id="set">
					<h3>Number of sets :</h3>
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
					<h3>Angular value :</h3>
					<form action="" id="angleid">
						<input type="radio" name="ang" value="90" />90 <input
							type="radio" name="ang" value="60" />60 / 120 <input
							type="radio" name="ang" value="1" />others <br />
					</form>
				</div>

			</div>


			<div id="result">
				<h3>List of Minerals Result :</h3>
				<textarea type="text" id="res" cols="50" rows="10" readonly> </textarea>
			</div>
			<div id="reset">
				<br />
				<button id="resetbutton" type="reset23">Reset</button>
			</div>
		</div>
		<div id="tables" class="col-md-6" style="border: medium;overflow-y: scroll;">
			<table border="2" width="100%" class="table table-striped table-bordered" id="example" cellspacing="0">
				<tr>
					<th>Hardness min</th>
					<th>Hardness max</th>
					<th>SG min</th>
					<th>SG max</th>
					<th>Streak</th>
					<th>color</th>
					<th>cleavage</th>
					<th>sets</th>
					<th>angle</th>
					<th>Mineral</th>
				</tr>
				<tbody id="mtable">
				</tbody>
			</table>
		</div>


	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
 </c:when>
<c:otherwise>
<c:redirect url="/GeoApp/view/login" />
</c:otherwise>
</c:choose>
<%@ include file="./Footer.jsp"%>