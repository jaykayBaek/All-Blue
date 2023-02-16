$(function(){
    const fishChildName = ['금붕어', '구피', '베타', '디스커스', '테트라', '플레코', '바브', '기타 열대어'];
    const fishChildCode = ['0101', '0102', '0103', '0104', '0105', '0106', '0107', '0108'];

    const goldFishName=["코메트", "유금", "토좌금", "오란다", "진주린", "난주", "수포안", "기타"];
    const goldFishCode=["010101", "010102", "010103", "010104", "010105", "010106", "010107", "010108"];
    const guppyName = ['알비노', '블루', '레드', '블랙', '그린', '옐로우', '퍼플', '드래곤', '브론즈', '기타'];
    const guppyCode = ['010201', '010202', '010203', '010204', '010205', '010206', '010207', '010208', '010209', '010210']
    const bettaName = ['베일테일', '더블테일', '델타', '하프문', '크라운테일', '플라캇', '기타'];
    const bettaCode = ['010301', '010302', '010303', '010304', '010305', '010306', '010307'];
    const discusName = ['디스커스/일반/기타'];
    const discusCode = ['010401'];
    const tetraName = ['테트라/일반/기타'];
    const tetraCode = ['010501'];
    const flecoName = ['플레코/일반/기타'];
    const flecoCode = ['010601'];
    const barbName = ['바브/일반/기타'];
    const barbCode = ['010701'];
    const etcFishName = ['기타 열대어'];
    const etcFishCode = ['010801'];

    const noneFishChildName = ['수초', '바닥재', '영양제/약품', '어항', '축양장', '여과기', '조명', '히터', '먹이', '유목', '청소도구', '기타'];
    const noneFishChildCode = ['0201', '0202', '0203', '0204', '0205', '0206', '0207', '0208', '0209', '0210', '0211', '0212'];

    const healthAndMedicationName = ['물고기영양제/약품', '수초영양제/약품', '수질약품'];
    const healthAndMedicationCode = ['020301', '020302', '020303'];

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
            case '0101' :
                for(let i=0; i<goldFishCode.length; i++){
                    opt = $('<option>').text(goldFishName[i]).attr("value", goldFishCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0102':
                for(let i=0; i<guppyCode.length; i++){
                    opt = $('<option>').text(guppyName[i]).attr("value", guppyCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0103':
                for(let i=0; i<bettaCode.length; i++){
                    opt = $('<option>').text(bettaName[i]).attr("value", bettaCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0104':
                for(let i=0; i<discusCode.length; i++){
                    opt = $('<option>').text(discusName[i]).attr("value", discusCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0105':
                for(let i=0; i<tetraCode.length; i++){
                    opt = $('<option>').text(tetraName[i]).attr("value", tetraCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0106':
                for(let i=0; i<flecoCode.length; i++){
                    opt = $('<option>').text(flecoName[i]).attr("value", flecoCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0107':
                for(let i=0; i<barbCode.length; i++){
                    opt = $('<option>').text(barbName[i]).attr("value", barbCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0108':
                for(let i=0; i<etcFishCode.length; i++){
                    opt = $('<option>').text(etcFishName[i]).attr("value", etcFishCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return;
            case '0201':
                opt = $('<option>').text("수초/일반/기타").attr("value", "020101");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0202':
                opt = $('<option>').text("바닥재/일반/기타").attr("value", "020202");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0203':
                for(let i=0; i<healthAndMedicationCode.length; i++){
                    opt = $('<option>').text(healthAndMedicationName[i]).attr("value", healthAndMedicationCode[i]);
                    $(category).append(opt);
                }
                $('.grandchild__product__layer').html(category);
                return
            case '0204':
                opt = $('<option>').text("어항/일반/기타").attr("value", "020401");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0205':
                opt = $('<option>').text("축양장/일반/기타").attr("value", "020501");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0206':
                opt = $('<option>').text("여과기/일반/기타").attr("value", "020601");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0207':
                opt = $('<option>').text("조명/일반/기타").attr("value", "020701");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0208':
                opt = $('<option>').text("히터/일반/기타").attr("value", "020801");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0209':
                opt = $('<option>').text("먹이/일반/기타").attr("value", "020901");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0210':
                opt = $('<option>').text("유목/일반/기타").attr("value", "021001");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0211':
                opt = $('<option>').text("청소도구/일반/기타").attr("value", "021101");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
            case '0212':
                opt = $('<option>').text("제품/기타").attr("value", "021201");
                $(category).append(opt);
                $('.grandchild__product__layer').html(category);
                return;
        }
    })

});