$(function () {
    $(".pet-item").on('click', ".delete-pet-btn", function () {
        let parent = $(this).parents(".pet-item");

        let props = {
            parentElem: parent,
            petId: parent.find(".pet-id").val(),
        }
        deletePetRequest(props);
    });
});

function deletePetRequest(props) {
    $.ajax({
        type: "DELETE",
        url: `api/pets/${props.petId}`,
        success: function () {
            props.parentElem.slideUp(function() { $(this).remove();});
        }
    })
}