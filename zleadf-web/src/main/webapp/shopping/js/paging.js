(function($,window,document,undefined){
    var initDate={
        pageNo:1,
        totalPage:1,
        callback:function(){}
    };
    function Paging(element,options){
        this.element = element;
        this.options=options=$.extend(initDate,options||{});
        this.init();
    }
    Paging.prototype={
        constructor:Paging,
        init:function(){
            this.creatHtml();
            this.bindEvent();
        },
        creatHtml:function(){
            var me=this;
            var content="";
            var current=me.options.pageNo;
            var total=me.options.totalPage;
            if(current > 1){
                content += "<a>上一页</a>";
            }
            if(total > 7){
                if(current < 4){
                    for(var i=1;i<7;i++){
                        if(current==i){
                            content += "<a class='current'>"+i+"</a>";
                        }else{
                            content += "<a>"+i+"</a>";
                        }
                    }
                    content += "...";
                    content += "<a>"+total+"</a>";
                }else{
                    if(current < total - 3){
                        content += "<a name='1' type='button' class='page num'>1</a>";
                        content += "...";
                        for(var i=current-2;i<current+3;i++){
                            if(current==i){
                                content += "<a class='current'>"+i+"</a>";
                            }else{
                                content += "<a>"+i+"</a>";
                            }
                        }
                        content += "...";
                        content += "<a>"+total+"</a>";
                    }else{
                        content += "<a>1</a>";
                        content += "...";
                        for(var i=total-5;i<total+1;i++){
                            if(current==i){
                                content += "<a class='current'>"+i+"</a>";
                            }else{
                                content += "<a>"+i+"</a>";
                            }
                        }
                    }
                }
            }else{
                for(var i=1;i<total+1;i++){
                    if(current==i){
                        content += "<a class='current'>"+i+"</a>";
                    }else{
                        content += "<a>"+i+"</a>";
                    }
                }
            }
            if(current < total){
                content += "<a>下一页</a>";
            }
            content += " 跳转 ";
            content += "&nbsp;<input class='text_class' maxlength='3' value='"+current+"' type='text' />";
            content += "<a class='tz_tiao'>GO</a>";

            content += " 页 ";
            me.element.html(content);
        },
        bindEvent:function(){
            var me=this;
            me.element.on('click','a',function(){
                var num=$(this).html();
                if(num=="上一页"){
                    me.options.pageNo=+me.options.pageNo-1;
                }else if(num=="下一页"){
                    me.options.pageNo=+me.options.pageNo+1;
                }else if(num=="GO"){
                    var ipt=+me.element.find('input').val();
                    if(ipt&&ipt<=me.options.totalPage&&ipt!=me.options.pageNo){
                        me.options.pageNo=ipt;
                    }
                }else{
                    me.options.pageNo=+num;
                }
                me.creatHtml();
                if(me.options.callback){
                    me.options.callback(me.options.pageNo);
                }
            });
        }
    };
    $.fn.paging=function(options){
        options=$.extend(initDate,options||{});
        return new Paging($(this),options);
    }
})(jQuery,window,document);
