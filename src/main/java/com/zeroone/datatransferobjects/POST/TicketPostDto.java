package com.zeroone.datatransferobjects.POST;

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

    private String userEmail;

    private String ticketBody;

    private String category;

}
