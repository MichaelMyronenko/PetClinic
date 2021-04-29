$(function () {
    getMyIssues();
});

function myIssueList(listSelector, issues) {
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
            `<li class="list-group-item my-issue-list-item-${issues[i].issueId}">
                <input type="hidden" class="hidden-issue-id" value="${issues[i].issueId}">
                <div class="d-flex">
                    <div class="col-1 issue-id">${issues[i].issueId}</div>
                    <div class="col-8 issue-title">${issues[i].issueTitle}</div>
                    <div class="col-3 issue-assigned-to"></div>
                </div>
             </li>`);
        getAssignedTo(issues[i]);
    }
}

function getAssignedTo(issue) {
    if (issue.doctorId === null) {
        $(`.my-issue-list-item-${issue.issueId}`).find(`.issue-assigned-to`)
            .html(`<h6>Not assigned</h6>`);
    } else {
        $.get(`/api/doctors/${issue.doctorId}`, function (data) {
            $(`.my-issue-list-item-${issue.issueId}`).find(`.issue-assigned-to`).html(`<a href="#">${data.username}</a>`)
        });
    }
}

function getMyIssues() {
    $.get(`/api/issues/owner`, function (data) {
        myIssueList($(`.my-issues-list ul`), data);
    });
}