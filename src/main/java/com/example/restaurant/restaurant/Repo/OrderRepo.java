package com.example.restaurant.restaurant.Repo;

import com.example.restaurant.restaurant.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
