var main = {
    init : function () {
        var _this = this;
        $('#btn-request-save').on('click', function () {
            if(_this.request_check()) _this.request_save();
        });

        $('button[name=btn-request-delete]').on('click', function () {
            var rid = $(this).data('rid');
            if(_this.delete_check()) _this.request_delete(rid);
        });
    },
    request_save : function () {
        var data = {
            trade: $('#trade').val(),
            addressKindU: $('#addressKindU').val(),
            addressKindD: $('#addressKindD').val(),
            transaction: $('#transaction').val(),
            price: $('#price').val(),
            date: $('#date').val(),
            visit: $('#visit').val(),
            name: $('#name').val(),
            phone: $('#phone').val(),
            message: $('#summernote').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/request',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('의뢰가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    request_check : function () {
        var f = document.request;

        if (f.addressKindU.value == "전체") {
            alert("아파트를 선택해주세요");
            f.addressKindU.focus();
            return false;
        }

        if (f.addressKindD.value == "전체") {
            alert("타입을 선택해주세요");
            f.addressKindD.focus();
            return false;
        }

        if (f.transaction.value == "전체") {
            alert("거래종류를 선택해주세요");
            f.transaction.focus();
            return false;
        }

        if (f.price.value == "") {
            alert("가격범위를 입력해주세요");
            f.price.focus();
            return false;
        }

        if (f.date.value == "") {
            alert("구매예정 날짜를 입력해주세요");
            f.date.focus();
            return false;
        }

        if (f.visit.value == "") {
            alert("방문 날짜를 입력해주세요");
            f.visit.focus();
            return false;
        }

        if (f.name.value == "") {
            alert("이름을 입력해주세요");
            f.name.focus();
            return false;
        }

        if (f.phone.value == "") {
            alert("휴대폰 번호를 입력해주세요");
            f.phone.focus();
            return false;
        }

        if($(":radio[name='consent']:checked").val() != "agree"){
            alert('개인정보 수집에 동의해주세요');
            f.consent.focus();
            return false;
        }
        return true;
    },
    delete_check : function() {
        if (confirm("정말 삭제하시겠습니까?") == true) return true;
        else return false;
    },
    request_delete : function (id) {

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/request/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/administer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();