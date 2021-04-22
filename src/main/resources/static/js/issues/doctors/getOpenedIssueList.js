$(function () {
    getOpenedIssuesRequest();
});

function openedIssueList(listSelector, issues) {
    let issuesMarkup = issues.length !== 0
        ? `<li class="list-group-item d-flex">
                <div class="col-1">ID</div>
                <div class="col-8">Issue title</div>
                <div class="col-3">Assigned to</div>
           </li>`
        : ``;

    for (let i = 0; i < issues.length; i++) {
        issuesMarkup +=
            `<li class="list-group-item opened-issue-list-item">
                <input type="hidden" class="hidden-issue-id" value="${issues[i].issueId}">
                <div class="d-flex">
                    <div class="col-1 issue-id">${issues[i].issueId}</div>
                    <div class="col-8 issue-title">${issues[i].issueTitle}</div>
                    <div class="col-3 issue-assigned-to">${getAssignedTo(issues[i])}</div>
                </div>
             </li>`;
    }
    listSelector.html(issuesMarkup);
}

function getAssignedTo(issue) {
    return issue.doctor == null
        ? `<button type="button" class="btn btn-primary btn-sm assign-issue-to-me">Assign to me</button>`
        : `<a href="#">${issue.doctor.username}</a>`;
}

function getOpenedIssuesRequest() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: `/api/issues`,
        success: function (data) {
            openedIssueList($(`.opened-issues-list ul`), data);
        }
    })
}