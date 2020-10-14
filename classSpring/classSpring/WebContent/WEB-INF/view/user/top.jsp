<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String user_name = nvl((String)session.getAttribute("user_name"));

%>
<nav class="navbar navbar-light navbar-expand-lg navbar-fixed white no-background bootsnav" style="background-color:grey;">
	<!-- Start Top Search -->
	<div class="top-search">
		<div class="contianer">
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-serach"></i></span>
				<input type="text" class="form-control" placeholder="Search">
				<span class="input-group-addon close-search" onClick="JavaScript:search();"><i class="fa fa-times">검색</i>
			</div>
		</div>
	</div>
	<!-- End Top Search -->
	
	<div class="container">
		<!-- Start Atribute Navigation -->
		<div class="attr-nav">
			<ul>
				<li>
					<%if(user_name.isEmpty()){ %>
						<a href="/user/userLogin.do" style="color:#2b2b2b;">
							sign in
						</a>
					<%}else { %>
						<a href="/user/logOut.do" style="color:#2b2b2b;"><%=user_name %>님 Sign Out </a>
					<%} %>
			</ul>
		</div>
		<!-- End Atribute Navigation -->
	</div>
	<!-- End Side Menu -->

</nav>