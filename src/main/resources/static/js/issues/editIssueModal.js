$('#exampleModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let modal = $(this);
    modal.find('.modal-title').text('Edit issue');
})

$('.modal').on('click', '.confirm-edit-issue', function () {
    $('form.issue-edit').submit();
});