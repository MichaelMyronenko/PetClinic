$(function () {
    setCreatedBy();
    setAssignedTo();
    setPet();
});

function setCreatedBy() {
    let createdById = $('.issue').find('.issue-created-by').children('input[type="hidden"]').val();
    $.get(`/api/owners/${createdById}`, function (data) {
        $('.issue-created-by').append(
            `<a href="#" class="float-right">${data.username}</a>`
        )
    });
}

function setAssignedTo() {
    let assignedToId = $('.issue').find('.issue-assigned-to').children('input[type="hidden"]').val();
    $.ajax({
        url: `/api/doctors/${assignedToId}`,
        dataType: "json",
        success: function (data) {
            $('.issue-assigned-to').append(
                `<a href="#" class="float-right">${data.username}</a>`
            );
        },
        error: function (response) {
            console.log(response.responseJSON.message)
            console.log(response.status)
        }
    })
}

function setPet() {
    let petId = $('.issue').find('.issue-pet').children('input[type="hidden"]').val();
    $.get(`/api/pets/${petId}`, function (data) {
        $('.issue-pet').append(
            `<a href="#" class="float-right">${data.petName}</a>`);
    });
}