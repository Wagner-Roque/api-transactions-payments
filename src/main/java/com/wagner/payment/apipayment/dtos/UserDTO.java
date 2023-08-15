package com.wagner.payment.apipayment.dtos;

import com.wagner.payment.apipayment.enumerator.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance,
                      String email, String password, UserType userType) {
}
