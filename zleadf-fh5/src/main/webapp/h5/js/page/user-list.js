;
(function() {
    $.ajax({
        type: 'get',
        // data: {
        //     pageNum: '1',
        //     size: '10'
        // },
        // url: '/zlead/shopmgr/factoryAgent',
        url: '/zlead/shopmgr/queryAgentStatus',
        dataType: 'json',
        success: function(res) {
            console.log(res);
            // var agentId = res.data.agentId;
            var data = res.data;
            console.log(data);
            var str = '';

            // for(let i=0;i<data.length;i++){
            //     str+=`
            //     <li>
            //         <span>W</span>
            //         <div>
            //             <h5>
            //                 <b>${data[i].agentName}</b>
            //                 <span>B级</span>
            //                 <a href="javascript:;" class="my_active">审核中</a>
            //                 <strong class="accessButton" onclick="accessApply(${data[i].agentId},${i})">通过</strong>
            //             </h5>
            //             <p>
            //                 <img src="../../../images/ic_address@2x.png" alt="">
            //                 <span>上海市普陀区金沙江路长风二村17号306室</span>
            //             </p>
            //             <p>
            //                 <img src="../../../images/ic_shop@2x.png" alt="">
            //                 <span>华师大五金店</span>
            //             </p>
            //         </div>
            //     </li>
            //     `;
            // }

            str = {
                list: data
            }

            s = template('userList', str)
            $('.content_ul').html(s);


            mui.ready(function() {

                let options = {
                    scrollY: true, //是否竖向滚动
                    scrollX: false, //是否横向滚动
                    startX: 0, //初始化时滚动至x
                    startY: 0, //初始化时滚动至y
                    indicators: true, //是否显示滚动条
                    deceleration: 0.0006, //阻尼系数,系数越小滑动越灵敏
                    bounce: true //是否启用回弹
                }

                mui('.mui-scroll-wrapper').scroll(options)
            })
        },
        error: function(param) {
            console.log('error')
        }
    })

})();

function accessApply(id, i) {

    $.ajax({
        type: 'GET',
        data: {
            status: 1,
            agentId: id
        },
        url: '/zlead/shopmgr/setAgentStatus',
        dataType: 'json',
        success: function(res) {

            $('.content_ul').children('li').eq(i).find('.my_active').html('通过');
            $('.content_ul').children('li').eq(i).find('.accessButton').hide();
        }
    })
}

// mui.ready(function() {

//     let options = {
//         scrollY: true, //是否竖向滚动
//         scrollX: false, //是否横向滚动
//         startX: 0, //初始化时滚动至x
//         startY: 0, //初始化时滚动至y
//         indicators: true, //是否显示滚动条
//         deceleration: 0.0006, //阻尼系数,系数越小滑动越灵敏
//         bounce: true //是否启用回弹
//     }

//     mui('.mui-scroll-wrapper').scroll(options)
// })