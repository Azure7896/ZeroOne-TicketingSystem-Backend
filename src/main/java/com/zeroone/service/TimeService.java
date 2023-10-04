package com.zeroone.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TimeService {

    private static final long TWENTY_FOUR_HOURS_IN_SECONDS = 24 * 60 * 60;

    public String createTimeRemaining(Date date, String status) {
        LocalDateTime createdDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Duration duration = Duration.between(createdDateTime, currentDateTime);
        long timeRemainingInSeconds = TWENTY_FOUR_HOURS_IN_SECONDS - duration.toSeconds();

        if (status.equals("Closed") || status.equals("Suspended")) {
            return "-";
        } else if (isTimeExceeded(timeRemainingInSeconds)) {
            return "Resolve time exceeded";
        } else {
            long hours = timeRemainingInSeconds / 3600;
            long minutes = (timeRemainingInSeconds % 3600) / 60;
            return hours + "H " + minutes + "M";
        }
    }

    public boolean isTimeExceeded(long timeInSeconds) {
        return timeInSeconds < 0;
    }
}
