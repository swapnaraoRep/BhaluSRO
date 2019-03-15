<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  
<spring:url value="/resources/BootStrap.css" var="bootStrap" />
<link href="${bootStrap}" rel="stylesheet" />

</head>
<body>
 <ul class="nav navbar-nav">
   <li><a href="#">Home</a></li>
       
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
        </ul>
      <ul class="nav navbar-nav navbar-right">
     
<!--   <li><a href="LogOut"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
 --> 
 <c:if test="${!empty userImage}">
 <li><img width="100" height="100" src="data:image/jpeg;base64,${userImage}"/></li>
 </c:if>
 <%-- <li><img width="100" height="100" src="data:image/jpeg;base64,${userImage}"/></li> --%>
 <li><spring:url value="/LogOut" var="LogOut" htmlEscape="true" />
<a href="${LogOut}">LogOut</a></li>
   </ul>
</body>
</html>