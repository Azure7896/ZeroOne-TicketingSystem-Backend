package com.zeroone.datatransferobjects.GET;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FailureInfoDto {

    private String failureTitle;

    private Date failureStart;

    private Date failureEnd;

    private String additionalInfo;
}
