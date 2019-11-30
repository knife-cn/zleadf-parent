;

function Loading(obj, type) {
    // type=1,ajax前；type=0，ajax后
    if (type) {
        var str = '';
        str += '<div class="loading">'
        str += '<img src="../../shopping/img/upload/login.gif" alt="">'
        str += '<p>正在急速加载中...</p>'
        str += '</div>'
        obj.append(str)
        obj.children('.loading').show();
    } else {
        obj.children('.loading').hide();
    }
};