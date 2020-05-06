<%@page import="java.sql.Date"%>
<%@page import="model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	session.setAttribute("statusMsg","");
	System.out.println("Trying to process...");
	
	
	if (!(request.getParameter("dname") == null || request.getParameter("dname").isEmpty())){
				 
		Doctor DocObj = new Doctor();
			 
		String stsMsg = "";
		String dname = request.getParameter("dname");
		String dept = request.getParameter("dept");
		Date bday = Date.valueOf(request.getParameter("bday"));
		String phoneno = request.getParameter("phoneno");
		String address = request.getParameter("address");
		String hidDIDSave = request.getParameter("hidDIDSave");
		
		
		//Insert--------------------------
		if ( hidDIDSave == ""){
			stsMsg = DocObj.AddDoctor(dname, dept, bday, phoneno, address);
			
			session.setAttribute("statusMsg", stsMsg);
			//System.out.println("statusMsg"+ stsMsg);
			
		}
		else{//Update----------------------
			stsMsg = DocObj.updateDoctor(hidDIDSave, dname, dept, bday, phoneno, address);
		
			session.setAttribute("statusMsg", stsMsg);
		}
		 
		
	}
	

		//Delete-----------------------------
	if (request.getParameter("hidDIDDelete") != null){
		
		Doctor DocObj = new Doctor();
		String stsMsg = DocObj.deleteDoctor(request.getParameter("hidDIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}

%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/bootstrap-datepicker.css">
<link rel="stylesheet" href="Views/bootstrap-datepicker.min.css">

<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/main.js"></script>
<script src="Components/moment.min.js"></script>
<script src="Components/bootstrap.min.js"></script>
<script src="Components/bootstrap-datepicker.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctors</title>
</head>
<body>

	<div class="container">
 		<div class="row">
 			<div class="col-8">
 				<h1 class="m-3">Doctor's Details</h1>
 				<form id="formDoctors" name="formDoctors">
 					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						 	<span class="input-group-text" id="lblName">Name: </span>
						</div>
						<input class="form-control" type="text" id="dname" name="dname" placeholder="Name">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						 	<span class="input-group-text" id="lblDept">Department: </span>
						</div>
						<input class="form-control" type="text" id="dept" name="dept" placeholder="Department">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						 	<span class="input-group-text" id="lblBday">Birthday: </span>
						</div>
						<input class="form-control" type="text" id="bday" name="bday" placeholder="YYYY-MM-DD">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						 	<span class="input-group-text" id="lblPno">Phone No: </span>
						</div>
						<input class="form-control" type="text" id="phoneno" name="phoneno" placeholder="Phone No">
					</div>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						 	<span class="input-group-text" id="lblAdd">Address: </span>
						</div>
						<textarea class="form-control" name="address" id= "address">Address...</textarea>
					</div>
					
					<input type="button" id="btnSave" name="btnSave"  value="Save" class="btn btn-primary">
					
					<input type="hidden" id="hidDIDSave" name="hidDIDSave" value="">
					
					
 				</form><br>
 				
 				
 			<div id="alertSuccess" class="alert alert-success"></div>				
			<div id="alertError" class="alert alert-danger"></div><br>
			
			<div id="devDocGrid">
 				<% 
 					Doctor DocObj = new Doctor();
 					out.print(DocObj.readDoctorDetails()); 				
 				
 				%>			
 			</div>
			
		 	</div>
 			
		</div>
	</div>

<script type="text/javascript">

	 
$(function () {    
	$('#bday').datepicker({
		format: 'yyyy-mm-dd'
	});
});


</script>

</body>
</html>