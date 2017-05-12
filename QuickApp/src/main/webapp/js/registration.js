$(document).ready(function(){

$("#register").click(function(){

	var district = $("#district").val();
	var modal = $("#mname").val();
	var village = $("#vname").val();
	
	if( district =='' || modal =='' || village =='')
		{
		  alert("Please fill all fields...!!!!!!");
		}	
	
	else 
	   {
	     $.post("user",{ district: district, mname: modal, vname:village},
		  function(data) {
		   if(data.trim()=='Location added successfully')
		   {
			$("#myForm").trigger('reset');
		   }
		   alert(data);
		});
	   }
	
	});

});
