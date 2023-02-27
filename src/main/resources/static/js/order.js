let orderUid = "";
$(function(){
    $.ajax({
        url: "http://localhost:9090/green2209s_08/checkout/order-uid",
        method: "post",
        async: false,
        success: function (result){
            console.log(result.orderUid);
            orderUid = result.orderUid;
        },
        error: function (result){
            console.log(result);
            location.reload();
        }
    })
})

function order() {
    var IMP = window.IMP;
    IMP.init("imp57053858");
    const memberId = $('#memberId').val();
    const addressId = $('#addressId').val();

    const wishlistIdList = [];
    $('.wishlistId').each(function() {
        wishlistIdList.push($(this).val());
    });

    const itemIdList = [];
    $('.itemId').each(function() {
        itemIdList.push($(this).val());
    });

    $.ajax({
        url: "http://localhost:9090/green2209s_08/checkout/order-info",
        method: "post",
        data: {
            memberId: memberId,
            addressId: addressId,
            wishlistIdList: wishlistIdList,
            itemIdList: itemIdList
        },
        async: false,
        success: function (result) {
            console.log(result);
            requestPay(
                result.addressOrderResponse,
                result.itemOrderResponse,
                result.memberOrderResponse,
                result.checkoutTotalPriceDto,
                orderUid
            );
        },
        error: function (result) {
            console.log(result);
        }
    })

}
function requestPay(address, item, member, totalPrice, orderUid) {
    console.log(address);
    console.log(item);
    console.log(member);
    let itemName = '';
    const orderItemIdList = [];
    for (const i of item) {
        itemName += i.itemName + ", "
        orderItemIdList.push(i.itemId);
    }
    itemName = itemName.substring(0, itemName.lastIndexOf(","));
    IMP.request_pay({
        pg: "html5_inicis.INIpayTest",
        pay_method: 'card',
        merchant_uid: orderUid,
        name: itemName,
        amount: 100,
        buyer_email: member.memberEmail,
        buyer_name: address.recipient,
        buyer_tel: address.phoneNo,
        buyer_addr: address.address,
        buyer_postcode: address.zipcode
    }, function (rsp) {
        if (rsp.success) {
            console.log("rspp?? ", rsp);
            $.ajax({
                url:"http://localhost:9090/green2209s_08/order",
                method:"post",
                traditional: true,
                data:{
                    impUid: rsp.imp_uid,
                    orderUid: rsp.merchant_uid,
                    paymentMethod : rsp.pay_method,
                    orderItemName : rsp.name,
                    email : rsp.buyer_email,
                    recipient : rsp.buyer_name,
                    zipcode : rsp.buyer_postcode,
                    address : rsp.buyer_addr,
                    currency : rsp.currency,
                    totalPrice : totalPrice.totalPrice,
                    totalDeliveryPrice : totalPrice.totalDeliveryPrice,
                    orderItemIdList : orderItemIdList
                },
                success: function (result){
                    console.log(result);
                },
                error: function (result){
                    console.log(result);
                }
            })
        } else {
            console.log(rsp);
            alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
        }
    })
}

