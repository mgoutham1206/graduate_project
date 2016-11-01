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

			<form:form method="POST" action="/GeoApp/instructor"
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
					<input type="submit" class="btn" value="Login" />
				</div>
			</form:form>
			<a href="/GeoApp/student/enrollment/getAllClassesPage" >Enroll Now</a>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>