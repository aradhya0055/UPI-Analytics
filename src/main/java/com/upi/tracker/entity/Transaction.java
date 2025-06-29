package com.upi.tracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderUpiId;
    private String receiverUpiId;
    private Double amount;
    private String country;
    private String currency;
    private Double exchangeRate;
    private String purpose;
    private LocalDateTime timestamp;
}
