package com.zeroone.datatransferobjects;

import com.zeroone.model.TicketBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewTicketDto {

    private String name;

//    private User user;

    private String ticketBody;

}
