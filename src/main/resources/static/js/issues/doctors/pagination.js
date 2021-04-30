$(function () {
    $('[class*="issues-list"]').on('click', '.pagination li', function () {
        if (!$(this).hasClass("disabled")) {
            let pageNum;
            let currentPage = Number.parseInt($(this).parents('.pagination').find('li.active input').val());

            switch ($(this).find('input').val()) {
                case "prev":
                    pageNum = currentPage - 1;
                    break;
                case "next":
                    pageNum = currentPage + 1;
                    break;
                default:
                    pageNum = $(this).find('input').val();
                    break;
            }
            if ($(this).parents('[class*="issues-list"]').hasClass("opened-issues-list")) {
                getOpenedIssuesRequest(pageNum);
            } else if ($(this).parents('[class*="issues-list"]').hasClass("my-issues-list")) {
                getMyIssues(pageNum);
            }
        }
    });
});