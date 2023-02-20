function quilljsediterInit(){
    var option = {
        modules: {
            toolbar: [
                [{header: [1,2,3,4] }],
                [{ 'size': ['small', false, 'large', 'huge'] }],
                ['bold', 'italic', 'underline'],
                ['image'],
                [{ list: 'ordered' }, { list: 'bullet' }],
                [{ 'color': [] }, { 'background': [] }],
                [{ 'font': [] }],
                [{ 'align': [] }],
                ['clean']
            ]
        },
        theme: 'snow'
    };

    quill = new Quill('#item__content', option);

    quill.on('text-change', function() {
        document.getElementById("quill_data").value = quill.root.innerHTML;
    });

    quill.getModule('toolbar').addHandler('image', function () {
        selectLocalImage();
    });
}



function selectLocalImage() {
    const fileInput = document.createElement('input');
    fileInput.setAttribute('type', 'file');
    console.log("input.type " + fileInput.type);

    fileInput.click();

    fileInput.addEventListener("change", function () {
        const formData = new FormData();
        const file = fileInput.files[0];
        if(file.size> 1048576 * 10){
            alert('사진의 용량은 10mb를 초과할 수 없습니다');
            return false;
        }else if(!(file.name.toLowerCase().endsWith('png') || file.name.toLowerCase().endsWith('jpeg') || file.name.toLowerCase().endsWith('jpg'))){
            alert('사진은 jpg, png만 지원합니다');
            return false;
        }
        formData.append('file', file);

        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: 'http://localhost:9090/green2209s_08/common/vendor/image/upload',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                const range = quill.getSelection();
                quill.insertEmbed(range.index, 'image', 'http://localhost:9090/green2209s_08/common/vendor/image/display/'+data.saved_FILENAME);

            },
            error: function (err) {
                console.log(err);
            }
        });

    });
}

quilljsediterInit();