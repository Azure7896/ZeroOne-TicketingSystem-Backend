package com.zeroone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "failures_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FailureInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String failureTitle;

    @Temporal(TemporalType.TIMESTAMP)
    private Date failureStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date failureEnd;

    @Column(nullable = false, length = 200)
    private String additionalInfo;
}
