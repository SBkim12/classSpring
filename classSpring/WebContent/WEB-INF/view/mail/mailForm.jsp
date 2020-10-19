<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/mail/sendMail.do">
		<table border="1">
			<tr>
			<th>받는사람(이메일주소)</th>
			<td width="500px"><input type="text" name="toMail" style="width:98%"></td>
			</tr>
			
			<tr>
			<th>메일 제목</th>
			<td  width="500px"><input type="text" name="title" style="width:98%"></td>
			</tr>
			
			<tr>
			<th>메일 내용</th>
			<td width="500px" height="200px"><input type="text" name="contents" style="width:98%; height:100%;"></td>
			</tr>
		</table>
		<input type="submit" value="[메일전송]">
		<input type="reset" value="[내용초기화]">
	</form>
</body>
</html>