<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<c:set var="pageTitle" value="" />

<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<script>
$(document).ready(function(){
    $('#testBtn').click(function(){
        $.ajax({
            url : '/usr/home/test',
            type : 'GET',
            data : {
                key1 : 'value1',
                key2 : 'value2'
            },
            dataType : 'json',
            success : function(test) {
                console.log(test);
                $('#testDiv').append('<div>' + test.data.key1 + '</div>');
            },
            error : function(xhr, status, error) {
                if (xhr.status === 401) { 
                    window.location.href = '/usr/member/login';
                }
                console.log(error);
            }
        });
    });
})
</script>

<section class="h-screen bg-[#f9f5eb] text-[#4a4a4a] flex flex-col items-center">
    <div class="mt-6">
        <button id="testBtn" class="btn bg-[#e4d9c0] text-[#4a4a4a] border-none shadow-md hover:bg-[#dac9a6]">
            테스트 버튼
        </button>
    </div>
    <div id="testDiv" class="mt-8 p-4 w-3/4 bg-[#fffaf0] rounded shadow-sm border border-[#e4d9c0]">
        <p class="text-center text-lg">게시판 테스트</p>
    </div>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
