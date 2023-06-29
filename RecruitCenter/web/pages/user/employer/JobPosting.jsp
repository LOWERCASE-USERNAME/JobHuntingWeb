<html>

<head>
    <title>RecruitCenter</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="styleJobPosting.css">
</head>

<body>
    <form action="" class="form-container">
        <fieldset class="form">
            <legend class="heading">Company Information</legend>
            <div class="form-group">
                <label for="company">Your company's name: </label>
                <input id="company" type="text" class="form-control" name="company" placeholder="Company ABC"
                    required>
            </div>
            <div class="form-group">
                <label for="company_size">Your company size: </label>
                <select id="company_size" type="text" class="form-select" name="company_size">
                    <option selected disabled hidden></option>
                    <option value="0-50">0-50 persons</option>
                    <option value="50-100">50-150 persons</option>
                    <option value="150-300">150-300 persons</option>
                    <option value="300-500">300-500 persons</option>
                    <option value=">500">>500 persons</option>
                </select>
            </div>
            <div class="form-group">
                <label for="company">Your company's website: </label>
                <input id="company_website" type="url" class="form-control" name="company_website"
                    placeholder="https://mycompanywebsite.com">
            </div>
            <hr>
            <div class="form-group">
                <label for="contact_name">Contact person: </label>
                <input id="contact_name" type="text" class="form-control" name="contact_name"
                    placeholder="Mr/Mrs ABC" required>
            </div>
            <div class="form-group">
                <label for="contact_email">Your contact email: </label>
                <small>Please make sure that the email above is work email, because we will send notifiaction about the
                    candidate through this email address</small>
                <input id="contact_email" type="email" class="form-control" name="contact_email"
                    placeholder="abc@work.com" required>
            </div>
            <div class="form-group">
                <label for="contact_phone">Your contact phone number: </label>
                <small>Please make sure that the email above is work phone, because this will be public to all
                    candidate</small>
                <input id="contact_phone" type="tel" class="form-control" name="contact_phone"
                    placeholder="(+84)000 999 11">
            </div>
        </fieldset>

        <button type="submit" class="btn">Submit</button>

        <fieldset class="form">
            <legend class="heading">Job Information</legend>
            <div class="form-group">
                <!-- TODO: put a level at here eg Fresher Junior -->
                <label for="job_title">Job Title: </label>
                <input id="job_title" type="text" class="form-control" name="job_title"
                    placeholder="Software Developer" required>
            </div>
            <div class="form-group">
                <label for="job_type">Job Type: </label>
                <select id="job_type" type="text" class="form-select" name="job_type">
                    <option selected disabled hidden></option>
                    <option value="1">Full-time</option>
                    <option value="2">Part-time</option>
                    <option value="3">Temporary</option>
                    <option value="4">Contract</option>
                    <option value="5">Freelance</option>
                </select>
            </div>
            <div class="form-group">
                <label for="job_field">Field: </label>
                <small>Not required, but this will help your job reach a wider audience</small>
                <select id="job_field" type="text" class="form-select" name="job_field">
                    <!-- TODO: find field on internet -->
                    <option value="">Agriculture, Food & Natural Resources</option>
                    <option value="">Architecture & Construction</option>
                    <option value="">Arts, Audio/Video Technology & Communications</option>
                    <option value="">Business Management & Administration</option>
                    <option value="">Education & Training</option>
                    <option value="">Finance</option>
                    <option value="">Government & Public Administration</option>
                    <option value="">Health Science</option>
                    <option value="">Hospitality & Tourism</option>
                    <option value="">Human Services</option>
                    <option value="">Information Technology</option>
                    <option value="">Law, Public Safety, Corrections & Security</option>
                    <option value="">Manufacturing</option>
                    <option value="">Marketing</option>
                    <option value="">Science, Technology, Engineering & Mathematics</option>
                    <option value="">Transportation, Distribution & Logistics</option>
                </select>
            </div>

            <!-- TODO: figure out how to do the address thingy -->
            <div class="form-group">
                <label for="job_location">Job Location: </label>
                <!-- create four datalist to recommend and one input which is readonly -->
                <input id="job_location" type="text" class="form-control" name="job_location" placeholder=""
                    required>
            </div>
            <div class="form-group">
                <label for="job_salary">Job Salary: </label>
                <input id="job_salary" type="number" class="form-control" name="job_salary" placeholder=""
                    required>
                <div class="form-check-inline">
                    <input class="form-check-input" type="checkbox" name="neg" id="neg" value="negotiable">
                    <label class="form-check-label" for="neg">Negotiable</label><!-- tick here if negotiable -->
                </div>
                
            </div>
            <div class="form-group">
                <label for="job_expire">Expiration Date: </label>
                <input id="job_expire" type="datetime-local" class="form-control" name="job_expire" placeholder="">
            </div>
            <div class="form-group">
                <label for="job_skill">Prefered Skill: </label>
                <!-- implement skill and title input here -->
            </div>
            <div class="form-check-inline">
                <label for="job_gender">Prefered Gender: </label>
                <div class="d-flex flex-row gap-4">
                    <label class="form-check-label" for="job_gender_male"><input id="job_gender_male" type="radio" class="form-check-input" name="job_gender" value="male">Male</label>
                    <label class="form-check-label" for="job_expire_female"><input id="job_expire_female" type="radio" class="form-check-input" name="job_gender" value="female">Female</label>
                </div>
            </div>
            <div class="form-group">
                <label for="job_degree">Minimum Degree: </label>
                <select id="job_degree" type="text" class="form-select" name="job_degree">
                    <option selected disabled hidden></option>
                    <option value="1">Secondary school</option>
                    <option value="2">High school</option>
                    <option value="3">College</option>
                    <option value="4">Bachelor</option>
                    <option value="5">Master</option>
                    <option value="6">PhD</option>
                </select>
            </div>
            <div class="form-group">
                <label for="job_degree">Job Type: </label>
                <select id="job_degree" type="text" class="form-select" name="job_degree">
                    <option selected disabled hidden></option>
                    <option value="1">Onsite</option>
                    <option value="2">Remote</option>
                    <option value="3">Both</option>
                </select>
            </div>
            <hr>
            <div class="form-group">
                <label for="job_desc">Job Descriptions: </label>
                <textarea class="form-control" id="job_desc" rows="10"></textarea>
            </div>
            <div class="form-group">
                <label for="job_qualification">Job Qualifications: </label>
                <textarea class="form-control" id="job_qualification" rows="10"></textarea>
            </div>
            <div class="form-group">
                <label for="job_benefit">Job Benefits: </label>
                <textarea class="form-control" id="job_benefit" rows="10"></textarea>
            </div>
            <!-- small: three of this will be aggregated together to create a overall description or you can upload a pdf to heree -->
        </fieldset>
    </form>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html> 















