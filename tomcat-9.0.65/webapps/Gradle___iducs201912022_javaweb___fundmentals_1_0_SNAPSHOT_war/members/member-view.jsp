<%@ page import="iducs201912022.javaweb.blog201912022.model.MemberDTO" %><%--
  Created by IntelliJ IDEA.
  User: IN302
  Date: 2022-10-24
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%--pageEncoding UTF8 해줘야 한글안깨짐--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>등록 정보</h1>
  <%--
  <%=request.getParameter("fullname")%> 님 등록에 성공하였습니다
  <br>
  당신의 이메일 주소는 <%=request.getParameter("email")%>

    <%
        MemberDTO m = (MemberDTO) request.getAttribute("member");
        out.println(m.getFullname());
        out.print(m.getEmail());
    %>
        --%>


  <%-- EL(Expression Language) : attribute 처리 전담
  requestScope : EL 기본 객체, request : JSP 기본 객체 --%>
    ${requestScope.member.fullname}
    ${requestScope.member.email}

</body>
</html>
