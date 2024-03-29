package com.zeroone.repository;

import com.zeroone.model.Ticket;
import com.zeroone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT COALESCE(COUNT(t), 0) " +
            "FROM Ticket t " +
            "WHERE YEAR(t.createdDate) = :year AND MONTH(t.createdDate) = :month")
    Integer getTicketCountByMonth(@Param("year") int year, @Param("month") int month);


    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.ticketStatus = 'New' " +
            "UNION ALL " +
            "SELECT COUNT(t) FROM Ticket t WHERE t.ticketStatus = 'In progress' " +
            "UNION ALL " +
            "SELECT COUNT(t) FROM Ticket t WHERE t.ticketStatus = 'Closed' " +
            "UNION ALL " +
            "SELECT COUNT(t) FROM Ticket t WHERE t.ticketStatus = 'Suspended'")
    List<Integer> countTicketsByStatus();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.createdDate BETWEEN :startDate AND :endDate")
    Long countTicketsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select distinct t from Ticket t join fetch t.user order by t.createdDate desc")
    List<Ticket> findAllTickets();

    @Query("select distinct t from Ticket t join fetch t.user order by t.createdDate")
    List<Ticket> findAllTicketsByOldest();

    List<Ticket> findByAttendant(User user);

    List<Ticket> findByUser(User user);

    Ticket findByTicketNumberContainingIgnoreCase(String ticketNumber);

    Ticket findFirstByOrderByIdDesc();

    List<Ticket> findByNameContainingIgnoreCaseOrderByTicketStatus(String title);

    List<Ticket> findByTicketStatusAndUser(String status, User user);

}
