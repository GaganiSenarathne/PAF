 $(document).ready(function(){
 	if ($("#alertSuccess").text().trim() == "" ){		
		$("#alertSuccess").hide();
	}
	$("#alertError").hide(); 
			  
 });
 
$(document).on("click", "#btnSave", function(event){

	var type = ($("#hidDIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		 url : "DoctorAPI",
		 type : type,
		 data : $("#formDoctors").serialize(),
		 dataType : "text",
		 complete : function(response, status){
			
			onDocSaveComplete(response.responseText, status);
			
	 	}
	});
	
});

function onDocSaveComplete(response, status){

	if (status == "success"){
 
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success"){
		 	$("#alertSuccess").text("Successfully saved.");
		 	$("#alertSuccess").show();
		 	$("#devDocGrid").html(resultSet.data);
 
 		}else if (resultSet.status.trim() == "error"){
 	
	 		$("#alertError").text(resultSet.data);
	 		$("#alertError").show();
	 	}
 	} else if (status == "error"){
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
 	} else{
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	}
	
	 $("#hidDIDSave").val("");
	 $("#formDoctors")[0].reset();
}

	
function validateItemForm(){

	if ($("#dname").val().trim() == ""){
		return "Insert Doctor name";
	}
	if ($("dept").val().trim() == ""){
		return "Insert Department Name"; 
	}
	if ($("#bday").val().trim() == ""){
		return "Select a Date.";
	}
	if ($("#phoneno").val().trim() == ""){
		return "Enter the Phone No.";
	}
	if($("#address").val().trim() == ""){
		return "Enter Address";	
	}
	return true;
}
// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event){

	
	$.ajax({
		 url : "DoctorAPI",
		 type : "DELETE",
		 data : "did=" + $(this).data("did"),
		 dataType : "text",
 		 complete : function(response, status){
 			
 			onDocDeleteComplete(response.responseText, status);
 		}
 	}); 
}); 

function onDocDeleteComplete(response, status){

	if (status == "success"){
		var resultSet = JSON.parse(response);
	
		if (resultSet.status.trim() == "success"){
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#devDocGrid").html(resultSet.data);
			 
	 	} else if (resultSet.status.trim() == "error"){
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
	 	}
 	} else if (status == "error"){
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	
	} else{
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
 	}
}

$(document).on("click", ".btnUpdate", function(event){
	
	$("#hidDIDSave").val($(this).closest("tr").find("#hidDIDUpdate").val());
	$("#dname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#dept").val($(this).closest("tr").find('td:eq(1)').text());
	$("#bday").val($(this).closest("tr").find('td:eq(2)').text());
	$("#phoneno").val($(this).closest("tr").find('td:eq(3)').text());
	$("#address").val($(this).closest("tr").find('td:eq(4)').text());

});

