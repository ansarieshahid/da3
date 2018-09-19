<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css" href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="/css/flexslider-min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css">

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
					<li class="active"><a href="/secured/board">Menu Board</a></li>
					<li><a href="/secured/menu">Menu</a></li>
					<li><a href="/secured/size">Size</a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!--  -->
	<div class="flexslider left">
		<ul class="slides">
			<c:forEach begin="0" end="${size-1}" varStatus="outerLoop">
			<li>
				<div class="row text-center text-lg-left">
					<c:forEach begin="${outerLoop.index*9}" end="${((outerLoop.index+1)*9)-1}" varStatus="innerLoop">
				        <div class="col-lg-4 col-md-4 col-xs-6">
				          <a href="#" class="d-block mb-4 h-100">
				          	<span class="badge badge-warning pull-left">${menuList[innerLoop.index].title}</span>
				          	<c:forEach items="${menuList[innerLoop.index].menuSizeList}" var="menuSize">
										<c:if test="${menuSize.status.code == 'STAT001'}">
											<span class="badge badge-primary pull-right">${menuSize.size.title} - ${menuSize.price} ${store.currency.symbol}</span>
											<br>
										</c:if>
							</c:forEach>
				            <img src="${menuList[innerLoop.index].filePath}${menuList[innerLoop.index].fileName}" alt="">
				          </a>
				        </div>
			        </c:forEach>
		        </div>
	        </li>
	        </c:forEach>
		</ul>
	</div>
	
	
	<!--  -->
		
	<script type="text/javascript"
		src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.2.0/jquery.flexslider-min.js"></script>
	<script>
		$('.flexslider').flexslider({
			animation : "slide",
			controlNav : false
		});
	</script>
</body>

</html>