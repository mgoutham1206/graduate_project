<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./GeoAppLoginHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
		<div style="width: 50%; margin: 10px auto;">

			<form:form method="post" action="/GeoApp/view/instructor"
				modelAttribute="instructor" id="loginDiv">
				<p style="color: orange;font-size: 20px">
					<img style="margin: 0 10px 0 0;"
						src="/GeoApp/template/pics/lock.png" />Login
				</p>
				<div>
					<form:input path="loginName" style="font-size: 20px;"
						placeholder="Username" />
				</div>
				<div>
					<form:password path="userPassword" style="font-size: 20px;"
						placeholder="Password" />
				</div>
				<div>
					<input type="submit" class="btn" value="Login"  />
				</div>
				<div>
					<%-- <%=request.getAttribute("messageInfo") %> --%>
					${messageInfo}
				</div>
			</form:form>
			<a id = "EnrollId">Enroll Now</a> 
			| <a id = "forgetId">forget Password?</a>
		</div>
		
	</section>
	
	<script>
	/* function showMessage() */
	var url = window.location.pathname.substring(0,window.location.pathname.indexOf('/view',0));
	console.log = url;
	
	window.onload = function () {
		document.getElementById("EnrollId").href = url+"/student/enrollment/getAllClassesPage";
		document.getElementById("forgetId").href = url + "/view/forgetPassword";
	}
	</script>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>