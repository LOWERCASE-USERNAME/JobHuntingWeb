<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RecruitCenter</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styleCVbuilder.css">
    <script>
        function getPDF() {
            var HTML_Width = $(".template").width();
            var HTML_Height = $(".template").height();
            var top_left_margin = 15;
            var PDF_Width = HTML_Width + (top_left_margin * 2);
            var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
            var canvas_image_width = HTML_Width;
            var canvas_image_height = HTML_Height;

            var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;


            html2canvas($(".template")[0], { allowTaint: true }).then(function (canvas) {
                canvas.getContext('2d');

                console.log(canvas.height + "  " + canvas.width);


                var imgData = canvas.toDataURL("image/jpeg", 1.0);
                var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
                pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);


                for (var i = 1; i <= totalPDFPages; i++) {
                    pdf.addPage(PDF_Width, PDF_Height);
                    pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height * i) + (top_left_margin * 4), canvas_image_width, canvas_image_height);
                }

                pdf.save("RecruitCenter_Resume.pdf");
            });
        };
    </script>
</head>

<body>
    <div class="template">
        <div style="width: 100%;">
            <div class="text-center w-100" id="thumbnail">
                <img src="../../../assets/unknown-man.png" class="img-thumbnail img-circle">
            </div>
            <div contenteditable="true" id="name" class="text-center">Tran Hoang Giang</div>
            <div contenteditable="true" id="headline" class="text-center">Your headline or current title</div>
        </div>
        <hr>
        <div class="d-flex flex-row justify-content-between">
            <div style="width: 70%;">
                <div id="aboutme">
                    <strong>ABOUT ME</strong>
                    <div contenteditable="true">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Beatae dolor illum repudiandae?
                        Dignissimos,
                        nam tenetur voluptatibus dolorem incidunt debitis, consequuntur nisi quo repellat praesentium
                        eos
                        obcaecati fugit iusto assumenda ipsum!
                    </div>
                </div>
                <div id="workexp">
                    <strong>WORK EXPERIENCE</strong>
                    <!-- TODO: create a button here to add more work record -->
                    <div class="job-record">
                        <div class="record-title" contenteditable="true"><mark>Junior Software Developer</mark></div>
                        <div class="record-company" contenteditable="true">IBM Vietnam</div>
                        <div class="record-desc">
                            <ul contenteditable="true">
                                <li>Designing and building advanced applications for the Android platform.</li>
                                <li>Collaborating with cross-functional teams to define, design, and ship new features.
                                </li>
                                <li>fixing and improving application performance.</li>
                            </ul>
                        </div>
                    </div>
                    <div class="job-record">
                        <div class="record-title" contenteditable="true"><mark>Software Engineer</mark></div>
                        <div class="record-company" contenteditable="true">Adobe Systems</div>
                        <div class="record-desc">
                            <ul contenteditable="true">
                                <li>Contributed to the development of CTS-PARS (Automated Permitting and Routing System)
                                    applications for the Road Transportation Office.</li>
                                <li>Designed and Developed CTS-MCnow (Carrier App) for fetching Route information using
                                    Xamarin Forms.</li>
                                <li>Developed some modules in the Automated Routing System using the ArcGIS platform.
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="job-record">
                        <div class="job-record-title" contenteditable="true"><mark>Software Engineering Intern</mark>
                        </div>
                        <div class="job-record-company" contenteditable="true">Jet Brains</div>
                        <div class="job-record-desc">
                            <ul contenteditable="true">
                                <li>Provided engineering support on ongoing software projects</li>
                                <li>Wrote test data frameworks.</li>
                                <li>Worked with the software team to coordinate the development of new software features
                                    and
                                    resolving issues.</li>
                                <li>Provided technical support to the project's Officers.</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div id="education">
                    <strong>EDUCATION</strong>
                    <div class="edu-record">
                        <div class="edu-record-title" contenteditable="true">Bachelor Of Science In Software Engineering
                        </div>
                        <div class="edu-record-school" contenteditable="true">FPT University</div>
                        <div class="edu-record-desc" contenteditable="true">3.6 GPA Graduate, 1 semester of Excellency
                        </div>
                        <div class="edu-record-subject">
                            <ul contenteditable="true">
                                <li>Computer Science</li>
                                <li>Software Engineering</li>
                                <li>Computer Architecture</li>
                                <li>Web Dev(Frontend, Backend)</li>
                                <li>Programming Fundamental</li>
                                <li>Mathemetics</li>
                                <li>Operating System</li>
                                <li>Networking</li>
                                <li>Data Structure and Algorithm</li>
                                <li>Database</li>
                                <li>Japanese</li>
                                <li>IOT</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div style="width: 25%;">
                <div id="contact">
                    <strong>CONTACT</strong>
                    <div class="location" contenteditable="true">
                        <i class="fa-solid fa-map-location-dot"></i>
                        Ngoc Lam, Long Bien, Ha Noi
                    </div>
                    <div class="phonenum" contenteditable="true">
                        <i class="fa-solid fa-phone"></i>
                        0979590069
                    </div>
                    <div class="email" contenteditable="true">
                        <i class="fa-solid fa-at"></i>
                        giangthhe170978@fpt.edu.vn
                    </div>
                    <!-- TODO: add contact button -->
                </div>
                <div id="skills">
                    <strong>SKILL</strong>
                    <!-- TODO: add skill -->
                    <div class="skill">
                        <div class="skill-field" contenteditable="true">Build Systems</div>
                        <input type="range" max="100" min="0" step="1" value="100">
                        <br>
                        <ul contenteditable="true" class="skill-list">
                            <li>Maven</li>
                            <li>Ant</li>
                        </ul>
                    </div>
                    <div class="skill">
                        <div class="skill-field" contenteditable="true">Version Control Systems</div>
                        <input type="range" max="100" min="0" step="1" value="100">
                        <br>
                        <ul contenteditable="true" class="skill-list">
                            <li>Git</li>
                        </ul>
                    </div>
                    <div class="skill">
                        <div class="skill-field" contenteditable="true">Programming Language</div>
                        <input type="range" max="100" min="0" step="1" value="100">
                        <br>
                        <ul contenteditable="true" class="skill-list">
                            <li>C</li>
                            <li>C#</li>
                            <li>Java</li>
                            <li>Python</li>
                        </ul>
                    </div>
                    <div class="skill">
                        <div class="skill-field" contenteditable="true">Databases</div>
                        <input type="range" max="100" min="0" step="1" value="100">
                        <br>
                        <ul contenteditable="true" class="skill-list">
                            <li>SQL Server</li>
                            <li>MySQL</li>
                        </ul>
                    </div>
                    <div class="skill">
                        <div class="skill-field" contenteditable="true">Office</div>
                        <input type="range" max="100" min="0" step="1" value="100">
                        <br>
                        <ul contenteditable="true" class="skill-list">
                            <li>Microsoft Word</li>
                            <li>Microsoft Excel</li>
                            <li>Microsoft Powerpoint</li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="text-center">
        <button class="btn btn-success m-4" onclick="getPDF()">Save To PDF</button>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>
    <script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
    <script src="./CVbuilderScript.js"></script>
    
    <script src="https://kit.fontawesome.com/1d7268affb.js" crossorigin="anonymous"></script>
</body>

</html>