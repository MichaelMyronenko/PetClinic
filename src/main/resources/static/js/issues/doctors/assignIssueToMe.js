$(function () {
    $(`.opened-issues-list`).on('click', 'button.assign-issue-to-me', function () {
        let parent = $(this).parents('[class*="opened-issue-list-item"]');
        let issueId = parent.find(".hidden-issue-id").val();
        let pageNum = Number.parseInt($(this).parents('.opened-issues-list').find('.pagination li.active input').val())
        updateIssueRequest(issueId, pageNum)
    });
});

function updateIssueRequest(issueId, pageNum) {
    $.ajax({
        type: "PUT",
        url: `api/issues/${issueId}/assign`,
        success: function () {
            getOpenedIssuesRequest(pageNum);
        }
    })
}