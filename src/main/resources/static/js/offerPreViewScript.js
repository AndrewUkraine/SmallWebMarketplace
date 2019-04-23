;(function () {
    // debugger;
    document.getElementsByClassName('carousel-item')[0].classList.add('active');
})();

debugger;
    $("#my-button").on('click', function() {
        var $this = $(this);

        $this.attr('disabled', true);

// разблокировка
        setTimeout(function() {
            $this.attr('disabled', false);
        }, 5000);
    });
