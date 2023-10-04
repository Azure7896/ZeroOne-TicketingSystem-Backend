package com.zeroone.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;


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
    @Column(nullable = false, length = 6, unique = true)
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

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private TicketBody ticketBody;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "ticket")
    private List<TicketReply> ticketReplies;

}
