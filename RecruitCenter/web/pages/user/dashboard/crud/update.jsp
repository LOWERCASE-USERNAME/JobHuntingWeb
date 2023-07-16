<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.JobType" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background-image: url('../../../../assets/martian.jpg');
                background-size: 100vw 100vh;
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
                grid-template-rows: auto 1fr 1fr 1fr 1fr 1fr 1fr;
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
        %>
        <form action="../../JobServlet" method="GET">
            <h1 class="text-white">UPDATE RECRUITMENTS ${jobid}</h1>
            <jsp:include page="template.jsp">
                <jsp:param name="page" value="update"/>
            </jsp:include>
            <input type="hidden" value="${jobid}"name="job-id">        
            <div id="hidden">
                <input type="hidden" value="" data-id='job-title' name="job-title">
                <input type="hidden" value="" data-id='job-location' name="job-location">
                <input type="hidden" value="" data-id='comp-location' name="comp-location">
                <input type="hidden" value="" data-id='company-title' name="comp">
                <input type="hidden" value="" data-id='salary' name="salary">
                <input type="hidden" value="" data-id='nego' name="nego">
                <input type="hidden" value="" data-id='comp-web' name="comp-web">
                <input type="hidden" value="" data-id='job-card' name="job-desc">
                <input type="hidden" value="" data-id='contact_name' name="contact_name">
                <input type="hidden" value="" data-id='contact_email' name="contact_email">
                <input type="hidden" value="" data-id='contact_phone' name="contact_phone">
            </div>
            <div class="text-center m-3">
                <button class="btn btn-danger" type="reset">Cancel</button>
                <button class="btn btn-success"  type="submit">Save changes</button>
            </div>
        </form>




        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js"></script>
        <script>
            let submitted = false;
            $(document).ready(function () {
                $('.contenteditable').attr('contenteditable', true);
            })
            $('form').on('submit', function (e) {
                e.preventDefault();
                $('#hidden input').each(function () {
                    var element = $("#" + $(this).attr('data-id'));
                    var text = element.text();
                    $(this).val(text);
                });
                submitted = true;
                document.forms[0].submit();
            })
            window.addEventListener("beforeunload", (event) => {
                if(!submitted){
                    event.preventDefault();
                    return event.returnValue = ''; /*✔️ Need this for pseudo elements to work! */
                }
            })
        </script>
    </body>
</html>
