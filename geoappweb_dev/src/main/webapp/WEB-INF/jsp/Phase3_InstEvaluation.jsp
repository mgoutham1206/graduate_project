<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script>

$(document).ready(function() {
	$.getJSON("/GeoApp/rest/instructor/findSubmissionsByAssignmentID?assignmentId="
			+ assignmentID, function(data) {
		console.log(data);
		var submissionData = data;
		for (var i = 0; i < submissionData.length; i++){
		console.log(submissionData[i].split(",")[0]+"NameName")
		
		if(submissionData[i].split(",")[0]==(submissionID)){
			console.log(submissionData[i].split(",")[2]+"jsfhasjfasfsdfsdjfns")
			document.getElementById("stuid").innerHTML=submissionData[i].split(",")[1]
			document.getElementById("stuname").innerHTML=submissionData[i].split(",")[2];
		}
		
	}
		
		
});
});

$( document ).ready(function() {
	/** on clicking submit. add comments to database **/
	
	 $( "#back" ).click(function() {
		 
		 window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID")%>';
	 });
	
	 $.ajax({
		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
		 url : "/GeoApp/rest/instructor/getFeedbackbyPhase/"+ submissionID+"/3",
					}).then(function(data, status, jqxhr) {
						console.log(data[0]===NaN +"********************");						
						if(data[0]==undefined)
							document.getElementById("commentsTxtId").value="";
						else
						document.getElementById("commentsTxtId").value=data[0];
						console.log(data[0]+"********************");
						if(data[1]=="undefined")
							document.getElementById("pointsID").value=null;
						else
						document.getElementById("pointsID").value=data[1];
						
						if(data[2]=="Y")
							{
							console.log("***********************",data[2])
							document.getElementById("submit").style.display = "none";
							
							}
						
						/* document.getElementById("myTable").innerHTML += "<tr><td><img src='data:image/png;base64,'"+data[i].imageList+" width='100' height='100'"
	       +"alt='Rock Image' /></td><td><input type='text' value="+data[i].longitude+" class='lang' disabled='disabled'  placeholder='longitude'></td>"
									+ "<td><input value="+data[i].latitude+" type='text' class='long' disabled='disabled' placeholder='latitude'></td>"
									+ "<td><input type='button' value='&#10004;' class='right'  id='"
									+ data[i].imageID
									+ "_right'  onclick='evaluateRock(this)'>"
									+ "</td>"
									+ "<td><input type='button' value='&#10008;' class='wrong'  id='"
									+ data[i].imageID
									+ "_wrong' onclick='evaluateRock(this)'></td>"
									+ "<td><input type='text' class='comment' id='"+data[i].imageID+"' name='imageComment' placeholder='comment' ></td><td><span id="+data[i].imageID+"_span></span></td></tr>"; */
									
						/*	document.getElementById("myTable").innerHTML += '<tr><td><img src= "data:image/png;base64,'+data[i].imageList+'"width="100" height="100"/></td></tr>';*/
							 	
						
					});
});

	
	var rocknameData = new Object();
	
            function nextPage() {
            	 
            	//Added VAli
            	if(document.getElementById("commentsTxtId").value==null || document.getElementById("commentsTxtId").value==""){
				alert("Please enter Overall feedback!")
            	return;
					}
				if(document.getElementById("pointsID").value==null || document.getElementById("pointsID").value==""){
				alert("Please enter total points!")
				return;
				}
				if(evaloverall == false){
				alert("Please Accept or reject total phase")
				return;
				}
				if(document.getElementById("pointsID").value>totalPoints){
					alert("Maximum points"+totalPoints);
					return;
					}
	
            	
            	
            	rocknameData.Points = document.getElementById("pointsID").value;
            
            		rocknameData.Comments=document.getElementById("comment");	

            	console.log(rocknameData);
            	
            	 var comments= document.getElementById("commentsTxtId").value;
     			/** sending GET ajax request for updating comments **/	
     				 $.ajax({
     					 url : "/GeoApp/rest/instructor/saveCommentsForPhase3?comments="+comments+"&submissionID="+ submissionID+"&rocknameData="+ JSON.stringify(rocknameData),
     				    }).then(function(data, status, jqxhr) {
     				    	
     				    	console.log(data);
     				    	 
     				    	alert("Phase-3 of the assignment has been evaluated");
     				    	
     				    	window.location.href = window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID")%>';
     				    	
     		
     				    })
     			
            }
            
            var evaloverall = false;

            function evaluateRock(Tobj){
            	
            	evaloverall  = true;
        		
        		console.log(Tobj);
        		
        		
        		if(Tobj.getAttribute("class")=='right'){
        			
        			Tobj.setAttribute("class","right_selected");
        			
        			document.getElementById(Tobj.id.split("_")[0]+"_wrong").setAttribute("class","wrong");
        			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:green'>Accepted</font>";
        			if(Tobj.id.split("_")[0] == "overall"){
            			
        				rocknameData.overall= "Y";
        						
        				
        			}else{
        			rocknameData.IsAccepted= "Y";
        			
        		}
        		
        		}else if(Tobj.getAttribute("class") =='wrong'){
        			
        			
        			Tobj.setAttribute("class","wrong_selected");
        			
        			document.getElementById(Tobj.id.split("_")[0]+"_right").setAttribute("class","right");
        			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:red'>Rejected</font>";
					if(Tobj.id.split("_")[0] == "overall"){
            			
						rocknameData.overall= "N";
        						
        				
        			}else{
        			rocknameData.IsAccepted= "N";
        			
        			}	
        		
        		}  
        		
        	}
            
            var submissionID =  '<%=request.getAttribute("submissionID")%>';
            var assignmentID =  '<%= request.getAttribute("assignmentID") %>';
            var totalPoints=0;
            $.ajax({
       		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
       		 url : "/GeoApp/rest/instructor/getPossiblePointsByPhase/"+ submissionID+"/3",
       					}).then(function(data, status, jqxhr) {
       						console.log(data+"harsha********");
       						totalPoints=data;
       						document.getElementById("totalpoints").innerHTML=data;
       						
       					}
       					);

            
	  $.ajax({
			 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
			 url : "/GeoApp/rest/instructor/getPhase3?submissionID="+ '<%=request.getAttribute("submissionID")%>',
						}).then(function(data, status, jqxhr) {

							console.log(data);
							document.getElementById("myTable").innerHTML += "<th>Rock Name</th> <th> Rock Type </th> <th> Description </th>";
							for (var i = 0; i < data.length; i++) {

															
								console.log(data[i].imageList);								

								 document.getElementById("myTable").innerHTML += '<tr><td>'+data[i].rockName+' </td>'
															+ '<td>'+data[i].rockType+' </td>'
															+ '<td>'+data[i].description+' </td>'
															+ '<td></td>'
															+ '<td></td>'
															+ '<td></td><td><span id='+data[i].rockID+'_span></span></td></tr>';
															
							}
						});
	  
	  
	
	  	
	  	

	  
	  
	</script>
	
	<!-- Content Header (Page header) -->

	<section class="content" style="background-color:white"">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
	<form action="">	
		
			
			
			<div class="mapview">
	<map name="navImage">
    <area shape="circle" coords="48,84,22"  href="phase1?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    <area shape="circle" coords="250,84,22" href="phase2?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    <area shape="circle" coords="440,84,22" href="phase3?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    
	</map>
	</div>
	<center>
				<img src="../template/pics/Phase3img.PNG" width="532" height="147"
					alt="Capture1" usemap="#navImage" />
			</center>
	
		
<h4>Student Name: <b><span id="stuname"></span></b></h4>
			<h4>Student ID: <b><span id="stuid"></span></b></h4>
		
			<table style="border-collapse: separate; border-spacing: 1.5em;"
				id="myTable">


			</table>
			
			Total Phase accepted?:
		<td colspan=2><input type="button" value="&#10004;" class="right"
			id="overall_right" onclick="evaluateRock(this);"> <input
			type="button" value="&#10008;" class="wrong" id="overall_wrong"
			onclick="evaluateRock(this);"></td>
		<td><span id="overall_span"> </span>
		<td colspan="3"></td>
		</td> <br> <br>
			
		Comment :
		<textarea style="width: 300px; height: 75px;" placeholder="Comments"
			id="commentsTxtId"></textarea>
		
		Points : <input type="number" class="points"
			placeholder="Phase 3 points" id="pointsID">
			Total possible points:<span id="totalpoints"></span>
			
		<input type="button" style="float: right" class="btn btn-primary" id="back"
					value="BackToSubmissions" />
		<input type="button" value="SubmitPhase3Evaluation" style="float: right;"
			onclick="nextPage();" class="btn btn-primary" />
		


	</form>
	</section>
	
	<%@ include file="./Footer.jsp"%>