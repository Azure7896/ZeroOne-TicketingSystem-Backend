package com.zeroone.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

    private final long TWENTY_FOUR_HOURS_IN_MILLIS = TimeUnit.HOURS.toMillis(24);

    private long calculateTimeBetweenDates(Date date) {
        Date currentDate = new Date();
        
        return currentDate.getTime() - date.getTime();
        
    }

    private long calculateTimeRemaining(Date date) {
        long timeInMillisBetweenDates = this.calculateTimeBetweenDates(date);
        System.out.println("Second method" + ( this.TWENTY_FOUR_HOURS_IN_MILLIS - timeInMillisBetweenDates));
        return this.TWENTY_FOUR_HOURS_IN_MILLIS - timeInMillisBetweenDates;
    }

    public String createTimeRemaining(Date date) {
        long timeRemaining = this.calculateTimeRemaining(date);
        return (new SimpleDateFormat("HH:mm")).format(new Date(timeRemaining));
    }
}
