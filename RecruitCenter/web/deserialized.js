$(document).ready(function(){
    deserializeUrlParams();
})

function deserializeUrlParams() {
    const queryString = window.location.search.substring(1);
    const params = queryString.split('&');
  
    for (let i = 0; i < params.length; i++) {
      const param = params[i].split('=');
      const paramName = decodeURIComponent(param[0]);
      const paramValue = decodeURIComponent(param[1].replace(/\+/g, ' '));
  
      // Find the corresponding input field or select element and set its value
      const inputField = $('input[name="' + paramName + '"]');
      if (inputField.length > 0) {
        inputField.val(paramValue);
      } else {
        const selectField = $('select[name="' + paramName + '"]');
        if (selectField.length > 0) {
          selectField.val(paramValue);
        }
      }
    }
  }