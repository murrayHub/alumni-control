;(function($){
    window.onbeforeunload = function() {
        document.documentElement.scrollTop = 0;
        document.body.scrollTop = 0;
    };
    $(window).scroll(function() {
        var top = $('.headerWrap').offset().top;
        if(top > 60) {
            $('.headerWrap').addClass('blackHeader');
        } else {
            $('.headerWrap').removeClass('blackHeader');
        }
    });
})(jQuery);