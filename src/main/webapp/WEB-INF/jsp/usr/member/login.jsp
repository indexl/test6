<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />

<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<script>
	const loginForm_onSubmit = function(form) {
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		
		if (form.loginId.value.length === 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return false;
		}
		
		if (form.loginPw.value.length === 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return false;
		}
		
		return true;
	}
</script>

<section class="mt-16">
	<div class="container mx-auto flex justify-center">
		<form action="doLogin" method="post" onsubmit="return loginForm_onSubmit(this);" class="w-full max-w-sm bg-white shadow-md rounded px-8 pt-6 pb-8">
			<h2 class="text-2xl font-semibold text-center mb-6">로그인</h2>
			<div class="mb-4">
				<label class="block text-gray-700 text-sm font-bold mb-2" for="loginId">아이디</label>
				<input 
					class="w-full px-3 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-400" 
					type="text" 
					name="loginId" 
					id="loginId" 
					placeholder="아이디를 입력해주세요" />
			</div>
			<div class="mb-6">
				<label class="block text-gray-700 text-sm font-bold mb-2" for="loginPw">비밀번호</label>
				<input 
					class="w-full px-3 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-400" 
					type="password" 
					name="loginPw" 
					id="loginPw" 
					placeholder="비밀번호를 입력해주세요" />
			</div>
			<div class="flex items-center justify-center">
				<button 
					class="w-full bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" 
					type="submit">
					로그인
				</button>
			</div>
		</form>
	</div>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>