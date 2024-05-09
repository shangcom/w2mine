<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-05-07
  Time: 오후 5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo 수정/삭제</title>
</head>
<body>
<form id="form1" action="/todo/modify" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}">
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked": ""} >
    </div>
    <div>
        <button type="submit">수정</button>
    </div>
</form>

<form id="form2" action="/todo/remove" method="post">
    <input type="hidden" name="tno" value="${dto.tno}" readonly>
    <div>
        <button type="submit">제거</button>
    </div>
</form>

</body>
</html>
