$('.seemore_btn').one('click', function(e){
    e.preventDefault();
    e.target.innerText = "SEE LESS";
    $(this).parentsUntil('.card-container').last().find('.clamped-text').css('max-height', '100em');
    $(this).one("click", seeless);
})


function seemore(e) {
    e.preventDefault();
    e.target.innerText = "SEE LESS";
    $(this).parentsUntil('.card-container').last().find('.clamped-text').css('max-height', '100em');
    $(this).one("click", seeless);
}

function seeless(e) {
    e.preventDefault();
    e.target.innerText = "SEE MORE";
    $(this).parentsUntil('.card-container').last().find('.clamped-text').css('max-height', '10em');
    $(this).one("click", seemore);
}