package com.example.restaurant.restaurant.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private long id;

      private List<OrderItem> items;
      private  double totalAmount ;

      @Enumerated(EnumType.STRING)
      private Status status;

      private String customerName;
      private String customerPhone;
      private LocalDateTime createdAt;
}
