function data() {
    return {
        ['id']: document.getElementById('clientId').value,
        ['lastName']: document.getElementById('clientLastName').value,
        ['firstName']: document.getElementById('clientFirstName').value,
        ['patronymic']: document.getElementById('clientPatronymic').value,
        ['phoneNumber']: document.getElementById('clientPhoneNumber').value,
        ['passportNumber']: document.getElementById('clientPassportNumber').value,
        ['bank']: document.getElementById('clientBank').value,
    }
}