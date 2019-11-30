package com.zlead.service.impl;

import com.zlead.dao.RegionDao;
import com.zlead.entity.Region;
import com.zlead.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 查询区域
 * @author fqf
 *
 */
@Transactional
@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
    private RegionDao regionDao;
	
	public Region getRegion(long parentId, String regionName){
		Region region = regionDao.getReginByparentIdAndregionName(parentId, regionName);
		return region;
	}
	
	//查询名称
	public String getRegionName(String regionCode){
		Region region = regionDao.getReginByCode(regionCode);
		String reginName = "";
		if(region!=null){
			reginName = region.getRegionName();
		}
		return reginName;
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

	@Override
	public Region getByName(String Name) {
		// TODO Auto-generated method stub
		
		return regionDao.getByName(Name);
	}
	@Override
	public List<Region> findprovince(){
		return regionDao.findprovince();
	}
	@Override
	public List<Region> findid(int parentId){

		return  regionDao.findid(parentId);
	}
}
