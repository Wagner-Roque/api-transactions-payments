package com.wagner.payment.apipayment.service;

import com.wagner.payment.apipayment.dtos.UserDTO;
import com.wagner.payment.apipayment.enumerator.UserType;
import com.wagner.payment.apipayment.repositories.UserRepository;
import com.wagner.payment.apipayment.domain.user.UserEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateTransacion(UserEntity sender, BigDecimal amount) throws Exception {
// se usuário for do tipo logista
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário não está autorizado a realizar transação!");
        }
        //se na comparação o valor do amount for zero
        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuário não tem saldo suficiente para a transação solicitada!");
        }
    }

    public UserEntity findUserEntityById(Long id) throws Exception {
       return this.userRepository.findUserEntityById(id).orElseThrow(() ->
               new Exception("Usuário não encontrado"));
    }

    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity createUser(UserDTO data) {
        UserEntity newUser = new UserEntity(data);
        this.saveUser(newUser);
        return  newUser;
    }

    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }
}
