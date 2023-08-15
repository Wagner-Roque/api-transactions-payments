package com.wagner.payment.apipayment.repositories;

import com.wagner.payment.apipayment.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findUserEntityByDocument(String document);

    Optional<UserEntity> findUserEntityById(Long id);
}
