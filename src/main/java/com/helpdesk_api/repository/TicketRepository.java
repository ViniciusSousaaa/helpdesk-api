package com.helpdesk_api.repository;

import com.helpdesk_api.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}