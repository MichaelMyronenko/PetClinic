$(function () {
    $(".pet-item").on('click', ".confirm-edit-pet", function () {
        let parent = $(this).parents(".pet-item");

        let props = {
            parentElem: parent,
            petId: parent.find(".pet-id").val(),
            petName: parent.find(".new-pet-name").val()
        }
        editPetRequest(props);
    });
});

function editPetRequest(props) {

    let petRequestModel = {name: props.petName};

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: `api/pets/${props.petId}`,
        data: JSON.stringify(petRequestModel),
        success: function () {
            let newProps = {
                parentElem: props.parentElem,
                petId: props.petId
            };
            getPetRequest(newProps);
        }
    })
}

function getPetRequest(props) {
    $.ajax({
        dataType: "json",
        url: `api/pets/${props.petId}`,
        success: function (data) {
            props.parentElem.find("div.pet-name").text(data.petName);
            hideForm(props.parentElem)
        }
    })
}

function hideForm(parent) {
    let hiddenForm = parent.children('.hidden-edit-form');
    toggleForm(hiddenForm);
}