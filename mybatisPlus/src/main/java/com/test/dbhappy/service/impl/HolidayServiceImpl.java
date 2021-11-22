package com.test.dbhappy.service.impl;

import com.test.dbhappy.entity.HolidayDTO;
import com.test.dbhappy.mapper.HolidayMapper;
import com.test.dbhappy.service.IHolidayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HolidayServiceImpl   implements IHolidayService {

    @Resource
    private HolidayMapper holidayMapper;

    @Override
    public Integer selectHoliday(HolidayDTO holiday) {
        return holidayMapper.selectHoliday(holiday);
    }

}
