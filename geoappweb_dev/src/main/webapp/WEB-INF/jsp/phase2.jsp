<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="./StudentPageHeader.jsp" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
.add{

}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
</script>
<script type="text/javascript">
</script>
<script>
function myFunction() {
    document.getElementById("add").style.color = "${phaseTwoForm.minerals}";
}
</script>
<!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Phase 2-Mineral Submissions</h1>
				<h2>Add mineral details</h2>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Your Page Content Here -->
		<div id="circle">
			<div class="text">
				<a href="phasetwo?assignID=${progressmodel.assignID}&studentID=${progressmodel.studentID}">Mineral1</a>
			</div>
		</div>
		
		<div class="rname">

			<br />
			<table border=1>
			
			<tr>
			<th>Luster</th>
			<th>Hardness</th>
			<th>Cleavage</th>
			<th>Streak Color</th>
			<th>Color</th>
			<th>Specific Gravity</th>
			<th>Mineral Name</th>
			<th colspan="2">Actions</th>
			</tr>
			<c:forEach items="${phaseTwoForm.minerals}" var="mineral" varStatus="status">
			<tr>
				<td><c:out value="${mineral.luster}"/></td>
				<td><c:out value="${mineral.hardness}"/></td>
				<td><c:out value="${mineral.geometry}"/></td>
				<td><c:out value="${mineral.streakColor}"/></td>
				<td><c:out value="${mineral.color}"/></td>
				<td><c:out value="${mineral.minName}"/></td>
				<td><c:out value="${mineral.specificGravity}"/></td>
				<td><c:out value="${mineral.minName}"/></td>
				<td><a href='phasetwo?studentID=${studentID}&assignID=${assignID}&mineralID=${mineral.mineralId}'>Edit</a></td>
				<td><a href='phasetwo?studentID=${studentID}&assignID=${assignID}&mineralID=${mineral.mineralId}&action=delete'>Delete</a></td>
			</tr>
			</c:forEach>
			</table>
			<input type="button" value="Add another mineral" onclick="window.location.href='phasetwo?studentID=${studentID}&assignID=${assignID}&action=add'"/>
			
			<c:if test="${not empty mineral.submitId}">
			<form:form action="phasetwo/submit" modelAttribute="mineral" method="post">
			
				<h1><c:out value="${mineral.minName}"></c:out></h1> 
				<ol>
					<li>Luster: <form:select path="luster">
							<form:option value="Select"></form:option>
							<form:options items="${luster}" itemValue="lusterTypeID" itemLabel="lusterName" path="lusterName"/>
						</form:select> <br/><br/></li>

					<li>Hardness: <form:select path="hardness">
					<form:option value="none" />Select
					<br/>
					<c:forEach var="hard" items="${hardness}">
        				<form:option value="${hard.hardnessTypeID}"><c:out value="${hard.hardnessName} (${hard.hardnessMin}-${hard.hardnessMax})"/></form:option>
   						 </c:forEach>
<%-- 					<form:options items="${hardness}" itemValue="hardnessTypeID" itemLabel="hardnessName" path="hardnessName"/> --%>
					</form:select>
					</li>
<br/>
						
					<li>Steak Color: <form:select path="streakColor">
							<form:option value="Select"></form:option>
							<form:options items="${streakcolor}" itemValue="streakColorID" itemLabel="streakColorName" path="streakColorName"/>
						</form:select> <br /><br/>
					</li>
					<li>Cleavage: <form:select path="geometry" >
						<form:option value="Select"></form:option>
						<c:forEach var="geometry" items="${cleavage}">
        				<form:option value="${geometry.geometryTypeID}"><c:out value="${geometry.angleType}, ${geometry.geometrySets}"/></form:option>
   						 </c:forEach>
<%-- 							<form:options items="${cleavage}" itemValue="geometryTypeID" itemLabel="angleType geometrySets" path="angleType geometrySets"/> --%>
						</form:select>
					</li>
<br/>
					<li>Specific Gravity: <form:select path="specificGravity">
							<form:option value="Select"></form:option>
							<form:options items="${specificgravity}" itemValue="specificGravityID" itemLabel="specificGravityName" path="specificGravityName"/>
						</form:select>
					</li>
					<br/>
					<li>Color: <form:input type="text" path="color" value=""
							size="35" />
					</li>
					<br/>
					<li>Name: <form:select path="minName">
							<form:option value="select"></form:option>
							<form:option value="APATITE"></form:option>
							<form:option value="AUGITE"></form:option>
							<form:option value="BARITE"></form:option>
							<form:option value="BAUXITE"></form:option>
							<form:option value="BIOTITE"></form:option>
							<form:option value="BORNITE"></form:option>
							<form:option value="CALCITE"></form:option>
							<form:option value="CHALCOPYRITE"></form:option>
							<form:option value="CHLORITE"></form:option>
							<form:option value="CORUNDUM"></form:option>
							<form:option value="DOLOMITE"></form:option>
							<form:option value="EPIDOTE"></form:option>
							<form:option value="FLUORITE"></form:option>
							<form:option value="GARNET"></form:option>
							<form:option value="GOETHITE"></form:option>
							<form:option value="GRAPHITE"></form:option>
							<form:option value="GYPSUM"></form:option>
							<form:option value="HALITE"></form:option>
							<form:option value="HEMATITE"></form:option>
							<form:option value="HORNBLENDE"></form:option>
							<form:option value="KAOLINITE"></form:option>
							<form:option value="LIMONITE"></form:option>
							<form:option value="MAGNETITE"></form:option>
							<form:option value="MUSCOVITE"></form:option>
							<form:option value="NEPHELINE"></form:option>
							<form:option value="OLIVINE"></form:option>
							<form:option value="ORTHOCLASE"></form:option>
							<form:option value="PHLOGOPITE"></form:option>
							<form:option value="PLAGIOCLASE"></form:option>
							<form:option value="PYRITE"></form:option>
							<form:option value="PYRRHOTITE"></form:option>
							<form:option value="QUARTZ"></form:option>
							<form:option value="SERPENTINE"></form:option>
							<form:option value="SPHALERITE"></form:option>
							<form:option value="TALC"></form:option>
							<form:option value="TOURMALINE"></form:option>

						</form:select> <br /> 
						</li>
						</ol>
						<form:input type="hidden" path="submitId" />
						<form:input type="hidden" path="mineralId" />
	
						<input type="submit"
						value="Submit" />
						<br/><br/>
						
			
			</form:form>
			</c:if>
		</div>
		</section>
		</div>
		<%@ include file="./Footer.jsp" %>