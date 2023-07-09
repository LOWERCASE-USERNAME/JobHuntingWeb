<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RecruitCenter</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="login.css">
        <link rel="stylesheet" href="../../css/toast.css">
</head>
<body>
    
    <div class="notifications"></div>
    <h1>Back to your professional career hub</h1>
    <form action="../../SignInServlet" method="post">
        <div class="form-group">
            <label for="username_login">Username: </label>
            <div class="input-group">
                <input id="username_login" type="text" class="form-control" name="username_login" required>
                <i class="fa-solid fa-envelope"></i>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd_login">Password: </label>
            <div class="input-group">
                <input id="pwd_login" type="password" class="form-control" name="pwd_login" required>
                <i class="fa-solid fa-lock-open"></i>
            </div>
        </div>
        <div class="form-check utils">
            <input type="checkbox" name="remember" id="rem-box" class="form-check-input" value="remember_me">
            <label for="rem-box" class="form-check-label">Remember me</label>
            <a href="#" id="forgot_pw">Forgot your password?</a>
        </div>
        <div class="form-group input-group justify-content-center">
            <input class="btn btn-outline-success" type="submit" value="Login Now">
        </div>
        <div class="signup-login">Don't have an account? <span>Sign up now</span></div>
    </form>
    <img src="../../assets/poly_job_man.jpg" alt="" srcset="">
    <img src="../../assets/lego_building_team.jpg" alt="" srcset="">
    <img src="../../assets/task_checking_man.jpg" alt="" srcset="">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/1d7268affb.js" crossorigin="anonymous"></script>
    <script src="../../js/toast.js"></script>
    <script>
        <c:choose>
            <c:when test="${sessionScope.loginSuccess == null || sessionScope.loginSuccess == false}">
                <c:if test="${sessionScope.accountExisted == false}">
                    createToast("error", "Account not existed. Check your username.");
                </c:if>
                <c:if test="${sessionScope.passwordIncorrect == true}">
                    createToast("warning", "Password is wrong.");
                </c:if>
            </c:when>
            <c:otherwise>
                <c:set scope="session" var="userid" value="${sessionScope.SignInAcc.id}"/>
                createToast("success", "Login successful.");
                setTimeout(()=>{
                    document.location.href = "../../home.jsp";
                }, 5000);
            </c:otherwise>
        </c:choose>
        <c:remove scope="session" var="loginSuccess"/>
        <c:remove scope="session" var="accountExisted"/>
        <c:remove scope="session" var="passwordIncorrect"/>
    </script>
</body>
</html>



