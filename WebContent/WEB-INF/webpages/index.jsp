<%@ include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div class="col-md-8 col-md-offset-2">
		<div class="jumbotron">
		  <center>
		  	<h1>Welcome to bookd!</h1>
			<h3>What would you like to do today?</h3>
		  </center>
		</div>
		<div style="padding: 20px">
			<div class='col-md-4' style="display:inline-block">
				<div class="panel panel-default">
					<div class="panel-heading">
				    	<h3 class="panel-title">Users</h3>
				  	</div>
				  	<!-- <div class="panel-body">
				  	</div> -->
			    	<ul class="list-group">
					  <a href="users/add" class="list-group-item">Add User</a>
					  <!-- <a href="users/delete" class="list-group-item">Delete User</a> -->
					  <a href="users/search" class="list-group-item">Search User</a>
					</ul>
				</div>
			</div>
			
			<div class='col-md-4' style="display:inline-block">
				<div class="panel panel-default"">
					<div class="panel-heading">
				    	<h3 class="panel-title">Books</h3>
				  	</div>
				  	<!-- <div class="panel-body">
				  	</div> -->
			    	<ul class="list-group">
					  <a href="books?action=add" class="list-group-item">Add Book</a>
					  <!-- <a href="books?action=delete" class="list-group-item">Delete Book</a> -->
					  <a href="books?action=search" class="list-group-item">Search Book</a>
					</ul>
				</div>
			</div>
			
			<!-- <div class='col-md-4 ' style="display:inline-block">
				<div class="panel panel-default">
					<div class="panel-heading">
				    	<h3 class="panel-title"><a href="reviews">Go to Reviews</a></h3>
				  	</div>
				  	<div class="panel-body">
				  	</div>
			    	<ul class="list-group">
					  <li class="list-group-item">Add Review</li>
					  <li class="list-group-item">Search Review(s)</li>
					  <li class="list-group-item">Delete Review</li>
					</ul>
				</div>
			</div> -->
			
		</div>
	</div>
</body>
</html>