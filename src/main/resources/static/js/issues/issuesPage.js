$(function () {
    $(`[class*="issues-list"] ul`).on('click', '[class*=issue-list-item]', function () {
        let issueId = $(this).find('.hidden-issue-id').val();

        document.location.href = `/issues/${issueId}`;
    });
});