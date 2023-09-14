package com.zeroone.datatransferobjects.GET;

import com.zeroone.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class TicketReplyDto {
    private String ticketReply;
    private User user;
    private Date replyDate;

}
