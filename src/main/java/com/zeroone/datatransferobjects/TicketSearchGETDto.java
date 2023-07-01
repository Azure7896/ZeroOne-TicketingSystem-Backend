package com.zeroone.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketSearchGETDto {
    private String ticketNumber;
    private String name;

    public TicketSearchGETDto(String ticketNumber, String name) {
        this.ticketNumber = ticketNumber;
        this.name = name;
    }
}
