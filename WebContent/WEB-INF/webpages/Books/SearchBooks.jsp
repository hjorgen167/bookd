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
	<form action="books?action=search" method="post" class="form-horizontal">
		<fieldset>
		
		<!-- Form Name -->
		<center><h1>Search</h1></center>
		
		<!-- Text input-->
		<div class="form-group">
		  <div class="col-md-8 col-md-offset-2"  style="display:inline-block">
		  	<input id="searchParam" name="searchParam" type="text" placeholder="Enter ASIN, Title or Publisher name" class="form-control input-md" required="">
		  </div>
		  <div style="display:inline-block">
		    <button id="submit" name="submit" class="btn btn-info">Search</button>
		  </div>
		</div>
		
		<!-- Multiple criterion (inline) -->
		<div class="col-md-8 col-md-offset-2">
		  <label class="col-md-5 control-label" for="criterion">Search By: </label>
		  <div class="col-md-6"> 
		    <label class="radio-inline" for="criterion-0">
		      <input type="radio" name="criterion" id="criterion-0" value="asin" checked="checked">
		      ASIN
		    </label> 
		    <label class="radio-inline" for="criterion-1">
		      <input type="radio" name="criterion" id="criterion-1" value="title">
		      Title
		    </label> 
		    <label class="radio-inline" for="criterion-2">
		      <input type="radio" name="criterion" id="criterion-2" value="publisher">
		      Publisher
		    </label>
		  </div>
		</div>
		
		</fieldset>
		</form>
			
	<br>
	
	<c:choose>
	    <c:when test="${empty books}">
	        <div class="alert alert-danger col-md-8 col-md-offset-2" role="alert">${messages.error}</div>
	    </c:when>
	    <c:otherwise>
        	<center><h3 id="successMessage"><b>${messages.success}</b></h3></center>
			<h1>${messages.title}</h1>
		    <div style="padding: 10px">
		    	<table class="table">
		            <thead>
		            	<tr>
			            	<th>ASIN</th>
							<!-- <th>model</th> -->
							<th>Title</th>
							<th>Binding</th>
							<th>EditorialReview</th>
							<!-- <th>EditorialReviews</th> -->
							<th>Publication Date</th>
							<th>Release Date</th>
							<th>Pages</th>
							<th>Publisher</th>
							<th>Region</th>
							<th>Delete Book</th>
			            </tr>
		            </thead>
		            <tbody>
		            	<c:forEach items="${books}" var="book" >
			                <tr>
			                    <td><c:out value="${book.getASIN()}" /></td>
			                    <%-- <td><c:out value="${book.getModel()" /></td> --%>
			                    <td class="col-md-2"><c:out value="${book.getTitle()}" /></td>
			                    <td><c:out value="${book.getBinding()}" /></td>
			                    <td class="col-md-4"><c:out value="${book.getEditorialReview()}" /></td>
			                    <%-- <td><c:out value="${book.getEditorialReviews()}" /></td> --%>
			                    <td><fmt:formatDate value="${book.getPublicationDate()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
			                    <td><fmt:formatDate value="${book.getReleaseDate()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
			                    <td><c:out value="${book.getPages()}" /></td>
			                    <td><c:out value="${book.getPublisher()}" /></td>
			                    <td><c:out value="${book.getRegion()}" /></td>
			                    <td><a href="books?action=delete&asin=<c:out value="${book.getASIN()}"/>">Delete</a></td>
			                </tr>
			            </c:forEach>
		            </tbody>
		       </table>
		    </div>
	    </c:otherwise>
	</c:choose>
</body>
</html>
