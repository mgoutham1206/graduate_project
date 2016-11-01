<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="../css/style.css"></link>
<link href="../css/LayoutBaseStyle.css"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>

<body>
	<h3>Section List</h3>
	<br />
	<c:if test="${!empty listSections}">
		<table class="tg">
			<tr>
				<th width="80">Event ID</th>
				<th width="80">Title</th>
				<th width="80">Year</th>
				<th width="80">Basic Description</th>

			</tr>
			<c:forEach items="${listSections}" var="sec">
				<tr>
					<td>${sec.sectionID}</td>
					<td>${sec.title}</td>
					<td>${sec.year}</td>
					<td>${sec.basicdescription}</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<br><br><br><br><br><br><br><br><br><br>
	
	<form>
	 Class Name<input type="text" id="name" name="2" value="  "/><br>
	 Class URL<input type="text" id="url" name="2" value="  "/><br>
	 Class Password <input type="text" id="password" name="2" value="  "/><br>
	 <input type="button" id="submit" name="2" value="Submit"/>
	 
	 
	<script>
	$( document ).ready(function() {

		
		$( "#submit" ).click(function() {
			 console.log( "ready!" );
			 var name = document.getElementById(name);
			 var url = document.getElementById(url);
			 var password = document.getElementById(password);
			 var data ={
						'title' :$('#name').val(),
						'classURL' : $('#url').val(),
						'enrollmentPassword' : $('#password').val()
				};
				console.log(data);
				$.ajax({
					  type: "POST",
					  url: http://localhost:8080/Section/getSections,
					  data: JSON.stringify(data),
					  success: success,
					  dataType: dataType
					});
		});
		
		
	   
	});
	</script>
	</form>
	
	

	
<%-- <form:form action="${addAction}" commandName="event">

<table>
    
    <tr>
        <td>
            <form:label path="eventName">
                <spring:message text="Event Name :"/>
            </form:label>
        </td>
        <td>
            <form:input path="eventName" />
        </td> 
    </tr>
    <tr>
        <td style="margin-bottom: 50px">
            <form:label path="eventDescription">
                <spring:message text="Event Description: "/>
            </form:label>
        </td>
        <td style="margin-bottom: 50px">
            <form:input path="eventDescription" />
        </td>
    </tr> 
   
    <tr>
        <td>
            <form:label path="eventLocation">
                <spring:message text="Event Location: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventLocation" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="eventInviteContacts">
                <spring:message text="Invite Contacts: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventInviteContacts" />
        </td>
    </tr>
    
    <tr>
        <td> 
            <form:label path="eventDate">
                <spring:message text="Event Date: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventDate"/>
        </td>
    </tr>
    
    <tr>
        <td>
            <form:label path="eventRemindContacts">
                <spring:message text="Remind Contacts: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventRemindContacts" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="eventNotes">
                <spring:message text="Add Notes: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventNotes" />
        </td>
    </tr>
    <tr >
        <td> 
            <form:label path="eventTasks">
                <spring:message text="Add or Schedule Tasks: "/>
            </form:label>
        </td>
        <td>
            <form:input path="eventTasks" />
        </td>
    </tr>
    
    
    <tr>
    	<td></td>
        <td colspan="2">
            <c:if test="${!empty event.eventName}">
                <input type="submit"
                    value="<spring:message text="Edit Event"/>" />
            </c:if>
            <c:if test="${empty event.eventName}">
                <input type="submit"
                    value="<spring:message text="Add Event"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form> --%>
<br>


</body>
</html>
