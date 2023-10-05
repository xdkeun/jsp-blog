<%--
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-24
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%--pageEncoding UTF8 해줘야 한글안깨짐--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>회원가입</h1>
<form action="members" method="post"> <%-- method가 post이므로 doPost에다가 짬 --%>
 이름 : <input type="text" name="fullname" value="미지정">
 이메일 : <input type="text" name="email">
 <input type="submit" value="등록">
</form>
</body>
</html>
