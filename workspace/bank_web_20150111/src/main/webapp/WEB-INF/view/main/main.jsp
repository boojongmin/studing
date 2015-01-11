<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation"><a href="<%=contextPath %>/web/user/login">로그인</a></li>
            <li role="presentation"><a href="#">About</a></li>
            <li role="presentation"><a href="#">Contact</a></li>
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