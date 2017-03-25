<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <a href="<c:url value="/home/" />" class="navbar-brand">Home</a>

<!--         <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse"> -->
<!--             <span class="icon-bar"></span> -->
<!--             <span class="icon-bar"></span> -->
<!--             <span class="icon-bar"></span> -->
<!--         </button> -->

        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
<!--                 <li ><a href="#">Home</a></li> -->
 <sec:authorize access="hasRole('ADMIN')">
             <li><a href="<c:url value="/admin/users" />">Users</a></li>
                 <li><a href="<c:url value="/admin/roles" />">Roles</a></li>
        </sec:authorize>
            
                <li><a href="<c:url value="/logout" />">Logout &nbsp;&nbsp; <strong>${loggedinuser}</strong></a></li>
<!--                 <li><a href="#contact" data-toggle="modal">Contact</a></li> -->
            </ul>
        </div>
    </div>
</div>