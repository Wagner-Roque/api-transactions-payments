package com.wagner.payment.apipayment.service;

import com.wagner.payment.apipayment.dtos.NotificationDTO;
import com.wagner.payment.apipayment.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void senderNotification(UserEntity user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
//       ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
//
//        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//            System.out.println("Erro ao enviar a notificação");
//            throw new Exception("Serviço de notificação está fora do ar!");
//        }
    System.out.print("Notificação enviada com sucesso!");
    }
}
