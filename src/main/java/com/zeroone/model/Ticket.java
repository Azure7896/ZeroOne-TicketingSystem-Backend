package com.zeroone.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 6)
    private String ticketNumber;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    public Ticket(String ticketNumber, String name, User user, Date createdDate) {
        this.ticketNumber = ticketNumber;
        this.name = name;
        this.user = user;
        this.createdDate = createdDate;
    }
}
