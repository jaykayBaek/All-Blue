let checkEmailResult = false;
let checkPasswordResult = false;
let checkNameResult = false;
let checkBirthdateResult = false;
let checkPhoneNoResult = false;

function signup(){
    let email = $('#email').val();
    let password = $('#pwd').val();
    let passwordConfirm = $('#passwordConfirm').val();
    let name = $('#name').val();
    let birthdate = $('#birthdate').val();
    let phoneNo = $('#phoneNo').val();

    trimCheck(email, password, passwordConfirm, name, birthdate, phoneNo);

    if(checkEmailResult && checkPasswordResult && checkNameResult && checkBirthdateResult && checkPhoneNoResult){
        let data = {
            email : email,
            password : password,
            name : name,
            name : name,
            birthdate : birthdate,
            phoneNo : phoneNo
        }
        $.ajax({
            url : "http://localhost:9090/green2209s_08/member/register",
            method: "post",
            data: data,
        });
        location.replace('http://localhost:9090/green2209s_08/member/email/send?email='+email);
    }
}

$('#email').blur(function (){
    const email = $('#email').val();
    $('#validate__email').html('')

    const regEmail = /^[\w-\.]{1,25}@([\w-]+\.)+[\w-]{2,4}$/i;

    if (email.trim() == '') {
        $('.validate__email__result').html('이메일을 입력하세요.');
        checkEmailResult = false;
        return false;
    }else if(!email.match(regEmail)){
        $('.validate__email__result').html('이메일을 올바르게 입력해주세요');
        checkEmailResult = false;
        return false;
    }

    let result = checkEmail(email);
    if(result != undefined){
        if(result.success == "FALSE"){
            $('.validate__email__result').html(result.message);
            return false;
        } else{
            $('.validate__email__result').html('');
        }
    }

    checkEmailResult = true;
    $('.validate__email__result').html('');
    $('#validate__email').html('<i class="fa-solid fa-check"></i>')
})


$('#pwd').blur(function (){
    const regPwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
    const password = $('#pwd').val();
    const passwordConfirm = $('#passwordConfirm').val();
    $('.validate__password__result').html('');

    if(password.trim() == ''){
        $('.validate__password__result').html('비밀번호를 입력하세요.');
        checkPasswordResult = false;
        return false;
    } else if(!password.match(regPwd)){
        $('.validate__password__result').html('비밀번호는 8자 이상, 하나 이상의 문자, 숫자, 특수문자를 포함하여 만들어주세요.');
        checkPasswordResult = false;
        return false;
    } else if(password != passwordConfirm){
        $('.validate__password__result').html('비밀번호가 일치하지 않습니다.');
        checkPasswordResult = false;
        return false;
    }
    checkPasswordResult = true;
})

$('#passwordConfirm').blur(function (){
    const regPwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
    const password = $('#pwd').val();
    const passwordConfirm = $('#passwordConfirm').val();

    $('.validate__password__result').html('');
    $('.validate__password').html('')

    if(passwordConfirm.trim() == ''){
        $('.validate__password__result').html('비밀번호를 입력하세요.');
        checkPasswordResult = false;
        return false;
    } else if(!passwordConfirm.match(regPwd)){
        $('.validate__password__result').html('비밀번호는 8자 이상, 하나 이상의 문자, 숫자, 특수문자를 포함하여 만들어주세요.');
        checkPasswordResult = false;
        return false;
    } else if(password != passwordConfirm){
        $('.validate__password__result').html('비밀번호가 일치하지 않습니다.');
        checkPasswordResult = false;
        return false;
    }
    checkPasswordResult = true;
    $('.validate__password__result').html('');
    $('.validate__password').html('<i class="fa-solid fa-check"></i>')
})

$('#name').blur(function(){
    const regName = /^[가-힣a-zA-Z]{2,25}$/i;
    const name = $('#name').val();
    $('.validate__name').html('');
    if(name.trim() == ''){
        checkNameResult = false;
        $('.validate__name__result').html('이름을 입력하세요.');
        return false;
    }
    else if(!name.match(regName)){
        checkNameResult = false;
        $('.validate__name__result').html('이름을 정확히 입력하세요.');
        return;
    }
    checkNameResult = true;
    $('.validate__name').html('<i class="fa-solid fa-check"></i>')
    $('.validate__name__result').html('');
});

// 생년월일 체크
$('#birthdate').blur(function(){
    const regBirth = /^(19|20)\d{2}$/
    let birthdate = $('#birthdate').val();
    $('.validate__birthdate').html('')

    if(birthdate.trim()==""){
        $('.validate__birthdate__result').html('생년월일을 입력하세요.');
        checkBirthdateResult = false;
        return false;
    }
    else if (!birthdate.match(regBirth)){
        $('.validate__birthdate__result').html('생년월일일 정확히 입력하세요.');
        checkBirthdateResult = false;
        return false;
    }
    $('.validate__birthdate').html('<i class="fa-solid fa-check"></i>')
    $('.validate__birthdate__result').html('');
    checkBirthdateResult = true;
});


/* 핸드폰 번호 유효성 검사*/
$('#phoneNo').blur(function(){
    const phoneNo=$('#phoneNo').val().replace(/-/g, '');
    $('#phoneNo').val(phoneNo);
    $('.validate__phoneNo').html('');

    const regPhoneNo = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/
    if(phoneNo.trim()=='') {
        $('.validate__phoneNo__result').html('휴대폰 번호를 입력하세요.');
        checkPhoneNoResult = false;
        return false;
    }else if(!phoneNo.match(regPhoneNo)){
        $('.validate__phoneNo__result').html('휴대폰 번호를 정확하게 입력하세요.');
        checkPhoneNoResult = false;
        return false;
    }
    checkPhoneNoResult = true;
    $('.validate__phoneNo').html('<i class="fa-solid fa-check"></i>');
    $('.validate__phoneNo__result').html('');

});

function checkEmail(email) {
    let response;
    $.ajax({
        method:"post",
        url:"http://localhost:9090/green2209s_08/member/verify/email",
        data : {
            email: email
        },
        async : false,
        success: function (data){
            response = data.responseJSON;
        },
        error: function (data){
            response = data.responseJSON;
        }
    })
    return response;
}


function trimCheck(email, password, passwordConfirm, name, birthdate, phoneNo) {
    let result = true;
    if (email.trim() == '') {
        $('.validate__email__result').html('이메일을 입력하세요.');
        result = false;
    }
    if (password.trim() == '') {
        $('.validate__password__result').html('비밀번호를 입력하세요.');
        result = false;
    }
    if (name.trim() == '') {
        $('.validate__name__result').html('이름을 입력하세요.');
        result = false;
    }
    if (birthdate.trim() == '') {
        $('.validate__birthdate__result').html('생년월일을 입력하세요.');
        result = false;
    }
    if (phoneNo.trim() == '') {
        $('.validate__phoneNo__result').html('핸드폰 번호를 입력하세요.');
        result = false;
    }
    return result;
}

