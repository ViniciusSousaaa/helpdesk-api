package com.helpdesk_api.controller;

import com.helpdesk_api.domain.ticket.Ticket;
import com.helpdesk_api.domain.ticket.TicketRequestDTO;
import com.helpdesk_api.domain.ticket.TicketResponseDTO;
import com.helpdesk_api.domain.ticket.TicketStatus;
import com.helpdesk_api.domain.user.User;
import com.helpdesk_api.repository.TicketRepository;
import com.helpdesk_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO data) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User customer = (User) userRepository.findByEmail(userEmail);

        Ticket newTicket = new Ticket();
        newTicket.setTitle(data.title());
        newTicket.setDescription(data.description());
        newTicket.setStatus(TicketStatus.OPEN);
        newTicket.setCreatedAt(LocalDateTime.now());
        newTicket.setCustomer(customer);

        ticketRepository.save(newTicket);

        TicketResponseDTO responseDTO = new TicketResponseDTO(
                newTicket.getId(),
                newTicket.getTitle(),
                newTicket.getDescription(),
                newTicket.getStatus().toString(),
                newTicket.getCreatedAt(),
                customer.getName()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        List<TicketResponseDTO> ticketList = ticketRepository.findAll().stream()
                .map(ticket -> new TicketResponseDTO(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus().toString(),
                        ticket.getCreatedAt(),
                        ticket.getCustomer().getName()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(ticketList);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<TicketResponseDTO> closeTicket(@PathVariable Long id) {
        var optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ticket ticket = optionalTicket.get();
        ticket.setStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);

        TicketResponseDTO responseDTO = new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus().toString(),
                ticket.getCreatedAt(),
                ticket.getCustomer().getName()
        );

        return ResponseEntity.ok(responseDTO);
    }
}