package com.zeroone.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

    private final long TWENTY_FOUR_HOURS_IN_MILLIS = TimeUnit.HOURS.toMillis(24);

    private int calculateTimeBetweenDatesInSeconds(Date date) {
        Timestamp createdDate = new Timestamp(date.getTime());
        Timestamp currentDate = new Timestamp(new Date().getTime());
        return (int)(currentDate.getTime() - createdDate.getTime())/1000;
    }

    private int calculateTimeRemainingInSeconds(Date date) {
        int timeInMillisBetweenDates = this.calculateTimeBetweenDatesInSeconds(date);
        return (int) this.TWENTY_FOUR_HOURS_IN_MILLIS/1000 - timeInMillisBetweenDates;
    }

    public String createTimeRemaining(Date date) {
        int timeRemaining = this.calculateTimeRemainingInSeconds(date);

        if (this.isTimeExceeded(timeRemaining)) {
            return "Pick-up time exceeded";
        } else {
            int hours = timeRemaining / 3600;
            int minutes = (timeRemaining % 3600) / 60;
            StringBuilder createdTimeRemaining = new StringBuilder();
            createdTimeRemaining.append(hours).append("H ").append(minutes).append("M");
            return createdTimeRemaining.toString();
        }
    }

    public boolean isTimeExceeded(long timeInMillis) {
        return timeInMillis < 0;
    }
}
