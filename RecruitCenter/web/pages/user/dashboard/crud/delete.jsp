<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String jobid = request.getParameter("id");
            request.setAttribute("jobid", jobid);
        %>
        <sql:setDataSource var="dataSource"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                           user="giangthhe170978" password="123" />
        <sql:update dataSource="${dataSource}" var="count">
            DELETE FROM Recruitments WHERE id LIKE '${jobid}'
        </sql:update>
        <jsp:forward page="../dashboard.jsp"/>
    </body>
</html>
