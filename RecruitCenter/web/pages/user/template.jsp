<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.JobType" %>
<%@page import="model.Field" %>
<%@page import="model.Workplace" %>
<%@page import="model.Degree" %>
<sql:setDataSource var="dataSource"
                   driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                   user="giangthhe170978" password="123" />
<sql:query dataSource="${dataSource}" var="resultRecord">
    SELECT * from Recruitments WHERE id LIKE '${jobid}';
</sql:query>
<c:set var="job" value="${resultRecord.rows[0]}"/>
<c:set var="isUpdate" value="${param.page == 'update'}"/>
<sql:query dataSource="${dataSource}" var="comp">
    SELECT * from Companies WHERE CompanyID LIKE '${job.companyID}';
</sql:query>
<%--<c:out value="${isUpdate}"/>--%>
<div id="container">
    <div class="card p-3 contenteditable" id="intro-card">
        <div>
            <h1 id="job-title">${job.jobtitle}</h1>
            <h3 id="company-title">${comp.rows[0].company}</h3>
            <i class="fa-solid fa-money-check-dollar"></i><div ><span id="salary">${job.salaries}</span> 
                <c:if test="${job.negotiable == true}">
                    <span id="nego">(negotiable)</span>
                </c:if>
            </div>
            <i class="fa-solid fa-location-dot"></i><div id="job-location">${job.location}</div>
            <i class="fa-solid fa-person-walking"></i>
            <c:choose>
                <c:when test="${isUpdate}">
                    <select name="workplace">
                        <c:forEach var="w" begin="1" end="${fn:length(Workplace.WORKPLACES) - 1}">
                            <option value="${Workplace.WORKPLACES[w]}" <c:if test="${Workplace.WORKPLACES[w] == job.workplace}">selected</c:if>>${Workplace.WORKPLACES[w]}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <div>${job.workplace}</div>
                </c:otherwise>
            </c:choose>
            <i class="fa-solid fa-file-signature"></i>
            <c:choose>
                <c:when test="${isUpdate}">
                    <select name="jobtype">
                        <c:forEach var="w" begin="1" end="${fn:length(JobType.JOB_TYPES) - 1}">
                            <option value="${w}" <c:if test="${w == job.jobtypeid}">selected</c:if>>${JobType.JOB_TYPES[w]}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <div>${JobType.JOB_TYPES[job.jobtypeid]}</div>
                </c:otherwise>
            </c:choose>
            <i class="fa-solid fa-venus-mars"></i>
            <c:choose>
                <c:when test="${isUpdate}">
                    <select name="gender">
                        <option value="male" <c:if test="${job.gender == 'male'}">selected</c:if>>male</option>
                        <option value="female" <c:if test="${job.gender == 'female'}">selected</c:if>>female</option>
                        <option value="both" <c:if test="${job.gender == 'both'}">selected</c:if>>both</option>
                        </select>
                </c:when>
                <c:otherwise>
                    <div>${job.gender}</div>
                </c:otherwise>
            </c:choose>

            <i class="fa-solid fa-graduation-cap"></i>
            <c:choose>
                <c:when test="${isUpdate}">
                    <select name="degree">
                        <c:forEach var="w" begin="1" end="${fn:length(Degree.DEGREES) - 1}">
                            <option value="${Degree.DEGREES[w]}" <c:if test="${Degree.DEGREES[w] == job.degree}">selected</c:if>>${Degree.DEGREES[w]}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <div>${job.degree}</div>
                </c:otherwise>
            </c:choose>
        </div>
        <hr>
        <c:choose>
            <c:when test="${isUpdate}">
                Posted Date: <input type="date" value="${job.postedDate}" name="postedDate" readonly="true">
            </c:when>
            <c:otherwise>
                <div>Posted Date: ${job.postedDate}</div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${isUpdate}">
                Expiration Date: <input type="date" value="${job.expirationDate}" name="expDate">
            </c:when>
            <c:otherwise>
                <div>Expiration Date: ${job.expirationDate}</div>
            </c:otherwise>
        </c:choose>
        <sql:query dataSource="${dataSource}" var="skill">
            SELECT * from SkillandTitle WHERE ID LIKE '${job.skillandtitleid}';
        </sql:query>
        <sql:query dataSource="${dataSource}" var="skillAll">
            SELECT * from SkillandTitle;
        </sql:query>
        <c:choose>
            <c:when test="${isUpdate}">
                Skills: 
                <select name="skill">
                    <c:forEach items="${skillAll.rows}" var="skillitem">
                        <option value="${skillitem.id}" <c:if test="${skillitem.id == job.skillandtitleid}">selected</c:if>>${skillitem.skillandtitle}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <div>Skills: ${skill.rows[0].skillandtitle}</div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${isUpdate}">
                Field: 
                <select name="field">
                    <c:forEach var="w" begin="1" end="${fn:length(Field.FIELD) - 1}">
                        <option value="${w}" <c:if test="${w == job.fieldid}">selected</c:if>>${Field.FIELD[w]}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <div>Field: ${Field.FIELD[job.fieldid]}</div>
            </c:otherwise>
        </c:choose>
        <div class="position-relative">
            <a href="${applicationScope.emailContent}" class="btn btn-danger text-white m-3 w-75">Apply Now</a>
            <input id="toggle-heart" type="checkbox" />
            <label for="toggle-heart"><i class="fa-regular fa-face-grin-hearts"></i></label>
        </div>
    </div>
    <div class="card p-3 contenteditable" id="company-card">
        <h2 id="comp">${comp.rows[0].company}</h2>
        <h5>Company Website: <span id="comp-web">${comp.rows[0].website}</span></h5>
        <i class="fa-solid fa-users"></i>
        <div>
            Company Size:
            <c:choose>
                <c:when test="${isUpdate}">
                    <select name="companysize">
                        <option value="0-50 persons" <c:if test="${job.company_size == '0-50 persons'}">selected</c:if>>0-50 persons</option>
                        <option value="50-150 persons" <c:if test="${job.company_size == '50-150 persons'}">selected</c:if>>50-150 persons</option>
                        <option value="150-300 persons" <c:if test="${job.company_size == '150-300 persons'}">selected</c:if>>150-300 persons</option>
                        <option value="300-500 persons" <c:if test="${job.company_size == '300-500 persons'}">selected</c:if>>300-500 persons</option>
                        <option value=">500 persons" <c:if test="${job.company_size == '>500 persons'}">selected</c:if>>>500 persons</option>
                        </select>
                </c:when>
                <c:otherwise>
                    ${job.company_size}
                </c:otherwise>
            </c:choose>
        </div>
        <i class="fa-solid fa-map-location-dot"></i><div >Company Location: <span id="comp-location">${job.location}</span></div>
        <i class="fa-solid fa-star"></i><div>Company Ratings: ${comp.rows[0].ratings}</div>
        <i class="fa-solid fa-signature"></i><div >Contact Name: <span id="contact_name">${job.contactname}</span></div>
        <i class="fa-solid fa-at"></i><div >Contact Email: <span id="contact_email">${job.contactemail}</div>
        <i class="fa-solid fa-mobile-retro"></i><div >Contact Phone Number: <span id="contact_phone">${job.contactphonenumber}</span></div>
    </div>
    <div class="card p-2 contenteditable" style="white-space: pre-wrap" id="job-card">
        ${job.jobdescription}
    </div>
</div>