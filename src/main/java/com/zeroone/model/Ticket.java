package com.zeroone.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 6)
    private String ticketNumber;

    @Column(nullable = false, length = 50)
    private String name;

    private String ticketStatus;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private User attendant;

}
