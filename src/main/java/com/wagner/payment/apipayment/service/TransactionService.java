package com.wagner.payment.apipayment.service;

import com.wagner.payment.apipayment.domain.transactions.Transaction;
import com.wagner.payment.apipayment.dtos.TransactionDTO;
import com.wagner.payment.apipayment.repositories.TransactionRepository;
import com.wagner.payment.apipayment.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionService {
    private UserService userService;
    private TransactionRepository transactionRepository;
    private NotificationService notificationService;

    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        UserEntity sender = this.userService.findUserEntityById(transactionDTO.senderId());
        UserEntity receiver = this.userService.findUserEntityById(transactionDTO.receiverId());

        userService.validateTransacion(sender, transactionDTO.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());
        if (!isAuthorized){
            throw new Exception("Transação não autorizada!");
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.senderNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.senderNotification(receiver, "Transação recebida com sucesso!");
        return transaction;
    }

    public boolean authorizeTransaction(UserEntity sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("http://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                Map.class);
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}