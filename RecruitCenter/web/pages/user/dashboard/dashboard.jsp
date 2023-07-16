<%-- Document : dashboard Created on : Jul 16, 2023, 11:20:05 PM Author : dell --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container-fluid">
            <div class="row">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="dashboard"/>
                </jsp:include>
                <div class="col-10">
                    <h1>Dashboard</h1>
                    <h2>Your past recruitment</h2>
                    <jsp:include page="crud/history.jsp" />
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            $('.nav-link').on('mouseover', function(e){
                $(this).addClass('active');
            })
            $('.nav-link').on('mouseleave', function(e){
                $(this).removeClass('active');
            })
                            </script>
    </body>

</html>