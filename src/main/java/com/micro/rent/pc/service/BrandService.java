package com.micro.rent.pc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.vo.BrandVo;

@Service
public interface BrandService {

    /**
     * 推荐品牌列表
     *
     * @Title: recommendBrandList
     * @Description: TODO
     * @return
     * @return: List<Brand>
     */
    List<BrandVo> getRecommendBrandList(String region);

    Brand getBrandById(Integer id);

    // waibao
    Brand findBrandById(Long brandId) throws Exception;

    List<BrandVo> findNearbyBrandByAddress(Double x, Double y, Double distinct,
            Long brandId) throws Exception;
}
