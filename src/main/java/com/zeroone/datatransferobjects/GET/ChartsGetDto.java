package com.zeroone.datatransferobjects.GET;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChartsGetDto {

    private List<Integer> ticketsCountByMonth;

    private List<Integer> ticketsCountByStatus;

}
