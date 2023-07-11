let eventSource;
let data;
let cityCode;
let distCode;
$(document).ready(function () {
  $('#job-search').on('focus', function () {
    var searchTerm = $(this).val();
    $.ajax({
      url: 'SearchJobTerm',
      type: 'GET',
      data: { jobSearch: searchTerm },
      success: function (data) {
        $('#jobsList').empty();
        // console.log(data);
        data.forEach(function (option) {
          $('#jobsList').append('<option value="' + option + '">');
        });
      },
      error: function (xhr, status, error) {
        // Handle errors
        console.error("Request failed with status: " + error);
      }
    })
  })
})


