var main = {
    init : function () {
        var _this = this;
        $('#btn-find-password').on('click', function () {
            if(_this.check_form()) _this.send_pwd();
        });
    },
    check_form : function () {
        if($('#find_pwd_email').val() == ""){
            alert("이메일을 입력해주세요");
            $('#find_pwd_email').focus();
            return false;
        }
        if($('#find_pwd_name').val() == ""){
            alert("이름을 입력해주세요");
            $('#find_pwd_name').focus();
            return false;
        }
        return true;
    },
    send_pwd : function () {

        var email = $('#find_pwd_email').val();
        var name = $('#find_pwd_name').val();

        $.ajax({
            type: 'GET',
            url: '/api/v0/find_pw',
            data: {
                userEmail: email,
                userName: name
            },
            contentType: "application/x-www-form-urlencoded; charset=UTF-8;"
        }).done(function(value) {
            if(value.check){
                alert("입력하신 이메일로 임시비밀번호가 발송되었습니다");
                $.ajax({
                    type: 'POST',
                    url: '/api/v0/send_email',
                    data: {
                        userEmail: email,
                        userName: name
                    },
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8;"
                }).done(function(data) {
                    window.location.href = '/loginPage'

                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
            else{
                alert("일치하는 정보가 없습니다");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }

}

main.init();