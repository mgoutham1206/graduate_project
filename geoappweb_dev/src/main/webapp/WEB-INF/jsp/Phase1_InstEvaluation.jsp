<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



	<!-- Content Header (Page header) -->
	<section class="content" style="background-color:white">

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
				<img src="../template/pics/Phase1img.PNG" width="532" height="147"
					alt="Capture1" usemap="#navImage" />
			</center>
			
			
			<h4>Student Name: <b><span id="stuname"></span></b></h4>
			<h4>Student ID: <b><span id="stuid"></span></b></h4>
			<%-- <br> <br> Upload Pictures of the Rock for Submission Id : '<%= request.getAttribute("submissionID") %>' <br> <br>--%>
			<table style="border-collapse: separate; border-spacing: 1.5em;"
				id="myTable">

			</table>
			Total Phase accepted?:
			<td colspan=2><input type="button" value="&#10004;"
					class="right" id="8_right" onclick="evaluateRockTotal(this);"> <input
					type="button" value="&#10008;" class="wrong" id="8_wrong"
					onclick="evaluateRockTotal(this);"></td>
					<td><span id="8_span"> </span><td colspan="3"></td></td>	<br><br>
			
		Comment :
		<textarea style="width: 300px; height: 75px;" placeholder="Comments"
			id="commentsTxtId" required> </textarea>
		
		<%-- Added Text filed for points  --%>
		
		Points : <input type="number" class="points"
			placeholder="Phase 1 points" id="pointsID" required>
		
		Total possible points:<span id="totalpoints"></span>	 
			
			<input type="button" style="float: right" class="btn btn-primary" id="back"
					value="BackToSubmissions" /> 
						
						
				
				<input type="button"  style="float: right" class="btn btn-primary" id="submit"
					value="SubmitPhase1Evaluation" onclick="val()" />
		
		
	</form>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>

var evaloverall = false;
function val()
{
  
}
	
	function evaluateRock(Tobj){
		
		console.log(Tobj);
		
		if(Tobj.getAttribute("class")=='right'){
			
			Tobj.setAttribute("class","right_selected");
			document.getElementById(Tobj.id.split("_")[0]+"_wrong").setAttribute("class","wrong");
			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:green'>Accepted</font>";
			/** iterating array **/
			for(var i=0; i< updateImageSubmissionData.length; i++){
				
				if(updateImageSubmissionData[i].imageID == Tobj.id.split("_")[0]){
					
					updateImageSubmissionData[i].imageIsAccept = "Y";
				console.log(updateImageSubmissionData[i].imageIsAccept);
					return;
					
				}
				
			}			 
			
		}else if(Tobj.getAttribute("class") =='wrong'){
						
			Tobj.setAttribute("class","wrong_selected");
			
			document.getElementById(Tobj.id.split("_")[0]+"_right").setAttribute("class","right");
			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:red'>Rejected</font>";
			/** iterating array **/
			for(var i=0; i< updateImageSubmissionData.length; i++){
				
				if(updateImageSubmissionData[i].imageID == Tobj.id.split("_")[0]){
					
					updateImageSubmissionData[i].imageIsAccept = "N";
					return;
					
				}
				
			}			
		}  
		
	}
	
