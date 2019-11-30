package com.zlead.constant;


import com.zlead.exception.ErrorDef;

/**
 * Created by hejie on 14-1-20.
 */
public enum ErrorCode {
    @ErrorDef("%s") FR_000,
    @ErrorDef("服务器处理请求失败：[%s], [%s]") FR_001,
    @ErrorDef("参数不能为空") FR_002,
    @ErrorDef("读取CSV文件出错") FR_003,
    @ErrorDef("写入CSV文件出错") FR_004,
    @ErrorDef("生成Excel工作簿出错") FR_005,
    @ErrorDef("向Excel工作簿中追加数据出错") FR_006,
    @ErrorDef("获取模板文件 %s 出错") FR_007,
    @ErrorDef("将Map数据转换为对象 %s 出错") FR_008,
    @ErrorDef("初始化对象 %s 出错") FR_009,
    @ErrorDef("服务地址 %s 格式错误") FR_010,
    @ErrorDef("出错了") FR_011,
    @ErrorDef("实体类 %s 初始化错误") FR_012,
    @ErrorDef("实体类 %s 需要有公共空构造函数") FR_013,
    @ErrorDef("Solr服务地址配置错误") FR_014,
    @ErrorDef("请配置Solr服务，定义zkHost(CloudSolrServer)、solrServerUrl(HttpSolrServer)、solrServerUrls(LBHttpSolrServer)其中之一") FR_015,
    @ErrorDef("查询Solr服务错误") FR_016,
    @ErrorDef("读取excel文件失败") FR_017,
    @ErrorDef("队列命令执行超时") FR_018,
}
