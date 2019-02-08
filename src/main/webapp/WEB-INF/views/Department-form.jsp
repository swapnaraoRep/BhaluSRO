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
<spring:url value="/resources/style.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />

</head>
<body>
<form:form action="${pageContext.request.contextPath}/addDepartments" modelAttribute="departmentVO">
<div id="addEmployeeDiv">
<table align="center" >
<tr>
<td>Department ID</td><td><form:input path="DepartmentId" cssClass="focus1"/></td>
<td><form:errors path="DepartmentId" cssClass="error"/></td></tr>
<tr>
<td>Department Name</td><td><form:input path="DepartmentName" cssClass="focus1"/></td>
<td><form:errors path="DepartmentName" cssClass="error" /></td>
</tr>
<tr>
<td> Location(*)</td><td><form:input path="DepartmentLoc" cssClass="focus1"/></td>
<td><form:errors path="DepartmentLoc" cssClass="error"/></td>
</tr>
<tr>
 <%-- <c:forEach var="dept" items="${DepartmentList}" >
 <td  width="80">${dept.DepartmentName}</td>
 </c:forEach> --%>
 
</tr>
<tr>
<td><input type="submit"/></td>

</tr>

</table>

</div>	
	</form:form>
	<h3>DepartmentList List ${DepartmentList}</h3>
<c:if test="${!empty DepartmentList}">
<div class="center">
<div class="pagination">
	<table class="tg">
	<tr>
		<th>ID</th>
		<th width="80">ID</th>
		<th width="120">Name</th>
		<th width="120">Loc</th>
		
		
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<tr>${list}</tr>
	
<c:forEach items="${DepartmentList}" var="department">
	
		<tr>
		<td  width="80">${department.getDepartmentId()}</td>
				<td width="120">${department.getDepartmentName()}</td>
				<td width="120">${department.getDepartmentLoc()}</td> 
				
			<td width="60"><a href="<c:url value='/editEmployee/${department}' />" >Edit</a></td>
			<td width="60"><a href="<c:url value='/removeEmployee/${department}' />" >Delete</a></td>
		</tr>
	
	</c:forEach> 
	</table>
	</div>
</c:if>
</body>
</html>