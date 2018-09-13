<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

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
				<a class="navbar-brand" href="#">Developer Homework</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li class="active"><a href="payroll">Payroll</a></li>
					<li><a href="reports">Reports</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Developer Homework</h1>
			<h2>${message}</h2>
			
			<form method="POST" action="/uploadReport" enctype="multipart/form-data">
			    <input type="file" name="file" />
			    <input type="submit" value="Submit" />
			</form>
			
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<td>Employee ID</td>
						<td>Pay Period</td>
						<td>Amount Paid</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reports}" var="report">
		            	<tr>
		            		<td>${report[0]}</td>
		            		<td>${report[1]}</td>
		            		<td>${report[2]}</td>
		            	</tr>
		        	</c:forEach>
        		</tbody>
        	</table>
		</div>

	</div>
	
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>