<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="./InstructorSideBar.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<div class="content-wrapper"> 
	<!-- Content Header (Page header) -->
	<br> <br>
	<h3>Create Pool of Questions</h3>

	<section class="content">

		<section class="content-header">
			
		</section>
		<div>
		
		<form action="${pageContext.request.contextPath}/Pool/uploadandreadtextdoc" method="post" >     

			 	
					
					<table>
						<tr>
							<td>Pool Name:</td>					
							<td><input type="text" name="PoolName" ></td>
						</tr>
						
						<tr>
							<td>No. of Questions in Quiz:</td>
							<td><input type="text" name="QuestionsinQuiz" ></td>
						</tr>
						
						<tr>
							<td>Qualifying Percent:</td>
							<td><input type="text" name="qualPercent" ></td>
						</tr>
						
						<tr>
							<td>No. of takes allowed:</td>
							<td><input type="text" name="takes" ></td>
						</tr>
					

						<tr>
					
							<td>Upload quiz:</td>  
							<td><input type="file" name="documentcontent1" id="documentcontent1" required /> </td>
						</tr>
					
					</table> <br>
							<p>                                                    
		        			<input type="submit" id="submitBtn" name="submitBtn"  value="Submit" />
		        			</p>
			        	
		        	
		</form>
</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>