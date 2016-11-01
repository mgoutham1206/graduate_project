
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" style="padding-top: 225px">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
          	<!-- Sidebar Menu -->
				<ul class="sidebar-menu"
					style="height: 10%; float: bottom; margin-top: 50px">

					<!-- Optionally, you can add icons to the links -->
					                    <li class="" role="presentation"><a href="${pageContext.request.contextPath}/view/instructor"><i
                            class="fa fa-home"></i> <span style="font-size: larger">
                                Instructor Home</span></a></li><br> <br>
					
					<li class="" role="presentation"><a href="${pageContext.request.contextPath}/view/createSection"><i
							class="fa fa-link"></i> <span style="font-size: larger">
								Create Class</span></a></li> <br>
					<li class="" role="presentation"><a href="${pageContext.request.contextPath}/view/getSections"><i
							class="fa fa-link"></i> <span style="font-size: larger">
								Manage Class</span></a></li><br>
								<li class="" role="presentation"><a href="/GeoApp/Pool/redirecttoupload"><i
							class="fa fa-link"></i> <span style="font-size: larger">
								Create Quiz Pool</span></a></li>	
										
					
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>
