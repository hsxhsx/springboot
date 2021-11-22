package com.test.dbhappy.service.impl;

import com.test.dbhappy.entity.HolidayDTO;
import com.test.dbhappy.service.AsyncTask;
import com.test.dbhappy.service.IHolidayService;
import com.test.dbhappy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Future;

@Service
public class AsyncTaskImpl implements AsyncTask {

    @Autowired
    private IHolidayService holidayService;

    @Async("taskExecutor")
    @Override
    public void tesTask(int i) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-----"+i);
        Thread.sleep(50);
    }

    @Async("taskExecutor")
    @Override
    public Future<String> stringTask(String str) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+str);
        Thread.sleep(50);
        return new AsyncResult<String>(String.valueOf(str));
    }

    @Override
    public Future<Number> getWorkDay(Date startTime, Date endTime, int limitTime) {
        long lastDay = DateUtils.getDatePoor(endTime, startTime);
        Date holidayStartTime = lastDay >= 0L ? startTime : endTime;
        Date holidayEndTime = lastDay >= 0L ? endTime : startTime;

        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setStartTime(holidayStartTime);
        holidayDTO.setEndTime(holidayEndTime);


        Integer holidaySum = holidayService.selectHoliday(holidayDTO);//查询当前日期到时限日期之间的节假日（补班的为-1，避免在周六周日里面再次扣除）
        holidaySum = Objects.isNull(holidaySum) ? 0 : holidaySum;


        int dutyDay = DateUtils.getDutyDays(holidayStartTime, holidayEndTime); //周六周日
        //受理时间-now > 0 ？+时限 - lastDay - 节假日 - 周末 ： +时限+lastDay+节假日（含补班逻辑）+ 周末
        long workDay = lastDay >= 0 ? limitTime - lastDay - holidaySum - dutyDay : limitTime + lastDay + holidaySum + dutyDay;
        return  new AsyncResult<Number>(workDay);
    }

}
