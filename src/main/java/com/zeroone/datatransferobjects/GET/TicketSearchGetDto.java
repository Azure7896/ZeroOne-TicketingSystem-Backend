package com.zeroone.datatransferobjects.GET;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketSearchGetDto {
    private String ticketNumber;
    private String name;

    public TicketSearchGetDto(String ticketNumber, String name) {
        this.ticketNumber = ticketNumber;
        this.name = name;
    }
}
