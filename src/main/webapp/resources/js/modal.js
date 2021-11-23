$(document).ready(function (){
    $('#createBtn').on('click',function (event){
        //prevent deleting by click
        event.preventDefault();
        var feedbacks = document.getElementsByClassName('invalid-feedback');
        for (let i = 0; i < feedbacks.length; i++) {
            feedbacks.item(i).innerHTML= '';
        }
        var  errorFields = document.getElementsByClassName('has-error');
        for (let i = 0; i < errorFields.length; i++) {
            errorFields.item(i).classList.remove('has-error');
        }
        document.getElementById('backendValidation').innerHTML= '';
        var $modal = $('#createModal');
        //copy attribute value to confirm removing button
        var action = $(this).attr('action');
        $('#createModal #createForm').attr('action',action);
        //show dialog
        $modal.modal();
    });
    $('#editBtn').on('click',function (event){
        event.preventDefault();
        var feedbacks = document.getElementsByClassName('invalid-feedback');
        for (let i = 0; i < feedbacks.length; i++) {
            feedbacks.item(i).innerHTML= '';
        }
        var  errorFields = document.getElementsByClassName('has-error');
        for (let i = 0; i < errorFields.length; i++) {
            errorFields.item(i).classList.remove('has-error');
        }
        document.getElementById('backendValidation').innerHTML= '';
    });
    $('.table #deleteBtn').on('click',function (event){
        //prevent deleting by click
        event.preventDefault();
        var $modal = $('#deleteModal');
        //copy attribute value to confirm removing button
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href',href)
        //show dialog
        $modal.modal();
    });
    $('#createForm').submit(function (event) {
        event.preventDefault();
        if (checkInputs() === true) {
            var action = $(this).attr('action');
            $.post(action,data(), function (response) {
                    if (response.isSuccess) {
                        window.location.replace(response.redirectUrl);
                    } else {
                        let errMsg = document.getElementById('backendValidation');
                        errMsg.innerHTML = '';
                        response.errors.forEach(error => errMsg.insertAdjacentHTML('beforeEnd',
                            error.field==='undefined'?'':error.field + ' ' + error.defaultMessage + '<br/>'));
                    }
                }
            )
        }
    });


});


function checkInputs() {
    const formsNeedsValidate = document.querySelectorAll('.needs-validation');
    let isCorrect = true;
    formsNeedsValidate.forEach(cmp => {
        if (cmp.checkValidity) {
            let parent = cmp.parentElement;
            while (!parent.className.startsWith('form-group')) {
                parent = parent.parentElement;
            }
            let feedback = parent.querySelector('.invalid-feedback');
            if (!cmp.checkValidity()) {
                parent.classList.add('has-error');
                let test = parent.querySelector('.invalid-feedback');
                feedback.innerHTML = cmp.validationMessage;
                isCorrect = false;
            } else {
                parent.classList.remove('has-error');
                feedback.innerHTML = '';
            }
        }
    });
    return isCorrect;
}