var main = {
    init : function () {
        var _this = this;
        $('#btn-question-save').on('click', function () {
            _this.question_save();
        });

        $('#btn-question-update').on('click', function () {
            _this.question_update();
        });

        $('#btn-question-delete').on('click', function () {
            _this.question_delete();
        });
    },
    question_save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#summernote').val(),
            uid: $('#uid').val(),
            statement: "답변대기"
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/question',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('글이 등록되었습니다.');
            window.location.href = '/question';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    question_update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#summernote').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/question/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('글이 수정되었습니다.');
            window.location.href = '/question';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    question_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/question/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');

        }).fail(function (error) {
            alert(JSON.stringify(error));
            window.location.href = '/question';
        });
    }

};

main.init();

//답변 수정
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

//답변 수정 취소
function change_view(){
     $('#answer_text').remove();
     $('#view_ans').css('display','');
}