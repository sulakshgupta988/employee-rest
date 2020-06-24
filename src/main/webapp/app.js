
function init(){
	$('#add-employee').click(addEmployee);
	$('#refresh-data').click(getEmployeeList);
}

function addEmployee(){
	var $form = $('#employee-form');
	var json = toJson($form);
	
	$.ajax({
		url: './api',
		type: 'POST',
		data: json,
		success: function(response){
			alert("Employee Created");
			getEmployeeList();
		},
		error: function(res){
			alert("An error occurred.");
		}

	});
	return false;

}

function getEmployeeList(){
	$.ajax({
		url: './api',
		type: 'GET',
		success: function(response){
			console.log("Employee Data Fetched");
			console.log(response);
			displayEmployeeList(response);
		},
		error: function(res){
			console.log("An error occurred.");
		}

	});
}

function deleteEmployee(id){
	$.ajax({
		url: './api?id='+id,
		type: 'DELETE',
		success: function(response){
			console.log("Employee Deleted");
			getEmployeeList();
		},
		error: function(){
			console.log("An error occurred.");
		}
	});
}

function displayEmployeeList(data){
	console.log("Printing employee data");
	var $tbody = $("#employee-table").find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonHtml = '<button onClick="deleteEmployee('+ e.id + ')">Delete</button>';
		var row = "<tr>"
		+ "<td>" + e.id + "</td>"
		+ "<td>" + e.name + "</td>"
		+ "<td>" + e.age + "</td>"
		+ "<td>" + buttonHtml + "</td>"
		+ "</tr>";
		$tbody.append(row);
	}
}
function toJson($form){
	var serialized = $form.serializeArray();
	console.log(serialized);
	var s = '';
	var data = {};
	for(s in serialized){
		data[serialized[s]['name']] = serialized[s]['value'];
	}
	var json = JSON.stringify(data);
	console.log(json);
	return json;
}

$(document).ready(init);