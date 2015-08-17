<%@ page import="com.weblibrary.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.weblibrary.entity.Genre" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Iterator" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="css/mainStyle.css" rel="stylesheet" type="text/css">
  <link href="css/viewStyle.css" rel = stylesheet type = "text/css">
  <title>All books!!!</title>
</head>
<body>

<div id = "header">
  Your books Please
</div>

<div id = view>
  <% HashSet<Book> list = (HashSet<Book>) request.getAttribute("books");
    Iterator<Book> iterator=list.iterator();
    while (iterator.hasNext()){
      Book book=iterator.next();
  %>
  <div id = bookCard>
    <div class = block>
      <div class = ttl>title</div>
      <div class = data>
        <div class = dataField>
          <%=book.getTitle()%>
        </div>
      </div>
    </div>
    <div class = block>
      <div class = ttl>author</div>
      <div class = data>
        <div class = dataField>
          <%=book.getAuthor()%>
        </div>
      </div>
    </div>
    <div class = block>
      <div class = ttl>ISBN</div>
      <div class = data>
        <div class = dataField>
          <%=book.getIsbn()%>
        </div>
      </div>
    </div>
    <div class = block>
      <div class = ttl>year</div>
      <div class = data>
        <div class = dataField>
          <%=book.getYear()%>
        </div>
      </div>
    </div>

    <%
      List<Genre> genres = (List<Genre>)book.getGenres();
      for(Genre genre:genres) {
    %>
      <div class = block>
        <div class = ttl>genre</div>
        <div class = data>
          <div class = dataField>
            <%=genre.getGenre()%>
          </div>
        </div>
      </div>
  <%}%>
  </div>

  <%}%>
</div>

<form action = index.html>
  <button class = submitButton>return</button>
</form>
</body>
</html>