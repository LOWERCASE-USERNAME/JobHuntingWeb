<sql:setDataSource var="dataSource"
                   driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                   user="giangthhe170978" password="123" />
<sql:query dataSource="${dataSource}" var="resultCompany" style='display: none'>
    SELECT * from Companies;
</sql:query>
<div class="CTA-title text-center">Choose your side</div>
<div id="form">
    <form method="POST" action="../../SignUpServlet">
        <div class="form-group">
            <label for="company">Company/Organization</label>
            <input id="company" type="text" class="form-control" name="company" list="company-list">

            <datalist id="company-list">
                <c:forEach items="${resultCompany.rows}" var="item">
                    <option value="${item.company}">${item.company}</option>
                </c:forEach>
            </datalist>
        </div>
        <div class="form-group">
            <label for="typeaccount">Are you a <span>employees</span> or <span>employers</span></label>
            <br> 
            <div id="radio-type-acc">
                <input id="employees" value="employees" autocomplete="off" type="radio" name="typeaccount" class="btn-check" checked>
                <label for="employees" class="btn btn-outline-success">Employee</label>
                <input id="employers" value="employers" autocomplete="off" type="radio" name="typeaccount" class="btn-check">
                <label for="employers" class="btn btn-outline-danger">Employer</label>
            </div>           
        </div>
        <input type="submit" class="btn-primary" value="Yes I am a employee">
        <input type="hidden" name="page" value="querytypeaccount">
    </form>
    <div class="progress" data-next-page="3">
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 5%"></div>
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" style="width: 15%"></div>
        <div class="progress-bar progress-bar-striped progress-bar-animated bg-info" role="progressbar" style="width: 20%"></div>
        <div class="progress-bar progress-bar-striped progress-bar-animated bg-warning" role="progressbar" style="width: 20%"></div>
        <div class="progress-bar progress-bar-striped progress-bar-animated bg-danger" role="progressbar" style="width: 0%" data-width="20%"></div>
    </div>
</div>

<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../auth/signup.css"> -->