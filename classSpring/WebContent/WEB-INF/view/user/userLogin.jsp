<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="/user/userLoginProc.do" method="post">
		<table border="0" cellspacing="0" width="400" height="100">

			<tr bgcolor="#00bfff" width="400" height="100">
				<td align="right"><font size="2">아이디 :</font></td>
				<td><input type="text" name="id" size="10"></td>
			</tr>

			<tr bgcolor="#00bfff">
				<td align="right"><font size="2"> 비밀번호 : </font></td>
				<td><input type="password" name="pwd" size="10"></td>
			</tr>
			<tr bgcolor="#00bfff">
				<td colspan="2" align="center"><input type="submit" value="로그인">
					<input type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>
	<button onclick="location.href='/user/userRegForm.do'">회원가입</button>
</body>
</html>
</html>