function evaluateRockTotal(Tobj){
	evaloverall=true;
		console.log(totaleval);
		
		if(Tobj.getAttribute("class")=='right'){
			
			Tobj.setAttribute("class","right_selected");
			document.getElementById(Tobj.id.split("_")[0]+"_wrong").setAttribute("class","wrong");
			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:green'>Accepted</font>";				
					totaleval="Y";					
					// updateImageSubmissionData[i].imageIsAccept = "Y";
					//console.log(updateImageSubmissionData[i].imageIsAccept);
					return;
	
		}else if(Tobj.getAttribute("class") =='wrong'){
			Tobj.setAttribute("class","wrong_selected");
			
			document.getElementById(Tobj.id.split("_")[0]+"_right").setAttribute("class","right");
			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:red'>Rejected</font>";
			/** iterating array **/
			
					totaleval="N";
					//updateImageSubmissionData[i].imageIsAccept = "N";
					return;			
		}  
		
	}
	
	function isBestClicked(obj){
		
		var imageID = obj.id.split("_")[0];
		
		for(var i=0;i<updateImageSubmissionData.length;i++){
			
			if(updateImageSubmissionData[i].imageID == imageID){
				
				updateImageSubmissionData[i].imageIsBest ="Y";
			}else{
				
				updateImageSubmissionData[i].imageIsBest ="N";
			}
		}
		
		
	}
	
	var updateImageSubmissionData =[];
	
	var totaleval="N";
	var totalPoints=0;
	
	
	var submissionID =  '<%= request.getAttribute("submissionID") %>';
	var assignmentID =  '<%= request.getAttribute("assignmentID") %>';
	
	$.ajax({
		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
		 url : "/GeoApp/rest/instructor/getPossiblePointsByPhase/"+ submissionID+"/1",
					}).then(function(data, status, jqxhr) {
						console.log(data+"harsha********");
						totalPoints=data;
						document.getElementById("totalpoints").innerHTML=data;
						
					}
					);
	
	$.ajax({
		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
		 url : "/GeoApp/rest/instructor/getFeedbackbyPhase/"+ submissionID+"/1",
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
	
	
	
	/** ajax request for retrieving phase 1 details with submissionID **/
	 $.ajax({
		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
		 url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>',
					}).then(function(data, status, jqxhr) {

						console.log(data);
						document.getElementById("myTable").innerHTML += "<th>IsBest</th> <th> Rock Image </th> <th> Longitude </th> <th> Latitude </th><th></th><th colspan=2> Is Accepted ?</th> <th> comments </th>";
						var phase1evalimg=""; 
						for (var i = 0; i < data.length; i++) {

							updateImageSubmissionData.push({
								"imageID" : data[i].imageID,
								"imageComment" : "",
								"imageIsAccept" : "Y",
								"imageIsBest" : "N"

							});							
							console.log(data[i].comments);
							

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
							 phase1evalimg += '<tr><td> <input type="radio" name="image" onclick="isBestClicked(this);" '+data[i].isEvaluated+' id="'+data[i].imageID+'_radio" value=""></td><td><img src="data:image/png;base64,'+data[i].imageList+'" width="100" height="100"'
						       +'alt="Rock Image" /></td><td>'+data[i].longitude+' </td>'
														+ '<td>'+data[i].latitude+' </td>'
														+ '<td>'+data[i].description+' </td>'
														+ '<td><input type="button" value="&#10004;" class="right"  id="'
														+ data[i].imageID
														+ '_right"  onclick="evaluateRock(this)">'
														+ '</td>'
														+ '<td><input type="button" value="&#10008;" class="wrong"  id="'
														+ data[i].imageID
														+ '_wrong" onclick="evaluateRock(this)"></td>'
														+ '<td><input type="text" class="comment" id="'+data[i].imageID+'" value="'+data[i].comments+'" name="imageComment" placeholder="comment" required ></td>';
														
														if(data[i].isAcceptedPhaseOne=="Y")
														phase1evalimg+='<td><span id='+data[i].imageID+'_span>Accepted</span></td></tr>';
														else
														phase1evalimg+='<td><span id='+data[i].imageID+'_span>Rejected</span></td></tr>';
						}
						document.getElementById("myTable").innerHTML=phase1evalimg;
					});
	
	

//Getting student ID and Name
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
//Code for Saving comments
$( document ).ready(function() {
		/** on clicking submit. add comments to database **/
		
		 $( "#back" ).click(function() {
			 
			 window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID")%>';
			 
		 });
		 $( "#submit" ).click(function() {			
			
			var imageComments = document.getElementsByName("imageComment");			
			
			for(var j=0; j< imageComments.length; j++){
						updateImageSubmissionData[j].imageComment = imageComments[j].value;
			}
			
			console.log(updateImageSubmissionData);
			
			 var comments= document.getElementById("commentsTxtId").value;
			 if(document.getElementById("commentsTxtId").value==null || document.getElementById("commentsTxtId").value=="")
				{alert("Please enter Overall feedback!");return;}
				if(document.getElementById("pointsID").value==null || document.getElementById("pointsID").value=="")
				{alert("Please enter total points!");return;}
				if(evaloverall == false)
				{alert("Please Accept or reject total phase");return;}
				if(document.getElementById("pointsID").value>totalPoints){
					alert("Maximum points"+totalPoints);
					return;
					}
			/** sending GET ajax request for updating comments **/	
				 $.ajax({
					 //url : "http://localhost:8080/rest/instructor/saveComments?comments="+comments+"&submissionID="+ submissionID+"&imageIDData="+JSON.stringify(updateImageSubmissionData),
					 url : "/GeoApp/rest/instructor/saveComments?comments="+comments+"&submissionID="+ submissionID+"&imageIDData="+JSON.stringify(updateImageSubmissionData)+"&points="+document.getElementById("pointsID").value +"&overallAccept="+totaleval,
					// url : "http://localhost:8081/GeoApp/rest/instructor/saveComments?comments="+comments+"&submissionID="+ submissionID+"&imageIDData="+JSON.stringify(updateImageSubmissionData)+"&points="+document.getElementById("pointsID").value,
				    }).then(function(data, status, jqxhr) {
				    	
				    	console.log(data);
				    	console.log(totaleval);
				    	
				    	alert("Phase-1 of the assignment has been evaluated");				    	
				    	
				    	window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID")%>';
				    			
				    })
			event.preventDefault();				
		});			   
	}); 	 
	
	

</script>


<%@ include file="./Footer.jsp"%>