	function sayHello(){
		var name = document.getElementById("name").value;
		alert("Name: " + name);
		calculateSalary(printSalary);
	}

	function calculateSalary(xyzFunction){
		var age = document.getElementById("age").value;
		var salary = 2*age;
		xyzFunction(salary);
	}

	function printSalary(salary){
		console.info("The salary is " + salary)
	}

	function getEmployee(){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = processRequest;
		xhttp.open("GET", "http://localhost:9000/employee/api", true);
		xhttp.send();
	}

	function processRequest(){
		if(this.status == 200){
			console.log(this.responseText);
		}else{
			console.log("A network error has happened");
		}
	}
