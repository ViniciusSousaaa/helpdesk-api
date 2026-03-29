package com.helpdesk_api.domain.ticket;

import java.time.LocalDateTime;

public record TicketResponseDTO(Long id, String title, String description, String status, LocalDateTime createdAt, String customerName) {
}