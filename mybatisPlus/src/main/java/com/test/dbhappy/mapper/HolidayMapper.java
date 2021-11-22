package com.test.dbhappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.dbhappy.entity.Holiday;
import com.test.dbhappy.entity.HolidayDTO;

public interface HolidayMapper extends BaseMapper<Holiday> {

    /**
     * 自定义分页
     *
     * @param holiday
     * @return
     */
    Integer selectHoliday(HolidayDTO holiday);

}
