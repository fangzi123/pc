package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.BrandDao;
import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandDao brandDao;

    @Override
    public List<BrandVo> getRecommendBrandList(String code) {
        List<BrandVo> brandList = brandDao.queryRecommendedBrands(code);
        // TODO
        // for (BrandVo brand : brandList) {
        // List<BrandPic> picList = brandDao.queryPicByBrandId(brand.getId());
        // int indx = new Random().nextInt(picList.size());
        // String pic = picList.get(indx).getPicPath();
        // brand.setName(pic);
        // }
        return brandList;
    }

    @Override
    public Brand getBrandById(Integer id) {
        Brand brand = brandDao.queryById(id);
        return brand;
    }

    @Override
    public Brand findBrandById(Long brandId) throws Exception {
     Map<String, Object> map = new HashMap<String, Object>();
     map.put("brandId", brandId);
     return brandDao.findBrandById(map);
    }
    
    @Override
    public List<BrandVo> findNearbyBrandByAddress(Double x, Double y, Double distinct, Long brandId) throws Exception {
     Map<String, Object> map = new HashMap<String, Object>();
     map.put("x", x);
     map.put("y", y);
     map.put("distinct", distinct);
     map.put("brandId", brandId);
     return brandDao.findNearbyBrandByAddress(map);
    }

}
