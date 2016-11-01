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
		 url : "/GeoApp/rest/instructor/getFeedbackbyPhase/"+ submissionID+"/2",
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

var mineralcount = 0;
$.ajax({

		 url : "/GeoApp/rest/instructor/getPhase2?submissionID="+ '<%=request.getAttribute("submissionID")%>',
			}).then(function(data){
				console.log(data.length+"count");
				mineralcount = data.length;
				document.getElementById("totalmincount").innerHTML=mineralcount;
			});

	var mineralData = new Object();
	var mineralData1 = new Object();
	var count = 0;
	function nextPage1() {	
		//Added Validations
		for(var i=0;i<=6;i++){
	    	//if(document.getElementById((i+1)+"_span").value == null || document.getElementById((i+1)+"_span").value ==""){
	    		//alert("Please enter Mineral feedback!") 	}
	    	if(document.getElementById((i+1)+"_span").innerHTML == "Rejected" && document.getElementById((i+1)+"_comment").value =="" || document.getElementById((i+1)+"_comment").value ==null){
	    		alert("Please enter Mineral feedback!") 	}
	    	}
		
    	for(var i=0;i<=6;i++){
    	mineralData.Attributes[i].Comments=document.getElementById((i+1)+"_comment").value;
    	count++;
    	}
    	console.log(mineralData);
    	
				 $.ajax({
					 url : "/GeoApp/rest/instructor/saveCommentsForPhaseAtt2?mineralData="+ JSON.stringify(mineralData),
				    }).then(function(data, status, jqxhr) {
				    	
				    	console.log(data);
				    	 
				    	alert("Mineral is eveluated!");
				    
				    })
				
    }  
	
            function nextPage() {	     	
            	
            	
            	//Added Validations
            	if(count==0){
    				alert("Please evaluate  minerals feedback!")
                	return;
    					}
            	if(count<mineralcount){
    				alert("Please evaluate remaining mineral properties!")
                	return;
    					}
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
        			
        	
        	
            	mineralData1.Points = document.getElementById("pointsID").value;
               	//for(var i=0;i<=6;i++){
            	//mineralData.Attributes[i].Comments=document.getElementById((i+1)+"_comment").value;
            	//}
            	//console.log(mineralData);
            	
            	 var comments= document.getElementById("commentsTxtId").value;
     			/** sending GET ajax request for updating comments **/	
     				 $.ajax({
     					 url : "/GeoApp/rest/instructor/saveCommentsForPhase2?comments="+comments+"&submissionID="+ submissionID+"&mineralData1="+ JSON.stringify(mineralData1),
     				    }).then(function(data, status, jqxhr) {
     				    	
     				    	console.log(data);
     				    	 
     				    	alert("Phase-2 of the assignment has been evaluated");
     				    
     				    	window.location.href = "/GeoApp/instructor/viewSubmissionsByAssignId?assignmentId="+'<%= request.getAttribute("assignmentID")%>';
     				    	
     		
     				    })
     				    
     			
     			
            }            
 
            var evaloverall = false;
