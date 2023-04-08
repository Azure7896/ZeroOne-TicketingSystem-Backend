package com.zeroone.repository;

import com.zeroone.model.Ticket;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAll();

    Ticket getById(Long id);

    Ticket findTopByOrderByIdDesc();
}
