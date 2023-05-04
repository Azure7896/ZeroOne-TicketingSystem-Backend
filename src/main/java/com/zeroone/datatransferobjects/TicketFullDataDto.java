package com.zeroone.datatransferobjects;

import com.zeroone.model.TicketBody;
import com.zeroone.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketFullDataDto {
    private String ticketNumber;

    private String name;

    private String ticketStatus;

    private User user;

    private Date createdDate;

    private User attendant;

    private String timeToEnd;

    private TicketBody ticketBody;
}
