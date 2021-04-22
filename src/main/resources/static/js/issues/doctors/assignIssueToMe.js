$(function () {
    $(`.opened-issues-list`).on('click', 'button.assign-issue-to-me', function () {
        let parent = $(this).parents(".opened-issue-list-item");
        let issueId = parent.find(".hidden-issue-id").val();
        updateIssueRequest(issueId)

    });
});

function updateIssueRequest(issueId) {
    $.ajax({
        type: "PUT",
        url: `api/issues/${issueId}/assign`,
        success: function () {
            getOpenedIssuesRequest();
        }
    })
}