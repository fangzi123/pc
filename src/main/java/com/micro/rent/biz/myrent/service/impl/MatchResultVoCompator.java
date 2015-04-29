package com.micro.rent.biz.myrent.service.impl;

import com.micro.rent.biz.myrent.vo.MatchResultVo;

import java.util.Comparator;

public class MatchResultVoCompator implements Comparator<MatchResultVo> {

    @Override
    public int compare(MatchResultVo o1, MatchResultVo o2) {
        String du1 = o1.getDuration();
        String du2 = o2.getDuration();


        return transformDb(du1) > transformDb(du2) ? 1 : transformDb(du1) == transformDb(du2) ? 0 : -1;
    }

    private double transformDb(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
