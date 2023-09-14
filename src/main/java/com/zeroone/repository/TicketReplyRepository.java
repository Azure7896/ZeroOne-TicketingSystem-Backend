package com.zeroone.repository;

import com.zeroone.model.Ticket;
import com.zeroone.model.TicketReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketReplyRepository extends JpaRepository<TicketReply, Long> {

    List<TicketReply> findTicketRepliesByTicketOrderByReplyDate(Ticket ticket);
}
