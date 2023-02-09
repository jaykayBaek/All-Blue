function login(){
    const email = $("#email").val();
    const password = $("#password").val();
    if(email.trim() == "" || password.trim() == ""){
        alert("아이디나 비밀번호를 입력해주세요.");
        return false;
    }

    const data = {
        email : email,
        password : password
    }

    const response = sendLoginData(data);
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
