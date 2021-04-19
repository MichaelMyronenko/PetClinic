$(function () {
    $(".list-group-item").on('click', ".edit-pet-btn", function () {
            let hiddenForm = $(this).parents(".pet-item").children('.hidden-edit-form');
            toggleForm(hiddenForm);
        }
    );
});

function toggleForm(hiddenForm) {
    hiddenForm.toggleClass("opened-pet-editing");
    if (hiddenForm.hasClass("opened-pet-editing")) {
        hiddenForm.slideDown();
    } else {
        hiddenForm.slideUp();
    }
}