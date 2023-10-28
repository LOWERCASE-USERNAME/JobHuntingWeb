let eventSource;
let data;
let cityCode;
let distCode;
$(document).ready(function () {
  $('.location-search').on('keydown', (e) => {
    eventSource = e.key ? 'input' : 'list'; //e.key not null then from keyboard, else from datalist
    eventSource += e.target.id;
    console.log(eventSource);
  });
  $('.location-search').on('keydown change input paste', (e) => {
    $('#job_location').val($('#house-search').val() + ", " + $('#ward-search').val() + ", " + $('#dist-search').val() + ", " + $('#city-search').val());
  });
  $('.location-search').on('focus', function (e) {
    const searchTerm = $(this).val();
    const type = $(this).attr('data-type');
    const container = $(this).next();
    autocompleteLocation(searchTerm, type, container);
  })
  $('#city-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedCity = data.find(function (city) {
      return city.name === value;
    })
    if (selectedCity) {
      cityCode = selectedCity.code;
      autocompleteLocation('', 'd', $('#distList'), false);
      $('#dist-search').focus();
    }
  })
  $('#dist-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedDist = data.find(function (dist) {
      return dist.name === value;
    })
    if (selectedDist) {
      distCode = selectedDist.code;
      autocompleteLocation('', 'w', $('#wardList'), false);
      $('#ward-search').focus();
    }
  })
  $('#ward-search').on('change input paste', (e) => {
    let value = e.target.value;
    let selectedWard = data.find(function (dist) {
      return dist.name === value;
    })
    if (selectedWard) {
      $('#house-search').focus();
    }
  })
})


function autocompleteLocation(searchTerm, type, container, render = true) { //I actually don't remember why I must do api call from the server side 
  const baseURL = 'http://localhost:9999/RecruitCenter/ServerSideRequest';
  axios.get(baseURL, {
    params: {
      URL: `https://provinces.open-api.vn/api/${type}/`,
      term: searchTerm
    }
  })
    .then(function (response) {
      data = JSON.parse(response.data);
      if (type === 'd') {
        if(eventSource === 'listcity-search'){
          data = data.filter(function (district) {
            return district['province_code'] === cityCode;
          })
        }
      }
      
      if (type === 'w') {
        if(eventSource === 'listdist-search'){
          data = data.filter(function (ward) {
            return ward['district_code'] === distCode;
          })
        }
      }
      if(render){
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
