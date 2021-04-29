function getDoctor(id, success) {
    $.ajax({
        type: "GET",
        url: `/api/doctors/${id}`,
        success: success
    });
}