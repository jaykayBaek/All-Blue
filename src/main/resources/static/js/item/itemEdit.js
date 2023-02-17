function changeName(){
    let itemName = $('#itemName').val();
    $.ajax({
        method: "post",
        url:"http://localhost:9090/green2209s_08/item/item-name",
        data : {
            itemName : itemName
        },
        success: function () {

        },
        error: function () {

        }
    });
}