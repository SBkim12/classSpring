<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String res = CmmUtil.nvl((String) request.getAttribute("res"));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감정 분석 결과</title>
</head>
<body>
	<h2>감정 분석 결과</h2>
	<hr />
	<br />
	<%=res %>
</body>
</html>