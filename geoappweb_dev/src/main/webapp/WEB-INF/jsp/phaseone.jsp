<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.nwmissouri.geoapp.model.TblUser"%>

<c:choose>
<c:when test="${ sessionScope.userdetailsinfo != null }">

<%@ include file="./StudentPageHeader.jsp" %>

<style type="text/css">
.lat {
	top: 60%; position: relative;
	}
.long {
	top: 70%; position: relative;
	}
.pic1 {	
	top: 40%; position: relative;
	}
.lat1 {
	top: 40%; position: relative;
	}
.lon1 {
	top: 40%; position: relative;
	}
.pic2 {
	top: 50%; position: relative;
	}

.pic3 {
	top: 60%; position: relative;
	}

.pic4 {
	top: 70%; position: relative;
	}

.pic5 {
	top: 80%; position: relative;
	}

.desc {
	top: 90%; position: relative;
	}

.submit {
	top: 95%; left: 50%; position: relative; font-size: medium;
	}
.comments {
	position: relative; font-size: medium;
	}
	
.thumb-image{
	float:left; width:100px; position:relative; padding:5px;
}
</style>

<script type="text/javascript">

function imagedelete(imageID){
	
	 $.ajax({url : "/GeoApp/student/phaseone/deleteimage?imageID="+imageID}).always(function() {			
		 var image = document.getElementById(imageID);
		 image.parentNode.removeChild(image);
						var delBut = document.getElementById("del"+imageID);
						delBut.parentNode.removeChild(delBut);
		 });
}
	 
$(document).ready(function() {
    $("#fileUpload0").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder0");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });
  
  
$(document).ready(function() {
    $("#fileUpload1").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder1");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });
  
$(document).ready(function() {
    $("#fileUpload2").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder2");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });
  
$(document).ready(function() {
    $("#fileUpload3").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder3");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });
  
$(document).ready(function() {
    $("#fileUpload4").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder4");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });
  </script>



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
  RequestDispatcher rd = request.getRequestDispatcher("redirect:/view/login");
  rd.include(request, response);
  out.println("Session has ended.  Please login.");
}
%>


			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>Phase 1-Rock Submission</h1>
			<h2>Upload rock pictures:</h2>

		<form:form action="phaseone/submit" method="post"
			modelAttribute="phaseOneForm" enctype="multipart/form-data">
			
			<div class="pic1">
				<c:choose>
				<c:when test="${not empty phaseOneForm.imageIDs[0]}" >
				<img id="${phaseOneForm.imageIDs[0]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[0]}" height=100 width="100">
				<input id="del${phaseOneForm.imageIDs[0]}" type="button" value="Delete" onclick="imagedelete('${phaseOneForm.imageIDs[0]}')">
				<c:out value="${phaseOneForm.evalComments[0]}"></c:out>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[0] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[0] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:when>
				<c:otherwise>
				<input type="file" name="images[0]" id="fileUpload0"/>
				<div id="image-holder0"></div>
				</c:otherwise>
				</c:choose>
				
			</div>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			<div class="pic2">
				<c:choose>
				<c:when test="${not empty phaseOneForm.imageIDs[1]}" >
				<img id="${phaseOneForm.imageIDs[1]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[1]}" height=100 width="100">
				<input id="del${phaseOneForm.imageIDs[1]}" type="button" value="Delete" onclick="imagedelete('${phaseOneForm.imageIDs[1]}')">
				<c:out value="${phaseOneForm.evalComments[1]}"></c:out>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[1] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[1] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:when>
				<c:otherwise>
				<input type="file" name="images[1]" id="fileUpload1"/>
				<div id="image-holder1"></div>
				</c:otherwise>
				</c:choose>
			</div>
			<br/>
			<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			<div class="pic3">
				<c:choose>
				<c:when test="${not empty phaseOneForm.imageIDs[2]}" >
				<img id="${phaseOneForm.imageIDs[2]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[2]}" height=100 width="100">
				<input id="del${phaseOneForm.imageIDs[2]}" type="button" value="Delete" onclick="imagedelete('${phaseOneForm.imageIDs[2]}')">
				<c:out value="${phaseOneForm.evalComments[2]}"></c:out>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[2] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[2] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:when>
				<c:otherwise>
				<input type="file" name="images[2]" id="fileUpload2"/>
				<div id="image-holder2"></div>
				</c:otherwise>
				</c:choose>
			</div>
			<br/>
			<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			<div class="pic4">
				<c:choose>
				<c:when test="${not empty phaseOneForm.imageIDs[3]}" >
				<img id="${phaseOneForm.imageIDs[3]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[3]}" height=100 width="100">
				<input id="del${phaseOneForm.imageIDs[3]}" type="button" value="Delete" onclick="imagedelete('${phaseOneForm.imageIDs[3]}')">
				<c:out value="${phaseOneForm.evalComments[3]}"></c:out>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[3] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[3] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:when>
				<c:otherwise>
				<input type="file" name="images[3]" id="fileUpload3"/>
				<div id="image-holder3"></div>
				</c:otherwise>
				</c:choose>
			</div>
			<br/>
			<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			<div class="pic5">
				<c:choose>
				<c:when test="${not empty phaseOneForm.imageIDs[4]}" >
				<img id="${phaseOneForm.imageIDs[4]}" src="/GeoApp/temp/${phaseOneForm.imageIDs[4]}" height=100 width="100">
				<input id="del${phaseOneForm.imageIDs[4]}" type="button" value="Delete" onclick="imagedelete('${phaseOneForm.imageIDs[4]}')">
				<c:out value="${phaseOneForm.evalComments[4]}"></c:out>
				<c:choose>
				<c:when test="${phaseOneForm.isAccepted[4] == 'Y'}">
				Image is accepted.
				</c:when>
				<c:when test="${phaseOneForm.isAccepted[4] == 'N'}">
				Image is not accepted.
				</c:when>
				</c:choose>
				</c:when>
				<c:otherwise>
				<input type="file" name="images[4]" id="fileUpload4"/>
				<div id="image-holder4"></div>
				</c:otherwise>
				</c:choose>
			</div>
			<br/>
			<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			<div class="lat">
				<p>Latitude:
				<form:input type="text" path="latitude" size="15" pattern = "-?([0-8]?[0-9]|90)\(.[0-9]{1,6})^*" required= "true"/>
				<p> The range for latitude is -90  to 90.</p>
				</p>
			</div>
			<br/>
			<div class="long">
				<p>Longitude:
				<form:input type="text" path="longitude" size="15" pattern = "-?((1?[0-7]?|[0-9]?)[0-9]|180)\(.[0-9]{1,6})^*" required= "true"/>
				<p> The range for latitude is -180  to 180.</p>
				</p>
			</div>
			<br/>
			<div class="desc">
				<p>Description:
				<form:input type="text" path="description" size="100" />
				</p>
			</div>
			<br/>
			<div class="comments">	
				<p>
				<c:out value="${phaseOneForm.comment}"></c:out>
				</p>
			</div>
			<br/>
			<form:input type="hidden" path="assignID" />
			<form:input type="hidden" path="userId" />
			<form:input type="hidden" path="submitId" />
			<div class="submit">
				<input type="submit" value="Submit"
					style="background-color: lightgreen" />
			</div>
			<br/>

		</form:form>
		</section>
	</div>
<%@ include file="./Footer.jsp" %>

</c:when>
<c:otherwise>
<c:redirect url="/view/login" />
</c:otherwise>
</c:choose>