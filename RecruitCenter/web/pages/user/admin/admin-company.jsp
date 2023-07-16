<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sql:setDataSource var="dataSource"
                               driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                               url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                               user="giangthhe170978" password="123" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RecruitCenter History</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    
    <body>
        <a href="../../home.jsp">Back To Home</a>
        <sql:query dataSource="${dataSource}" var="resultComp">
                SELECT * from Companies;
        </sql:query>
        <table class="table caption-top table-hover table-bordered">
            <caption>List Of Company for admin ${resultAcc.rows[0].username}</caption>
            <thead class="table-dark">
                <tr>
                    <th scope="col">Company ID</th>
                    <th scope="col">Company</th>
                    <th scope="col">Website</th>                    
                    <th scope="col">Address</th>
                    <th scope="col">Reviews</th>
                    <th scope="col">Rating</th>
                    <th scope="col">Number of Ratings</th>
                    <th scope="col"></th>
                </tr>
            </thead>    
            <c:forEach items="${resultComp.rows}" var="record">
                <tr style="height: 40px; overflow: hidden">
                    <td>${record.companyid}</td>
                    <td>${record.company}</td>
                    <td>${record.website}</td>
                    <td>${record.address}</td>
                    <td>${record.reviews}</td>
                    <td>${record.ratings}</td>
                    <td>${record.numofrating}</td>
                    <td class="d-flex flex-column gap-4">
<!--                        <a href="read.jsp?id=${record.id}" class="btn btn-primary">
                            <i class="fa-regular fa-folder-open"></i>
                        </a>-->
                        <a href="updateCompany.jsp?id=${record.companyid}" class="btn btn-warning">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </a>
                        <a href="delete.jsp?id=${record.companyid}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this item?');">
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
