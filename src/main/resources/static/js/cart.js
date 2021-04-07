var scrap = $('#scrap_check').val();
if(scrap == 'on') $('#btn-cart').css('color','blue');
var main = {
    init : function () {
        var _this = this;
        $('#btn-cart').on('click', function () {
            if(scrap == 'off'){
                $('#btn-cart').css('color','blue');
                scrap = 'on';
                _this.cart_save();
            }
            else{
                $('#btn-cart').css('color','');
                scrap = 'off';
                _this.cart_delete();
            }
        });
        $('#btn-cart-delete').on('click', function(){
            _this.cart_delete();
        });
    },
    cart_save : function () {
        var data = {
            uid: $('#uid').val(),
            pid: $('#id').val(),
            oauth: $('#oauth').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/cart',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cart_delete : function () {
        var data = {
            uid: $('#uid').val(),
            pid: $('#id').val()
        };

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/cart',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if (location.pathname == '/cart') location.reload();

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};
main.init();