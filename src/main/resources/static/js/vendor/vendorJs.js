function formSubmit(){
    const content = $("#quill_data").val();
    const imageSrcReg = /(<img[^>]+src\s*=\s*[\"']?([^>\"']+)[\"']?[^>]*>)/g;

    let imageSrcArr = new Array();

    while (imageSrcReg.test(content)){
        let getSrc = RegExp.$2;
        imageSrcArr.push(getSrc);
    }

    $('#vendorInventory').attr("action", 'http://localhost:9090/green2209s_08/vendor/vendor-inventory/register');
    $('#vendorInventory').attr("method", 'post');
    $('#vendorInventory').attr("enctype", 'multipart/form-data');
    $('#vendorInventory').submit();
}