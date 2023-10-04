package com.zeroone.datatransferobjects.GET;

import com.zeroone.model.TicketBody;
import com.zeroone.model.TicketReply;
import com.zeroone.model.User;
import lombok.*;

import java.util.Date;
import java.util.List;


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

    private String ticketTimeRemaining;

    private TicketBody ticketBody;

    private List<TicketReply> ticketReplies;
}
