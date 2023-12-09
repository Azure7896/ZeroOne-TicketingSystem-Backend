package com.zeroone.datatransferobjects;

import com.zeroone.model.Role;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoGET {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Date createdDate;
    private boolean isDisabled;
}
