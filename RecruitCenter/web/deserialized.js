$(document).ready(function(){
    deserializeUrlParams();
    console.log('hi')
    console.log(decodeURIComponent('Th%E0nh%20ph?%20H%E0%20N?i'));
})

function deserializeUrlParams() {
    var queryString = window.location.search.substring(1);
    var params = queryString.split('&');
  
    for (var i = 0; i < params.length; i++) {
      var param = params[i].split('=');
      var paramName = decodeURIComponent(param[0]);
      var paramValue = decodeURIComponent(param[1].replace(/\+/g, ' '));
  
      // Find the corresponding input field or select element and set its value
      var inputField = $('input[name="' + paramName + '"]');
      if (inputField.length > 0) {
        inputField.val(paramValue);
      } else {
        var selectField = $('select[name="' + paramName + '"]');
        if (selectField.length > 0) {
          selectField.val(paramValue);
        }
      }
    }
  }