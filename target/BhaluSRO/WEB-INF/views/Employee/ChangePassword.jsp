<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<spring:url value="/resources/main.css" var="mainCss" />
<spring:url value="/resources/main.css" var="errCss" />
<link href="${mainCss}" rel="stylesheet" />
<link href="${errCss}" rel="stylesheet" />
</head>
<script>
function validatePassWord(enterPass,oldPass)
{
	if(enterPass!=oldPass)
		alert("password is incorrect");
	
}
</script>
<body>
${login.employeeLogin.role}
<div class="col-sm-8 text-left"> 

<form:form action="${pageContext.request.contextPath}/UpdatePassword/${login.id}/${login.employeeLogin.password}"  modelAttribute="employeeLogin">
<div id="addEmployeeDiv">
<table align="center" >
<tr>
<td><form:hidden path="userName" value="${login.employeeLogin.userName}" cssClass="focus1" /></td>
<td><form:errors path="userName" cssClass="error"/></td>
</tr>
<tr>
<td><form:hidden path="role" value="${login.employeeLogin.role}" cssClass="focus1" /></td>
<td><form:errors path="role" cssClass="error"/></td>
</tr>
<tr>
<td>Current Password</td><td><input type="text" name="oldPassword" cssClass="focus1"/></td>
</tr>
<tr>
<td>New Password</td><td><form:password path="password" cssClass="focus1"/></td>
<td><form:errors path="password" cssClass="error"/></td>
</tr>

 <tr>
<td>Confirm Password</td><td><form:password  path="confirmPassword" cssClass="focus1"/></td>
<td><form:errors path="confirmPassword" cssClass="error"/></td>
</tr> 
<tr>
<td><input type="submit" value="changePassword"/></td>

</tr>
<tr>



</tr>
</table>
</div>
</form:form>
<c:if test="${!empty PasswordFlag}">
${PasswordFlag}
</c:if>
<c:if test="${empty PasswordFlag}">
<p></p>
</c:if>
</div>
</body>
</html>