var main = {
    init : function () {
        var _this = this;
        // id = btn-login가 클릭됬을 때 login function 실행
        $('#btn-login').on('click', function () {
            _this.login();
        });
    },

    login : function () {
        var data = {
            userphone: $("#userphone").val()
        };

        $.ajax({
            type: 'POST',
            url: '/authentication',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인성공');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();