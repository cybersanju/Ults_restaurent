package com.example.restaurant.restaurant.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.event.ComponentAdapter;

@Entity
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long  id;
  private String name;
  private double price;
  private int availableQuantity;
  @Enumerated(EnumType.STRING)
  public Category category;


}
