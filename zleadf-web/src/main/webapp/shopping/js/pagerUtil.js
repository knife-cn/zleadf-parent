function pagerUtil(pagesize, total, currPage) {
    var html = "";
    var currentPage = parseInt(currPage);
    var totalPages = getPages(total, pagesize);
   /* html += '<div class="page1"><div class="dataTables_info" aria-live="polite">共' + total + '条，' + totalPages + '页</div></div>';*/
    html += '<div class="pagination-panel"> 到第 ' +
        '<input id="toPage" type="text" class="form-control input-mini input-inline input-sm" maxlenght="5" style="text-align:center; margin: 0 5px;">页，' +
        '<a href="javascript:check()" class="btn btn-sm default" title="goto">跳转</a>' +
        '</div>';
    html += '<div class="page2">';
    html += '<div class="dataTables_paginate paging_bootstrap_full_number">';
    html += '<ul class="pagination">';
    var i = 1;
    //html += "<li class='paginate_button'><a href='javascript:;'>共"+total+"条，"+totalPages+"页</a></li>";
    html += "<li class='paginate_button'><a href='javascript:turnPage(1);'>首页</a></li>";
    if (totalPages <= 5) {
        html += "<li class='paginate_button previous disabled'><a href='javascript:;'><</a></li>";
        for (i; i <= totalPages; i++) {
            if (i == currentPage) {
                html += "<li class='paginate_button active'><a href='javascript:;' class=current>" + currentPage + "</a></li>";
            } else {
                html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
            }
        }
        html += "<li class='paginate_button previous disabled'><a href='javascript:;'>></li>";
    } else {
        if (currentPage == 1) {
            html += "<li class='paginate_button previous disabled'><a href='javascript:;'><</a></li>";
            for (i; i < 6; i++) {
                if (i == currentPage) {
                    html += "<li class='paginate_button active'><a href='javascript:;'>" + currentPage + "</a></li>";
                } else {
                    html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
                }
            }
            html += "<li class='paginate_button previous'><a href='javascript:turnPage(" + i + ");'>></li>";
        } else if (currentPage == totalPages) {
            i = currentPage - 4;
            html += "<li class='paginate_button previous'><a href='javascript:turnPage(" + (currentPage - 5) + ");'><</a></li>";
            for (i; i < currentPage; i++) {
                html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
            }
            html += "<li class='paginate_button active'><a href='javascript:;'>" + currentPage + "</a></li>";
            html += "<li class='paginate_button previous disabled'><a href='javascript:;'>></li>";
        } else {
            if (currentPage <= 3) {
                html += "<li class='paginate_button previous disabled'><a href='javascript:;'><</a></li>";
                for (i; i <= 5; i++) {
                    if (i == currentPage) {
                        html += "<li class='paginate_button active'><a href='javascript:;' class=current>" + currentPage + "</a></li>";
                    } else {
                        html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
                    }
                }
                html += "<li class='paginate_button previous'><a href='javascript:turnPage(" + i + ");'>></li>";
            } else if (currentPage + 4 > totalPages) {
                i = totalPages - 4;
                if (i < 1) {
                    i = 1;
                }
                html += "<li class='paginate_button previous'><a href='javascript:turnPage(" + (i - 1) + ");'><</a></li>";
                for (i; i <= totalPages; i++) {
                    if (i == currentPage) {
                        html += "<li class='paginate_button active'><a href='javascript:;' class=current>" + currentPage + "</a></li>";
                    } else {
                        html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
                    }
                }
                html += "<li class='paginate_button previous disabled'><a href='javascript:;'>></li>";
            } else {
                i = currentPage - 2;
                html += "<li class='paginate_button previous'><a href='javascript:turnPage(" + (i - 1) + ");'><</a></li>";
                for (i; i <= currentPage + 2; i++) {
                    if (i == currentPage) {
                        html += "<li class='paginate_button active'><a href='javascript:;' class=current>" + currentPage + "</a></li>";
                    }
                    else {
                        html += "<li class='paginate_button'><a href='javascript:turnPage(" + i + ");'>" + i + "</a></li>";
                    }
                }
                html += "<li class='paginate_button previous '><a href='javascript:turnPage(" + i + ");'>></li>";
            }
        }
    }
    html += "<li class='paginate_button'><a href='javascript:turnPage(" + totalPages + ");'>尾页</a></li>";
    html += '<input type="hidden" id="totalPages" value="' + totalPages + '"/>';
    html += "</ul></div></div>";

    return html;
}

function getPages(total, pagesize) {
    var pages;
    if (total % pagesize == 0) {
        pages = total / pagesize;
        pages = pages == 0 ? 1 : pages;
    } else {
        pages = Math.ceil(total / pagesize);
    }
    return pages;
}

//检查是否为正整数
function isUnsignedInteger(a) {
    var r = /^\+?[1-9][0-9]*$/;
    return r.test(a);
}

$(document).ready(function () {
    $(document).on('keydown', '#toPage', function (e) {
        var totalPages = $("#totalPages").val();
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            var p = $.trim($("#toPage").val());
            if (!isUnsignedInteger(parseInt(p))) {
                $("#toPage").select();
            } else {
                if (parseInt(totalPages) >= p) {
                    turnPage(p);
                } else {
                    turnPage(totalPages);
                }
            }
        }
    });
});

function check() {
    var totalPages = $("#totalPages").val();
    var p = $.trim($("#toPage").val());
    if (!isUnsignedInteger(parseInt(p))) {
        $("#toPage").select();
    } else {
        if (parseInt(totalPages) >= p) {
            turnPage(p);
        } else {
            turnPage(totalPages);
        }
    }
}
