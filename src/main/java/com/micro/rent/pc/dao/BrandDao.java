package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.vo.BrandVo;

public interface BrandDao {
    /**
     * 参数待定(分页)
     *
     * @Title: queryRecommendedBrands
     * @Description: TODO
     * @return
     * @return: List<Brand>
     */
    List<BrandVo> queryRecommendedBrands(String region);

    // List<BrandPic> queryPicByBrandId(Integer brandId);
    Brand queryById(Integer id);
    
    //waibao
    Brand findBrandById(Map<String, Object> map) throws Exception;
    
    List<BrandVo> findNearbyBrandByAddress(Map<String, Object> map) throws Exception;
}
