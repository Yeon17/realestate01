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
            url: '/api/v0/request',
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
            url: '/api/v0/request/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();

    //아파트 별 면적 표시
    function addressKindChange(e) {
        var pakeujai = ["전체", "61㎡ A형","61㎡ B형","61㎡ C형","61㎡ D형","61㎡ E형","61㎡ F형"];
        var eastpole = ["전체", "78㎡", "84A㎡","84B㎡","103㎡","108㎡"];
        var samsung4 = ["전체", "82㎡"];
        var eastpark = ["전체", "84㎡ A형","84㎡ B형","84㎡ C형","84㎡ D형","84㎡ E형","84㎡ F형","84㎡ G형","95㎡ T형","98㎡ A형","98㎡ B형","98㎡ C형","98㎡ D형","117㎡ P형","118㎡ P형"];
        var puleujio = ["전체", "59㎡A", "59㎡B", "59㎡C", "59㎡D", "84㎡A"];

        var target = document.getElementById("addressKindD");

        if(e.value == "전체") var d = ["아파트를 선택해주세요"];
        else if(e.value == "동천파크자이") var d = pakeujai;
        else if(e.value == "더샵 동천 이스트포레") var d = eastpole;
        else if(e.value == "수지 삼성4차") var d = samsung4;
        else if(e.value == "수지 래미안이스트파크") var d = eastpark;
        else if(e.value == "수지 파크푸르지오") var d = puleujio;

        target.options.length = 0;

        for (x in d) {
            var opt = document.createElement("option");
            opt.value = d[x];
            opt.innerHTML = d[x];
            target.appendChild(opt);
        }
    }

$(document).on("click", "#content_detail", function () {
     var content = $(this).data('content');
     $("#content_view").html(content);

}); //administer 상세내용 창

var b1 = document.getElementsByClassName("btn-re");
if (location.pathname == '/administer/request') {
  b1[0].classList.add('clicked');
}
if (location.pathname == '/administer/request/buy') {
  b1[1].classList.add('clicked');
}
if (location.pathname == '/administer/request/sell') {
  b1[2].classList.add('clicked');
}

function check_new(){
    var dateList = document.getElementsByClassName("modi_date");
    var post_new = document.getElementsByClassName("post_new");
    var today = new Date();

    for (var i = 0; i < dateList.length; i++){
        var a = dateList[i].innerHTML;
        var comp_date = a.substring(0,a.indexOf('T'));
        if(today.getFullYear() == comp_date.substring(0,4) && today.getMonth()+1 == comp_date.substring(5,7) && today.getDate() == comp_date.substring(8,10)){
            $(post_new[i]).append("<img src='/img/new_icon.gif'>");
        }
    }
}

check_new();