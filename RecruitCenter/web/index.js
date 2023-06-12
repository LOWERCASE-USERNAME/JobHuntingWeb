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

  $('.location-search').on('keydown', (e) => {
    eventSource = e.key ? 'input' : 'list'; //e.key not null then from keyboard, else from datalist
  });
  $('.location-search').on('focus', function (e) {
    var searchTerm = $(this).val();
    var type = $(this).attr('data-type');
    const container = $(this).next();
    autocompleteLocation(searchTerm, type, container);
  })

  $('#city-search').on('change', (e) => {
    let value = e.target.value;
    let selectedCity = data.find(function (city) {
      return city.name === value;
    })
    if (selectedCity) {
      cityCode = selectedCity.code;
      autocompleteLocation('', 'd', $('#distList'), false, true);
      $('#dist-search').focus();
    }
  })
  $('#dist-search').on('change', (e) => {
    let value = e.target.value;
    let selectedDist = data.find(function (dist) {
      return dist.name === value;
    })
    if (selectedDist) {
      distCode = selectedDist.code;
      autocompleteLocation('', 'w', $('#wardList'), false, true);
      $('#ward-search').focus();
    }
  })
})


function autocompleteLocation(searchTerm, type, container, usingCode = false, falling = false) {
  const baseURL = 'ServerSideRequest';
  axios.get(baseURL, {
    params: {
      URL: `https://provinces.open-api.vn/api/${type}/`,
      term: searchTerm,
      usingCode: usingCode
    }
  })
    .then(function (response) {
      data = JSON.parse(response.data);
      if (falling && type === 'd') {
        data = data.filter(function (district) {
          return district['province_code'] === cityCode;
        })
        renderLocationList(data, container);
      }
      if (type === 'p') {
        renderLocationList(data, container);
      }
      if (falling && type === 'w') {
        data = data.filter(function (ward) {
          return ward['district_code'] === distCode;
        })
        renderLocationList(data, container);
      }
    })
    .catch(function (error) {
      console.log(error);
    })
}

function renderLocationList(optionList, container) {
  container.empty();
  optionList.forEach(function (option) {
    container.append(`<option value="${option.name}" data-code="${option.code}">`);
  });
}
