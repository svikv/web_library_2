<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="css/viewStyle.css" rel = stylesheet type = "text/css">
    <title>Show</title>
</head>
<body>
    <div id = "header">
        Your book
    </div>

    <div id = view>
        <%  String genre= (String) request.getAttribute("genre");%>
        <div id = bookCard>
            <div class = block>
                <div class = ttl>title</div>
                <div class = data>
                    <div class = dataField>
                        ${book.title}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>author</div>
                <div class = data>
                    <div class = dataField>
                        ${book.author}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>ISBN</div>
                <div class = data>
                    <div class = dataField>
                        ${book.isbn}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>year</div>
                <div class = data>
                    <div class = dataField>
                        ${book.year}
                    </div>
                </div>
            </div>
            <div class = block>
                <div class = ttl>year</div>
                <div class = data>
                    <div class = dataField>
                        <%=genre%>
                    </div>
                </div>
            </div>
        </div>

        <form method = post action = select>
            <button type = submit class = submitButton>next book</button>
        </form>
    </div>

</body>
</html>
