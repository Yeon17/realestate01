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
			url : "/image_upload",
			contentType : false,
			processData : false
		}).done(function(data) {
            //항상 업로드된 파일의 url이 있어야 한다.
		    $(editor).summernote('insertImage', data.url);
	    }).fail(function(error) {
            alert(JSON.stringify(error));
		});
	}

//웹페이지에 summernote 내용 표시
var contents = $('.string_html').data('contents');
$(".string_html").html(contents);
