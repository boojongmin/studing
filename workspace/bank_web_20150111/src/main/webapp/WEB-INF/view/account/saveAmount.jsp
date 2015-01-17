<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<% String contextPath = request.getContextPath(); %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>입금</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap.min.css" rel="stylesheet">
    
  </head>

  <body>

    <div class="container">
	  <div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form:form class="form-signin"
				action="${pageContext.request.contextPath}/web/account/saveAmountAction"
				method="post"
				modelAttribute="accountsVo" >       
				<h2 class="form-signin-heading">입금</h2>
				<label class="sr-only">계좌번호</label>
				<form:input cssClass="form-control"
					path="account_number" placeholder="계좌번호를 입력해주세요" 
					autofocus="autofocus"/>
				<form:errors path="account_number" cssClass="error"
					element="span" />
				<label class="sr-only">금액</label>
				<form:input cssClass="form-control" path="amount" placeholder="입금액을 입력해주세요"/>
				<form:errors path="amount" cssClass="error"
					element="span" />
				<input type="button" id="saveAmountBtn" class="btn btn-lg btn-primary btn-block" value="입금"/>
				<!-- <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button> -->
			</form:form>
		</div>
	  </div>
      

    </div> <!-- /container -->

	<script type="text/javascript" src="<%=contextPath %>/js/jquery-1.11.2.min.js"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js"></script>
<script>
$('#saveAmountBtn').click(function(){
	var account_number = $('#account_number').val();
	console.debug(JSON.stringify({account_number: account_number}));
	$.ajax({
		url:'<%=contextPath%>/web/account/checkExistsAccount',
		type: 'post',
		dataType:'json',
		data: JSON.stringify({account_number: account_number}),
		contentType: "application/json",
		success:function(data){
			console.debug(data);
			alert('성공 : '+ data);
		}, error: function(j, t, error){
			alert('실패 : ' + error);
		}
	});
});
</script>

  </body>
</html>
