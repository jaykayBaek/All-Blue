function login(){
    let email = $("#email").val();
    let password = $("#password").val();
    if(email.trim() == "" || password.trim() == ""){
        alert("아이디나 비밀번호를 입력해주세요.");
        return false;
    }

    let data = {
        email : email,
        password : password
    }

    let response = sendLoginData(data);
    console.log(response);
    if(response.success == 'TRUE'){
        location.replace("http://localhost:9090/green2209s_08/");
    }
    else if(response.success == 'FALSE'){
        $('.login__result').html(response.message);
    }
}

function sendLoginData(data){
    let response;
    $.ajax({
        url : "http://localhost:9090/green2209s_08/member/login",
        method : "post",
        data : data,
        async : false,
        success: function (result){
            response = result;
        },
        error: function (result){
            response = result.responseJSON;
        }
    })
    return response;
}
