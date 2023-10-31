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
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RecruitCenter History</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
                    <!--                    <th scope="col"></th>-->
                </tr>
            </thead>    
            <%
                String companyid = request.getParameter("id");
                request.setAttribute("companyid", companyid);
            %>
            <c:out value="${companyid}"/>
            <c:forEach items="${resultComp.rows}" var="record">
                <c:choose>
                    <c:when test="${companyid == record.companyid}">
                        <tr style="height: 40px; overflow: hidden; border: 3px" contenteditable="true">
                        </c:when>
                        <c:otherwise>
                        <tr style="height: 40px; overflow: hidden; background-color: gray" contenteditable="false">
                        </c:otherwise>
                    </c:choose>
                    <td contenteditable="false">${record.companyid}</td>
                    <td id="company">${record.company}</td>
                    <td id="website">${record.website}</td>
                    <td id="address">${record.address}</td>
                    <td id="reviews">${record.reviews}</td>
                    <td id="rating">${record.ratings}</td>
                    <td id="num">${record.numofrating}</td>
                </tr>
            </c:forEach>
        </table>
        <form>
            <div id="hidden">
            <input type="hidden" value="" data-col-id="company">
            <input type="hidden" value="" data-col-id="website">
            <input type="hidden" value="" data-col-id="address">
            <input type="hidden" value="" data-col-id="reviews">
            <input type="hidden" value="" data-col-id="rating">
            <input type="hidden" value="" data-col-id="num">
            </div>
            <div class="text-center m-3">
                <button class="btn btn-danger" type="reset">Cancel</button>
                <button class="btn btn-success"  type="">Save changes</button>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                       integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
               crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js" integrity="sha384-gSr0WJ3yDy5Un8K6/rE72Bfj6apLHmacM6XF7B4xXLrP1sS0R+DPCvQzsyi5uxCc" crossorigin="anonymous"></script>
        <script>
            $('form').on('submit', function (e) {
                e.preventDefault();
                $('#hidden input').each(function () {
                    var element = $("#" + $(this).attr('data-col-id'));
                    var text = element.text();
                    $(this).val(text);
                });
                submitted = true;
                document.forms[0].submit();
            })
            window.addEventListener("beforeunload", (event) => {
                if (!submitted) {
                    event.preventDefault();
                    return event.returnValue = ''; /*✔️ Need this for pseudo elements to work! */
                }
            })
        </script>
    </body>
</html>
