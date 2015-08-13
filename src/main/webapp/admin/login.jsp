<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../css/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="../css/loginStyle.css" rel = stylesheet type = "text/css">
    <title></title>
</head>
<body>
    <div id = form>
        <div id = errorMessage><%if (request.getAttribute("error") != null) out.print(request.getAttribute("error"));%></div>
        <form method="post" action = admin>
            <div class = inputBlock>
                <label for = login>Login</label><br>
                <input type = text id = login name = login class = textinput>
            </div>
            <div class = inputBlock>
                <label for = password>Password</label><br>
                <input type = password id = password name = password class = textinput>
            </div>

            <button type = submit class = submitButton>log in</button>
        </form>
    </div>
</body>
</html>