function evaluateRock(Tobj){
        		
	 evaloverall = true;
        		console.log(Tobj);
        		
        		if(Tobj.getAttribute("class")=='right'){
        			
					Tobj.setAttribute("class","right_selected");
        			
        			document.getElementById(Tobj.id.split("_")[0]+"_wrong").setAttribute("class","wrong");
        			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:green'>Accepted</font>";
        
        			
        			if(Tobj.id.split("_")[0] == "overall"){
        			
        				mineralData1.overall= "Y";
        						
        				
        			}else{
        			
        			
        			
        						
        			for(var j=0;j<mineralData.Attributes.length;j++){
        				
        				if(mineralData.Attributes[j].AttributeID== Tobj.id.split("_")[0] ){
        					
        					mineralData.Attributes[j].IsAccepted= "Y";
        					
        				}
        				
        			}
        			}
        			
        			 
        			 
        			
        		}else if(Tobj.getAttribute("class") =='wrong'){
        			
        			
        			Tobj.setAttribute("class","wrong_selected");
        			
        			document.getElementById(Tobj.id.split("_")[0]+"_right").setAttribute("class","right");
        			document.getElementById(Tobj.id.split("_")[0]+"_span").innerHTML="<font style='color:red'>Rejected</font>";
        			/** iterating array **/
        			
        			if(Tobj.id.split("_")[0] == "overall"){
            			
        				mineralData1.overall= "N";
        						
        				
        			}else{
        			
        			
        			
							for(var j=0;j<mineralData.Attributes.length;j++){
        				
        				if(mineralData.Attributes[j].AttributeID== Tobj.id.split("_")[0] ){
        					
        					mineralData.Attributes[j].IsAccepted= "N";
        					
        				}
        				
        			}  
        			}       			 
        			
        		}  
        		
        	}     
 
            
            var submissionID =  '<%=request.getAttribute("submissionID")%>';
            var assignmentID =  '<%= request.getAttribute("assignmentID") %>';
            var totalPoints=0;
            
            $.ajax({
       		 <%-- url : "/GeoApp/rest/instructor/getPhase1?submissionID="+ '<%=request.getAttribute("submissionID")%>', --%>
       		 url : "/GeoApp/rest/instructor/getPossiblePointsByPhase/"+ submissionID+"/2",
       					}).then(function(data, status, jqxhr) {
       						console.log(data+"harsha********");
       						totalPoints=data;
       						document.getElementById("totalpoints").innerHTML=data;
       						
       					}
       					);
        	

            $.ajax({

       		 url : "/GeoApp/rest/instructor/getPhase2?submissionID="+ '<%=request.getAttribute("submissionID")%>',
					}).then(function(data, status, jqxhr) {

						console.log(data+"Veeru************");
						
						for (var i = 0; i<data.length; i++){
						    var opt = document.createElement('option');
						    opt.value = data[i].mineralID;
						    opt.innerHTML = data[i].name;
						    document.getElementById("minerals").appendChild(opt);
						}						
						
					});
            
            
            function getMineralData(mineralId){
            
        	/** ajax request for retrieving phase 2 details**/
        	 $.ajax({

        		 url : "/GeoApp/rest/instructor/getPhase02?submissionID="+ '<%=request.getAttribute("submissionID")%>'
									+ "&mineralID=" + mineralId,
						}).then(function(data, status, jqxhr) {
							document.getElementById("1_right").setAttribute("class","right");
							document.getElementById("2_right").setAttribute("class","right");
							document.getElementById("3_right").setAttribute("class","right");
							document.getElementById("4_right").setAttribute("class","right");
							document.getElementById("5_right").setAttribute("class","right");
							document.getElementById("6_right").setAttribute("class","right");
							document.getElementById("7_right").setAttribute("class","right");
							
							document.getElementById("1_wrong").setAttribute("class","wrong");
							document.getElementById("2_wrong").setAttribute("class","wrong");
							document.getElementById("3_wrong").setAttribute("class","wrong");
							document.getElementById("4_wrong").setAttribute("class","wrong");
							document.getElementById("5_wrong").setAttribute("class","wrong");
							document.getElementById("6_wrong").setAttribute("class","wrong");
							document.getElementById("7_wrong").setAttribute("class","wrong");
							console.log(data);
							var specificGravity = data.specificGravity;
							var geometry = data.geometry;
							var luster = data.luster;
							var hardness = data.hardness
							var streakColor = data.streakColor;
							var rockName = data.name;
							var rockColor = data.color;
							/**if (luster === "Metallic") {
								document.getElementById("radioM").checked = true;
								document.getElementById("radioN").checked = false;
							} else {
								document.getElementById("radioN").checked = true;
								document.getElementById("radioM").checked = false;
							}**/

							document.getElementById("typeId").innerHTML = luster;

							document.getElementById("hardnessId").innerHTML = hardness;

							document.getElementById("streakcolorId").innerHTML = streakColor;
							document.getElementById("specificgravityId").innerHTML = specificGravity;
							document.getElementById("nameId").innerHTML = rockName;
							document.getElementById("colorId").innerHTML = rockColor;
							document.getElementById("geometryId").innerHTML = geometry;
							
							
							
							var mineralID = document.getElementById("minerals").value;
			            	
			            	mineralData.MineralID = mineralID;
			            	
			            	mineralData.Attributes = [];
			            	
			            	for(var i=1;i<=7;i++){
			            	mineralData.Attributes.push({"AttributeID":i,"IsAccepted":"Y","Comments":document.getElementById(i+"_comment").value});
			            	
			            	}
						});
        	
        	 $.ajax({

        		 url : "/GeoApp/rest/instructor/getPhase02Comments?submissionID="+ '<%=request.getAttribute("submissionID")%>'
									+ "&mineralID=" + mineralId,
						}).then(function(data, status, jqxhr) {
							
							for(var i=0;i<data.length;i++){
								console.log(data[i].comments+"veeru*****")
								document.getElementById((i+1)+"_comment").value	= data[i].comments;				
							}
							
						});        	
        	
	}

	function getMineralInfo() {

		//document.getElementById("mineralSelection").style.display="none";
		
		var comments = document.getElementsByName("comment");
		
		for(var i=0;i<comments.length;i++){
			document.getElementById((i+1)+"_span").innerHTML='';
			comments[i].value = '';
		}

		document.getElementById("mineralTableId").style.display = "block";

		var mineralValue = document.getElementById("minerals").value;

		getMineralData(mineralValue);
	}
