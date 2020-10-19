<%@page import="java.util.ArrayList"%>
<%@page import="poly.dto.NoticeDTO"%>
<%@page import="java.util.List"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	session.setAttribute("SESSION_USER_ID", "mar5924");

List<NoticeDTO> rList = (List<NoticeDTO>) request.getAttribute("rList");

if (rList == null) {
	rList = new ArrayList<NoticeDTO>();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 리스트</title>
<script type="text/javascript">
	function doDetail(seq) {
		location.href = "/notice/NoticeInfo.do?nSeq=" + seq;
	}
</script>
</head>
<body>
	<h2>공지사항</h2>
	<hr />
	<br />
	<div style="margin: auto; width: 600px;">
		<table border="1" style="width: 100%">
			<thead>
				<tr>
					<td width="100" align="center">순번</td>
					<td width="100" align="center">제목</td>
					<td width="100" align="center">조회수</td>
					<td width="100" align="center">등록자</td>
					<td width="100" align="center">등록일</td>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < rList.size(); i++) {
					NoticeDTO rDTO = rList.get(i);

					if (rDTO == null) {
						rDTO = new NoticeDTO();
					}
				%>
				<tr>
					<td align="center">
						<%
							if (nvl(rDTO.getNotice_yn()).equals("1")) {
							out.print("<b>[공지]</b>");

						} else {
							out.print(nvl(rDTO.getNotice_seq()));
						}
						%>
					</td>

					<td align="center"><a
						href="javascript:doDetail('<%=nvl(rDTO.getNotice_seq())%>');">
							<%=nvl(rDTO.getTitle())%>
					</a></td>
					<td align="center"><%=nvl(rDTO.getRead_cnt())%></td>
					<td align="center"><%=nvl(rDTO.getUser_name())%></td>
					<td align="center"><%=nvl(rDTO.getReg_dt())%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<a href="/notice/NoticeReg.do">[글쓰기]</a>
	</div>
	</div>
</body>
</html>