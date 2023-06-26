<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <div class="notifications">
            
    </div>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>recruitCenter</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="./signup.css">
        <link rel="stylesheet" href="../../css/animate.css">
        <link rel="stylesheet" href="../../css/toast.css">
        <!-- <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script> -->
        
    </head>

    <body>
        <header>

        </header>
        <main>
            
            <div class="CTA-title text-center">Sign in to your online recruit center</div>
            <div id="form">
                <form method="POST" action="../../SignUpServlet">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input autocomplete="nope" id="email-input" type="email" class="form-control" name="email"
                               placeholder="abc@def.com">
                        <small class="valid-feedback">Look good!</small>
                        <small class="invalid-feedback">Please select a valid email!
                            Try: minimum 1 character before and after the @, and an domain after dot.</small>
                    </div>
                    <div class="form-group">
                        <label for="pw">Password</label>
                        <input autocomplete="nope" id="pw-input" type="password" name="password" class="form-control" value="Giang270903"
                               placeholder="Abc123">
                        <small class="valid-feedback">Look good!</small>
                        <small class="invalid-feedback">Please select a valid password! Try: length from
                            6 to 20, must contain at least 1 uppercase, 1 lowercase, 1 digit.</small>
                    </div>
                    <input type="submit" class="btn-primary" value="Continue">
                    <input type="hidden" name="page" value="signup">
                </form>
                <div class="progress" data-next-page="0">
                    <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar"
                         style="width: 5%"></div>
                    <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar"
                         style="width: 0" data-width="15%"></div>
                </div>
            </div>
            
        </main>
        <img src="../../assets/problem-solve.jpg" alt="background" id="img-bg-1">
        <img src="../../assets/idea-thinker.jpg" alt="background" id="img-bg-2">


        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js" crossorigin="anonymous"></script>
        <script src="auth.js"></script>
        <script src="../../js/toast.js"></script>
    </body>

</html>