$(function(){
    const fishChildName = ['금붕어', '구피', '베타', '디스커스', '테트라', '플레코', '바브', '기타 열대어'];
    const fishChildCode = ['101', '102', '103', '104', '105', '106', '107', '108'];

    const goldFishName=["코메트", "유금", "토좌금", "오란다", "진주린", "난주", "수포안", "기타"];
    const goldFishCode=["01", "02", "03", "04", "05", "06", "07", "08"];
    const guppyName = ['알비노', '블루', '레드', '블랙', '그린', '옐로우', '퍼플', '드래곤', '브론즈', '기타'];
    const guppyCode = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10']
    const bettaName = ['베일테일', '더블테일', '델타', '하프문', '크라운테일', '플라캇', '기타'];
    const bettaCode = ['01', '02', '03', '04', '05', '06', '07'];

    const noneFishChildName = ['수초', '바닥재', '영양제/약품', '어항', '축양장', '여과기', '조명', '히터', '먹이', '유목', '청소도구', '기타'];
    const noneFishChildCode = ['201', '202', '203', '204', '205', '206', '207', '208', '209', '210', '211', '212'];

    const healthAndMedicationName = ['물고기영양제/약품', '수초영양제/약품', '수질약품'];
    const healthAndMedicationCode = ['01', '02', '03'];

    const fishDetailName = ['breederName', 'size'];
    const fishDetailValue = ['브리더 이름', '사이즈'];
    const noneFishDetailName = ['brandName'];
    const noneFishDetailValue = ['브랜드명'];


    $("#parent__category").on("change", function () {
        let val = $(this).val();
        let category = $('<select>').addClass('child__category form-control').attr("name", "childCategory");
        let firstOpt = $('<option>').text('옵션을 선택하세요.').attr('value', '');
        $('.detail__input__title').html('');
        $('.detail__input__content').html('');
        category.html(firstOpt);
        let opt;
        if(val == '01'){
            for(let i=0; i<fishChildCode.length; i++){
                opt = $('<option>').text(fishChildName[i]).attr("value", fishChildCode[i]);
                $(category).append(opt);
            }
            // --- 디테일 정보 ---
            for(let i=0; i<fishDetailName.length; i++){
             let titleTd = $('<td>').text(fishDetailValue[i]);
            $('.detail__input__title').append(titleTd);

            let contentTd = $('<td>');
            $('.detail__input__content').append(contentTd);

            let input = $('<input>').attr("name", fishDetailName[i]).attr("placeholder", fishDetailValue[i]);
            $(contentTd).html(input);
        }
            let titleTd = $('<td>').text('성별');
            $('.detail__input__title').append(titleTd);

            let fishSex = $('<select>').addClass("form-control").attr("name", "fishSex");
            const fishSexOpt1 = $('<option>').attr("value", "MALE").text("수컷");
            const fishSexOpt2 = $('<option>').attr("value", "FEMALE").text("암컷");
            const fishSexOpt3 = $('<option>').attr("value", "UNKNOWN").text("불분명");
            fishSex.append(fishSexOpt1);
            fishSex.append(fishSexOpt2);
            fishSex.append(fishSexOpt3);

            let contentTd = $('<td>').append(fishSex);
            $('.detail__input__content').append(contentTd);

            $('.child__product__layer').html(category);
            $('.grandchild__product__layer').html('');
        }
        else if(val == '02'){
            for(let i=0; i<noneFishChildCode.length; i++){
                opt = $('<option>').text(noneFishChildName[i]).attr("value", noneFishChildCode[i]);
                $(category).append(opt);
            }

            // --- 디테일 정보 ---
            for(let i=0; i<noneFishDetailName.length; i++){
                let titleTd = $('<td>').text(noneFishDetailValue[i]);
                $('.detail__input__title').append(titleTd);

                let contentTd = $('<td>');
                $('.detail__input__content').append(contentTd);

                let input = $('<input>').attr("name", noneFishDetailName[i]).attr("placeholder", noneFishDetailValue[i]);
                $(contentTd).html(input);
            }

            $('.child__product__layer').html(category);
            $('.grandchild__product__layer').html('');
        }
    });

    $(document).on('change','.child__category',function(){
        let val = $(this).val();
        let category = $('<select>').addClass('grandchild__category form-control').attr("name", "grandchildCategory");
        let firstOpt = $('<option>').text('옵션을 선택하세요.').attr('value', '');
        category.html(firstOpt);

        let opt;
        switch (val) {
            case '101' :
                for(let i=0; i<goldFishCode.length; i++){
                    opt = $('<option>').text(goldFishName[i]).attr("value", goldFishCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '102':
                for(let i=0; i<guppyCode.length; i++){
                    opt = $('<option>').text(guppyName[i]).attr("value", guppyCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '103':
                for(let i=0; i<bettaCode.length; i++){
                    opt = $('<option>').text(bettaName[i]).attr("value", bettaCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '104':
                $('.grandchild__product__layer').html('');
                return;
            case '105':
                $('.grandchild__product__layer').html('');
                return;
            case '106':
                $('.grandchild__product__layer').html('');
                return;
            case '107':
                $('.grandchild__product__layer').html('');
                return;
            case '108':
                $('.grandchild__product__layer').html('');
                return;
            case '201':
                $('.grandchild__product__layer').html('');
                return;
            case '202':
                $('.grandchild__product__layer').html('');
                return;
            case '203':
                for(let i=0; i<healthAndMedicationCode.length; i++){
                    opt = $('<option>').text(healthAndMedicationName[i]).attr("value", healthAndMedicationCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return
            case '204':
                $('.grandchild__product__layer').html('');
                return;
            case '205':
                $('.grandchild__product__layer').html('');
                return;
            case '206':
                $('.grandchild__product__layer').html('');
                return;
            case '207':
                $('.grandchild__product__layer').html('');
                return;
            case '208':
                $('.grandchild__product__layer').html('');
                return;
            case '209':
                $('.grandchild__product__layer').html('');
                return;
            case '210':
                $('.grandchild__product__layer').html('');
                return;
            case '211':
                $('.grandchild__product__layer').html('');
                return;
            case '212':
                $('.grandchild__product__layer').html('');
                return;
        }
    })

});