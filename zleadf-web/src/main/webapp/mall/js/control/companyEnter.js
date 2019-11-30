$(function() {
    initAreaSelect('expressArea', function(expressArea) {
        $("#expressArea dl").html(expressArea);
    })
})

function uploadImg() {
    $.ajaxFileUpload({
        url : '../ajeasy/fdfs/uploading',
        secureuri : false,
        fileElementId : 'demo_input',//上传控件的id
        dataType : 'json',
        success : function(res) {
            if(res.code==1){
                var msg = "上传图片成功！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    $(".img_area_img").attr('src','http://116.62.124.171/group1/'+res.data);
                }, 1000);
                $(".backDate").val(res.data);
            }else if(res.code==2){
                var msg = "上传失败！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
            }
        },
    });
    return false;
}

function validateForm(){
    var contactName = $("#addMemebrId").val();
    if(contactName==''){
        var msg = "请输入加盟商编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var contactName = $("#contactName").val();
    if(contactName==''){
        var msg = "请输入联系人姓名！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var contactPhone = $("#contactPhone").val();
    if(contactPhone==''){
        var msg = "请输入联系人电话！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var companyName = $("#companyName").val();
    if(companyName==''){
        var msg = "请输入公司名称！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var province_name = $("#prive").val();
    var city_name = $("#city").val();
    var county_name = $("#trxo").val();

    if($("#prive").val() == null||$("#prive").val() == "") {
        var msg ="请选择省市区";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var companyAddress = $("#companyAddress").val();
    if(companyAddress==''){
        var msg = "请输入详细地址！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    var legalName = $("#legalName").val();
    if(legalName==''){
        var msg = "请输入公司法人名字！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }

    var businessSn = $("#businessSn").val();
    if(businessSn==''){
        var msg = "请输入营业执照编号！";
        appSupport.cm.errorMessageShow(errorMsg, msg);
        setTimeout(function() {
            appSupport.cm.errorMessageHide(errorMsg);
        }, 1000);
        return false;
    }
    $("#validate").attr("onclick","");
    $.ajax({
        url:$("#form1").attr("action"),
        type:"post",
        dataType:"json",
        data:$("#form1").serialize(),
        success : function(res) {
            if(res.code==1) {
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                    window.location.href = "agentAccount.jsp";
                }, 2000);
            }else{
                var msg = res.message;
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 2000);
                $("#validate").attr("onclick","validateForm()");
            }
        }
    });
}
function jump() {
    window.location.href='index.jsp';
}

$(function() {
    /*var IMG_LENGTH = 1;//图片最大1MB
    var IMG_MAXCOUNT = 9;//最多选中图片张数
    var UP_IMGCOUNT = 0;//上传图片张数记录
//打开文件选择对话框
    $("#div_imgfile").click(function () {
        if ($(".lookimg").length >= IMG_MAXCOUNT) {
            var msg = "一次最多上传" + IMG_MAXCOUNT + "张图片！";
            appSupport.cm.errorMessageShow(errorMsg, msg);
            setTimeout(function() {
                appSupport.cm.errorMessageHide(errorMsg);
            }, 1000);
            return;
        }

        var _CRE_FILE = document.createElement("input");
        if ($(".imgfile").length <= $(".lookimg").length) {//个数不足则新创建对象
            _CRE_FILE.setAttribute("type", "file");
            _CRE_FILE.setAttribute("class", "imgfile");
            _CRE_FILE.setAttribute("accept", ".png,.jpg,.jpeg");
            _CRE_FILE.setAttribute("num", UP_IMGCOUNT);//记录此对象对应的编号
            $("#div_imgfile").after(_CRE_FILE);
        }
        else { //否则获取最后未使用对象
            _CRE_FILE = $(".imgfile").eq(0).get(0);
        }
        return $(_CRE_FILE).click();//打开对象选择框
    });

//创建预览图，在动态创建的file元素onchange事件中处理
    $(".imgfile").live("change", function () {
        if ($(this).val().length > 0) {//判断是否有选中图片

            //判断图片格式是否正确
            var FORMAT = $(this).val().substr($(this).val().length - 3, 3);
            if (FORMAT != "png" && FORMAT != "jpg" && FORMAT != "peg") {
                var msg = "文件格式不正确！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
                return;
            }

            //判断图片是否过大，当前设置1MB
            var file = this.files[0];//获取file文件对象
            if (file.size > (IMG_LENGTH * 1024 * 1024)) {
                var msg = "图片大小不能超过" + IMG_LENGTH + "MB！";
                appSupport.cm.errorMessageShow(errorMsg, msg);
                setTimeout(function() {
                    appSupport.cm.errorMessageHide(errorMsg);
                }, 1000);
                $(this).val("");
                return;
            }
            //创建预览外层
            var _prevdiv = document.createElement("div");
            _prevdiv.setAttribute("class", "lookimg");
            //创建内层img对象
            var preview = document.createElement("img");
            $(_prevdiv).append(preview);
            //创建删除按钮
            var IMG_DELBTN = document.createElement("div");
            IMG_DELBTN.setAttribute("class", "lookimg_delBtn");
            IMG_DELBTN.innerHTML = "移除";
            $(_prevdiv).append(IMG_DELBTN);
            //创建进度条
            var IMG_PROGRESS = document.createElement("div");
            IMG_PROGRESS.setAttribute("class", "lookimg_progress");
            $(IMG_PROGRESS).append(document.createElement("div"));
            $(_prevdiv).append(IMG_PROGRESS);
            //记录此对象对应编号
            _prevdiv.setAttribute("num", $(this).attr("num"));
            //对象注入界面
            $("#div_imglook").children("div:last").before(_prevdiv);
            UP_IMGCOUNT++;//编号增长防重复

            //预览功能 start
            var reader = new FileReader();//创建读取对象
            reader.onloadend = function () {
                preview.src = reader.result;//读取加载，将图片编码绑定到元素
            }
            if (file) {//如果对象正确
                reader.readAsDataURL(file);//获取图片编码
            } else {
                preview.src = "";//返回空值
            }
            //预览功能 end
        }
    });

//删除选中图片
    $(".lookimg_delBtn").live("click", function () {
        $(".imgfile[num=" + $(this).parent().attr("num") + "]").remove();//移除图片file
        $(this).parent().remove();//移除图片显示
    });

//删除按钮移入移出效果
    $(".lookimg").live("mouseover", function () {
        if ($(this).attr("ISUP") != "1")
            $(this).children(".lookimg_delBtn").eq(0).css("display", "block");;
    });
    $(".lookimg").live("mouseout", function () {
        $(this).children(".lookimg_delBtn").eq(0).css("display", "none");;
    });

//确定上传按钮
    $("#btn_ImgUpStart").click(function () {
        if ($(".lookimg").length <= 0) {
            var msg = "还未选择需要上传的图片！";
            appSupport.cm.errorMessageShow(errorMsg, msg);
            setTimeout(function() {
                appSupport.cm.errorMessageHide(errorMsg);
            }, 1000);
            return;
        }

        //全部图片上传完毕限制
        if ($(".lookimg[ISUP=1]").length == $(".lookimg").length) {
            var msg = "图片已全部上传完毕！";
            appSupport.cm.errorMessageShow(errorMsg, msg);
            setTimeout(function() {
                appSupport.cm.errorMessageHide(errorMsg);
            }, 1000);
            return;
        }

        //循环所有已存在的图片对象，准备上传
        for (var i = 0; i < $(".lookimg").length; i++) {
            var NOWLOOK = $(".lookimg").eq(i);//当前操作的图片预览对象
            NOWLOOK.index = i;
            //如果当前图片已经上传，则不再重复上传
            if (NOWLOOK.attr("ISUP") == "1")
                continue;

            //上传图片准备
            var IMG_BASE = NOWLOOK.children("img").eq(0).attr("src"); //要上传的图片的base64编码
            var IMG_IND = NOWLOOK.attr("num");
            var IMG_ROUTE = $(".imgfile[num=" + IMG_IND + "]").eq(0).val();//获取上传图片路径，为获取图片类型使用
            var IMG_ENDFOUR = IMG_ROUTE.substr(IMG_ROUTE.length - 4, 4);//截取路径后四位，判断图片类型
            var IMG_FOMATE = "jpeg"; //图片类型***
            if (IMG_ENDFOUR.trim() == ".jpg")
                IMG_FOMATE = "jpg";
            else if (IMG_ENDFOUR.trim() == ".png")
                IMG_FOMATE = "png";

            //图片正式开始上传
            $.ajax({
                type: "POST",
                url: "../ajeasy/fdfs/uploading",
                data: { 'imgBase': IMG_BASE, 'imgFormat': IMG_FOMATE, 'lookIndex': NOWLOOK.index },//图片base64编码，图片格式（当前仅支持jpg,png,jpeg三种），图片对象索引
                dataType: "json",
                success: function (res) {
                    if (res.code == 1) {
                        //图片上传成功回调
                        var UPTIME = Math.ceil(Math.random() * 400) + 400;//生成一个400-800的随机数，假设进图条加载时间不一致
                        $(".lookimg").eq([res.ind]).attr("ISUP", "1");//记录此图片已经上传
                        $(".lookimg").eq([res.ind]).children(".lookimg_progress").eq(0).children("div").eq(0).animate({ width: "100%" }, UPTIME, function () {
                            $(this).css("background-color", "#00FF00").text('上传成功');
                        });
                    }
                    else {//图片未上传成功回调
                        $(".lookimg")[res.ind].children(".lookimg_progress").eq(0).children("div").eq(0).css("width", "100%").css("background-color", "red").text("上传失败");
                    }
                },
                error: function (err) {
                    //服务器连接失败报错处理
                    var msg = "服务器出错！";
                    appSupport.cm.errorMessageShow(errorMsg, msg);
                    setTimeout(function() {
                        appSupport.cm.errorMessageHide(errorMsg);
                    }, 1000);
                },
                beforeSend: function () {
                    //图片上传之前执行的操作，当前为进度条显示
                    NOWLOOK.children(".lookimg_progress").eq(0).css("display", "block");//进度条显示
                }
            });
        }
    });*/
})