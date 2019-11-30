package com.zlead.service;

import com.zlead.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 查询区域
 * @author fqf
 *
 */
public interface RegionService {
	 Region getRegion(long parentId, String regionName);
	 //查询名称
	 String getRegionName(String regionCode);
	 
	 List<Region> findAll();
	 Region getByName(String name);

	List<Region> findprovince();

	List<Region> findid(@Param(value = "parentId") int parentId);
}
