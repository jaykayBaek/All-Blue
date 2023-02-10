let checkLoginIdResult = false;
let checkPasswordResult = false;
let checkNameResult = false;
let checkEmailResult = false;
let checkPhoneNoResult = false;

function vendorSignup(){
    const vendorLoginId = $('#vendorLoginId').val();
    const vendorPassword = $('#vendorPassword').val();
    const vendorPasswordConfirm = $('#vendorPasswordConfirm').val();
    const vendorName = $('#vendorName').val();
    const vendorEmail = $('#vendorEmail').val();
    const vendorPhoneNo = $('#vendorPhoneNo').val();

    let data = {
        vendorLoginId : vendorLoginId,
        vendorPassword : vendorPassword,
        vendorPasswordConfirm : vendorPasswordConfirm,
        vendorName : vendorName,
        vendorEmail : vendorEmail,
        vendorPhoneNo : vendorPhoneNo
    }

    let trimResult = trimCheck(data);

    if(trimResult == false){
        return false;
    }

    if(checkLoginIdResult&& checkEmailResult && checkPasswordResult && checkNameResult  && checkPhoneNoResult){
        let response;
        $.ajax({
            url : "http://localhost:9090/green2209s_08/vendor/register",
            method: "post",
            data: data,
            success : function (result){
                response = result;
                alert('회원가입이 완료되었습니다');
                location.replace('http://localhost:9090/green2209s_08/');

            },
            error : function (result) {
                response = result.responseJSON;
                $('.validate__vendorLoginId__result').html(response);
            }
        });
    }}

$('#vendorLoginId').blur(function () {
    const vendorLoginId = $('#vendorLoginId').val();
    const regLoginId = /[a-z0-9_-]{6,20}$/;
    $('.validate__vendorLoginId__result').html('');
    $('.validate__vendorLoginId').html('');

    if (vendorLoginId.trim() == '') {
        $('.validate__vendorLoginId__result').html('아이디를 입력하세요.');
        return false;
    }
    else if(!vendorLoginId.match(regLoginId)){
        $('.validate__vendorLoginId__result').html('아이디는 6~20자 영문 또는 숫자이어야 합니다. 영문은 소문자만 허용합니다.');
        return false;
    }

    console.log(vendorLoginId);
    let response = validateLoginId(vendorLoginId);
    console.log(response);

    if(response.success == 'TRUE'){
        checkLoginIdResult = true;
        $('.validate__vendorLoginId__result').html('');
        $('.validate__vendorLoginId').html('<i class="fa-solid fa-check"></i>');
    }else if(response.success == 'FALSE'){
        $('.validate__vendorLoginId__result').html(response.message);
        return false;
    }

})
$('#vendorPassword').blur(function (){
    const regPwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
    const password = $('#vendorPassword').val();
    const passwordConfirm = $('#vendorPasswordConfirm').val();
    $('.validate__password__result').html('');
    $('.validate__password').html('')

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

$('#vendorPasswordConfirm').blur(function (){
    const regPwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
    const password = $('#vendorPassword').val();
    const passwordConfirm = $('#vendorPasswordConfirm').val();
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

$('#vendorName').blur(function() {
    const regName = /^[가-힣a-zA-Z]{2,25}$/i;
    const name = $('#vendorName').val();
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
})


function trimCheck(data) {
    let result = true;

    if (data.vendorLoginId.trim() == '') {
        $('.validate__vendorLoginId__result').html('아이디를 입력하세요.');
        result = false;
    }
    if (data.vendorPassword.trim() == '') {
        $('.validate__password__result').html('비밀번호를 입력하세요.');
        result = false;
    }
    if (data.vendorPasswordConfirm.trim() == '') {
        $('.validate__password__result').html('비밀번호를 입력하세요.');
        result = false;
    }
    if (data.vendorName.trim() == '') {
        $('.validate__name__result').html('이름을 입력하세요.');
        result = false;
    }
    if (data.vendorEmail.trim() == '') {
        $('.validate__vendorEmail__result').html('이메일을 입력하세요.');
        result = false;
    }
    if (data.vendorPhoneNo.trim() == '') {
        $('.validate__phoneNo__result').html('핸드폰번호를 입력하세요.');
        result = false;
    }
    return result;
}

$('#vendorEmail').blur(function (){
    const email = $('#vendorEmail').val();
    $('.validate__email__result').html('');
    $('.validate__email').html('')

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

    checkEmailResult = true;
    $('.validate__email__result').html('');
    $('.validate__email').html('<i class="fa-solid fa-check"></i>')
})

$('#vendorPhoneNo').blur(function(){
    const phoneNo=$('#vendorPhoneNo').val().replace(/-/g, '');
    $('#vendorPhoneNo').val(phoneNo);
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

function validateLoginId(vendorLoginId) {
    let response;
    $.ajax({
        method : "post",
        url : "http://localhost:9090/green2209s_08/vendor/verify/vendorLoginId",
        data : {
            vendorLoginId : vendorLoginId
        },
        async : false,
        success : function (result){
            response = result;
        },
        error : function (result){
            response = result.responseJSON;
        }
    })
    return response;
}