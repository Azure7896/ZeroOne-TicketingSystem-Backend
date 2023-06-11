package com.zeroone.repository;

import com.zeroone.model.Ticket;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select distinct t from Ticket t join fetch t.user order by t.createdDate desc")
    List<Ticket> findAllTickets();

    Ticket getById(Long id);
    Ticket findFirstByOrderByIdDesc();

//    List<Ticket> findByCreatedDateBetween(Date today, Date sevenDaysBefore);
    Long countAllByCreatedDate(Date date);

}
