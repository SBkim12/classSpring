<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- top start -->
	<div>
		<%@ include file="/WEB-INF/view/user/top.jsp" %>
	</div>
	<!-- top end -->
	
	<!-- ajax start -->
	<div style="display: inline-block; margin:0;">
		<div class="input-group">
			<input type="text" placeholder="이름" name="user_name" id="user_name" style="height: 40px !important;"/>
			<input type="button" onClick="JavaScript:search();" value="검색" />
		</div>
	</div>
	<!-- ajax end -->
	
</body>

<script>
	function search(){
		//alert("test");
		var user_name = $('#user_name').val();
		//alert("user_name : "+user_name);
		if( $('#user_name').val()==""){
			$('#user_name').focus();
			return false;
		}
		console.log("user_name : " +user_name);
		$.ajax({
			url : '/user/userSearchList.do',
			type : 'post',
			data : {name :user_name}, //Controller에 보낼  key: value
			dataType:'json',
			success : function(data) { //성공시
				var resHTML ='';
				console.log(data);
				if(data.length==0) {
					alert("값없음");
					resHTML += '<div class="trow" style="display: table-row;">';
					resHTML += '<div class="ajax_ddiv_content_box">검색 결과가 없습니다.</div>';
					resHTML += '</div>';	
				}
				for( var i=0; i<data.length;i++) {
					alert("값 있음");
					resHTML += '<div class="trow" style="display: table-row;">';
					resHTML += '<div class="div_content_box">'
						+ 'user_id : '
						+ data[i].user_id + '</div>';
					resHTML += '<div class="div_content_box">'
						+ 'user_name : '
						+ data[i].user_name + '</div>';
					resHTML += '<div class="div_content_box">'
						+ 'reg_dt : '
						+ data[i].reg_dt + '</div>';
					resHTML += '<div class="div_content_box">'
						+ 'reg_id : '
						+ data[i].reg_id + '</div>'
						+ '===================';
				}
				$('#resContainer').html(resHTML); //HTML에 결과 추가
			}
		})
	}
</script>
</html>