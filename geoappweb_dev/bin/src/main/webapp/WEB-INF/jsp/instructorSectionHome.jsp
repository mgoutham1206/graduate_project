<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
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
	<form action="">		
			
		<div id="loginDiv">
			Welcome <c:out value="${instructor.getName()}" />!!!
		</div>
	</form>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>
