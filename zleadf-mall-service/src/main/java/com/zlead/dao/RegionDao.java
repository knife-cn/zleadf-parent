package com.zlead.dao;

import com.zlead.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionDao {
    /**
     * This method:deleteByPrimaryKey
     *   region
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   region
     *
     * @ET
     */
    int insert(Region record);

    /**
     * This method:insertSelective
     *   region
     *
     * @ET
     */
    int insertSelective(Region record);

    /**
     * This method:selectByExample
     *   region
     *
     * @ET
     */
   /* List<Region> selectByExample(RegionExample example);*/

    /**
     * This method:selectByPrimaryKey
     *   region
     *
     * @ET
     */
    Region selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   region
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Region record);

    /**
     * This method:updateByPrimaryKey
     *   region
     *
     * @ET
     */
    int updateByPrimaryKey(Region record);
    
    //通过省的名称去查询信息(通过名称和父级ID去查询信息)
    Region getReginByparentIdAndregionName(@Param(value = "parentId") long parentId, @Param(value = "regionName") String regionName);
    //通过code查询名称
    Region getReginByCode(@Param(value = "regionCode") String regionCode);

	List<Region> findAll();

	Region getByName(String name);

	List<Region> findprovince();

	List<Region> findid(@Param(value = "parentId") int parentId);
    
    
}