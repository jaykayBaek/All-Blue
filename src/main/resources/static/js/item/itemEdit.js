function changeName(){
    const itemName = $('#itemName').val();
    const itemId = $('#itemId').val();

    $.ajax({
        method: "patch",
        url:"http://localhost:9090/green2209s_08/item/name",
        data : {
            itemId : itemId,
            itemName : itemName
        },
        success: function (result) {
            if(result.success == 'TRUE'){
                alert('상품 수정이 완료되었습니다.');
                location.reload();
            }
        },
        error: function (result) {
            console.log('result? ', result);
        }
    });
}

function changeNumber(){
    const price = $('#price').val();
    const salePrice = $('#salePrice').val();
    const stockQuantity = $('#stockQuantity').val();
    const deliveryPrice = $('#deliveryPrice').val();
    const itemId = $('#itemId').val();

    $.ajax({
        method: "patch",
        url:"http://localhost:9090/green2209s_08/item/number",
        data : {
            itemId : itemId,
            price : price,
            salePrice : salePrice,
            stockQuantity : stockQuantity,
            deliveryPrice : deliveryPrice
        },
        success: function (result) {
            if(result.success == 'TRUE'){
                alert('상품 수정이 완료되었습니다.');
                location.reload();
            }        },
        error: function (result) {
            console.log('result? ', result);
        }
    });
}

function changeFish(){
    const itemId = $('#itemId').val();
    const fishSex = $('#fishSex').val()
    const breederName = $('#breederName').val();
    const size = $('#size').val();

    $.ajax({
        method: "patch",
        url:"http://localhost:9090/green2209s_08/item/fish",
        data : {
            itemId : itemId,
            fishSex : fishSex,
            breederName : breederName,
            size : size
        },
        success: function (result) {
            if(result.success == 'TRUE'){
                alert('상품 수정이 완료되었습니다.');
                location.reload();
            }        },
        error: function (result) {
            console.log('result? ', result);
        }
    });
}

function changeProduct(){
    const brandName = $('#brandName').val();
    const itemId = $('#itemId').val();

    $.ajax({
        method: "patch",
        url:"http://localhost:9090/green2209s_08/item/product",
        data : {
            itemId : itemId,
            brandName : brandName
        },
        success: function (result) {
            if(result.success == 'TRUE'){
                alert('상품 수정이 완료되었습니다.');
                location.reload();
            }        },
        error: function (result) {
            console.log('result? ', result);
        }
    });
}