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
<title>영화 수집 결과</title>
</head>
<body>
CGV 영화 홈페이지에서 <%=res %>개의 영화 순위 정보가 수집되었습니다.
</body>
</html>