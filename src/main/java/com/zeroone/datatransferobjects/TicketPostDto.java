package com.zeroone.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketPostDto {

    private String name;

//    private User user;

    private String ticketBody;

}
