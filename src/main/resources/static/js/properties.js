var main = {
    init : function () {
        var _this = this;
        $('#btn-property-save').on('click', function () {
            _this.property_save();
        });
        $('#btn-property-update').on('click', function () {
            _this.property_update();
        });
        $('#btn-property-delete').on('click', function () {
            _this.property_delete();
        });

    },
    property_save : function () {
        var img = document.getElementsByClassName("img_src");
        var src;
        var represent_src;
        if($(img[0]).attr('src') != "/img/img_upload.png"){
            represent_src = $(img[0]).attr('src');
            src = represent_src;
            for (var i = 1; $(img[i]).attr('src') != "/img/img_upload.png"; i++) {
                src = src + ';' + $(img[i]).attr('src');
            }
        }
            var data = {
                apart: $('#addressKindU').val(),
                address: $('#address').val(),
                trade: $('input[name="trade"]:checked').val(),
                price: $('#price').val(),
                admin_expense: $('#admin_expense').val(),
                include: $('#include').val(),
                floor_current: $('#floor_current').val(),
                floor_total: $('#floor_total').val(),
                floor_height: $('#floor_height').val(),
                room: $('#room').val(),
                bathroom: $('#bathroom').val(),
                actual_area: $('#actual_area').val(),
                actual_area_py: $('#actual_area_py').val(),
                supply_area: $('#supply_area').val(),
                supply_area_py: $('#supply_area_py').val(),
                park: $('#park').val(),
                park_all: $('#park_all').val(),
                direction: $('#direction').val(),
                elevator: $('#elevator').val(),
                enter_date: $('#enter_date').val(),
                heat: $('#heat').val(),
                household: $('#household').val(),
                title: $('#title').val(),
                content: $('#summernote').val(),
                image: src,
                represent_img: represent_src,
                uid: $('#uid').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/property',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('매물이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    property_update : function () {
        var img = document.getElementsByClassName("img_src");
        var src;
        var represent_src;
        if($(img[0]).attr('src') != "/img/img_upload.png"){
            represent_src = $(img[0]).attr('src');
            src = represent_src;
            for (var i = 1; $(img[i]).attr('src') != "/img/img_upload.png"; i++) {
                src = src + ';' + $(img[i]).attr('src');
            }
        }
            var data = {
                apart: $('#addressKindU').val(),
                address: $('#address').val(),
                trade: $('input[name="trade"]:checked').val(),
                price: $('#price').val(),
                admin_expense: $('#admin_expense').val(),
                include: $('#include').val(),
                floor_current: $('#floor_current').val(),
                floor_total: $('#floor_total').val(),
                floor_height: $('#floor_height').val(),
                room: $('#room').val(),
                bathroom: $('#bathroom').val(),
                actual_area: $('#actual_area').val(),
                actual_area_py: $('#actual_area_py').val(),
                supply_area: $('#supply_area').val(),
                supply_area_py: $('#supply_area_py').val(),
                park: $('#park').val(),
                park_all: $('#park_all').val(),
                direction: $('#direction').val(),
                elevator: $('#elevator').val(),
                enter_date: $('#enter_date').val(),
                heat: $('#heat').val(),
                household: $('#household').val(),
                title: $('#title').val(),
                content: $('#summernote').val(),
                image: src,
                represent_img: represent_src
            };

            var id = $('#id').val();

            $.ajax({
                type: 'PUT',
                url: '/api/v1/property/'+id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('매물이 수정되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
    property_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/property/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('매물이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();

//매물 업로드
function check_represent(){
    var span = $("<span>");
    span.attr('class','represent_img');
    span.attr('id','rps_img');
    span.html('대표 이미지');
    if($('#property_img').children().first().attr('id') != 'grid_img' && !$('#rps_img').length){
        $('#property_img').children().children().first().append(span);
    }
};

function upd_link(){
    $('#p_img').trigger('click');
}

$('#p_img').on('change', function() {
    var files = document.getElementById('p_img').files;
    for (var i = 0; i < files.length; i++) {
        image_upload(files[i]);
    }
    $('#p_img').val(null);
});

function image_upload(file) {
    data = new FormData();
    data.append("file", file);
    	$.ajax({
    	    data : data,
    	    type : "POST",
    		url : "/image_upload",
    		contentType : false,
    		processData : false,
    		success : function(data) {
    		    var div_img = $('#grid_img').clone(true);
    		    div_img.removeAttr('id'); //다음에 복제시 문제가 되므로 class 속성 삭제
    		    div_img.removeAttr('onclick');

    		    var img = div_img.find('.img_src');
    		    img.next().css('display','');

    		    img.attr('src',data.url);
    		    $('#grid_img').before(div_img);
    		    check_represent();

    		},
    		error:function(request,status,error){
                        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
            }
    	});
    }

function delete_img(del_btn){
    var img = $(del_btn).prev();
    var src = img.attr('src');

    data = new FormData();
    data.append("src", src);
    $.ajax({
        data : data,
        type : 'DELETE',
        url : '/delete_img',
        contentType : false,
        processData : false,
        error:function(request,status,error){
            alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        }
    });

    var div_img = $(del_btn).parent().parent();
    $(div_img).remove();

    check_represent();
}

// 매물 이미지 중 첫번째 active
$(".carousel-inner").children().first().attr('class','carousel-item active');

//매물 수정시 기존 데이터 표시
if (location.pathname.includes('/administer/update-property/')) {
    var trade = $('input[name="trade"]');
    var trade_prev = $('#prev').data("trade_prev");
    if(trade_prev == '매매') $(trade[0]).attr('checked','checked');
    else if(trade_prev == '전세') $(trade[1]).attr('checked','checked');
    else if(trade_prev == '월세') $(trade[2]).attr('checked','checked');

    var apart_prev = $('#addressKindU').data("apart_prev");
    $("#addressKindU").val(apart_prev);

    var height_prev = $('#floor_height').data("height_prev");
    $("#floor_height").val(height_prev);
}

//매물 조회 아파트 표시
var b2 = document.getElementsByClassName("apt-main");

if (location.pathname == '/property') {
  b2[0].classList.add('clicked');
}
if (location.pathname == '/property/park-xi') {
  b2[1].classList.add('clicked');
}

var b3 = document.getElementsByClassName("apt-etc");
if (location.pathname == '/property/eastpole') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[0].classList.add('clicked');
}
if (location.pathname == '/property/samsung4') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[1].classList.add('clicked');
}
if (location.pathname == '/property/eastpark') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[2].classList.add('clicked');
}
if (location.pathname == '/property/puleujio') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[3].classList.add('clicked');
}