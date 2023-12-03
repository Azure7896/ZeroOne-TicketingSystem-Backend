package com.zeroone.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 64, unique = true)
    private String email;
    @Column(nullable = false, length = 320)
    @Size(min = 7, message = "Min. 7 characters required")
    private String password;
    @Column(nullable = false, length = 50)
    @Size(min = 2, message = "Min. 2 characters required")
    private String firstName;
    @Column(nullable = false, length = 200)
    @Size(min = 2, message = "Min. 2 characters required")
    private String lastName;
    @Column(nullable = false)
    private boolean isActive;

//    @Column(nullable = false, updatable = false)
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

}
