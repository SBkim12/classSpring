<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//0이면 메일 발송 실패하는것으로 스프링의 Serivce에서 정의했기때문에 0으로 한것
String jspRes = nvl((String) request.getAttribute("res"), "0");

//URL로붵 전달받는 값을(스프링은 기본적으로 포워드 방식으로 페이지를 이동하기 때문에 url에 입력받은 request 값을 가져올수 있음, 일반적인 jsp 불가능)
String toMail = nvl(request.getParameter("toMail"));
String title = nvl(request.getParameter("title"));
String contents = nvl(request.getParameter("contents"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 발송 결과 보기</title>
</head>
<body>
	<%
		if (jspRes.equals("1")) {

		out.println(toMail + "로 메일 전송이 성공하였습니다.");
	%>
	<br>
	<%
		out.println("메일제목 : " + title);
	%>
	<br>
	<%
		out.println("메일 내용 :" + contents);
	}
	%>
</body>
</html>