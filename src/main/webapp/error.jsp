<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 28.07.15
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href = "css/mainStyle.css" rel = stylesheet type="text/css">
  <link href="css/errorStyle.css" rel = stylesheet type = "text/css">
    <title></title>
</head>
<body>
  <div id = "header">
    Error!
  </div>

  <div id = view>
    <div id = errorMessage>
      <%=request.getAttribute("error")%>
    </div>
  </div>

  <form action = <%=request.getAttribute("forwardTo")%>>
    <button class = submitButton>return</button>
  </form>
</body>
</html>
