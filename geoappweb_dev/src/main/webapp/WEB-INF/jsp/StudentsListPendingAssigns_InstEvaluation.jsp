<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



	<!-- Content Header (Page header) -->

	<section class="content" style="background-color:white">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
<div class="container" style="background-color:white">
	<main id="main">
	<div class="innertube1">
		
		

		
		<br>
		<br>
		<div id="StuList">
            <form action="RockImages.html" style="
    padding-bottom: 500px;
">
                
                <input type="radio" name="StuID" value="Stu1" color:"white">S5221765/John 
                <button type="submit">Grade Submission </button><br>
                <input type="radio" name="StuID" value="Stu1">S5221764/Adam
                <button type="submit">Grade Submission</button><br>
                <input type="radio" name="StuID" value="Stu1">S5221767/Ray
                <button type="submit">Grade Submission</button><br>
            </form>
        </div>

	</div>
	</main>


	
</div>


<%@ include file="./Footer.jsp"%>

