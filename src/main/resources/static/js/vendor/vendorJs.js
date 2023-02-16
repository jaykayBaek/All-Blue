function formSubmit(){
    const content = $("#quill_data").val();
    // const imageSrcReg = /(<img[^>]+src\s*=\s*[\"']?([^>\"']+)[\"']?[^>]*>)/g;
    const imageSrcReg = /<img[^>]+src="([^">]+)/g;

    if (!$("#thumbnail-image").val()) {
        alert("대표이미지를 추가해주세요. 추가이미지를 선택했더라도 대표이미지를 선택하지 않으면 안 됩니다.");
        return false;
    }

    if($('#extra-image').get(0).files.length === 0) {
        alert('추가이미지는 최소 한 장 이상 업로드해야 합니다.');
        return;
    }

    let match;

    $('#vendorInventory').attr("action", 'http://localhost:9090/green2209s_08/item/enroll');
    $('#vendorInventory').attr("method", 'post');
    $('#vendorInventory').attr("enctype", 'multipart/form-data');

    $('#vendorInventory').submit();
}