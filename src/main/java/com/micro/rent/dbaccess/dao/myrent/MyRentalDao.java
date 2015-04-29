package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.biz.myrent.vo.MatchResultVo;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;

import java.util.List;
import java.util.Map;

public interface MyRentalDao {

    List<MatchResultVo> selectProjectByQueryVo(MyRentQueryVo queryVo);

    List<MatchResultVo> selectAllProject();

    List<MatchResultVo> selectCoverProject(Map map);

    List<MatchResultVo> selectHouseByQueryVo(MyRentQueryVo queryVo);

    /**
     * 按条件查询房源    算距离
     *
     * @param queryVo
     * @return
     * @author wff
     */
    List<MatchResultVo> findHouseByQueryVo(MyRentQueryVo queryVo);

    List<MatchResultVo> selectOneProjectByQueryVo(MyRentQueryVo queryVo);

    /**
     * 根據houseid查詢房源列表
     *
     * @param houseIds
     * @return
     * @author wff
     */
    List<MatchResultVo> findHousesByHouseIds(List<String> houseIds);

}
