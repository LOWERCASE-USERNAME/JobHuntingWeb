<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="model.Degree" %>
<!DOCTYPE html>
<html>

    <head>
        <title>RecruitCenter</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleheader.css">
        <link rel="stylesheet" href="css/stylesearch.css">
        <link rel="stylesheet" href="css/styleresult.css">
        <link rel="stylesheet" href="css/toast.css">
        <script src="https://unpkg.com/react@17/umd/react.development.js" crossorigin></script>
        <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js" crossorigin></script>
    </head>

    <body>
        <div style="z-index: 1000"><c:out value="${applicationScope.userid}"></c:out></div>
        <div style="z-index: 1000"><c:out value="${pageContext.session.id}"></c:out></div>
        <div class="notifications"></div>
        <header class="container-fluid">
            <nav class="header navbar navbar-expand-lg navbar-light bg-light">
                <div class="header-logo order-1 order-lg-0">
                    <a href="pages/login/login.html" class="navbar-brand">recruitCenter...</a>
                </div>
                <button class="navbar-toggler collapsed" type="button" data-toggle="collapse"
                        data-target="#navigation" aria-controls="navigation" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="header-collapse collapse navbar-collapse order-3 order-lg-0" id="navigation">
                    <ul class="navbar-nav">
                        <li class="nav-item active"><i class="fa-solid fa-house"></i><a class="nav-link"
                                                                                        href="">Home</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-briefcase"></i><a class="nav-link"
                                                                                     href="">Jobs</a></li>
                        <li class="nav-item"><i class="fa-solid fa-building"></i><a class="nav-link"
                                                                                    href="">Companies</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-message"></i><a class="nav-link"
                                                                                   href="">Messages</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-bell"></i><a class="nav-link"
                                                                                href="">Notification</a>
                        </li>
                    </ul>
                </div>
                <!--TODO: may have to change this condition soon -->
                <c:choose>
                    <c:when test="${applicationScope.userid == null and empty cookie['userid'].value}">
                        <div class="header-CTA order-2 order-lg-2">
                            <button class="btn-warning"><a href="./pages/auth/login.jsp">Login</a></button>
                            <button class="btn-danger"><a href="./pages/auth/signup.jsp">Sign Up</a></button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="header-account order-1 order-lg-1">
                            <div id="account-thumb" class="dropdown">
                                <div class="dropdown-toggle" id="accountDropdown" role="button"
                                     data-toggle="dropdown" style="z-index: 1000; position: relative">
                                    <c:choose>
                                        <c:when test = "${sessionScope.SignInAcc != null}">
                                            <c:out value="${sessionScope.SignInAcc.username.substring(0, 1)}"></c:out>
                                        </c:when>
                                        <c:when test = "${sessionScope.account != null}">
                                            <c:out value="${sessionScope.account.username.substring(0, 1)}"></c:out>
                                        </c:when>
                                        <c:when test = "${not empty cookie['userid'].value}">
                                            <c:out value="${cookie['username'].value.substring(0, 1)}"></c:out>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="dropdown-menu" aria-labelledby="accountDropdown">
                                    <a class="dropdown-item" href="#">Action</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="LogOutServlet">Log out</a>
                                    <!-- Go to LogOutServlet delete all log in information and redirect back to here  -->
                                </div>
                            </div>
                        </div>
                        <div class="header-CTA order-2 order-lg-2">
                            <button style="background-color: #6c63ff;"><a
                                    href="./pages/user/employer/JobPosting.jsp">Post a Job</a></button>
                            <button style="background-color: #ff6347;"><a
                                    href="./pages/user/employee/CVbuilder.jsp">Want
                                    a Job</a></button>
                            <!-- This is more of a post your cv type -->
                        </div>
                    </c:otherwise>
                </c:choose>

            </nav>

            <form>
                <div class="form-row row">
                    <div class="form-group col-md-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="job-search">What</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input id="job-search" name="job-search" class="form-control" type="text"
                               placeholder="Search..." list="jobsList" autocomplete="nope">
                        <datalist id="jobsList">
                        </datalist>
                    </div>

                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="city-search">City</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="cityList" id="city-search" name="city-search"
                               class="form-control location-search" type="text" placeholder="City..."
                               autocomplete="nope" data-type="p">
                        <datalist id="cityList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="dist-search">District</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="distList" id="dist-search" name="dist-search"
                               class="form-control location-search" type="text" placeholder="District..."
                               autocomplete="nope" data-type="d">
                        <datalist id="distList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="ward-search">Wards</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="wardList" id="ward-search" name="ward-search"
                               class="form-control location-search" type="text" placeholder="Wards..."
                               autocomplete="nope" data-type="w">
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
                        <option selected disabled hidden>Education Level</option>
                        <option value="1">Secondary school</option>
                        <option value="2">High school</option>
                        <option value="3">College</option>
                        <option value="4">Bachelor</option>
                        <option value="5">Master</option>
                        <option value="6">PhD</option>
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
            <div class="card-columns">
                <sql:setDataSource var="dataSource" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                                   url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                                   user="giangthhe170978" password="123" />
                <sql:query dataSource="${dataSource}" var="resultRec">
                    SELECT * from Recruitments;
                </sql:query>
                <c:forEach items="${resultRec.rows}" var="item">
                    <div class="card">
                        <div class="card-header">
                            <c:catch var ="catchtheException">  
                                <sql:query dataSource="${dataSource}" var="resultComp">
                                    SELECT * from Companies WHERE CompanyID = '${item.get("CompanyID")}';
                                </sql:query>
                                <c:set scope="page" var="company" value="${resultComp.rows[0]}"></c:set>
                            </c:catch>  
                            <c:if test = "${catchtheException != null}">  
                                <p>The type of exception is : ${catchtheException} <br />  
                                    There is an exception: ${catchtheException.message}</p>  
                            </c:if>
                            <div>${company.Company}</div>
                            <div>${item.JobTitle}</div>
                            <small>Posted from ${item.PostedDate}</small>
                        </div>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" data-toggle="tab" data-target="#job_${item.id}"
                                        type="button" role="tab" aria-controls="home"
                                        aria-selected="true">Job</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" data-toggle="tab" data-target="#company_${item.id}" type="button"
                                        role="tab" aria-controls="profile" aria-selected="false">Company</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" data-toggle="tab" data-target="#contact_${item.id}" type="button"
                                        role="tab" aria-controls="contact" aria-selected="false">Contact</button>
                            </li>
                        </ul>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="job_${item.id}" role="tabpanel"
                                 aria-labelledby="home-tab">
                                <div>
                                    <b>Job Type:</b>${item.JobTypeID}
                                </div>
                                <div>
                                    <b>Location:</b> ${item.Location}
                                </div>
                                <div>
                                    <b>Salary:</b>
                                    ${item.Salaries}
                                </div>
                                <div>
                                    <b>Preferred Gender: </b>
                                    ${item.Gender}
                                </div>
                                <div>
                                    <b>Minimum Degree: </b>
                                    ${item.Degree}
                                </div>
                                <div style="white-space: pre-wrap;" class="clamped-text">
                                    <b style="display: block">Job Description: </b>
                                    ${item.JobDescription}
                                </div>
                            </div>
                            <div class="tab-pane fade" id="company_${item.id}" role="tabpanel"
                                 aria-labelledby="profile-tab">
                                <div>
                                    <b>Company Name: </b>
                                    ${company.Company}
                                </div>
                                <div>
                                    <b>Company Website: </b>
                                    ${company.Website}
                                </div>
                                <div><b>Company Name: </b>
                                    ${company.Address}
                                </div>
                            </div>
                            <div class="tab-pane fade" id="contact_${item.id}" role="tabpanel"
                                 aria-labelledby="contact-tab">
                                <div><b>Contact Name:</b> ${item.ContactName}</div>
                                <div><b>Contact Email:</b> ${item.ContactEmail}</div>
                                <div><b>Contact Phone Number:</b> ${item.ContactPhoneNumber}</div>
                                <div></div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <div>Expire when ${item.ExpirationDate}</div>
                        </div>
                    </div>
                </c:forEach>

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
        <script src="./js/toast.js" type="text/javascript"></script>
        <script>
            <c:if test="${sessionScope.logout == true}">
                createToast("success", "Log out successfully");
                <c:remove scope="session" var="logout"/>
            </c:if>
        </script>
    </body>

</html>