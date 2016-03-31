<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../base.jsp" %>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="users?action=add" method="post" class="form-horizontal">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Form Name</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="userId">User ID</label>  
		  <div class="col-md-4">
		  <input id="userId" name="userId" type="text" placeholder="UserId" class="form-control input-md" required="">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="username">Username</label>  
		  <div class="col-md-4">
		  <input id="username" name="username" type="text" placeholder="Username" class="form-control input-md" required="">
		    
		  </div>
		</div>
		
		<div class="form-group">
		  <!-- <label class="col-md-4 control-label" for="rel_date"></label> -->  
		  <div class="col-md-2 col-md-offset-5">
			<div>
			    <button id="submit" name="submit" class="btn btn-info btn-block">Submit</button>
			</div>	    
		  </div>
		</div>
				
		</fieldset>
		</form>
	<br>
	
	<c:choose>
	    <c:when test="${empty users}">
	        <div class="alert alert-danger col-md-8 col-md-offset-2" role="alert">${messages.error}</div>
	    </c:when>
	    <c:otherwise>
        	<center><h3 id="successMessage"><b>${messages.success}</b></h3></center>
			<h1>${messages.title}</h1>
		    <div class="col-md-6 col-md-offset-3">
		    	<table class="table">
		            <thead>
		            	<tr>
			            	<th>UserId</th>
							<!-- <th>model</th> -->
							<th>UserName</th>
							<th></th>
			            </tr>
		            </thead>
		            <tbody>
		            	<c:forEach items="${users}" var="user" >
			                <tr>
			                    <td><c:out value="${user.getUserId()}" /></td>
			                    <td><c:out value="${user.getName()}" /></td>
			                    <td><a class="glyphicon glyphicon-trash" href="users?action=delete&userId=<c:out value="${user.getUserId()}"/>"></a></td>                    
			                </tr>
			            </c:forEach>
		            </tbody>
		       </table>
		    </div>
	    </c:otherwise>
	</c:choose>
</body>
</html>
