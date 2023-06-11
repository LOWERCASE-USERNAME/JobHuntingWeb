$(document).ready(function () {
  $('#job-search').on('input', function () {
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

  $('#location-search').on('input', function () {
    var searchTerm = $(this).val();
    $.ajax({
      url: 'https://provinces.open-api.vn/api',
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





