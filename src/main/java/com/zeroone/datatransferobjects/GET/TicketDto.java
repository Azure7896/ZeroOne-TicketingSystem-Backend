package com.zeroone.datatransferobjects.GET;


import com.zeroone.model.Category;
import com.zeroone.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class TicketDto {

    private String ticketNumber;

    private String name;

    private String ticketStatus;

    private User user;

    private Date createdDate;

    private User attendant;

    private String ticketTimeRemaining;

    private Category category;
}
