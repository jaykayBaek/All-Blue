function order() {
    var IMP = window.IMP;
    IMP.init("imp57053858");
    const memberId = $('#memberId').val();
    const addressId = $('#addressId').val();
    const wishlistId = $('.wishlistId').map(function () {
        return $(this).val();
    }).get();

    console.log("-----------")
    console.log(memberId);
    console.log(addressId);
    console.log(wishlistId);
    console.log("-----------")

        $.ajax({
            url: "http://localhost:9090/green2209s_08/checkout/order-info",
            method: "post",
            data: {
                memberId: memberId,
                addressId: addressId,
                wishlistId: wishlistId
            },
            async: false,
            success: function (result) {
                console.log(result);
            },
            error: function (result) {
                console.log(result);
            }
        })

}
function requestPay() {
    IMP.request_pay({
        pg: "html5_inicis.INIpayTest",
        pay_method: 'card',
        merchant_uid: "57008833-33004",
        name: '당근 10kg',
        amount: 1004,
        buyer_email: 'Iamport@chai.finance',
        buyer_name: '포트원 기술지원팀',
        buyer_tel: '010-1234-5678',
        buyer_addr: '서울특별시 강남구 삼성동',
        buyer_postcode: '123-456'
    }, function (rsp) { // callback
        if (rsp.success) {
            console.log(rsp);
        } else {
            console.log(rsp);
        }
    })
}

