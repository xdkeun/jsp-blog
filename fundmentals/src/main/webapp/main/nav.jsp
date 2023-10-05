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
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/get.do">블로그목록</a></li>
<%--                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="index.jsp">Home</a></li>--%>
            <c:choose>
                <c:when test="${sessionScope.logined == null}">
                <%-- 로그인 전 --%>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-login-form.jsp">로그인</a></li> <!--로그인-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-create-form.jsp">회원가입</a></li> <!--만들기-->
                </c:when>
                <c:when test="${sessionScope.logined =='admin201912022@induk.ac.kr'}">
<%--                    admin201912022@induk.ac.kr로 로그인했을떄--%>
<%--                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/members">사용자조회</a></li> <!--나자신만조회-->--%>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/get.do">전체사용자조회</a></li> <!--전체사용자조회-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do">블로그작성</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-detail?email=${sessionScope.logined}">상세정보</a></li> <!--내정보조회-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-logout">로그아웃</a></li> <!--로그아웃-->
                </c:when>
                <%-- 로그인 후 (Session 사용 예정)--%>
                <c:otherwise>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do">블로그작성</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-detail?email=${sessionScope.logined}">상세정보</a></li> <!--내정보조회-->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-logout">로그아웃</a></li> <!--로그아웃-->
                </c:otherwise>

            </c:choose>
            </ul>
        </div>
    </div>
</nav>