var main = {
    init : function () {
        var _this = this;
        $('#btn-property-save').on('click', function () {
            _this.property_save();
        });
    },
    property_save : function () {
        var img = document.getElementsByClassName("img_src");
        var src = null;
        var represent_src = null;
        if($(img[0]).attr('src') != "/img/img_upload.png"){
            represent_src = $(img[0]).attr('src');
            for (var i = 0; i < img.length; i++) {
                src = src + $(img[i]).attr('src') + ';';
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
    }
};

main.init();