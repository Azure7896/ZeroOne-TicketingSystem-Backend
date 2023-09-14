package com.zeroone.datatransferobjects.GET;

import com.zeroone.model.TicketBody;
import com.zeroone.model.User;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TicketAllDataGetDto {
    private String ticketNumber;

    private String name;

    private String ticketStatus;

    private User user;

    private Date createdDate;

    private User attendant;

    private String timeToEnd;

    private TicketBody ticketBody;
}
