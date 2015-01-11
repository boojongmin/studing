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

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap.min.css" rel="stylesheet">
    
  </head>

  <body>

    <div class="container">
	  <div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form:form class="form-signin"
				action="${pageContext.request.contextPath}/web/user/login"
				method="post"
				modelAttribute="usersVo" >       
				<h2 class="form-signin-heading">로그인</h2>
				<label class="sr-only">아이디</label>
				<form:input cssClass="form-control"
					path="userid" placeholder="아이디를 입력해주세요" 
					autofocus="autofocus"/>
				<form:errors path="userid" cssClass="error"
					element="span" />
				<input type="submit" class="btn btn-lg btn-primary btn-block" value="로그인"/>
				<!-- <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button> -->
			</form:form>
		</div>
	  </div>
      

    </div> <!-- /container -->

	<script type="text/javascript" src="<%=contextPath %>/jquery-1.11.2.min.js"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js"></script>

  </body>
</html>
