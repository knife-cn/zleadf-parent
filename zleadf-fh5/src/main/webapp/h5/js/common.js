var common = {
    /***
     *获取search值
     * @param name 字段
     * @param needdecoed Boolean 是否编码
     * @returns {*}
     */
    getQueryString: function (name, needdecoed) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var lh = window.location.search;
        if (needdecoed) {
            lh = decodeURI(window.location.search)
        }
        var r = lh.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    },
    /**
     * 根据毫秒数准换成时间戳
     * @param time
     * @returns {string}
     */
    getdate: function (time) {
        var now = new Date(time),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
    }
}