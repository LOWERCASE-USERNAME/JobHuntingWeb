<%-- 
    Document   : company
    Created on : Jul 17, 2023, 12:20:49 AM
    Author     : dell
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <!--${sessionScope.userid}-->
        <sql:setDataSource var="dataSource"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://DESKTOP-TFONDGT\\GIANG:1433;databaseName=recruitCenter"
                           user="giangthhe170978" password="123" />
        <sql:query dataSource="${dataSource}" var="qCompany">
            SELECT * from Companies WHERE companyID IN (SELECT companyID FROM Users WHERE id LIKE '${sessionScope.userid}');
        </sql:query>
        <!--${qCompany.rows[0]}-->
        <c:set var="comp" value="${qCompany.rows[0]}"/>
        <div class="container-fluid" >
            <div class="row">
                <jsp:include page="sidebar.jsp">
                    <jsp:param name="activePage" value="company"/>
                </jsp:include>
                <div class="col-10" style="display: grid; grid-template-rows: auto auto 1fr; gap:40px; border: 1px tomato">
                    <div>
                        <h1>${comp.company}</h1>
                        <div>${comp.address}</div>
                        <div>${comp.numOfRecruitment} jobs recruiting</div>
                    </div>
                    <div class="bg-dark text-bg-light" style="border-radius: 10px 10px 0 0">
                        <ul class="nav nav-tabs nav-fill flex-row mb-auto" id="myTab" role="tablist">
                            <li class="nav-item ">
                                <button class="nav-link active" aria-current="page" data-bs-toggle="tab"
                                        data-bs-target="#info" type="button"
                                        role="tab">
                                    Info
                                </button>
                            </li>
                            <li class="nav-item">
                                <button class="nav-link" aria-current="page" data-bs-toggle="tab"
                                        data-bs-target="#review" type="button"
                                        role="tab">
                                    Review
                                </button>
                            </li>
                        </ul> 
                    </div>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active"
                             id="info" role="tabpanel">
                            <div>
                                <h3>General Information:</h3>
                                <div>Company size: 300-500</div>
                                <div>Langugage: Vietnamese</div>
                                <div>Working days: Monday to Friday</div>
                                <div>Overtime policy: No OT</div>
                                <a href="${comp.website}" target="_blank    ">Company Website</a>
                            </div>
                            <hr>
                            <div>
                                <h4>Company overview:</h4> 
                                <div>
                                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eos voluptatum quia, obcaecati, aliquam asperiores voluptatibus soluta ex nemo corrupti velit facilis neque nostrum eligendi sapiente ab in? Similique, eligendi numquam?
                                    Animi ratione rem neque harum consectetur nisi quia atque dolorum expedita! Cum exercitationem natus pariatur nemo ducimus dolores veritatis alias possimus eum. Fugiat, dicta. Perferendis cum assumenda accusamus dolor et.
                                    Distinctio porro natus tenetur fuga, laudantium deserunt, hic cupiditate odit excepturi maxime voluptate suscipit! Voluptatum cupiditate delectus quasi illo ipsum, voluptas officia, accusantium consequatur eveniet numquam distinctio inventore reprehenderit repellat!
                                    Quia iste odio quibusdam delectus fugiat ducimus laboriosam omnis suscipit cupiditate fugit alias harum itaque deleniti, officia nemo, distinctio adipisci voluptas tenetur! Possimus impedit deleniti quam aspernatur debitis quod laboriosam.
                                    Ipsam, iure beatae. Eius voluptatem id quod at error sed quidem nostrum tempore, similique sint, laboriosam sequi, veniam saepe incidunt eaque accusamus fugit animi blanditiis totam consequuntur! Placeat, exercitationem labore!
                                </div>
                            </div>
                            <div>
                                <h4>Why you'll love working with us:</h4>
                                <div>
                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Necessitatibus non cum odio quis a ea tempore blanditiis. Consectetur, impedit recusandae animi omnis, quia voluptatem blanditiis quisquam rem commodi deserunt nemo.
                                    Molestiae, suscipit enim vero provident corporis atque adipisci ratione sed ad? Praesentium quae non ad qui autem. Eos vitae molestiae ratione ullam, tenetur accusamus, veniam sed adipisci in quia dignissimos.
                                    Similique commodi libero recusandae delectus aperiam, nihil voluptate nostrum eligendi! Inventore excepturi porro error optio quis ipsum veritatis autem qui ratione deleniti! Iusto temporibus eaque pariatur rerum aut optio nesciunt!
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade"
                             id="review" role="tabpanel">
                            <form>
                                <table id="rating">
                                    <tr><th>Work-Life balance:</th><td><input type="range" step="0.5" min="0" max="5" value="5" style="width: 200px;" name="r1"> <span class="ratingVal">5</span></td></tr>
                                    <tr><th>Pay and Benefits balance:</th><td> <input type="range" step="0.5" min="0" max="5" value="5" style="width: 200px" name="r2"> <span class="ratingVal">5</span> </td></tr>
                                    <tr><th>Job security and Advancement:</th><td> <input type="range" step="0.5" min="0" max="5" value="5" style="width: 200px" name="r3"> <span class="ratingVal">5</span></td></tr>
                                    <tr><th>Management:</th><td> <input type="range" step="0.5" min="0" max="5" value="5" style="width: 200px" name="r4"> <span class="ratingVal">5</span></td></tr>
                                    <tr><th>Culture:</th><td> <input type="range" step="0.5" min="0" max="5" value="5" style="width: 200px" name="r5"> <span class="ratingVal">5</span> </td></tr>
                                    <caption>Rating the company</caption>
                                </table>
                                <div id="comment">
                                    <textarea style="width: 50%" name="comment" rows="5" name="comment"></textarea>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <script src="https://code.jquery.com/jquery-3.3.1.min.js"
                    integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
            <script>
                $("input[type='range']").on('change input', function () {
//                    console.log($(this).siblings('.ratingVal'))

//                    $(this).siblings('.ratingVal').delay("slow").text($(this).val());
                    var $inputRange = $(this);

                    clearTimeout($inputRange.data('timeout'));
                    var delay = 50; // 500 milliseconds
                    var timeout = setTimeout(function () {
                        // Update the text of the sibling element with class 'ratingVal'
                        $inputRange.siblings('.ratingVal').text($inputRange.val());
                    }, delay);
                    $inputRange.data('timeout', timeout);
                })
            </script>
    </body>
</html>
