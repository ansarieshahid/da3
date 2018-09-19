<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/daterangepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/rowreorder/1.2.5/css/rowReorder.dataTables.min.css"/>

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
				<a class="navbar-brand" href="#">Developer Assignment </a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
					<li><a href="/secured/board">Menu Board</a></li>
					<li class="active"><a href="/secured/menu">Menu</a></li>
					<li><a href="/secured/size">Size</a></li>
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>${store.title}</h1>
			<button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#addModal">Add</button>

			<table id="dtMenu" class="table table-hover table-striped text-center">
				<thead>
					<tr>
						<td>#</td>
						<td></td>
						<td>Image</td>
						<td>Title</td>
						<td>Start date</td>
						<td>End date</td>
						<td>Size</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuList}" var="menu" varStatus="loop">
						<tr id="${menu.id}">
							<td>${loop.index+1}</td>
							<td>${menu.id}</td>
							<td><img
								src="${menu.filePath}${menu.fileName}"
								style="width: 120px; height: 120px;" alt="Menu"></td>
							<td>${menu.title}</td>
							<td>${menu.startDate}</td>
							<td>${menu.endDate}</td>
							<td><ul>
									<c:forEach items="${menu.menuSizeList}" var="menuSize">
										<c:if test="${menuSize.status.code == 'STAT001'}">
											<li>${menuSize.size.title} - ${menuSize.price} ${store.currency.symbol}</li>
										</c:if>
									</c:forEach>
								</ul></td>
							<td>
								<button type="button" class="btn btn-warning" data-toggle="modal"
									data-target="#editModal" onclick="setMenuModal(${menu.id})">Edit</button>
								<button type="button" class="btn btn-danger"
									onclick="deleteMenu(${menu.id})">Delete</button>
								<button type="button" class="btn btn-primary" onclick="setSizeModal(${menu.id})" data-toggle="modal"
									data-target="#sizeModal">Size</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="addModal" tabindex="-1"
		role="dialog" aria-labelledby="Add Menu"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Add Menu</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" action="/secured/addMenu" enctype="multipart/form-data">
					<div class="modal-body">
						<label for="title">Menu Title</label>
	  	 					<input class="form-control input-sm" name="title" id="title" type="text">
						<label for="dateRange">Availability</label>
						<input class="form-control input-sm" name="dateRange" id="dateRange" type="text">
			    		<label for="file">Menu Image</label>
			    		<input type="file" name="file" id="file" />			    		
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="editModal" tabindex="-1"
		role="dialog" aria-labelledby="Edit Menu"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Edit Menu</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" action="/secured/editMenu" enctype="multipart/form-data">
					<div class="modal-body">
						<input type="text" id="menu_edit" name="menu_edit" hidden=true/>
						<label for="title_edit">Menu Title</label>
	  	 					<input class="form-control input-sm" name="title_edit" id="title_edit" type="text">
						<label for="dateRange_edit">Availability</label>
						<input class="form-control input-sm" name="dateRange_edit" id="dateRange_edit" type="text">
			    		<label for="file">Menu Image</label>
			    		<input type="file" name="file_edit" id="file_edit" />
			    		
			    		<ul id="image_ul_edit">
			    			<li id="image_li_edit">
			    			</li>
			    		</ul>			    		
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="sizeModal" tabindex="-1"
		role="dialog" aria-labelledby="Menu Size"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Menu Size</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				
					<div class="modal-body">
						<ul>
							<c:forEach items="${sizeList}" var="size" varStatus="status">
								<li>
									<form method="POST" action="/secured/addMenuSize">
									   	<div class="checkbox">
											<label><input type="checkbox" id="chk${size.id}" name="checked"/>${size.title}</label>
										</div>
										<input type="text" id="size" name="size" value="${size.id}" hidden=true/>
									    <label for="price">Price</label>
									    <input type="text" class="input-sm" id="prc${size.id}" name="price" />
									    <input type="text" id="modalMenu" name="modalMenu" hidden=true/>
									    <button type="submit" class="btn btn-success">Save</button>

									</form>
								</li>
							</c:forEach>
						</ul>			    		
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<!-- <button type="button" class="btn btn-success">Save</button> -->
					</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
	
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
		
	<script type="text/javascript"
		src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-date-range-picker/0.19.0/jquery.daterangepicker.min.js"></script>
	
	<script type="text/javascript"
			src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<script type="text/javascript"
			src="https://cdn.datatables.net/rowreorder/1.2.5/js/dataTables.rowReorder.min.js"></script>
	
	<script type="text/javascript">
		$('input[name="dateRange"]').dateRangePicker();
		$('input[name="dateRange_edit"]').dateRangePicker();
		
		var table = $('#dtMenu').DataTable( {
			"rowReorder": true,
		    "bPaginate": true,
		    "iDisplayLength": 100,
		    "language": {
                "info": "Total: _TOTAL_",
                "infoEmpty": "Total 0"
              },
            "order": [[ 0, "asc" ]],
            "bLengthChange": true,
            "bDestroy": true,
            "columnDefs": [
                {
                    "targets": [ 1 ],
                    "visible": false,
                    "searchable": false
                }
            ],
        });
		
		table.on( 'row-reordered', function ( e, diff, edit ) {
            var result = 'Reorder started on row: ' + edit.triggerRow.data()[1]+'<br>';
                if(edit.nodes.length > 0){
                    var jsonArray = [];
                    table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
                        var data = this.data();
                        var json = {};
                        json["order"] = data[0];
                        json["id"] = data[1];
                        jsonArray.push(json);
                    } );
                    orderMenu(jsonArray);
                }
            });
		
		function orderMenu(jsonArray){
			var json = {};
		    json["menuList"] = jsonArray;
		    
		    var data = 'param=' + JSON.stringify(json);
		    	    	
	        $.ajax({
	        	method: "POST",
	            url : '/secured/orderMenu',
	            data: data,
	            success : function(data) {
	            }
	        });
		}
		
		function setSizeModal(id){
			$('input[name=modalMenu]').val(id);
			
			$('input[name=price]').val("");
			$('input[name=checked]').prop( "checked", false );
			
			var data = 'id=' + id;
	    	
	        $.ajax({
	        	method: "GET",
	            url : '/secured/getSize',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	      
	            	if(json["success"]){
	            		for(var i = 0 ; i < json["menuSizeInfoList"].length ; i++){
	            			$('#prc'+json["menuSizeInfoList"][i]["size"]).val(json["menuSizeInfoList"][i]["price"]);
	            			$('#chk'+json["menuSizeInfoList"][i]["size"]).prop( "checked", true );
	            		}
	            	}
	            }
	        });
		}
		
		function setMenuModal(id){
			$('input[name=menu_edit]').val(id);
			
			var data = 'id=' + id;
	    	
	        $.ajax({
	        	method: "GET",
	            url : '/secured/getMenu',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	      
	            	if(json["success"]){
	            		$("#dateRange_edit").val(json["menu"]["dateRange"]);
	            		$("#title_edit").val(json["menu"]["title"]);
	            		$("#image_li_edit").text(json["menu"]["fileName"]);
	            		if(json["menu"]["fileName"] != "-"){
	            			$("#image_ul_edit").parent().append('<a id="deleteImage" class="btn btn-sm btn-danger" onclick="deleteImage();">X</a>');
	            		}
	            	}
	            }
	        });
		}
		
		function deleteImage() {
			var data = 'id=' + $("#menu_edit").val();
				    	
	        $.ajax({
	        	method: "POST",
	            url : '/secured/deleteImage',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	            	if(json["success"]){
	            		$("#deleteImage").remove();
	            	}
	            }
	        });
	    }
		
		function deleteMenu(id) {
			var data = 'id=' + id;
	    	
	        $.ajax({
	        	method: "POST",
	            url : '/secured/deleteMenu',
	            data: data,
	            success : function(data) {
	            	var json = JSON.parse(data);
	            	if(json["success"]){
	            		$("#"+id).remove();
	            	}
	            }
	        });
	    }
	</script>
</body>

</html>