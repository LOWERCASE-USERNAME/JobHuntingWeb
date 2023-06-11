
const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
const personification = ['queryName', 'queryLocation', 'queryTypeAccount', 'queryUsername'];
// axios.defaults.withCredentials = true;

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
    const URL = personification[$('.progress').attr('data-next-page')];
    //animation for the progress bar
    $('.progress .progress-bar:last-child').css('width', $('.progress .progress-bar:last-child').attr('data-width'));
    //shove everything into cookies
    $('input').not('input[type="submit"],input[type="hidden"]').each(function(){
        if($(this).attr('name') === 'typeaccount'){
            document.cookie = `${$(this).attr('name')}=${$('input[name="typeaccount"]').val()}`
        } else {
            document.cookie = `${$(this).attr('name')}=${$(this).val()}`;
        }
    })

    //last page -> submit with cookie using Jquery
    if(URL === undefined){
        const form = document.querySelector('form');
        const encodedCookie = encodeURIComponent(document.cookie);
        $.ajax({
            url: form.action,
            method: form.method,
            xhrFields: {
                withCredentials: true
            },
            data: {
                "Cookie": encodedCookie
            },
            success: function(response) {
                // Handle the successful response
                console.log(response);
            },
            error: function(xhr, status, error) {
                // Handle errors
                console.error("Request failed with status: " + error);
            },
        });
        console.log(document.cookie);
        // document.forms[0].submit();
    } else {
        $.get(`../personification/${URL}.html`, function (data) {
            setTimeout(function () {
                //load the next page
                $('main').html(data);
                
                //special page action
                if (URL === 'queryLocation') {
                    //api to autocomplete user location
                    getLocation();
                }
                if (URL === 'queryTypeAccount') {
                    //change the submit button based on user action
                    $('#radio-type-acc label').on("click", function (e) {
                        const btnlabel = $(this);
                        $('input[type="submit"').val(`Yes I am a ${btnlabel.text().toLowerCase()}`)
                    })
                }
            }, 1000)
        });
    }

    // window.history.pushState({}, '', `../personification/${URL}.html`);
});

function handleChange(element, pattern) {
    const e = element;
    if (e.val() == '') {
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
            localityLanguage: 'vi'
        }
    })
        .then(function (response) {
            console.log(response.data);
            const data = response.data;
            $("#country").val(data.countryName);
            $("#country").addClass("is-valid");
            $("#city").val(data.city);
            $("#city").addClass("is-valid");
            $("#district").val(data.locality);
            $("#district").addClass("is-valid");
        })
}

function notAllow() {

}