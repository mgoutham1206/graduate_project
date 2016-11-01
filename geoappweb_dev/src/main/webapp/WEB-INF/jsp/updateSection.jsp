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
				<center><h1>Update Section</h1></center>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
				
				
				
   <div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<form id="submitClass" method="post" class="form-horizontal">
				<%-- <%
				String str = (String)(session.getAttribute("instructorSession"));
				System.out.println(str+""+session.getAttribute("instructorSession"));
				
				%>
				<%=str%> --%>
					 <label>Change Class Name:</label><input type="text"  class="form-control" id="title" required placeholder="Enter alphabets and numbers" pattern="[A-Za-z0-9 ]{0,25}" maxlength="25" value="" required/><br>
					<!--  <label>Change Class Name</label><input type="text"   class="form-control" id="name"  pattern="[A-Za-z0-9]{0,25}" maxlength="25" required/><br> -->
					<label>Class Year</label><input type="number"  class="form-control" min="2016" max="2099" pattern="[0-9]{4}" id="year" step="1"   placeholder="Enter 2016 or more" required/><br>
					<label>Class Description</label><textarea rows="3" id="description" col="10" class="form-control" placeholder="Description shoulde be less than 50 characters"  maxlength="50" required></textarea>
					 <!--  <label>Change Class URL:</label><input type="text" class="form-control" id="classURL" required value="" required/><br> -->
					 <label>Expected Enrollment Students:</label> <input type="number" min="0" max="50" class="form-control" required id="expectedNoofStudents" required  value=""/><br>
					 <label>Change Password:</label> <input type="text" id="password" class="form-control"  minlength="4" maxlength="10" placeholder="Enter min 4 characters and max 10 characters" required  value=""/><br>
					 <label>Students can be enrolled:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" name="name" id="yes" value="Y"/> YES &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" name="name" id="no" value="N"/>NO<br>
					 <label>Term:</label><select id="season" required class="form-control">
				                                   
				                                    <option>Fall</option>
				                                    <option>Spring</option>
				                                    <option>Summer</option>
				                                </select><br>
	 
					<center> <input type="submit" class="btn btn-lg btn-primary center"  id="submit"  value="Submit"/></center>
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
		var id = window.location.pathname.split("/");	
		$.ajax({			
			url : "/GeoApp/rest/section/getSectionByID/"+id[id.length-1],
			type : "GET",				
			contentType: 'application/json',
			success : function(data) {
				var termType = data["termID"];
				data = data["tblsec"];
				$('#title').val(data.title);
				$('#year').val(data.year);
				$('#classURL').val(data.classURL);
				$('#expectedNoofStudents').val(data.expectedNoofStudents);
                $('#password').val(data.enrollmentPassword);
                $('#description').val(data.basicdescription);
				if(data.allowEnrollement=='Y'){
					document.getElementById("yes").checked = true;					
				} else{
					document.getElementById("no").checked = true;
				}
				if(termType=='1'){
					console.log("Fall");
					$('#season').val("Fall");				
				} else if(termType=='2'){
					console.log("Spring");
					$('#season').val("Spring");
				} else if(termType=='3') {
					console.log("Summer");
					$('#season').val("Summer");
				}else{
					
				}
			},
		    complete: function () {
		    	 
		    }
		});
		event.preventDefault();		
		
		$("#submitClass").submit(function() {
			var yearValue = document.getElementById('year').value;
			if((yearValue.indexOf(".")) > 0) {
				alert("Please provide valid year with out decimals");
				
				
			}
			else {
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
				 var data ={
							'title' :$('#title').val(),
							'classURL' : $('#classURL').val(),
							'year': $('#year').val(),
							'expectedNoofStudents' : $('#expectedNoofStudents').val(),						
							'enrollmentPassword' : $('#password').val(),
							'basicdescription' : $('#description').val(),
							'allowEnrollement': yesOrNo
					};
				 
					$.ajax({
					url : "/GeoApp/rest/section/UpdateSection/"+id[id.length-1]+"/"+season,
					data : JSON.stringify(data),
					type : "PUT",				
					contentType: 'application/json',
					dataType: "text",
					success : function(data) {
						//console.log(data);
					},
				    complete: function () {
				    	window.location.assign("../updated");
				    }
				});
				event.preventDefault();	
			}
			 	
			
		});	
	});
	</script>
	<%@ include file="./Footer.jsp" %>
	</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>
	
