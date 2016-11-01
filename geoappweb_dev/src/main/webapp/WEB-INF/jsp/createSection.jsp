      
       <%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>

<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./SectionHeader.jsp"%>

<%@ include file="./InstructorSideBar.jsp" %>

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
  RequestDispatcher rd = request.getRequestDispatcher("redirect:/GeoApp/view/login");
  rd.include(request, response);
  out.println("Session has ended.  Please login.");
}
%>
            
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<center><h1>Create Section</h1></center>
				
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
   <div class="row">
				<div class="col-md-3">
				</div>
				<div class="col-md-6">
		        <form id="submitClass" method="post" class="form-horizontal">
                    <label>Class Name</label><input type="text"   class="form-control" id="name" placeholder="Enter alphabets and numbers" pattern="[A-Za-z0-9 ]{0,25}" maxlength="25" required/><br>
                     <label>Class Year</label><input type="number"  class="form-control" min="2016" max="2099" pattern="/^\d+$/" id="year"   placeholder="Enter 2016 or more" required/><br>
                     <% %>
					<!-- <label> Class URL</label><input type="text" class="form-control" id="url" value="" required/><br> -->
					 <label>Class Description</label><textarea rows="3" id="description" col="10"  class="form-control"  placeholder="Description shoulde be less than 50 characters"  maxlength="50" required></textarea>
                     <label>Expected Enrollment Students:</label> <input type="number" min="0" max="50" class="form-control"  id="expectedNoofStudents" required  value=""/><br>
                     <label>Class Password</label> <input type="text" class="form-control" minlength="4" maxlength="10" placeholder="Enter min 4 characters and max 10 characters" id="password"  required/><br>
					
					 <label>Students can be enrolled:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" name="name" id="yes" value="Y"/> YES &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" name="name" id="no" checked value="N"/>NO<br>
					 
					 
					
				     <label>Term:</label><select id="season" required class="form-control">
				                                    
				                                    <option>Fall</option>
				                                    <option>Spring</option>
				                                    <option>Summer</option>
				                                </select><br>
					<center><button type="submit" class="btn btn-lg btn-primary" id="submit"  value=${instructorID} >Submit</button>
					 </center> 
					 </form>
			 	</div>
			 	<div class="col-md-3">
				</div>
		 	</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
 

	<script>
	$( document ).ready(function() {		
		$("#submitClass").submit(function() {
			//function createSec(sectionid){
				//alert(sectionid);
				var sectionid = document.getElementById('submit').value;
				console.log(sectionid);
				var yearValue = document.getElementById('year').value;
				
				if((yearValue.indexOf(".")) > 0) {
					alert("Please provide valid year with out decimals");
					
				}
				
			 console.log( "ready!" );
			 var yesOrNo;
			 var season;
			 if(document.getElementById("yes").checked){
				 console.log(document.getElementById("yes").checked);
				 yesOrNo = 'Y';
			 }else {
				 yesOrNo = 'N';
			 }
			 if($('#season').val()==='Fall'){
				 season = 1;
				 
			 } else if($('#season').val()==='Spring'){
				 season = 2;
			 } else {
				 season = 3;
			 }
			 
			 var name = document.getElementById(name);
			 var url = document.getElementById(url);
			 var password = document.getElementById(password);
			 var data ={
						'title' :$('#name').val(),
						'classURL' : $('#url').val(),
						'enrollmentPassword' : $('#password').val(),
						'expectedNoofStudents' : $('#expectedNoofStudents').val(),	
						'year': $('#year').val(),
						'basicdescription' : $('#description').val(),
						'allowEnrollement': yesOrNo
						
				};
				console.log(data);
				$.ajax({
				url : "/GeoApp/rest/section/CreateSection/"+season+"/"+sectionid,
				data : JSON.stringify(data),
				type : "POST",				
				contentType: 'application/json',
				dataType: "text",				
				success : function(data) {
					//alert("Welcome "+data+ ", Account has been created successfully");
					window.location.assign("success");
				},
			    complete: function () {
			    	 
			    }
			});
			event.preventDefault();		
			
		});			   
	});
	</script>
	<%@ include file="./Footer.jsp" %>
	</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>
	