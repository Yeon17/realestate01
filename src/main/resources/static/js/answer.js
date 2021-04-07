var main = {
    init : function () {
        var _this = this;
        $('#btn-answer-save').on('click', function () {
            _this.answer_save();
        });

        $(document).on('click', '#btn-answer-update', function () { //동적으로 추가되는 태그이므로 동적으로 이벤트 추가
            _this.answer_update();
        });

        $('#btn-answer-delete').on('click', function () {
            _this.answer_delete();
        });
    },
    answer_save : function () {
        var data = {
            qid: $('#id').val(),
            author: $('#author').val(),
            content: $('#ans_content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/answer',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {

            alert('답변이 등록되었습니다.');
            location.reload();

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

        var qid = $('#id').val();

        var statement = {
            statement : '답변완료'
        };

         $.ajax({
            type: 'PUT',
            url: '/api/v1/question/statement/'+qid,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(statement)
         }).fail(function (error) {
            alert(JSON.stringify(error));
         });

    },
    answer_update : function () {
        var content = $('#ans_content').val();

        var ans_id = $('#ans_id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/answer/'+ans_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: content
        }).done(function() {
            alert('답변이 수정되었습니다.');

            location.reload();

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    answer_delete : function () {
        var ans_id = $('#ans_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/answer/'+ans_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('답변이 삭제되었습니다.');
            location.reload();

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

        var statement = {
            statement : '답변대기'
        };
        var qid = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/question/statement/'+qid,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(statement)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();