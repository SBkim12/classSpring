<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String res = CmmUtil.nvl((String)request.getAttribute("res"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 식단</title>
</head>
<body>
	폴리텍 홈페이지에서 <%=res %>개의 식단이 수집되었습니다.
</body>
</html>