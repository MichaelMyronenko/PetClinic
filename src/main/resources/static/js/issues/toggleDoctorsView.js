$(function () {
    $(".issues-links").on('click', '.nav-item', function () {
        $(".issues-links").find("a.nav-link").removeClass("active");
        $(this).find("a.nav-link").addClass("active");
        switch ($(this).find(".type").val()) {
            case "doctors-view":
                $(".issue-view").css('display', 'none').removeClass('opened');
                $(".doctor-issue-view").css('display', 'block').addClass('opened');
                getOpenedIssuesRequest(0);
                break;
            case "owners-view":
                $(".issue-view").css('display', 'none').removeClass('opened');
                $(".owner-issue-view").css('display', 'block').addClass('opened');
                getMyIssues(0);
                break;
        }
    });
});