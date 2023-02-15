function formSubmit(){
    const content = $("#quill_data").val();
    // const imageSrcReg = /(<img[^>]+src\s*=\s*[\"']?([^>\"']+)[\"']?[^>]*>)/g;
    const imageSrcReg = /<img[^>]+src="([^">]+)/g;

    if (!$("#thumbnail-image").val()) {
        alert("대표이미지를 추가해주세요. 추가이미지를 선택했더라도 대표이미지를 선택하지 않으면 안 됩니다.");
        return false;
    }

    let match;
    const srcs = [];
    while ((match = imageSrcReg.exec(content)) !== null) {
        srcs.push(match[1]);
    }

    $('#vendorInventory').attr("action", 'http://localhost:9090/green2209s_08/item/enroll');
    $('#vendorInventory').attr("method", 'post');
    $('#vendorInventory').attr("enctype", 'multipart/form-data');

    $('#vendorInventory').append($('<input/>', {type: 'hidden', name: 'imageSrcArr[]', value: srcs}));
    $('#vendorInventory').submit();
}