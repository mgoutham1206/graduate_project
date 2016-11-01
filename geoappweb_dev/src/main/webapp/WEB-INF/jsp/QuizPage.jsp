<%@page import="edu.nwmissouri.geoapp.model.TblUser"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./StudentPageHeader.jsp"%>
<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	

<!-- Content Wrapper. Contains page content -->

<div class="content-wrapper" >

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
		<h1>Quiz</h1>
		 <span id="CDtimer">HH : MM : SS</span>
	</section>

	<!-- Main content -->

	<section class="content">
		<!-- Your Page Content Here -->




		<form method="post">
			<div>
				<table id="Quizpage">
				</table>
<!-- 				<input type="button" onclick="alert('Your answers are saved!')" value="Save" />
 -->
				<!-- <input type="button" onclick="alert('Are you sure want to proceed?')" value="Submit" /> -->

				<input type="button" onclick="confirmSubmission()" value="Submit" />

			</div>
		</form>
	</section>

</div>





<script type="text/javascript">


	var selectedPool = $('#poolValue').val();
	var data1;

	$(document).ready(function() {
		
		
		/* console.log("###############");
		console.log(window.location.href);
		var assignID = new RegExp('[\?&]' + 'assignID' + '=([^&#]*)').exec(window.location.href);
		console.log(assignID[1]); */
		
		var assignID = ${quizmodel.assignID};
		var studentID = ${quizmodel.studentID};
		
		/* Function to disable F5 key and Ctrl + R to refresh the page */
		
		
		
		document.onkeydown = function(){
			  switch (event.keyCode){
			        case 116 : //F5 button
			            event.returnValue = false;
			            event.keyCode = 0;
			            return false;
			        case 82 : //R button
			            if (event.ctrlKey){ 
			                event.returnValue = false;
			                event.keyCode = 0;
			                return false;
			            }
			    }
			} 

	$.ajax({
			type : "GET",
			url : "/GeoApp/Pool/getquestions/"+assignID+"/"+studentID,
			data : "",
									//cache: false,
			success : function(data) {
										//console.log(data);
										// alert(data.questionlist);
		
		data1 = data;
		var count = 0;
		var questionCount = 0;
		
		$.each(data.questionlist,function() {
	
			questionCount++;
	
			var row1 = $("<tr/>")
			$('#Quizpage').append(row1);
			row1.append($("<td>"+ "Question " + questionCount + ": " + this + "</td>"));
			var answerCount = 0;
			
			for (i = 4 * (questionCount - 1); i < 4 * questionCount; i++) {
				answerCount++;
				var row2 = $("<tr/>"+ "\t");
				$('#Quizpage').append(row2);
				row2.append($("<td>"+ answerCount + ". " + '<input type="radio" name="'+ "question" + 
						questionCount+'" value="'+answerCount+'"/>' + "  " 
						+ data.choicelist[i] + "</td>"));
				count++;
											}
			var row3 = $("<tr/>") 
			$('#Quizpage').append(row3);
			row3.append($("</br> </br>"));
			
		});
		var time = data.timer.split(":");
		var hh = time[0];
		var mm = time[1];
		var ss = time[2];		
		timer();
		
		function timer() {
	              $("#CDtimer").html(hh + " : " + mm + " : " + ss);
	              
	              if( (ss == 0) && (mm > 0) ){
	                  ss = 60;
	                  mm--;
	              }
	              else if( (ss == 0) && (mm == 0) && (hh > 0) ){
	                  ss = 60;
	                  mm = 59;
	                  hh--;
	              }
	              ss--;
	              if( (hh == 0) && (mm == 0) ){
                      $("#CDtimer").addClass("blink");		            		           	 	                  
		        } 
	              if( (ss == 0) && (mm == 0) && (hh == 0) ){
	            	finalSubmission();	            	 	                  
	              }                                        
	              else {
	                  setTimeout(timer, 1000);  //
	              }
          	}

	}
	
	});

});

	function confirmSubmission() {
		//console.log(data1.options);

		if (confirm("Are you sure you want to submit?")) {
			finalSubmission();

		}
	}
	
	function finalSubmission(){
		var score = 0;

		for (i = 0; i < data1.questionlist.length; i++) {
			var c = 0;
			for (j = (4 * i); j < (4 * i) + 4; j++) {
				c++;
				if (data1.options[j] == 1
						&& $("input[name=question" + (i + 1) + "]:checked")
								.val() == c) {
					score++;
				}
			}
		}
<%--	var json = {
			'questionsInQuiz' : data1.questionlist.length,
			'scoreInQuiz' : score
		};
		
		console.log("score is: " + score);
		/* $("#quizResults").submit(); */
	
		$.ajax({
		 url : "/Pool/quizResults",
		 data : JSON.stringify(json),
		 type : "POST",
		 contentType : 'application/json',
		 //cache: false,
		 success : function() {
		 
		 }
		 }); --%>
		 
		 	var assignID = ${quizmodel.assignID};
		 	var studentID = ${quizmodel.studentID};
			$.ajax({
				type : "POST",
				url : "/GeoApp/Pool/saveScore/"+score+"/"+studentID+"/"+assignID,
				data : "",
				success : function(data) {
					
				}
		
			});
		 
			window.location.href = "/GeoApp/Pool/quizResults/?questionsInQuiz="
				+ data1.questionlist.length + "&scoreInQuiz=" + score
				+ "&studentID=${quizmodel.studentID}&assignID=${quizmodel.assignID}";
	}
</script>

<style>
            
            #CDtimer{font-size: 20px;}
            
            .blink{ animation: blinktext 2s infinite linear;}
            
            @keyframes blinktext{
                0%{opacity : 1.0}
                50%{opacity : 0}
                100%{opacity : 1.0}
            }
            
</style>






<%@ include file="./Footer.jsp"%>

</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>