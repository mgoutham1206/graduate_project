<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
function doIgneous(){
	alert("Goutham")
	var userId= '100006';
	var rocktype = 'Igneous';
	
	 $.ajax({
		 url : "/ds/geoapp/geomap/allimagesinfo/instructor/byrocktype/100006/Igneous",
        // url : "/ds/geoapp/geomap/allimagesinfo/instructor/byrocktype/" +userId+ "/" +rocktype+ "",
         
         	type : "GET",
			contentType : 'application/json',
			dataType : "json",
			data: "",
			success : function(data) {
				console.log(data[0]);
				
				var obj = $.parseJSON(data);
				for(var key in obj){
					alert(key + ': ' + obj[key]);
				}
			
				//alert(obj.length)
				
				//console.log(obj.section[0].latitude);
				//console.log(obj.section.length);
         }
     });
}
</script>
<title>Insert title here</title>
</head>
<body>
<button id="igneous" onclick="doIgneous()" title="Button">Get the time!</button>
</body>
</html>