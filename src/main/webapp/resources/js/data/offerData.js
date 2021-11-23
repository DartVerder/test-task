function data() {
    return {
        ['id']: document.getElementById('offerId').value,
        ['client']: document.getElementById('offerClient').value,
        ['creditSum']: document.getElementById('offerCreditSum').value,
        ['monthsCount']: document.getElementById('offerMonthsCount').value,
        ['credit']: document.getElementById('offerCredit').value,
        ['paymentScheduleType']: document.getElementById('offerScheduleType').value,
        ['startDate']: document.getElementById('offerStartDate').value
    }
}