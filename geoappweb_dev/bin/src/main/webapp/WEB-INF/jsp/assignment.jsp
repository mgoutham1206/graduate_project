<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="./InstructorPageHeader.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\
<html xmlns:th="http://www.thymeleaf.org">
<script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(function() {
    $( "#due_date" ).datepicker();
  });
</script>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<section class="content">

		<section class="content-header">
			<span id="pinfound" style="font-size: 250%;"> </span>
		</section>
				
				
				<form:form method="POST" action="/GeoApp/instructor/openSection/saveAssignment?sectionId=${sectionId}"
					modelAttribute="assignment" style="float: right; margin-right: 50%;">
					<table>
						<tr>
							<td>Assignment Name</td>
							<td><form:input path="name" /></td>
						</tr>
						<tr>
							<td>Instructions</td>
							<td><form:input path="description" /></td>
						</tr>
						<tr>
							<td>Possible Points Phase1</td>
							<td><form:input path="possiblepointsphase1" /></td>
						</tr>
						<tr>
							<td>Possible Points Phase2</td>
							<td><form:input path="possiblepointsphase2" /></td>
						</tr>
						<tr>
							<td>Possible Points Phase3</td>
							<td><form:input path="possiblepointsphase3" /></td>
						</tr>
						<tr>
							<td>Is Active</td>
							<td><form:input path="isActive" /></td>
						</tr>
						<tr>
							<td>Due Date</td>
							<td><form:input id="due_date" path="due_date" /> Date format: mm/dd/yyyy
								
							</td>
						</tr>
						<tr>
							<td>
								<form:hidden path="assignID" value="${assignment.getAssignID()}"/>
							</td>
							<td><c:choose>
									<c:when test="${assignment.getAssignID() == 0}">
										<input type="submit" class="btn" value="Save" />
									</c:when>
									<c:otherwise>
										<input type="submit" class="btn" value="Update" />
									</c:otherwise>
								</c:choose> <input type="reset" class="btn" value="Cancel" /></td>
						</tr>
					</table>
				</form:form>
		</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="./Footer.jsp"%>