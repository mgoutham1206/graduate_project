<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ include file="./InstructorSideBar.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">

function goToAssgnList(){
	location.href = "/GeoApp/view/instructor/openSection/listAssignments?secId=${secId}";
}

 function getvalueDate () {
	
	var stringDate = document.getElementById("dateId").value.toString();
	var res = stringDate.split("-");
	var dateStringAppend = res[1]+"/"+res[2]+"/"+res[0];
	document.getElementById("hiddenDateId").value = dateStringAppend;	
}
 

 
window.onload = function() {
	
	$.ajax({
		type : "GET",
		url : "/GeoApp/Pool/getPools/",
		data : "",
		success : function(data) {
			var existsOrNot = "";
			$.each(data, function () {	
			    var str1 = this.poolName.replace(/\s+/g, '');
			    if(existsOrNot.indexOf(str1) < 0 || existsOrNot == ""){
			    	$('#poolName').append($('<option value="' + this.poolName + '">' + this.poolName + '</option>'));
			    	existsOrNot += str1 + " ";
			    }			    			    	    		    
			});
		}

	});
	
	//action="/GeoApp/view/instructor/openSection/saveAssignment?sectionId=${secId}"
			
			
	$('#CreateAnAssignment').submit(function(event){
		
		$('#assignmentExistError').html("");
		var assignmentName = $('#assignmentName').val();
		var possiblepointsphase1 = $('#possiblepointsphase1').val();
		var possiblepointsphase2 = $('#possiblepointsphase2').val();
		var possiblepointsphase3 = $('#possiblepointsphase3').val();
		var poolName = $('#poolName').val();		
		var isActive = $("input[name=isActive]:checked").val();
		//04/20/2016
		//2016-02-12
		var hiddenDateIdTemp = $('#hiddenDateId').val().split("/");
		var hiddenDateId = hiddenDateIdTemp[2] + "-" + hiddenDateIdTemp[0] + "-" + hiddenDateIdTemp[1]; 
		//var hiddenDateId = $('#hiddenDateId').val();
		var description = $('#description').val();
		
		
		var sectionID = '${secId}';	
		
           	/* var sectionID = 200001;
 */		
	 	var json = {				
			'name': assignmentName,
			'possiblepointsphase1': possiblepointsphase1,
			'possiblepointsphase2': possiblepointsphase2,
			'possiblepointsphase3': possiblepointsphase3,
			'isActive': isActive,			
			'description':description
			/* 'due_date': hiddenDateId */
		};
		
		$.ajax({
			
			url : window.location.pathname.substring(0,window.location.pathname.indexOf('/newAssign',0)) +"/saveAssignment/"+sectionID+ "/" +poolName + "/" +hiddenDateId,
			data : JSON.stringify(json),	
			type : "POST",
			contentType : 'application/json',
	       	dataType: "text", 
	
		}).then(function(data, status, jqxhr) {
			
			if(data == "true"){
				
				window.location = window.location.pathname.substring(0,window.location.pathname.indexOf('/newAssign',0))+"/saveAssignmentsuccess/"+sectionID;           
				/* window.location.href = "http://localhost:8080/success.jsp"; */
			}else{
			 $('#assignmentExistError').html("Assignment Name already exists");		
			}					
		});
		event.preventDefault();
		
	});			
			
			
			
			
			
	
    document.getElementById("dateId").value = document.getElementById("hiddenDateId").value;
    
    var oldDate = document.getElementById("hiddenDateId").value;
    var oldDateObj = new Date(oldDate);
    if(oldDate == "") {
    	oldDateObj = new Date();
    } 
    
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; 
    var yyyy = today.getFullYear();
	var maxyyyy = today.getFullYear()+1;
    if(dd<10) {
        dd='0'+dd
    } 

    if(mm<10) {
        mm='0'+mm
    } 
    today = yyyy+'-'+ mm+'-'+dd;
    var todayDateObj = new Date(today); 
    var maxDate = new Date();
    maxDate = maxyyyy+'-'+ mm+'-'+dd;
    	
    if(todayDateObj <= oldDateObj)
    document.getElementById("dateId").min = yyyy+'-'+ mm+'-'+dd ;
    else
    document.getElementById("dateId").min = document.getElementById("hiddenDateId").value ;
    document.getElementById("dateId").max = maxyyyy+'-'+ mm+'-'+dd;
    
    var possiblepointsphase1 = $('#possiblepointsphase1').val() + '';
    var possiblepointsphase2 = $('#possiblepointsphase2').val() + '';
    var possiblepointsphase3 = $('#possiblepointsphase3').val() + '';
    possiblepointsphase1 = possiblepointsphase1.replace(new RegExp('\.00$'), '');
    $('#possiblepointsphase1').val(possiblepointsphase1)
    
    possiblepointsphase2 = possiblepointsphase2.replace(new RegExp('\.00$'), '');
    $('#possiblepointsphase2').val(possiblepointsphase2)
    
    possiblepointsphase3 = possiblepointsphase3.replace(new RegExp('\.00$'), '');
    $('#possiblepointsphase3').val(possiblepointsphase3)
};
</script>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
		
		<form id="CreateAnAssignment" method="post">
			<table>
				<tr>
					<td>Assignment Name:</td>
					<td><input id = "assignmentName" placeholder="Assignment Name" pattern="^[a-zA-Z]+*+[0-9]$" required="true" maxlength = "100"
					    oninvalid="setCustomValidity('Assignment name should be alphanumeric Ex: Assignment 1')"
    					onchange="try{setCustomValidity('')}catch(e){}"/></td>
    					<td><p id="assignmentExistError" style="color: red"></p></td>
				</tr>
				<tr>
					<td>Possible Points Phase1:</td>
					<td><input id="possiblepointsphase1" placeholder="Enter only integers"  pattern="^[0-9][0-9]?$|^100$" required="true"
						oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integer values  Ex: 10')"
    					onchange="try{setCustomValidity('')}catch(e){}"/></td> 					
				</tr>
				<tr>
					<td>Possible Points Phase2:</td>
					<td><input id="possiblepointsphase2" placeholder="Enter only integers" pattern="^[0-9][0-9]?$|^100$" required="true"
					oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integer values   Ex: 10')"
    					onchange="try{setCustomValidity('')}catch(e){}" /></td>
				</tr>
				
				<tr>
					<td>Possible Points Phase3:</td>
					<td><input id="possiblepointsphase3" placeholder="Enter only integers" pattern="^[0-9][0-9]?$|^100$" required="true"
					oninvalid="setCustomValidity('Allowable range for points is 0-100 and only integer values   Ex: 10')"
    					onchange="try{setCustomValidity('')}catch(e){}" /></td>
				</tr>
				
				<tr>
                        	<td>Select Quiz Pool:</td>
	                        <td><select id="poolName" name="poolName">                                        
	                        	<option >Select a Pool</option>                                            
	                        	</select>
	                        </td>                       
				</tr>
				
				<tr>
					<td>Is Active:</td>
					<td> <input type="radio" name="isActive" value="Y"> Yes
						<input type="radio" name="isActive" value="N" required="true"> No
					</td>
					<!-- <!-- <button type="" id="isActive" value="Yes"></button> 
							id="isActive" value="N" required="true"/> No</td>-->
				</tr>
				<tr>
					<td>Due Date:</td>
					<!--  new SimpleDateFormat("MM/dd/yyyy").format(new Date()); -->
					<td><input type ="date" id="dateId"  onchange="getvalueDate()" required="true"/>
						<input type="hidden" id="hiddenDateId"/>
					</td>
				</tr>
				<tr>
					<td>Instructions:</td>
					<td><textarea style="resize:none" rows="5" cols="40" id="description" maxlength = "5000" placeholder="Maximum limit is 5000 characters"/></textarea></td>
				</tr>
				
				<tr>
					<td><hidden id="assignID"
							value="${assignment.getAssignID()}" /></td>
					<td><c:choose>
							<c:when test="${assignment.getAssignID() == 0}">
								<input type="submit" id="poolNameValidation" class="btn" value="Save" />
							</c:when>
							<c:otherwise>
								<input type="submit" class="btn" value="Update" onclick="getvalueDate()" />
							</c:otherwise>
						</c:choose> <input type="button" onclick="goToAssgnList()" class="btn" value="Cancel" /></td>
				</tr>
			</table>
		</form>

			
	</section>
	<!-- /.content -->
</div>

<script type="text/javascript">
/*  $('#poolNameValidation').submit(function(){
	 if($('#poolName').val() == 'Select a Pool'){
		 alert("poolNameValidation");
		 return false;
	 }
	  
});  */
</script>


<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>