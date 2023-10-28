<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="util.HtmlHelper" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sql:setDataSource var="dataSource"
                   driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                   user="giangthhe170978" password="123" />
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RecruitCenter History</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <a href="../../../home.jsp">Back To Home</a>
        <sql:query dataSource="${dataSource}" var="resultAcc">
            SELECT * from Accounts WHERE userid LIKE '${cookie['userid'].value}';
        </sql:query>

            <c:set var="pageindex" value="${requestScope.pageindex}"/>
            <c:set var="totalpage" value="${requestScope.totalpage}"/>
            <c:set var="list" value="${requestScope.list}"/>
        /<!-- if is employer show past recruitment, if employee show past apply -->
        <%--<%= HtmlHelper.pager(5, pageindex, totalpage)%>--%>
        <table class="table caption-top table-hover table-bordered">
            <c:if test="${resultAcc.rows[0].role == 'employers'}">
                <sql:query dataSource="${dataSource}" var="resultRecord">
                    SELECT * from Recruitments WHERE createdby LIKE '${cookie['userid'].value}';
                </sql:query>
                <caption>List Of Recruitments of employer ${resultAcc.rows[0].username}</caption>

            </c:if>
            <c:if test="${resultAcc.rows[0].role == 'admin' || resultAcc.rows[0].role == 'staff'}">
                <sql:query dataSource="${dataSource}" var="resultRecord">
                    SELECT * from Recruitments;
                </sql:query>
                <caption>List Of Recruitments for ${resultAcc.rows[0].role} ${resultAcc.rows[0].username}</caption>
            </c:if>
            <thead class="table-dark">
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Job Title</th>
                    <th scope="col">Company</th>
                    <th scope="col">Posted Date</th>                    
                    <th scope="col">Expire Date</th>
                    <th scope="col">Location</th>
                    <th scope="col">Salary</th>
                    <th scope="col">Contact Name</th>
                    <th scope="col">Contact Email</th>
                    <th scope="col">Contact Phone</th>
                    <th scope="col"></th>
                </tr>
            </thead>    
            <c:forEach items="${resultRecord.rows}" var="record" varStatus="i">
                <sql:query dataSource="${dataSource}" var="comp">
                    SELECT Company from Companies WHERE CompanyID LIKE '${record.companyID}'
                </sql:query>
                <tr style="height: 40px; overflow: hidden">
                    <td>${i.index}</td>
                    <td>${record.jobtitle}</td>
                    <td>${comp.rows[0].company}</td>
                    <td>${record.posteddate}</td>
                    <td>${record.expirationdate}</td>
                    <td>${record.location}</td>
                    <td>
                        <c:choose>
                            <c:when test="${fn:endsWith(record.salaries, '(negotiable)')}">
                                <fmt:formatNumber type="currency"
                                                  value="${fn:substringBefore(record.salaries, '(negotiable)')}">
                                </fmt:formatNumber><br>(negotiable)
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber type="currency"
                                                  value="${record.salaries}">
                                </fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${record.contactname}</td>
                    <td>${record.contactemail}</td>
                    <td>${record.contactphonenumber}</td>  
                    <td class="d-flex flex-column gap-2">
                        <a href="crud/read.jsp?id=${record.id}" class="btn btn-primary">
                            <i class="fa-regular fa-folder-open"></i>
                        </a>
                        <a href="crud/update.jsp?id=${record.id}" class="btn btn-warning">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </a>
                        <a href="crud/delete.jsp?id=${record.id}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this item?');">
                            <i class="fa-solid fa-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js"></script>
    </body>
</html>
