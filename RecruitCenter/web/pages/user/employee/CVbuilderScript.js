$('.template div, .template ul').hover( function(e){
    e.stopPropagation();
    $(this).css('outline', 'black dashed 1px');
}, function(e){
    e.stopPropagation();
    $(this).css('outline', 'none');
})