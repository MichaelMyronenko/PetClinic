$(function () {
    $(`[class*="issues-list"] ul`).on('click', '[class*=issue-list-item] .issue-title', function () {
        let issueId = $(this).parents('[class*=issue-list-item]').find('.hidden-issue-id').val();

        document.location.href = `/issues/${issueId}`;
    });
});