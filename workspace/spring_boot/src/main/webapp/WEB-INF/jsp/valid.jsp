<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page isELIgnored="false"
%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vaild</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath }/valid" method="post" modelAttribute="usersVo">
    <form:input path="username" placeholder="아이디를 입력해주세요.." />
    <form:errors path="username" cssClass="error" element="span" />
    <input type="submit" />
</form:form>


</body>
</html>