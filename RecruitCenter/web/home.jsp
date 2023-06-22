
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>RecruitCenter</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleheader.css">
        <link rel="stylesheet" href="css/stylesearch.css">
        <link rel="stylesheet" href="css/styleresult.css">
        <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
    </head>

    <body>
        <header class="container-fluid">
            <nav class="header navbar navbar-expand-lg navbar-light  sticky-top">
                <div class="header-logo order-1 order-lg-0">
                    <a href="pages/login/login.html" class="navbar-brand">recruitCenter...</a>
                </div>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
                        aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="header-collapse collapse navbar-collapse order-3 order-lg-0" id="navigation">
                    <ul class="navbar-nav">
                        <li class="nav-item active"><i class="fa-solid fa-house"></i><a class="nav-link" href="">Home</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-briefcase"></i><a class="nav-link" href="">Jobs</a></li>
                        <li class="nav-item"><i class="fa-solid fa-building"></i><a class="nav-link" href="">Companies</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-message"></i><a class="nav-link" href="">Messages</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-bell"></i><a class="nav-link" href="">Notification</a>
                        </li>
                    </ul>
                </div>

                <c:choose>
                    <c:when test = "${applicationScope.userid == null}">
                        
                            <div class="header-CTA order-2 order-lg-2">
                                <button class="btn-warning"><a href="./pages/auth/login.html">Login</a></button>
                                <button class="btn-danger"><a href="./pages/auth/signup.html">Sign Up</a></button>
                            </div>
                    </c:when>
                    <c:otherwise>
                        <div class="header-account order-1 order-lg-1">
                            <div id="account-thumb" class="dropdown">
                                <div class="dropdown-toggle" id="accountDropdown" role="button" data-toggle="dropdown">
                                    <c:out value="${applicationScope.account.getUsername().substring(0, 1)}"></c:out>
                                </div>
                                <div class="dropdown-menu" aria-labelledby="accountDropdown">
                                    <a class="dropdown-item" href="#">Action</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Something else here</a>
                                </div>
                            </div>
                        </div>
                        <div class="header-CTA order-2 order-lg-2">
                            <button style="background-color: #6c63ff;"><a href="./pages/employer/JobPosting.html">Post a Job</a></button>
                            <button style="background-color: #ff6347;"><a href="./pages/auth/jobSearching.html">Want a Job</a></button>
                        </div>
                    </c:otherwise>
                </c:choose>

            </nav>

            <form>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="job-search">What</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input id="job-search" name="job-search" class="form-control" type="text" placeholder="Search..."
                               list="jobsList" autocomplete="nope">
                        <datalist id="jobsList">
                        </datalist>
                    </div>

                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="city-search">City</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="cityList" id="city-search" name="city-search" class="form-control location-search"
                               type="text" placeholder="City..." autocomplete="nope" data-type="p">
                        <datalist id="cityList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="dist-search">District</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="distList" id="dist-search" name="dist-search" class="form-control location-search"
                               type="text" placeholder="District..." autocomplete="nope" data-type="d">
                        <datalist id="distList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="ward-search">Wards</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="wardList" id="ward-search" name="ward-search" class="form-control location-search"
                               type="text" placeholder="Wards..." autocomplete="nope" data-type="w">
                        <datalist id="wardList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-2">
                        <input id="submit-button" class="btn-primary" type="submit" value="Find">
                    </div>
                </div>
                <div class="form-row" id="filter">
                    <select class="form-control">
                        <option selected disabled hidden>Date posted</option>
                        <option value="1">Anytime</option>
                        <option value="2">Within 24 hour</option>
                        <option value="3">Past week</option>
                        <option value="4">Past month</option>
                        <option value="5">Past year</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Salary</option>
                        <option value="1">$0 ~ $1,000</option>
                        <option value="2">$1,000 ~ $5,000</option>
                        <option value="3">$5,000 ~ $10,000</option>
                        <option value="4">$10,000 ~ $50,000</option>
                        <option value="5">$50,000 ~ $100,000</option>
                        <option value="5">Above $100,000</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Experience</option>
                        <option value="1">Internship</option>
                        <option value="2">Fresher</option>
                        <option value="3">Junior</option>
                        <option value="4">Senior</option>
                        <option value="5">Director</option>
                        <option value="5">Executive</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Field</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="4">Four</option>
                        <option value="5">Five</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Job Type</option>
                        <option value="1">Full-time</option>
                        <option value="2">Part-time</option>
                        <option value="3">Temporary</option>
                        <option value="4">Contract</option>
                        <option value="5">Freelance</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Allow remote</option>
                        <option value="1">Onsite</option>
                        <option value="2">Remote</option>
                        <option value="3">Both</option>
                    </select>
                    <select class="form-control">
                        <option selected disabled hidden>Language</option>
                        <option value="1">English</option>
                        <option value="2">Vietnamese</option>
                        <option value="3">Japanese</option>
                        <option value="4">Chinese</option>
                        <option value="5">Korean</option>
                    </select>
                    <button type="button" class="form-control btn-dark" id="all-filter">Filters</button>
                    <button class="btn-danger" type="reset" id="reset">Reset</button>
                </div>
            </form>
        </header>

        <main class="container-fluid">
            <div class="col-md-5">
                <div class="header-search-result">

                </div>
            </div>
            <div class="col-md-7">

            </div>
        </main>

        <div id="root"></div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js" crossorigin="anonymous"></script>
        <script src="./index.js" type="module" defer></script>
    </body>

</html>