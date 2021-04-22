$(function () {
    $(".issues-links").on('click', '.nav-item', function () {
        $(".issues-links").find("a.nav-link").removeClass("active");
        $(this).find("a.nav-link").addClass("active");
        switch ($(this).find(".type").val()) {
            case "doctors-view":
                $(".issue-view").css('display', 'none');
                $(".doctor-issue-view").css('display', 'block')
                getOpenedIssuesRequest();
                break;
            case "owners-view":
                $(".issue-view").css('display', 'none');
                $(".owner-issue-view").css('display', 'block')
                getMyIssues();
                break;
        }
    });
});