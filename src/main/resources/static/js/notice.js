var main = {
    init : function () {
        var _this = this;
        $('#btn-notice-save').on('click', function () {
            _this.notice_save();
        });

        $('#btn-notice-update').on('click', function () {
            _this.notice_update();
        });

        $('#btn-notice-delete').on('click', function () {
            _this.notice_delete();
        });
    },
    notice_save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#summernote').val(),
            uid: $('#uid').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/notice',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/notice';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    notice_update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#summernote').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/notice/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/notice';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    notice_delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/notice/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/notice';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();