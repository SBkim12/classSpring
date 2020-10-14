<%@page import="poly.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BoardDTO> rList = (List<BoardDTO>) request.getAttribute("rList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- top start -->
	<div>
		<%@ include file="/WEB-INF/view/user/top.jsp"%>
	</div>
	<!-- top end -->
	

	
	<div style="margin: auto; width: 800px;">
		<table border="1" style="width: 100%">
			<thead>
				<tr>
					<td>글 번호</td>
					<td>제목</td>
					<td>게시일</td>
					<td>게시자</td>
				</tr>
			</thead>
			<tbody>
				<%
					for (BoardDTO e : rList) {
				%>
				<tr>
					<td><%=nvl(e.getPost_no())%></td>
					<td><a href="/board/boardDetail.do?no=<%=e.getPost_no()%>"><%=nvl(e.getPost_title())%></a></td>
					<td><%=nvl(e.getReg_dt())%></td>
					<td><%=nvl(e.getReg_id())%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div style="width: 100%"; test-align:right; margin-top:5px;">
			<button type=button onclick="location.href='/board/newPost.do'">새글</button>
		</div>
	</div>
</body>
</html>