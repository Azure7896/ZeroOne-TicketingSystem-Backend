package com.zeroone.datatransferobjects.POST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketReplyPost {

    private String userEmail;
    
    private String replyBody;

}
