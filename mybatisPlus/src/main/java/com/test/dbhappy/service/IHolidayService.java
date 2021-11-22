package com.test.dbhappy.service;

import com.test.dbhappy.entity.HolidayDTO;

public interface IHolidayService {

    /**
     * 自定义分页
     *
     * @param holiday
     * @return
     */
    Integer  selectHoliday(HolidayDTO holiday);

}
