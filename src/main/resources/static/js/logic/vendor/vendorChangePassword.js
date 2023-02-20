function changePassword(){
    let password = $('#password').val();
    let newPassword = $('#newPassword').val();
    let repeatNewPassword = $('#repeatNewPassword').val();

    const regPwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;

    if(password.trim() == ''){
        $('.validate__password').html('비밀번호를 입력하세요.');
        return false;
    }
    if(newPassword.trim() == '' || repeatNewPassword.trim()==''){
        $('.validate__newPassword').html('비밀번호를 입력하세요.');
        return false;
    }

    if(!newPassword.match(regPwd) || !repeatNewPassword.match(regPwd)){
        $('.validate__newPassword').html('비밀번호는 8자 이상 16자 이하, 하나 이상의 문자, 숫자, 특수문자를 포함하여 만들어주세요.');
        return false;
    }

    if(newPassword != repeatNewPassword){
        $('.validate__newPassword').text('비밀번호가 일치하지 않습니다.');
        return false;
    }

    $.ajax({
        method: "post",
        url: "http://localhost:9090/green2209s_08/vendor/account/change-password",
        data: {
            password:password,
            newPassword:newPassword,
            repeatNewPassword:repeatNewPassword
        },
        success: function(){
            alert('비밀번호 변경이 완료되었습니다');
            location.reload();
        },
        error: function(result){
            const data = result.responseJSON;
            if(result.status == 401 || data.success == 'FALSE'){
                $('.validate__password').html(data.message);
            }
        }
    })

}
