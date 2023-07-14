<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="model.JobType" %>
<%@page import="model.Recruitments" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>

    <head>
        <title>RecruitCenter</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--        <link rel="stylesheet"
                      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                      crossorigin="anonymous">-->
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/styleheader.css">
        <link rel="stylesheet" href="css/stylesearch.css">
        <link rel="stylesheet" href="css/styleresult.css">
        <link rel="stylesheet" href="css/toast.css">
    </head>

    <body>
        <!--        <div style="z-index: 1000">
        <%--<c:out value="${applicationScope.userid}"></c:out>--%>
        </div>
        <div style="z-index: 1000">
        <%--<c:out value="${pageContext.session.id}"></c:out>--%>
        </div>-->
        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel" style="width: 200px">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Menu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body d-flex flex-column justify-content-around" style="height: 600px;">
                <nav class="navbar navbar-light w-100 flex-column" style="align-items: unset;">
                    <ul class="text-center list-unstyled">
                        <li class="nav-item active">
                            <a class="nav-link" href="">Home</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a class="nav-link" href="">Jobs</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a class="nav-link" href="">Companies</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a class="nav-link" href="">Messages</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a class="nav-link" href="">Notification</a>
                        </li>
                    </ul>
                </nav>
                <div class="btn-group">
                    <button class="btn btn-warning dropdown-toggle w-100" type="button" data-bs-toggle="dropdown" aria-expanded="false" >
                        Utilities Menu
                    </button>
                    <ul class="dropdown-menu">
                        <c:choose>
                            <c:when test="${applicationScope.userid == null and empty cookie['userid'].value}">
                                <a class="dropdown-item" href="./pages/auth/login.jsp">Login</a>
                                <a class="dropdown-item" href="./pages/auth/signup.jsp">SignUp</a>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item" href="./pages/user/employer/JobPosting.jsp">Job Posting</a>
                                <a class="dropdown-item" href="./pages/user/employee/CVbuilder.jsp">Create Resume</a>
                            </c:otherwise>
                        </c:choose>
                        <div class="dropdown-divider"></div>
                        <li ><a class="dropdown-item"href="./pages/user/history.jsp">History</a></li>
                        <li ><a class="dropdown-item" href="#">Setting</a></li>
                        <div class="dropdown-divider"></div>
                        <li ><a class="dropdown-item"  href="LogOutServlet">Logout</a></li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="notifications"></div>
        <header class="container-fluid position-relative" >
            <nav class="header navbar navbar-expand-lg navbar-light">
                <div class="header-logo order-1 order-lg-0">
                    <a href="home.jsp" class="navbar-brand">recruitCenter...</a>
                </div>
                <!-- <div class="d-flex" style="height: auto;"> -->
                <div class="vr"></div>
                <!-- </div> -->
                <button class="navbar-toggler collapsed d-none" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navigation"
                        aria-controls="navigation" aria-expanded="false"
                        aria-label="Toggle navigation">
                </button>
                <button class="btn btn-dark text-white d-lg-none position-absolute" style="right: 10px;" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
                    Offcanvas Menu
                </button>
                <div class="header-collapse collapse navbar-collapse order-3 order-lg-0"
                     id="navigation">
                    <ul class="navbar-nav">
                        <li class="nav-item active"><i class="fa-solid fa-house"></i><a
                                class="nav-link" href="">Home</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-briefcase"></i><a
                                class="nav-link" href="">Jobs</a></li>
                        <li class="nav-item"><i class="fa-solid fa-building"></i><a
                                class="nav-link" href="">Companies</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-message"></i><a
                                class="nav-link" href="">Messages</a>
                        </li>
                        <li class="nav-item"><i class="fa-solid fa-bell"></i><a
                                class="nav-link" href="">Notification</a>
                        </li>
                    </ul>
                    <c:choose>
                        <c:when
                            test="${applicationScope.userid == null and empty cookie['userid'].value}">
                            <div class="header-CTA order-2 order-lg-2 position-absolute" style="right: 20px">
                                <button class="btn btn-warning"><a
                                        href="./pages/auth/login.jsp">Login</a></button>
                                <button class="btn btn-danger"><a
                                        href="./pages/auth/signup.jsp">Sign
                                        Up</a></button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="header-account order-1 order-lg-1">
                                <div id="account-thumb" class="dropdown">
                                    <div class="dropdown-toggle text-white" id="accountDropdown"
                                         role="button" data-bs-toggle="dropdown">
                                        <c:choose>
                                            <c:when test="${SignInAcc != null}">
                                                <c:out
                                                    value="${SignInAcc.username.substring(0, 1)}">
                                                </c:out>
                                            </c:when>
                                            <c:when test="${account != null}">
                                                <c:out
                                                    value="${account.username.substring(0, 1)}">
                                                </c:out>
                                            </c:when>
                                            <c:when test="${not empty cookie['userid'].value}">
                                                <c:out
                                                    value="${cookie['username'].value.substring(0, 1)}">
                                                </c:out>
                                            </c:when>
                                            <c:otherwise>
                                                ?
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <ul class="dropdown-menu"
                                        aria-labelledby="accountDropdown">
                                        <li ><a class="dropdown-item" href="./pages/user/history.jsp">History</a></li>
                                        <li ><a class="dropdown-item" href="#">Setting</a></li>
                                        <div class="dropdown-divider"></div>
                                        <li ><a class="dropdown-item"  href="LogOutServlet">Logout</a></li>
                                        <!-- Go to LogOutServlet delete all log in information and redirect back to here   -->
                                    </ul>
                                </div>
                            </div>
                            <div class="header-CTA order-2 order-lg-2">
                                <button style="background-color: #6c63ff;"><a
                                        href="./pages/user/employer/JobPosting.jsp">Post a
                                        Job</a></button>
                                <button style="background-color: #ff6347;"><a
                                        href="./pages/user/employee/CVbuilder.jsp">Make Resume</a></button>
                                <!-- This is more of a post your cv type -->
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>



            </nav>

            <form method="GET" action="JobSearchServlet" accept-charset="utf-8">
                <div class="form-row row">
                    <div class="form-group col-md-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="job-search">What</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input id="job-search" name="job-search" class="form-control"
                               type="text" placeholder="Search..." list="jobsList"
                               autocomplete="nope">
                        <datalist id="jobsList">
                        </datalist>
                    </div>

                    <div class="form-group col-md-3 col-sm-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="city-search">City</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="cityList" id="city-search" name="city-search"
                               class="form-control location-search" type="text"
                               placeholder="City..." autocomplete="nope" data-type="p">
                        <datalist id="cityList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-3 col-sm-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text"
                                   for="dist-search">District</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="distList" id="dist-search" name="dist-search"
                               class="form-control location-search" type="text"
                               placeholder="District..." autocomplete="nope" data-type="d">
                        <datalist id="distList">
                        </datalist>
                    </div>
                    <div class="form-group col-md-3 col-sm-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="ward-search">Wards</label>
                        </div>
                        <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                        <input list="wardList" id="ward-search" name="ward-search"
                               class="form-control location-search" type="text"
                               placeholder="Wards..." autocomplete="nope" data-type="w">
                        <datalist id="wardList">
                        </datalist>
                    </div>

                </div>
                <div class="form-row" id="filter">
                    <select class="form-control" name="datePosted" value="1">
                        <option value="0" selected hidden>Date posted</option>
                        <option value="anytime">Anytime</option>
                        <option value="within24">Within 24 hour</option>
                        <option value="pastweek">Past week</option>
                        <option value="pastmonth">Past month</option>
                        <option value="pastyear">Past year</option>
                    </select>
                    <select class="form-control" name="salary">
                        <option value="0" selected hidden>Salary</option>
                        <option value="1">$0 ~ $1.000</option>
                        <option value="2">$1.000 ~ $5.000</option>
                        <option value="3">$5.000 ~ $10.000</option>
                        <option value="4">$10.000 ~ $50.000</option>
                        <option value="5">$50.000 ~ $100.000</option>
                        <option value="6">Above $100.000</option>
                    </select>
                    <select class="form-control" name="experience">
                        <option value="0" selected hidden>Experience</option>
                        <option value="1">Internship</option>
                        <option value="2">Fresher</option>
                        <option value="3">Junior</option>
                        <option value="4">Senior</option>
                        <option value="5">Director</option>
                        <option value="6">Executive</option>
                    </select>
                    <select class="form-control" name="field">
                        <option value="0" selected hidden>Field</option>
                        <option value="1">Agriculture, Food & Natural Resources</option>
                        <option value="2">Architecture & Construction</option>
                        <option value="3">Arts, Audio/Video Technology & Communications
                        </option>
                        <option value="4">Business Management & Administration</option>
                        <option value="5">Education & Training</option>
                        <option value="6">Finance</option>
                        <option value="7">Government & Public Administration</option>
                        <option value="8">Health Science</option>
                        <option value="9">Hospitality & Tourism</option>
                        <option value="10">Human Services</option>
                        <option value="11">Information Technology</option>
                        <option value="12">Law, Public Safety, Corrections & Security
                        </option>
                        <option value="13">Manufacturing</option>
                        <option value="14">Marketing</option>
                        <option value="15">Science, Technology, Engineering & Mathematics
                        </option>
                        <option value="16">Transportation, Distribution & Logistics</option>
                    </select>
                    <select class="form-control" name="jobType">
                        <option value="0" selected hidden>Job Type</option>
                        <option value="1">Full-time</option>
                        <option value="2">Part-time</option>
                        <option value="3">Temporary</option>
                        <option value="4">Contract</option>
                        <option value="5">Freelance</option>
                    </select>
                    <select class="form-control" name="education">
                        <option value="0" selected hidden>Education Level</option>
                        <option value="1">Secondary school</option>
                        <option value="2">High school</option>
                        <option value="3">College</option>
                        <option value="4">Bachelor</option>
                        <option value="5">Master</option>
                        <option value="6">PhD</option>
                    </select>
                    <select class="form-control" name="workplace">
                        <option value="0" selected hidden>Workplace</option>
                        <option value="1">Onsite</option>
                        <option value="2">Remote</option>
                        <option value="3">Both</option>
                    </select>
                    <select class="form-control" name="language">
                        <option value="0" selected hidden>Language</option>
                        <option value="1">English</option>
                        <option value="2">Vietnamese</option>
                        <option value="3">Japanese</option>
                        <option value="4">Chinese</option>
                        <option value="5">Korean</option>
                    </select>
                </div>
                <button type="button" class="form-control btn btn-dark"
                        id="all-filter">Filters</button>
                <button class="btn btn-danger" type="reset" id="reset">Reset</button>
                <input id="submit-button" class="btn btn-primary" type="submit"
                       value="Search">
            </form>
        </header>

        <main class="container-fluid">
            <div class="row">
                <div class="gap-3 card-container col-md-4">
                    <sql:setDataSource var="dataSource"
                                       driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                                       url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                                       user="giangthhe170978" password="123" />
                    <sql:query dataSource="${dataSource}" var="resultRec">
                        SELECT * from Recruitments;
                    </sql:query>
                    <% ArrayList<String> list = (ArrayList<String>)
                            session.getAttribute("recruitmentSearchList");
                            session.setAttribute("searchList", list);
                    %>
                    <c:if test="${empty sessionScope.firstVisit}">
                        <c:set var="firstVisit" value="true" scope="session" />
                    </c:if>

                    <c:forEach items="${resultRec.rows}" var="item">
                        <c:if test="${searchList.contains(item.ID) || sessionScope.firstVisit}">
                            <div class="card">
                                <c:choose>
                                    <c:when test="${pageScope.count == 0}">
                                        <div class="card-header bg-primary text-white">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="card-header bg-danger text-white">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:set scope="page" var="count"
                                               value="${1 - pageScope.count}"></c:set>
                                        <c:catch var="catchtheException">
                                            <sql:query dataSource="${dataSource}"
                                                       var="resultComp">
                                                SELECT * from Companies WHERE CompanyID =
                                                '${item.get("CompanyID")}';
                                            </sql:query>
                                            <c:set scope="page" var="company"
                                                   value="${resultComp.rows[0]}"></c:set>
                                        </c:catch>
                                        <c:if test="${catchtheException != null}">
                                            <p>The type of exception is : ${catchtheException}
                                                <br />
                                                There is an exception:
                                                ${catchtheException.message}</p>
                                            </c:if>
                                        <div>${company.Company}</div>
                                        <div>${item.JobTitle}</div>
                                        <small>Posted from ${item.PostedDate}</small>
                                    </div>
                                    <div class="nav nav-tabs nav-fill" id="myTab" role="tablist">
                                        <!-- <li class="nav-link active" role="presentation"> -->
                                        <button class="nav-link  active" data-bs-toggle="tab"
                                                data-bs-target="#job_${item.id}"
                                                type="button"
                                                role="tab" aria-controls="job"
                                                aria-selected="true">Job</button>
                                        <!-- </li> -->
                                        <!-- <li class="nav-item" role="presentation"> -->
                                        <button class="nav-link" data-bs-toggle="tab"
                                                data-bs-target="#company_${item.id}" type="button"
                                                role="tab" aria-controls="profile"
                                                aria-selected="false">Company</button>
                                        <!-- </li> -->
                                        <!-- <li class="nav-item" role="presentation"> -->
                                        <button class="nav-link" data-bs-toggle="tab"
                                                data-bs-target="#contact_${item.id}" type="button"
                                                role="tab" aria-controls="contact"
                                                aria-selected="false">Contact</button>
                                        <!-- </li> -->
                                    </div>
                                    <div class="tab-content" id="myTabContent">
                                        <div class="tab-pane fade show active"
                                             id="job_${item.id}" role="tabpanel"
                                             aria-labelledby="home-tab">
                                            <div>
                                                <b>Job Type:</b>
                                                ${JobType.JOB_TYPES[item.JobTypeID]}
                                            </div>
                                            <div>
                                                <b>Location:</b> ${item.Location}
                                            </div>
                                            <div>
                                                <b>Salary:</b>
                                                <fmt:formatNumber type="currency"
                                                                  value="${item.Salaries}">
                                                </fmt:formatNumber>
                                                <c:if
                                                    test="${item.negotiable == true}">
                                                    (negotiable)
                                                </c:if>
                                            </div>
                                            <div>
                                                <b>Preferred Gender: </b>
                                                ${item.Gender}
                                            </div>
                                            <div>
                                                <b>Minimum Degree: </b>
                                                ${item.Degree}
                                            </div>
                                            <div>
                                                <b style="display: block">Job Description: </b>
                                                <div style="white-space: pre-wrap;"
                                                     class="clamped-text job-desc">
                                                    ${item.JobDescription}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="company_${item.id}"
                                             role="tabpanel" aria-labelledby="profile-tab">
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
                                        <div class="tab-pane fade" id="contact_${item.id}"
                                             role="tabpanel" aria-labelledby="contact-tab">
                                            <div><b>Contact Name:</b> ${item.ContactName}</div>
                                            <div><b>Contact Email:</b> ${item.ContactEmail}
                                            </div>
                                            <div><b>Contact Phone Number:</b>
                                                ${item.ContactPhoneNumber}</div>
                                            <div></div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-dark d-flex align-items-center">
                                        <c:choose>
                                            <c:when test="${empty item.ExpirationDate}">
                                                <small class="text-white">Never expired</small>
                                            </c:when>
                                            <c:otherwise>
                                                <small class="text-white">Expire in
                                                    <fmt:formatDate
                                                        value="${item.ExpirationDate}" />
                                                </small>
                                            </c:otherwise>
                                        </c:choose>

                                        <div style="margin-left: auto; width: 60%"
                                             class="d-flex justify-content-around">
                                            <a href="#" class="btn btn-outline-info seemore_btn"
                                               id="seemore_${item.id}" data-product-id="${item.id}">SEE MORE</a>


                                            <c:catch var="catchtheException">
                                                <sql:query dataSource="${dataSource}"
                                                           var="resultUser">
                                                    SELECT * from Users WHERE id LIKE '${applicationScope.userid}';
                                                </sql:query>
                                                <c:set scope="page" var="user"
                                                       value="${resultUser.rows[0]}"></c:set>
                                            </c:catch>
                                            <c:if test="${catchtheException != null}">
                                                <p>The type of exception is : ${catchtheException}
                                                    <br />
                                                    There is an exception:
                                                    ${catchtheException.message}</p>
                                                </c:if>

                                            <%!
                                                String encodeMailToRCF(String str){
                                                    return str
                                                            .replace("%", "%25")
                                                            .replace(" ", "%20")
                                                            .replace("!", "%21")
                                                            .replace("\"", "%22")
                                                            .replace("#", "%23")
                                                            .replace("$", "%24")
                                                            .replace("&", "%26")
                                                            .replace("'", "%27")
                                                            .replace("(", "%28")
                                                            .replace(")", "%29")
                                                            .replace("*", "%2A")
                                                            .replace("+", "%2B")
                                                            .replace(",", "%2C")
                                                            .replace("/", "%2F")
                                                            .replace(":", "%3A")
                                                            .replace(";", "%3B")
                                                            .replace("=", "%3D")
                                                            .replace("?", "%3F")
                                                            .replace("@", "%40")
                                                            .replace("[", "%5B")
                                                            .replace("]", "%5D");
                                                }
                                            %>
                                            <a href="mailto:${item.ContactEmail}?subject=An%20bright%20potential%20applicant%20has%20seen%20and%20want%20to%20work%20with%20you%20on%20RecruitCenter&body=Dear%20${item.ContactName}%2C%0A%0AI%20was%20keenly%20interested%20in%20reading%20the%20job%20posting%20for%20the%20position%20of%20${item.JobTitle}%20at%20${company.Company}.%20I%20believe%20my%20experience%20is%20a%20strong%20match%20for%20the%20responsibilities%20pertaining%20to%20this%20role%2C%20and%20I%27m%20pleased%20to%20submit%20my%20application%20for%20the%20position.%0A%0A--YOUR%20EXPERIENCE%20HERE--%0A%0AI%20have%20attached%20my%20resume%20to%20this%20letter.%20Through%20it%2C%20I%20hope%20you%20will%20learn%20more%20about%20my%20background%2C%20education%2C%20achievements%2C%20and%20awards.%0A%0AIf%20I%20can%20provide%20you%20with%20any%20further%20information%2C%20please%20let%20me%20know.%20I%20look%20forward%20to%20hearing%20from%20you%20about%20this%20opportunity.%0A%0AThank%20you%20for%20your%20consideration.%0A%0A${user.LastName}%20${user.FirstName}%0A${user.Email}%0A${user.PhoneNumber}"
                                               class="btn btn-outline-warning apply_btn"
                                               id="apply_${item.id}">APPLY NOW</a>
                                            <c:set scope="application" var="emailContent" value="mailto:${item.ContactEmail}?subject=An%20bright%20potential%20applicant%20has%20seen%20and%20want%20to%20work%20with%20you%20on%20RecruitCenter&body=Dear%20${item.ContactName}%2C%0A%0AI%20was%20keenly%20interested%20in%20reading%20the%20job%20posting%20for%20the%20position%20of%20${item.JobTitle}%20at%20${company.Company}.%20I%20believe%20my%20experience%20is%20a%20strong%20match%20for%20the%20responsibilities%20pertaining%20to%20this%20role%2C%20and%20I%27m%20pleased%20to%20submit%20my%20application%20for%20the%20position.%0A%0A--YOUR%20EXPERIENCE%20HERE--%0A%0AI%20have%20attached%20my%20resume%20to%20this%20letter.%20Through%20it%2C%20I%20hope%20you%20will%20learn%20more%20about%20my%20background%2C%20education%2C%20achievements%2C%20and%20awards.%0A%0AIf%20I%20can%20provide%20you%20with%20any%20further%20information%2C%20please%20let%20me%20know.%20I%20look%20forward%20to%20hearing%20from%20you%20about%20this%20opportunity.%0A%0AThank%20you%20for%20your%20consideration.%0A%0A${user.LastName}%20${user.FirstName}%0A${user.Email}%0A${user.PhoneNumber}"/>
                                        </div>

                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                        <c:set var="firstVisit" value="false" scope="session" />
                    </div>
                    <section class="col-md-8">
                        <c:set var="cont" value="true"/>
                        <c:forEach items="${resultRec.rows}" var="item">
                            <c:if test="${searchList.contains(item.ID) && cont == true}">
                                <c:set var="cont" value="false"/>
                                <jsp:include page="./pages/user/read.jsp?id=${item.ID}&page=home"/>
                            </c:if>
                        </c:forEach>
                        
                    </section>
                </div>

            </div>
        </main>

        <button type="button" class="btn btn-primary d-none" id="modalbtn" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Submit your Resume</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="JobApplyServlet" method="post" id="file-form">
                            <input type="file" name="resume">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <a href="mailto:${item.ContactEmail}?subject=An%20bright%20potential%20applicant%20has%20seen%20and%20want%20to%20work%20with%20you%20on%20RecruitCenter&body=Dear%20${item.ContactName}%2C%0A%0AI%20was%20keenly%20interested%20in%20reading%20the%20job%20posting%20for%20the%20position%20of%20${item.JobTitle}%20at%20${company.Company}.%20I%20believe%20my%20experience%20is%20a%20strong%20match%20for%20the%20responsibilities%20pertaining%20to%20this%20role%2C%20and%20I%27m%20pleased%20to%20submit%20my%20application%20for%20the%20position.%0A%0A--YOUR%20EXPERIENCE%20HERE--%0A%0AI%20have%20attached%20my%20resume%20to%20this%20letter.%20Through%20it%2C%20I%20hope%20you%20will%20learn%20more%20about%20my%20background%2C%20education%2C%20achievements%2C%20and%20awards.%0A%0AIf%20I%20can%20provide%20you%20with%20any%20further%20information%2C%20please%20let%20me%20know.%20I%20look%20forward%20to%20hearing%20from%20you%20about%20this%20opportunity.%0A%0AThank%20you%20for%20your%20consideration.%0A%0A${user.LastName}%20${user.FirstName}%0A${user.Email}%0A${user.PhoneNumber}&attach=${attachedFile}" 
                           type="button" class="btn btn-primary" id="submit-modal">Save changes</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
        <!--        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>-->



        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://kit.fontawesome.com/1d7268affb.js"
        crossorigin="anonymous"></script>
        <script src="./search_term.js" type="module"></script>
        <script src="./search_location.js" type="module"></script>
        <script src="./js/toast.js" type="text/javascript"></script>
        <script src="./deserialized.js"></script>
        <!--<script src="./home.js"></script>-->
        <script>
            <c:if test="${sessionScope.logout == true}">
            createToast("success", "Log out successfully");
                <c:remove scope="session" var="logout" />
            </c:if>
        </script>
        <script>
            $('.apply_btn').on('click', function (e) {
                e.preventDefault();
                $('#modalbtn').click();
            })
            $('#submit-modal').on('click', function (e) {
                $('#file-form').submit();
            })
            $(document).ready(function () {
                $(".seemore_btn").click(function () {
                    var productId = $(this).data("product-id");
                    $("section").load("./pages/user/read.jsp?id=" + productId + "&page=home");
                });
            });
        </script>
    </body>

</html>