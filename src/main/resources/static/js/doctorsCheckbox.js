$(function () {
    $(".i-am-doctor").on('click', function () {
        if ($('.i-am-doctor').is(':checked')) {
            $(".doctors-login").css("display", "block");
        } else {
            $(".doctors-login").css("display", "none");
            $(".doctors-login input").val('');
        }
    });
});