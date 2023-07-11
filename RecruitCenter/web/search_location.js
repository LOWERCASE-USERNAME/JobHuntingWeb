let eventSource;
let data;
let cityCode;
let distCode;
$(document).ready(function () {
  $('.location-search').on('keydown change input paste', (e) => {
    // eventSource = e.key ? 'input' : 'list'; //e.key not null then from keyboard, else from datalist
    $('#job_location').val($('#house-search').val() + ", " + $('#ward-search').val() + ", " + $('#dist-search').val() + ", " + $('#city-search').val());
  });
  $('.location-search').on('focus', function (e) {
    var searchTerm = $(this).val();
    var type = $(this).attr('data-type');
    const container = $(this).next();
    autocompleteLocation(searchTerm, type,container);
  })
  $('#city-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedCity = data.find(function (city) {
      return city.name === value;
    })
    if(value === ''){
      $('#dist-search').attr('hidden', true);
      $('#ward-search').attr('hidden', true);
      $('#house-search').attr('hidden', true);
    }
    if (selectedCity) {
      cityCode = selectedCity.code;
      autocompleteLocation('', 'd', $('#distList'), false, true);
      $('#dist-search').focus();
      $('#dist-search').attr('hidden', false);
    }
  })
  $('#dist-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedDist = data.find(function (dist) {
      return dist.name === value;
    })
    if(value === ''){
      $('#ward-search').attr('hidden', true);
      $('#house-search').attr('hidden', true);
    }
    if (selectedDist) {
      distCode = selectedDist.code;
      autocompleteLocation('', 'w', $('#wardList'), false, true);
      $('#ward-search').focus();
      $('#ward-search').attr('hidden', false);
    }
  })
  $('#ward-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedWard = data.find(function (dist) {
      return dist.name === value;
    })
    if(value === ''){
      $('#house-search').attr('hidden', true);
    }
    if (selectedWard) {
      $('#house-search').focus();
      $('#house-search').attr('hidden', false);
    }
  })
})


function autocompleteLocation(searchTerm, type, container, usingCode = false, falling = false) { //I actually don't remember why I must do api call from the server side 
  const baseURL = 'http://localhost:9999/RecruitCenter/ServerSideRequest';
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
