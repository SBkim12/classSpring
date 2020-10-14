<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%

String SS_USER_ID= CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));

String res = CmmUtil.nvl((String)request.getAttribute("res"));

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 완료</title>
<%
String msg = "";

if(res.equals("1")){
msg = SS_USER_ID + "님이 로그인 되었습니다.";

}else if (res.equals("0")){
	msg = "아이디 , 비밀번호가 일치하지 않습니다.";
	
}else {
	msg="시스템에 문제가 발생하였습니다. 잠시후 다시 시도하여 바랍니다.";
}
%>

</head>
<body>
<%=msg %>

</body>
</html>