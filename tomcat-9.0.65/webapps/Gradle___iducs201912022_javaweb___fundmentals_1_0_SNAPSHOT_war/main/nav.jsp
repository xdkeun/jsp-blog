<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="index.jsp">Yongkeun`s blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
<%--                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="index.jsp">Home</a></li>--%>
            <c:choose>
                <c:when test="${sessionScope.logined == null}">

                <%-- 로그인 전 --%>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-login-form.jsp">로그인</a></li> <!--로그인-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-create-form.jsp">회원가입</a></li> <!--만들기-->
                </c:when>
                <%-- 관리자 모드 --%>
                <c:otherwise>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/members">전체정보조회</a></li> <!--전체정보조회-->
                <%-- 로그인 후 (Session 사용 예정)--%>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-detail?email=${login.email}">내정보조회</a></li> <!--내정보조회-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-update-form?email=${login.email}">수정</a></li> <!--수정-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-delete-form.jsp">삭제</a></li> <!--삭제-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-logout">로그아웃</a></li> <!--로그아웃-->
                </c:otherwise>
            </c:choose>
            </ul>
        </div>
    </div>
</nav>