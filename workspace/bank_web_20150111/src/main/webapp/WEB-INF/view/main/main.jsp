<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.school.bank_web.vo.UsersVo" %>    
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Bank Web</title>
    <link href="<%=contextPath %>/css/bootstrap.min.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">
      <div class="header">
<%
UsersVo usersVo = (UsersVo)session.getAttribute("usersVo");

if(usersVo != null) {
%>
		<%=usersVo.getUsername() %>님 환영합니다.
<%
}
%>      	
        <nav>
          <ul class="nav nav-pills pull-right">
<%

if(usersVo == null)
{
%>
            <li role="presentation"><a href="<%=contextPath %>/web/user/login">로그인</a></li>
<%
}
%>
            
            <li role="presentation">
            	<a href="<%=contextPath%>/web/account/viewMyAccount">내계좌보기</a>
            </li>
            <li role="presentation">
            	<a href="<%=contextPath%>/web/account/saveAmount">입금</a>
            </li>
          </ul>
        </nav>
        <h3 class="text-muted">Bank Web</h3>
      </div>
      
      <div class="row">
      	<div class="col-md-offset-3 col-md-6">
      		<img src="<%=contextPath %>/image/bank.jpg" />
      	</div>
      </div>

      <footer class="footer">
        <p>&copy; Company 2015</p>
      </footer>

    </div> <!-- /container -->
	
	<script type="text/javascript" src="<%=contextPath %>/js/jquery-1.11.2.min.js"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js"></script>

  </body>
</html>