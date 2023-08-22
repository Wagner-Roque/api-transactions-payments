package com.wagner.payment.apipayment.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
