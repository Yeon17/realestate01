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
if (location.pathname == '/administer') {
  b1[0].classList.add('clicked');
}
if (location.pathname == '/administer/buy') {
  b1[1].classList.add('clicked');
}
if (location.pathname == '/administer/sell') {
  b1[2].classList.add('clicked');
}

$(document).ready(function() {
  $('#summernote').summernote({

    height: 300,
    minHeight: null,
    maxHeight: null,
    lang : 'ko-KR',
    dialogsInBody: true,
    toolbar: [
    			    // [groupName, [list of button]]
    			    ['fontname', ['fontname']],
    			    ['fontsize', ['fontsize']],
    			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
    			    ['color', ['forecolor','color']],
    			    ['table', ['table']],
    			    ['para', ['ul', 'ol', 'paragraph']],
    			    ['height', ['height']],
    			    ['insert',['picture','link','video']],
    			    ['view', ['help']]
    			  ],
    			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
    			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    callbacks: {	//여기 부분이 이미지를 첨부하는 부분
    					onImageUpload : function(files) {
    						uploadSummernoteImageFile(files[0],this);
    					},
    					onPaste: function (e) {
                                    var bufferText = ((e.originalEvent || e).clipboardData || window.clipboardData).getData('Text');
                                    e.preventDefault();
                                    document.execCommand('insertText', false, bufferText);
                                }
    			}

  });

});

function uploadSummernoteImageFile(file, editor) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "/uploadSummernoteImageFile",
			contentType : false,
			processData : false,
			success : function(data) {
            	//항상 업로드된 파일의 url이 있어야 한다.
				$(editor).summernote('insertImage', data.url);
			},
			 error:function(request,status,error){
                    alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
             }
		});
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

var scrap = 'off';
function scrap_property(aa){
    if(scrap == 'off'){
        $(aa).css('color','blue');
        scrap = 'on';
    }
    else {
        $(aa).css('color','');
        scrap = 'off';
    }
}

var contents = $('.string_html').data('contents');
$(".string_html").html(contents);

$(".carousel-inner").children().first().attr('class','carousel-item active');


var b2 = document.getElementsByClassName("apt-main");
if (location.pathname == '/lookup-property') {
  b2[0].classList.add('clicked');
}
if (location.pathname == '/lookup-property/park-xi') {
  b2[1].classList.add('clicked');
}

var b3 = document.getElementsByClassName("apt-etc");
if (location.pathname == '/lookup-property/eastpole') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[0].classList.add('clicked');
}
if (location.pathname == '/lookup-property/samsung4') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[1].classList.add('clicked');
}
if (location.pathname == '/lookup-property/eastpark') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[2].classList.add('clicked');
}
if (location.pathname == '/lookup-property/puleujio') {
  $('#etc_detail').css('display','');
  b2[2].classList.add('clicked');
  b3[3].classList.add('clicked');
}

if (location.pathname.includes('/property/update/')) {
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


function modi_ans(){
     $('#view_ans').css('display','none');
     $('.answer').append(
        "<div id='answer_text' style='border:1px solid #a9a9a9; background-color:white'>"+
            "<textarea id='ans_content' style='width:100%; height:150px; border:none; padding:20px'></textarea>"+
            "<div style='border-top:1px solid #e4e4e4; height:40px;text-align:right;'>"+
                "<div id='btn-cancel' onclick='change_view()' style='padding:9px 25px 9px 25px;display:inline-block'>취소</div>"+
                "<div id='btn-answer-update' onclick='click_update()' style='padding:9px 25px 9px 25px; display:inline-block'>등록</div>"+
            "</div>"+
        "</div>"
     );
     $('#ans_content').html($('#view_ans_text').html());
}
function change_view(){
     $('#answer_text').remove();
     $('#view_ans').css('display','');
}