$(function() {
    $(document).on('contextmenu', function(e) {
        return !$(e.target).is('img');
    });
});