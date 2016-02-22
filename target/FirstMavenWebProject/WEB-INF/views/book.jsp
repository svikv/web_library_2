<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Book Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>Add a Book</h1>

<!--Связать форму с атрибутом модели-->
<!--Когда форма будет отправлена пользователем на сервер, значения полей будут сохранены в соответствующих свойствах
объекта Book для дальнейшей обработки.-->
<sf:form method="POST" action="/book/add" modelAttribute="book">
<table>
	<c:if test="${!empty book.author}">
	<tr>
		<td><label for="i"><spring:message text="ID"/></label></td>
		<td>
			<sf:input path="id" id="i" readonly="true" size="8"  disabled="true" />

		</td>
	</tr>
	</c:if>

	<tr>
		<td><label for="a"><spring:message text="Author"/></label></td>
		<td><sf:input path="author" id="a"/></td>
	</tr>

	<tr>
		<td><label for="y"><spring:message text="Year"/></label></td>
		<td><sf:input path="year" id="y"/></td>
	</tr>

	<tr>
		<td colspan="2">
			<c:if test="${!empty book.author}">
				<input type="submit" value="<spring:message text="Edit Book"/>" />
			</c:if>

			<c:if test="${empty book.author}">
				<input type="submit" value="<spring:message text="Add Book"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</sf:form>

<br>
<h3>Persons List</h3>
<c:if test="${empty listBooks}">
	<table class="tg">
	<tr>
		<th width="80">Book ID</th>
		<th width="120">Book Author</th>
		<th width="120">Book Year</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listBooks}" var="book">
		<tr>
			<td>${book.id}</td>
			<td>${book.author}</td>
			<td>${book.year}</td>
			<td><a href="<c:url value='/edit/${book.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${book.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
