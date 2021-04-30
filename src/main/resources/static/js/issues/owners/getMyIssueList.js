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

function getMyIssues(pageNum) {
    $.get(`/api/issues/owner`, {page: pageNum}, function (data) {
        myIssueList($(`.my-issues-list ul`), data.content);
        let content = getPaging(data.totalPages, data.number);
        $('.my-issues-list').find('.pagination').html(content);
    });
}

function getPaging(totalPages, numOfPage) {
    let content = numOfPage === 0
        ? `<li class="page-item disabled"><a class="page-link" href="#">Previous</a><input type="hidden" value="prev"></li>`
        : `<li class="page-item"><a class="page-link" href="#">Previous</a><input type="hidden" value="prev"></li>`

    for (let i = 0; i < totalPages; i++) {
        content += i === numOfPage
            ? `<li class="page-item active"><a class="page-link" href="#">${i + 1}</a><input type="hidden" value="${i}"></li>`
            : `<li class="page-item"><a class="page-link" href="#">${i + 1}</a><input type="hidden" value="${i}"></li>`;
    }
    content += numOfPage < totalPages - 1
        ? `<li class="page-item"><a class="page-link" href="#">Next</a><input type="hidden" value="next"></li>`
        : `<li class="page-item disabled"><a class="page-link" href="#">Next</a><input type="hidden" value="next"></li>`
    return content
}