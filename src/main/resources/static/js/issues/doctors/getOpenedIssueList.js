$(function () {
    if ($(".doctor-issue-view").hasClass('opened')) {
        getOpenedIssuesRequest();
    }
});

function openedIssueList(listSelector, issues) {
    listSelector.empty();
    listSelector.append(issues.length !== 0
        ? `<li class="list-group-item d-flex">
                <div class="col-1">ID</div>
                <div class="col-8">Issue title</div>
                <div class="col-3">Assigned to</div>
           </li>`
        : ``);

    for (let i = 0; i < issues.length; i++) {

        listSelector.append(
            `<li class="list-group-item opened-issue-list-item-${issues[i].issueId}">
                <input type="hidden" class="hidden-issue-id" value="${issues[i].issueId}">
                <div class="d-flex">
                    <div class="col-1 issue-id">${issues[i].issueId}</div>
                    <div class="col-8 issue-title">${issues[i].issueTitle}</div>
                    <div class="col-3 issue-assigned-to"></div>
                </div>
             </li>`);
        assignedTo(issues[i]);
    }
}

function assignedTo(issue) {
    if (issue.doctorId === null) {
        $(`.opened-issue-list-item-${issue.issueId}`).find(`.issue-assigned-to`)
            .html(`<button type="button" class="btn btn-primary btn-sm assign-issue-to-me">Assign to me</button>`);
    } else {
        $.get(`/api/doctors/${issue.doctorId}`, function (data) {
            $(`.opened-issue-list-item-${issue.issueId}`).find(`.issue-assigned-to`).html(`<a href="#">${data.username}</a>`)
        });
    }
}

function getOpenedIssuesRequest() {
    $.get(`/api/issues/doctor`, function (data) {
        openedIssueList($(`.opened-issues-list ul`), data);
    })
}