</script>



<!-- Content Header (Page header) -->

	<section class="content" style="background-color:white">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
	<form action="" >	
		<div class="mapview">
	<map name="navImage">
    <area shape="circle" coords="48,84,22"  href="phase1?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    <area shape="circle" coords="250,84,22" href="phase2?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    <area shape="circle" coords="440,84,22" href="phase3?submissionID=<%= request.getAttribute("submissionID") %>&assignmentID=<%= request.getAttribute("assignmentID")%>"/>
    
	</map>
	</div>
			<center>
				<img src="../template/pics/Capture2.PNG" width="532" height="147"
					alt="Capture1" usemap="#navImage" />
			</center>
			<h4>Student Name: <b><span id="stuname"></span></b></h4>
			<h4>Student ID: <b><span id="stuid"></span></b></h4>

		<div id="mineralSelection">
			<h4>Total minerals submitted: <b><span id=totalmincount></span></b></h4>
			<select id="minerals" onchange="getMineralInfo()">
				<option value="&#10014;">Select</option>

			</select>

		</div>
		<table
			style="display: none; bgcolor="#00FF00" border-collapse: separate; border-spacing: 5.0em;"
			id="mineralTableId">
			<tr>
				<td>1.Luster Type:</td>
				<td id="typeId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="1_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="1_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment" placeholder="comment"
					id="1_comment"></td>
				<td><span id="1_span"> </span></td>
			</tr>
			<tr>
				<td>2. Hardness:</td>
				<td id="hardnessId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="2_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="2_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment" placeholder="comment"
					id="2_comment"><br></td>

				<td><span id="2_span"> </span></td>
			</tr>
			<tr>
				<td>3. Streak Color :</td>
				<td id="streakcolorId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="3_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="3_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment" placeholder="comment"
					id="3_comment"></td>

				<td><span id="3_span"> </span></td>
			</tr>

			<tr>
				<td>4. Sets/Angle:</td>
				<td id="geometryId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="4_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="4_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment"  placeholder="comment"
					id="4_comment"></td>

				<td><span id="4_span"> </span></td>
			</tr>

			<tr>
				<td>5. Specific Gravity:</td>
				<td id="specificgravityId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="5_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="5_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment" placeholder="comment"
					id="5_comment"></td>
				<td><span id="5_span"> </span></td>

			</tr>
			<tr>
				<td>6. Color:</td>
				<td id="colorId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="6_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="6_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment"  placeholder="comment"
					id="6_comment"></td>
				<td><span id="6_span"> </span></td>

			</tr>
			<tr>
				<td>7. Mineral Name:</td>
				<td id="nameId"></td>
				<td colspan=2><input type="button" value="&#10004;"
					class="right" id="7_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="7_wrong"
					onclick="evaluateRock(this);"></td>
				<td><input type="text" class="comment" name="comment"  placeholder="comment"
					id="7_comment"></td>
				<td><span id="7_span"> </span></td>
			<tr>
				<td colspan="3"></td>


			</tr>
			<tr><td>
			<input type="button" value="Evaluate Mineral" style="float: right;"
			onclick="nextPage1();" class="btn btn-primary" /></td></tr>
		</table>			
		Total Phase accepted?:
			<td colspan=2>
			<input type="button" value="&#10004;"
					class="right" id="overall_right" onclick="evaluateRock(this);"> <input
					type="button" value="&#10008;" class="wrong" id="overall_wrong"
					onclick="evaluateRock(this);"></td>
					<td><span id="overall_span"> </span><td colspan="3"></td></td>	<br><br>
		Comment :
		<textarea style="width: 300px; height: 75px;" placeholder="Comments"
			id="commentsTxtId"></textarea>
		
		<%-- Added Text filed for points  --%>
		
		Points : <input type="number" class="points"
			placeholder="Phase 2 points" id="pointsID" maxlength="3">
			Total possible points:<span id="totalpoints"></span>
			
		
		<input type="button" style="float: right" class="btn btn-primary" id="back"
					value="BackToSubmissions" /> 
		  <input
			type="button" value="SubmitPhase2Evaluation" style="float: right;"
			onclick="nextPage();" class="btn btn-primary" />
		

	
	</form>
	</section>

	<%@ include file="./Footer.jsp"%>