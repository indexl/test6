<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 테일윈드CSS -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.14/dist/full.min.css" rel="stylesheet" type="text/css" />
<!-- JQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
<!-- common css -->
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
    <div class="h-20 flex container mx-auto text-3xl">
        <div><a class="h-full px-3 flex items-center" href="${pageContext.request.contextPath}/">로고</a></div>
        <div class="grow"></div>
    </div>

    <!-- Vertical navigation menu -->
    <div class="absolute top-1/4 right-0 bg-black p-4 rounded shadow-lg">
        <ul class="flex flex-col space-y-4 text-white">
            <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/">홈</a></li>
            <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/article/list?boardId=1">게시판1</a></li>
            <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/article/list?boardId=2">게시판2</a></li>

            <c:if test="${empty session.loginedMemberId}">
                <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/member/join">회원가입</a></li>
                <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/member/login">로그인</a></li>
            </c:if>

            <c:if test="${not empty session.loginedMemberId}">
                <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/member/myPage">나의 프로필</a></li>
                <li><a class="px-4 py-2 block hover:bg-gray-700 rounded" href="${pageContext.request.contextPath}/usr/member/doLogout">로그아웃</a></li>
            </c:if>
        </ul>
    </div>

    <section class="my-4 text-2xl">
        <div class="container mx-auto">
            <div>${pageTitle }&nbsp;</div>
        </div>
    </section>
</body>
</html>