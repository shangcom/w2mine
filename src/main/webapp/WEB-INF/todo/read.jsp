<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024-05-02
  Time: 오후 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo Read</title>
</head>
<body>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dt0.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" value="${dto.finished ? "checked": ""}" readonly>
</div>
<div>
    <a href="/todo/modify?tno=${dto.tno}">수정/삭제</a>
    <a href="/todo/list">리스트로 돌아가기</a>
</div>
</body>
</html>
