
const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
const personification = ['queryName.html', 'queryLocation.html', 'queryTypeAccount.jsp', 'queryUsername.html'];


$(document).ready(function () {
    //validation for email on password
    $('#email-input').on("keydown paste blur input", function () {
        handleChange($(this), emailPattern);
    })
    $('#pw-input').on("keydown paste blur input", function () {
        handleChange($(this), passwordPattern);
    })
})

$(document).on("submit", 'form', function (e) {
    e.preventDefault();
    //submit the data to SignUpServlet.
    const form = document.querySelector('form');
    const formData = $(this).serialize();
    console.log(formData);
    $.ajax({
        url: form.action,
        method: form.method,
        xhrFields: {
            withCredentials: true
        },
        data: formData,
        success: function (data, testStatus, jqXHR) {
            console.log(data);
            const page = $('input[name="page"]').val();
            console.log(jqXHR.getResponseHeader("X-NextPage"));
            if(page === 'signup'){ 
                if(jqXHR.getResponseHeader("X-NextPage") === "true"){
                    onFormSubmitSuccess();
                    createToast("success", "Email signup successful");
                } else {
                    createToast("error", "Email already exist. Try with another email");
                }
            } else if(page === 'queryusername') {
                if(jqXHR.getResponseHeader("X-NextPage") === "true"){
                    window.location.href = "../welcome/welcome.html";
                    createToast("success", "You have finish your personification");
                } else {
                    createToast("error", "Username already exist. Try with another username");
                }
            } else {
                onFormSubmitSuccess();
            }
        },
        error: function (xhr, status, error) {
            console.error("Request failed with status: " + error);
        }
    });
});

function onFormSubmitSuccess(){
    //get next page url
    const URL = personification[$('.progress').attr('data-next-page')];

    //animation for the progress bar
    $('.progress .progress-bar:last-child').css('width', $('.progress .progress-bar:last-child').attr('data-width'));

    if (URL !== undefined) { //load page in order
        $.get(`../personification/${URL}`, function (data) {
            setTimeout(function () {
                //load the next page
                $('main').html(data);
                //special page action
                if (URL === 'queryLocation.html') {
                    //api to autocomplete user location
                    getLocation();
                }
                if (URL === 'queryTypeAccount.jsp') {
                    //change the submit button based on user action
                    $('#radio-type-acc label').on("click", function (e) {
                        const btnlabel = $(this);
                        $('input[type="submit"').val(`Yes I am a ${btnlabel.text().toLowerCase()}`);
                    });
                }
            }, 1000);
        });
    }
}

function handleChange(element, pattern) {
    const e = element;
    if (e.val() === '') {
        e.removeClass('is-valid');
        e.removeClass('is-invalid');
    }
    else if (pattern.test(e.val())) {
        e.removeClass('is-invalid');
        e.addClass('is-valid');
    } else {
        e.removeClass('is-valid');
        e.addClass('is-invalid');
    }
}

function getLocation() {
    const options = {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0,
    };
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, notAllow, options);
    } else {
        console.log('not support geolocation');
    }
}

function showPosition(position) {
    axios.get('https://api.bigdatacloud.net/data/reverse-geocode-client', {
        params: {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
            localityLanguage: 'v`i'
        }
    })
        .then(function (response) {
            const data = response.data;
            $("#country").val(data.countryName);
            $("#country").addClass("is-valid");
            $("#city").val(data.city);
            $("#city").addClass("is-valid");
            $("#district").val(data.locality);
            $("#district").addClass("is-valid");
        })
        .catch(function (error) {
            console.log(error);
        })
}

function notAllow() {
    console.log("User not allow Geolocation")
}

