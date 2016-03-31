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
	<form action="books?action=add" method="post" class="form-horizontal">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Form Name</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="asin">ASIN</label>  
		  <div class="col-md-4">
		  <input id="asin" name="asin" type="text" placeholder="ASIN" class="form-control input-md" required="">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="title">Title</label>  
		  <div class="col-md-4">
		  <input id="title" name="title" type="text" placeholder="Title" class="form-control input-md" required="">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="model">Model</label>  
		  <div class="col-md-4">
		  <input id="model" name="model" type="text" placeholder="Model" class="form-control input-md">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="pages">Pages</label>  
		  <div class="col-md-4">
		  <input id="pages" name="pages" type="text" placeholder="Pages Length" class="form-control input-md">
		    
		  </div>
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="binding">Binding</label>
		  <div class="col-md-4">
		    <select id="binding" name="binding" class="form-control">
		      <option value="Paperback">Paperback</option>
		      <option value="Hardback">Hardback</option>
		      <option value="Kindle/Ebook">Kindle/Ebook</option>
		    </select>
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="region">Region</label>  
		  <div class="col-md-4">
		  <input id="region" name="region" type="text" placeholder="Region" class="form-control input-md">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="publisher">Publisher</label>  
		  <div class="col-md-4">
		  <input id="publisher" name="publisher" type="text" placeholder="Publisher" class="form-control input-md">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="pub_date">Published Date</label>  
		  <div class="col-md-4">
		  <input id="pub_date" name="pub_date" type="date" placeholder="Date" class="form-control input-md" required="" min="1900-01-01">
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="rel_date">Release Date</label>  
		  <div class="col-md-4">
			<input id="rel_date" name="rel_date" type="date" placeholder="Date" class="form-control input-md" required="" min="1900-01-01">		    
		  </div>
		</div>
		
		<!-- Text input-->
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
			                </tr>
			            </c:forEach>
		            </tbody>
		       </table>
		    </div>
	    </c:otherwise>
	</c:choose>
</body>
</html>
