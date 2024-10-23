package com.example.restaurant.restaurant.Repo;

import com.example.restaurant.restaurant.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<MenuItem,Long> {
}
