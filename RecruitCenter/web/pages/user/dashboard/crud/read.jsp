<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.JobType" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        
        <style>
            body{
                background-image: url('../../../../assets/cyberpunk-bg.webp');
                background-size: 100vw 100vh
            }
            #container{
                display: grid;
                grid-template-columns: 2fr 1fr;
                gap:40px
            }
            #intro-card div:first-child{
                display: grid;
                grid-template-columns: 30px 1fr 30px 1fr
            }
            #job-title, #company-title{
                grid-column: 1/5
            }
            #company-card{
                display: grid;
                grid-template-columns: 30px 1fr;
                grid-template-rows: auto 1fr 1fr 1fr 1fr;
            }
            #company-card h2, #company-card h5{
                grid-column: 1/3;
            }
            [id='toggle-heart'] {
                position: absolute;
                left: -100vw;
            }
            [for='toggle-heart'] {
                color: #aab8c2;
                transform: translate(100%, 10%);
            }

            [id='toggle-heart']:checked + label {
                color: #e2264d;
            }
            [for='toggle-heart'] {
                font-size: 2em;
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <%
            String jobid = request.getParameter("id");
            request.setAttribute("jobid", jobid);
            String homepage = request.getParameter("page");
            request.setAttribute("page", homepage);
        %>
        <c:if test="${page != 'home'}">
            <h1 class="text-white">PREVIEW RECRUITMENTS ${jobid}</h1>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        </c:if>
        <jsp:include page="template.jsp"/>
        <script src="https://kit.fontawesome.com/1d7268affb.js" integrity="sha384-gSr0WJ3yDy5Un8K6/rE72Bfj6apLHmacM6XF7B4xXLrP1sS0R+DPCvQzsyi5uxCc" crossorigin="anonymous"></script>
    </body>
</html>
