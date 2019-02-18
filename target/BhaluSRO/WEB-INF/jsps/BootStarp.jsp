<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>
<tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <div class="collapse navbar-collapse" id="myNavbar">
             <tiles:insertAttribute name="header"></tiles:insertAttribute>
     
    </div>
  </div>
</nav>
  


<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
     <tiles:insertAttribute name="menu"></tiles:insertAttribute>
    </div>
    <div class="col-sm-10 text-left"> 
     <tiles:insertAttribute name="body"></tiles:insertAttribute>
      
    </div>
  
  </div>
</div>
<%-- <footer class="container-fluid text-center">
  <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</footer> --%>

</body>
</html>