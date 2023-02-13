function fileSizeCheck ($target) {
    const file = $target.files[0];
    if(file.size > 1048576 * 10){
        alert('사진의 용량은 10mb를 초과할 수 없습니다');
        return false;
    }
}
$('#extra-image').on("change", function (){
    let length = $("#extra-image")[0].files.length;
    if(length > 7){
        alert("사진을 7장 이상 업로드할 수 없습니다");
        document.getElementById("extra-image").value = "";
        return false;
    }
});
