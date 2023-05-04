package com.zeroone.repository;


import com.zeroone.model.TicketBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBodyRepository extends JpaRepository <TicketBody, Long> {
}
