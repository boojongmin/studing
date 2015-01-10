<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
$(function(){
	$('#json_send_btn').click(function(){
		  console.debug(1);
		  $.ajax({
		    url: '/requestBody',
		    method:'post',
		    contentType: "application/json",
		    dataType:'json',
		    data: JSON.stringify({uid: 1, userid:'boojongmin' ,username : '부종민'}),
		    success:function(data){
		      console.debug(data);
		    },
		    error:function(){
		      console.debug('실패');
		    }
		  });
		  
		});
	
	
})
</script>
</head>
<body>
<input type="button" id="json_send_btn" value="확인"/>
</body>
</html>