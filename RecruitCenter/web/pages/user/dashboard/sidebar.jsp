<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-2 bg-dark text-white vh-100">
    <a href="../../../home.jsp" class="text-decoration-none text-white fs-3 m-4">RecruitCenter</a>
    <hr class="">
    <ul class="nav nav-pills flex-column mb-auto">        
            <li class="nav-item">
                <a href="dashboard.jsp" class="nav-link text-white <c:if test="${param.activePage == 'dashboard'}">active</c:if>" aria-current="page">
                    Dashboard
                </a>
            </li>
            <li>
                <a href="company.jsp" class="nav-link text-white <c:if test="${param.activePage == 'company'}">active</c:if>">
                    Your company
                </a>
            </li>
            <li>
                <a href="#" class="nav-link text-white">
                    Applied
                </a>
            </li>
            <li>
                <a href="#" class="nav-link text-white">
                    Profile
                </a>
            </li>
        
    </ul>
</div>