<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-05-02
  Time: 오후 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/register" method="post">
    <div>
        <input type="text" name="title" placeholder="제목 입력">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <div>
        <button type="reset">다시 입력</button>
        <button type="submit">등록</button>
    </div>
</form>
</body>
</html>
