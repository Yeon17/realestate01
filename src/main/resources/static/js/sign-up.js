var check_email = false; //이메일 중복확인 여부
var check_pwd = true; // 비밀번호 일치 여부
var main = {
    init : function () {
        var _this = this;
        $('#btn-user-save').on('click', function () {
            if(_this.check_form()) _this.user_save();
        });
        $('#btn-user-update').on('click', function () {
            if(_this.check_edit_form()) _this.user_update();
        });
        $('#btn-user-delete').on('click', function () {
            if(_this.check_password()) _this.user_delete();
        });
        $('#oauth-user-delete').on('click', function () {
            _this.oauth_user_delete();
        });
        $('button[name=btn-member-delete]').on('click', function () {
            var mid = $(this).data('mid');
            _this.member_delete(mid);
        });
    },
    check_form : function () {
        if($('#user_email').val() == ""){
            alert("이메일을 입력해주세요");
            $('#user_email').focus();
            return false;
        }
        if(!check_email){
            alert("이메일 중복확인을 해주세요");
            return false;
        }
        if(!check_pwd){
            alert("비밀번호가 일치하지 않습니다");
            return false;
        }
        if($('#user_password').val() == ""){
            alert("비밀번호를 입력해주세요");
            $('#user_password').focus();
            return false;
        }
        if($('#user_name').val() == ""){
            alert("이름을 입력해주세요");
            $('#user_name').focus();
            return false;
        }
        return true;
    },
    check_edit_form : function () {
        if(!check_pwd){
            alert("비밀번호가 일치하지 않습니다");
            return false;
        }
        if($('#user_name').val() == ""){
            alert("이름을 입력해주세요");
            $('#user_name').focus();
            return false;
        }
        return true;
    },
    check_password : function () {
        if($('#pwd_delete').val() == ""){
            alert("비밀번호를 입력해주세요");
            $('#pwd_delete').focus();
            return false;
        }
        return true;
    },
    user_save : function () {
        var data = {
            email: $('#user_email').val(),
            password: $('#user_password').val(),
            name: $('#user_name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v0/user',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입 완료');
            window.location.href = '/loginPage';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    user_update : function(){
        var data = {
            password: $('#user_password').val(),
            name: $('#user_name').val()
        };
        var uid = $('#uid').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/user/'+uid,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('수정되었습니다');
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    user_delete : function(){
        var uid = $('#uid').val();
        var password = $('#pwd_delete').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/user/'+uid,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: password
        }).done(function(data) {
            if(data.check){
                alert('탈퇴되었습니다');
                window.location.href = '/';
            }
            else{
                alert('비밀번호가 일치하지 않습니다.');
                location.reload();
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    oauth_user_delete : function(){
        var uid = $('#uid').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/oauth_user/'+uid,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('탈퇴되었습니다');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    member_delete : function(id){
        $.ajax({
            type: 'DELETE',
            url: '/administer/user/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('삭제되었습니다');
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();

function comp_pwd(){
    if($("#user_password").val() != $("#user_password_sec").val()){
         $("#check_pwd").text("비밀번호가 일치하지 않습니다");
         check_pwd = false;
     }
    else{
        $("#check_pwd").text('');
        check_pwd = true;
    }
}

function double_check(){

    var email = $('#user_email').val();

    $.ajax({
        type: 'POST',
        url: '/api/v0/doubleCheck',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: email
    }).done(function(data) {
        if(data.check == false){
            alert("사용가능한 아이디입니다.");
            check_email = true;
        }
        else{
            alert("이미 사용중인 아이디입니다.");
            check_email = false;
        }
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function init_check(){ //이메일 바꿀때마다 중복값 체크 초기화
    check_email = false;
}


//현재 계정 삭제 버튼 비활성화
var uid = $("#uid").val();
var midList = document.getElementsByClassName("midList");
for(var i = 0; i < midList.length; i++){
    if(uid == $(midList[i]).data('mid')) $(midList[i]).attr("disabled","disabled");
}

