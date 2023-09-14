package com.zeroone.repository;

import com.zeroone.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select distinct t from Ticket t join fetch t.user order by t.createdDate desc")
    List<Ticket> findAllTickets();

    @Query("select distinct t from Ticket t join fetch t.user order by t.createdDate")
    List<Ticket> findAllTicketsByOldest();

    Ticket findByTicketNumberContainingIgnoreCase(String ticketNumber);

    Ticket findFirstByOrderByIdDesc();

    List<Ticket> findByNameContainingIgnoreCaseOrderByTicketStatus(String title);

    List<Ticket> findByTicketStatus(String status);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.createdDate BETWEEN :startDate AND :endDate")
    Long countTicketsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
