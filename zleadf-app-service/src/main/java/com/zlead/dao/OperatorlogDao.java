package com.zlead.dao;

import com.zlead.entity.OperatorLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 订单节点明细基本操作接口
 * @author leiningbo
 */
public interface OperatorlogDao {


    /**
     * This method:deleteByPrimaryKey
     *   t_operator_log
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     *   t_operator_log
     *
     * @ET
     */
    int insertSelectiveOne(OperatorLog record);

    /**
     * This method:insertSelective
     *   t_operator_log
     *
     * @ET
     */
    int insertSelective(OperatorLog record);



    /**
     * This method:selectByPrimaryKey
     *   t_operator_log
     *
     * @ET
     */
    OperatorLog selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_operator_log
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OperatorLog record);

    /**
     * This method:updateByPrimaryKeyWithBLOBs
     *   t_operator_log
     *
     * @ET
     */
    int updateByPrimaryKeyWithBLOBs(OperatorLog record);

    /**
     * This method:updateByPrimaryKey
     *   t_operator_log
     *
     * @ET
     */
    int updateByPrimaryKey(OperatorLog record);

    List<OperatorLog> getListOpLogs(@Param("sn") Long sn);

    List<OperatorLog> getOperatorLogBySend(@Param("sn") Long sn, @Param("orderType") String orderType);

    OperatorLog findBySn(Integer id);

    Date getWeekMonday();

    Date getWeekSunday();

    Date getMonthStart();

    Date getMonthStop();

    Date getSeasonStart();

    Date getSeasonStop();
}
