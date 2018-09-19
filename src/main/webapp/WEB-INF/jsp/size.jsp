<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	
	<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Developer Assignment 3</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li><a href="/secured/board">Menu Board</a></li>
					<li><a href="/secured/menu">Menu</a></li>
					<li class="active"><a href="/secured/size">Size</a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>${message}</h1>
			
		    <label>Size</label><input type="text" name="title" id="title"/>
		    <button class="btn btn-success" type="button" id="save">Add</button>
			
			<table id="mainTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<td>Size</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sizeList}" var="size">
		            	<tr id="${size.id}">
		            		<td id="td${size.id}">${size.title}</td>
		            		<td>
		            		<button type="button" class="btn btn-warning" onclick="showEdit(${size.id})">Edit</button>
		            		<button type="button" class="btn btn-danger" onclick="deleteSize(${size.id})">Delete</button>
		            		</td>
		            	</tr>
		        	</c:forEach>
        		</tbody>
        	</table>
		</div>

	</div>
	
	<script type="text/javascript" src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$( "#save" ).click(function() {
			var title = $("#title").val();
	    	
	    	if(title.length === 0 || !title.trim()){
	    		alert("title cannot be empty");
	    		return;
	    	}
	    	
	    	var data = 'title=' + title;
	    	
	        $.ajax({
	        	method: "POST",
	            url : '/secured/addSize',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	            	if(json["success"]){
		            	var tableRef = document.getElementById('mainTable').getElementsByTagName('tbody')[0];
		            	var newRow   = tableRef.insertRow(tableRef.rows.length);
		            	newRow.id = json["id"];
		            	var newCell0  = newRow.insertCell(0);
		            	newCell0.id = "td"+json["id"];
		            	var newText0  = document.createTextNode(json["title"]);
		            	newCell0.appendChild(newText0);
		            		            	
		            	var newCell1  = newRow.insertCell(1);
		            	newCell1.innerHTML = '<button type="button" class="btn btn-warning" onclick="showEdit(' + json["id"] + ')">Edit</button>';
		            	newCell1.innerHTML += '<button type="button" class="btn btn-danger" onclick="deleteSize(' + json["id"] + ')">Delete</button>';
	            	}
	            }
	        });
	    })
	    
	    function deleteSize(id) {
			var data = 'id=' + id;
	    	
	        $.ajax({
	        	method: "POST",
	            url : '/secured/deleteSize',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	            	if(json["success"]){
	            		$("#"+id).remove();
	            	}
	            }
	        });
	    }
		
		function showEdit(id){
			$("#editTitle").remove();
			$("#editButton").remove();
			$("#editButtonClose").remove();
					    
			var newHtml = '<input type="text" name="editTitle" id="editTitle"/>';
			newHtml += '<button id="editButton" type="button" class="btn btn-warning" onclick="editSize(' + id + ')">Save</button>';
			newHtml += '<button id="editButtonClose" type="button" class="btn btn-danger" onclick="hideEdit()">X</button>';
			
			document.getElementById(id).insertAdjacentHTML('beforeend', newHtml);
		}
		
		function hideEdit(){
			$("#editTitle").remove();
			$("#editButton").remove();
			$("#editButtonClose").remove();
		}
		
		function editSize(id) {
			var title = $("#editTitle").val();
	    	
			if(title.length === 0 || !title.trim()){
	    		alert("title cannot be empty");
	    		return;
	    	}
			
			var data = 'id=' + id
			+ "&" + 'title=' + title;
			
	        $.ajax({
	        	method: "POST",
	            url : '/secured/editSize',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	            	if(json["success"]){
	            		document.getElementById("td"+id).innerHTML = title;
	            		hideEdit();
	            	}
	            }
	        });
	    }
	</script>

</body>

</html>