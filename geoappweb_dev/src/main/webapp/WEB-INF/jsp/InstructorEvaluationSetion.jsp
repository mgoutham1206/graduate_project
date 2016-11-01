<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!-- Content Wrapper. Contains page content -->
<%--   <div class="content-wrapper">  --%>
<!-- Content Header (Page header) -->

<div class="content-wrapper">
<!-- Main content -->


<br><br><br><br> 
<section class="content" >

	
			
			<c:if test="${!empty InstructorEvaluationSetion}">

				
							<center>
								<h4>Sections</h4>
							
							
							<table border="1" style="width: 50%" cellpadding="10">
									<th>Section Id</th>
									<th>
										Section Title
									</th>
									<th> Brief Desciption</th>
									<th >Year</th>
								
						

							<c:forEach items="${InstructorEvaluationSetion}" var="sec">


								
									<tr> <td> ${sec.sectionID} </td> 
									
									<td>	<a href="viewAssign?sectionID=${sec.sectionID}">
											${sec.title} </a> </td>
									<td>${sec.basicdescription}</td>
									<td>${sec.year}</td></tr>
								
						
						</c:forEach>
						</table>
						</center>
			</c:if>

		
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<!-- /.content-wrapper -->

<%@ include file="./Footer.jsp"%>