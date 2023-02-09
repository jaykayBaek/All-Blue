function vendorLogin(){
    const vendorLoginId = $("#vendorLoginId").val();
    const vendorPassword = $("#vendorPassword").val();

    if(vendorLoginId.trim() == '' || vendorPassword == ''){
        alert("아이디나 비밀번호를 입력해주세요");
        return false;
    }

    const data = {
        vendorLoginId : vendorLoginId,
        vendorPassword : vendorPassword
    }

    const response = sendLoginData(data);
    console.log(response);

    if(response.success == 'TRUE'){
        location.replace("http://localhost:9090/green2209s_08/");
    }
    else if (response.success == 'FALSE'){
        $('.validate__vendorId__result').html(response.message);
    }
}

function sendLoginData(data){
    let response;
    $.ajax({
        url:"http://localhost:9090/green2209s_08/vendor/login",
        method : "post",
        data : data,
        async: false,
        success: function (result){
            response = result;
        },
        error: function (result){
            response = result.responseJSON;
        }
    })
    return response;
}