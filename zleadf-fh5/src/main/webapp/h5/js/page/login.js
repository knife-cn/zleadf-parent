$(function () {
    function verifyInput(el, regular,rightTip, errorTip) {
        el.bind('input porpertychange', function() {
            if (regular.test($(this).val())) {
                console.log("正确");
                $(this).parents('.inputDv').addClass('rightDv');
                $(this).parent().siblings('label').text(rightTip);


            } else {
                $(this).parents('.inputDv').removeClass('rightDv');
                $(this).parents('.inputDv').addClass('errorDv');
                $(this).parent().siblings('label').text(errorTip);
            }
        });
    }
    function initInput() {
        $('.loginForm .inputDv').removeClass('rightDv  errorDv');
        $('.loginForm input').val('');
        $('input[data-name="phone"]').parent().siblings('label').text("工厂端账号");
        $('input[data-name="password"]').parent().siblings('label').text("密码");

    }
    function passwordErrorTip() {
        $('input[data-name="password"]').parents('.inputDv').removeClass('rightDv');
        $('input[data-name="password"]').parents('.inputDv').addClass('errorDv');
        $('input[data-name="password"]').parent().siblings('label').text("请输入正确的密码");
        $('input[data-name="password"]').val("");
    }
    verifyInput($('input[data-name="phone"]'), /^1[34578]\d{9}$/,"工厂端账号", "请输入正确的手机号！");
    verifyInput($('input[data-name="password"]'), /^[\w_-]{6,16}$/, "密码","请输入正确的密码！");

    $('.loginBtn').click(function () {
        var _phone = $('#phone').val();
        var _password = $('#password').val();

        if(_phone == ''){
            $('.phoneDv').addClass('errorDv');
            $('.phoneDv label').text('请输入手机号');
        }else if(_password == ''){
            $('.passwordDv').addClass('errorDv');
            $('.passwordDv label').text('请输入密码');
        }else{
            var data = {
                "account": _phone,
                "password": _password
            }
            var _host = "";
            // //zlead/login/login?account=15618264128&password=111111
            $.ajax({
                url: _host + "/zlead/member/login?account="+data.account+"&password="+data.password,
                type: "GET",
                dataType: "json",
                success: function (res) {
                    console.log(res,res["message"]);

                    if(res["success"]) {
                        
                        console.log(res["message"]);
                        // alert(res["message"])
    window.location.href = "/h5/wbIndex";
                        
                    }else{
                        if(res.message.indexOf("登录用户不存在")>-1) {
                            alert(res.message);
                            initInput();
                        }else if (res.message.indexOf("密码错误")>-1)  {
                            passwordErrorTip();
                            alert(res.message)
                        }

                    }

                },
                error: function(err){
                    console.log('err',err.message);
                }
            })
        }

    })

})
