package com.wagner.payment.apipayment.repositories;

import com.wagner.payment.apipayment.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRoleRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
